package com.yt.activity;

import com.yt.thread.PostThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

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
	
	public void startThread(Class<? extends PostThread<?>> cls,String path,Object data,int requestCode,int id){
		try {
			PostThread<?> t=cls.newInstance();
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
	
	public<T> void startThread(String path,Object data,int requestCode,int id){
			new PostThread<T>(path,data,createHandler(),requestCode,id).start();
	}
	
	protected Handler createHandler(){
		return null;
	}
}
