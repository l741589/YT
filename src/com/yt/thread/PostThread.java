package com.yt.thread;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.yt.bean.ResultBean;
import com.yt.bean.enums.ResultCode;
import com.yt.net.HttpHelper;
import com.yt.util.G;
import com.yt.util.Util;


public class PostThread<T> extends Thread{
	public enum Type{STRING,SEND_OBJECT,RECV_OBJECT}
	public Type type(){return Type.STRING;}
	private String path;
	private Object data;
	private int requestCode;
	private Handler handler;
	private int id;
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setData(Object data) {
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

	public PostThread(){
		this.path=null;
		this.data=null;
		this.requestCode=0;
		this.id=0;
		this.handler=null;
	}
	
	public PostThread(String path, Object data, Handler handler){
		super();
		this.path=path;
		this.data=data;
		this.requestCode=0;
		this.id=0;
		this.handler=handler;
	}
	
	public PostThread(String path, Object data, Handler handler,int requestCode){
		this(path,data,handler);
		this.requestCode=requestCode;
	}
	
	public PostThread(String path, Object data, Handler handler,int requestCode,int id){
		this(path,data,handler,requestCode);
		this.id=id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		init();
		ResultBean<T> result = null;
		switch(type()){
		case STRING:{
			Map<String,String> map=new HashMap<String,String>();
			map.put("data", Util.toJson(data));
			if (G.sessionid!=null) map.put("session", G.sessionid);
			String json = HttpHelper.doPost(path, map);
			if (json==null) {OnDisconnected();return;}
			result=Util.fromJson(json, new ResultBean<T>().getClass());			
		}break;
		case SEND_OBJECT:{
			String json=HttpHelper.doPostDataSend(path, (byte[])data, id);
			if (json==null) {OnDisconnected();return;}
			result=Util.fromJson(json, new ResultBean<T>().getClass());
		}break;
		case RECV_OBJECT:{
			Map<String,String> map=new HashMap<String,String>();
			map.put("data", Util.toJson(data));
			if (G.sessionid!=null) map.put("session", G.sessionid);
			byte[] bs = HttpHelper.doPostDataRecv(path, map);
			if (bs==null) {OnDisconnected();return;}
			result=new ResultBean<T>();
			result.setData((T)bs);
			result.setResultCode(ResultCode.SUCCESS);
		}
		}
		if (result==null) return;
		Message msg=Message.obtain();
		msg.what=result.getResultCode().ordinal();
		msg.arg1=requestCode;
		msg.arg2=id;
		msg.obj=result.getData();
		if (msg.what==0) OnSuccess(msg); else OnFail(msg);
		if (handler!=null) handler.sendMessage(msg);
	}
	
	protected void init(){}
	protected void OnSuccess(Message msg){};
	protected void OnFail(Message msg){}
	protected void OnDisconnected(){}
}
