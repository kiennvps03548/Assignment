package com.example.login_ps03528;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class DangkyKH extends Activity {
	EditText txtUser, txtPass, txtRepass;
	Button btnDK, btnHuy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dang_ky);
		txtUser = (EditText)findViewById(R.id.user);
		txtPass = (EditText)findViewById(R.id.pass);
		txtRepass = (EditText)findViewById(R.id.repass);
		btnDK = (Button)findViewById(R.id.signUp);
		btnHuy = (Button)findViewById(R.id.btnHuydk);
		btnDK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String user = txtUser.getText().toString();
				String pass= txtPass.getText().toString();
				String repass = txtRepass.getText().toString();
				if((user.equals(""))||(pass.equals(""))){
					Toast.makeText(DangkyKH.this, "Không được bỏ trống tên tài khoản và mật khẩu!", Toast.LENGTH_LONG).show();
				}else if(!pass.equals(repass)){
					Toast.makeText(DangkyKH.this, "Nhập lại mật khẩu không khớp!", Toast.LENGTH_LONG).show();
				}else{
					ParseObject testObject = new ParseObject("KhachHang"); //Ten bang
					testObject.put("UserKH", user); // nhap vao ten cot - du lieu
					testObject.put("PassKH", pass);
					
					testObject.saveInBackground(new SaveCallback() {
						
						@Override
						public void done(ParseException arg0) {
							// TODO Auto-generated method stub
							if(arg0==null){
								Toast.makeText(DangkyKH.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
							}else{
								Toast.makeText(DangkyKH.this, "Đăng ký thất bại!", Toast.LENGTH_LONG).show();
							}
						}
					}); //sau khi nhap save lai du lieu len parse
					finish();
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dangky_kh, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	}
