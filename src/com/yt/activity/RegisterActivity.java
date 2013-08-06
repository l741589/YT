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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {

	private EditText et_id;
	private EditText et_pw;
	private EditText et_pw_comfirm;
	private CheckBox cb_readagreement;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_register);
	    et_id=(EditText)findViewById(R.id.et_id);
	    et_pw=(EditText)findViewById(R.id.et_pw);
	    et_pw_comfirm=(EditText)findViewById(R.id.et_pw_comfirm);
	    cb_readagreement=(CheckBox)findViewById(R.id.cb_readagreement);
	    findViewById(R.id.bn_register).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!et_pw.getText().toString().equals(et_pw_comfirm.getText().toString())){
					Toast.makeText(RegisterActivity.this, "两次输入的密码不相同", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!cb_readagreement.isChecked()){
					Toast.makeText(RegisterActivity.this, "请阅读《用户协议》", Toast.LENGTH_SHORT).show();
					return;
				}
				Account bean=Account.newBuilder()
						.setUid(et_id.getText().toString())
						.setPassword(et_pw.getText().toString())
						.build();
				startThread("RegisterAction", bean,String.class, 0, 0);				
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
					Util.Toast("注册成功", Toast.LENGTH_SHORT);
				}else{
					Util.Toast("注册失败", Toast.LENGTH_SHORT);
				}
			}
		};
	}

}
