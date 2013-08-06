package com.yt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.Toast;

import com.yt.thread.PostThread;

public class BaseActivity extends Activity{
	
	private static BaseActivity frontActivity;

	public static BaseActivity getFrontActivity() {
		return frontActivity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		frontActivity=this;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		frontActivity=this;
	}
	
	
	@Override
	protected void onStart() {
		frontActivity=this;
		super.onStart();
	}
	
	public<REQ_TYPE,RES_TYPE> void startThread(Class<? extends PostThread<REQ_TYPE,RES_TYPE>> cls,
			String path,REQ_TYPE data,int requestCode,int id){
		try {
			PostThread<REQ_TYPE,RES_TYPE> t=cls.newInstance();
			if (path!=null) t.setPath(path);
			if (data!=null) t.setData(data);
			if (requestCode!=0) t.setRequestCode(requestCode);
			if (id!=0) t.setId(id);
			t.setHandler(createHandler());
			t.start();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public<REQ_TYPE,RES_TYPE> 
	void startThread(String path,REQ_TYPE data,Class<RES_TYPE> resType,int requestCode,int id){
			new PostThread<REQ_TYPE,RES_TYPE>((Class<REQ_TYPE>)data.getClass(),resType,path,data,createHandler(),requestCode,id).start();
	}
	
	protected Handler createHandler(){
		return null;
	}
	
	public Handler toastHandler=new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			Toast.makeText(BaseActivity.this,(String)msg.obj,msg.arg1).show();
			return true;
		}
	});
}
