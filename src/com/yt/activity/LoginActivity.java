package com.yt.activity;

import com.yt.R;
import com.yt.protocol.Yueting.Account;
import com.yt.protocol.Yueting.ResultCode;
import com.yt.util.G;
import com.yt.util.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	
	private EditText et_id;
	private EditText et_pw;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    et_id=(EditText)findViewById(R.id.et_id);
	    et_pw=(EditText)findViewById(R.id.et_pw);
	    findViewById(R.id.bn_login).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Account bean=Account.newBuilder()
						.setUid(et_id.getText().toString())
						.setPassword(et_pw.getText().toString())
						.build();
				startThread("LoginAction", bean,String.class, 0, 0);
			}
		});
	    findViewById(R.id.bn_register).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			}	    
	    });
	}
	
	@SuppressLint("HandlerLeak")
	@Override
	protected Handler createHandler() {
		return new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (msg.what==ResultCode.RC_SUCCESS.ordinal()){
					G.sessionid=msg.obj.toString();
					Util.Toast("µÇÂ¼³É¹¦", Toast.LENGTH_SHORT);
				}else{
					Util.Toast("µÇÂ¼Ê§°Ü", Toast.LENGTH_SHORT);
				}
			}
		};
	}

}
