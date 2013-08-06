package com.yt.thread;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.google.protobuf.GeneratedMessageLite;
import com.yt.activity.BaseActivity;
import com.yt.bean.ResultBean;
import com.yt.bean.enums.ResultCode;
import com.yt.net.HttpHelper;
import com.yt.protocol.Protocol;
import com.yt.protocol.Protocol.IntResult;
import com.yt.util.G;
import com.yt.util.Util;


public class PostThread<REQ_DATA_TYPE,RES_DATA_TYPE> extends Thread{
	private String path;
	private REQ_DATA_TYPE data;
	private int requestCode;
	private Handler handler;
	private int id;
	private final String PROTOCOL_PACKAGE = Protocol.class.getName()+"$";
	private Class<REQ_DATA_TYPE> reqDataType;
	private Class<RES_DATA_TYPE> resDataType;
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setData(REQ_DATA_TYPE data) {
		this.data = data;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setResDataType(Class<RES_DATA_TYPE> resDataType) {
		this.resDataType = resDataType;
	}

	public void setReqDataType(Class<REQ_DATA_TYPE> reqDataType) {
		this.reqDataType = reqDataType;
	}


	public PostThread(Class<REQ_DATA_TYPE> reqDataType,Class<RES_DATA_TYPE> resDataType){
		super();
		this.path=null;
		this.data=null;
		this.requestCode=0;
		this.id=0;
		this.handler=null;
		this.reqDataType=reqDataType;
		this.resDataType=resDataType;
	}

	public PostThread(Class<REQ_DATA_TYPE> reqDataType,Class<RES_DATA_TYPE> resDataType,
			String path, REQ_DATA_TYPE data, Handler handler,int requestCode,int id){
		this(reqDataType,resDataType);
		this.path=path;
		this.data=data;
		this.handler=handler;
		this.requestCode=requestCode;
		this.id=id;
	}
	
	@Override
	public void run() {
		try{
			init();
			Class<? extends GeneratedMessageLite> reqType=GetRequestType();
			Class<? extends GeneratedMessageLite> resType=GetResponseType();
			Object reqBuilder=reqType.getMethod("newBuilder").invoke(null);
			Class<?> reqBuilderType=reqBuilder.getClass();
			reqBuilder=reqBuilderType.getMethod("setData", reqDataType).invoke(reqBuilder, data);
			if (G.sessionid!=null) reqBuilder=reqBuilderType.getMethod("setSession", String.class).invoke(reqBuilder, G.sessionid);
			GeneratedMessageLite req=(GeneratedMessageLite)reqBuilderType.getMethod("build").invoke(reqBuilder);
			
			InputStream ret=HttpHelper.doPostData(path, req.toByteArray());
			
			if (ret==null) {OnDisconnected();return;}
			Object result=resType.getMethod("parseFrom", InputStream.class).invoke(null, ret);			
			Message msg=Message.obtain();
			msg.what=((Enum<?>)result.getClass().getMethod("getResultCode").invoke(result)).ordinal();			
			msg.arg1=requestCode;
			msg.arg2=id;
			msg.obj=result.getClass().getMethod("getData").invoke(result);
			if (msg.what==0) OnSuccess(msg); else OnFail(msg);
			if (handler!=null) handler.sendMessage(msg);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void init(){}
	protected void OnSuccess(Message msg){};
	protected void OnFail(Message msg){}
	
	protected void OnDisconnected(){
		Util.Toast("无法连接到服务器", Toast.LENGTH_SHORT);
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends GeneratedMessageLite> GetRequestType() throws ClassNotFoundException{
		if (reqDataType.isAssignableFrom(Integer.class)) 
			return (Class<? extends GeneratedMessageLite>)Class.forName(PROTOCOL_PACKAGE+"IntRequest");
		return (Class<? extends GeneratedMessageLite>)Class.forName(PROTOCOL_PACKAGE+reqDataType.getSimpleName()+"Request");
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends GeneratedMessageLite> GetResponseType() throws ClassNotFoundException{
		if (resDataType.isAssignableFrom(Integer.class)) 
			return (Class<? extends GeneratedMessageLite>)Class.forName(PROTOCOL_PACKAGE+"IntRequest");
		return (Class<? extends GeneratedMessageLite>)Class.forName(PROTOCOL_PACKAGE+resDataType.getSimpleName()+"Result");
	}

}
