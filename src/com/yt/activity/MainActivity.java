package com.yt.activity;

import com.yt.R;
import com.yt.R.layout;
import com.yt.R.menu;
import com.yt.bean.AccountBean;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AccountBean bean=new AccountBean();
				bean.setUid("1234567");
				bean.setPassword("7654321");
				startThread("TestServlet", bean, 0, 0);				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected Handler createHandler() {
		return new Handler(){
			@Override
			public void handleMessage(Message msg) {
				Toast.makeText(getFrontActivity(), (CharSequence)msg.obj, Toast.LENGTH_LONG).show();
			}
		};
	}

}
