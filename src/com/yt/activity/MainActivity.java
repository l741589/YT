package com.yt.activity;

import com.yt.R;
import com.yt.protocol.Yueting.Account;
import com.yt.util.G;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.bn_login).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Account bean=Account.newBuilder()
				.setUid("1234567")
				.setPassword("7654321")
				.build();
				startThread("TestServlet", bean,String.class, 0, 0);				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@SuppressLint("HandlerLeak")
	@Override
	protected Handler createHandler() {
		return new Handler(){
			@Override
			public void handleMessage(Message msg) {
				G.sessionid=msg.obj.toString();
				Toast.makeText(getFrontActivity(), (CharSequence)msg.obj, Toast.LENGTH_LONG).show();
			}
		};
	}

}
