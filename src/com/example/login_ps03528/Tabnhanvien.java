package com.example.login_ps03528;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import dssp.pkg.QuanLy_TabActivity;

public class Tabnhanvien extends Activity {
	EditText username, password;
	Button login, dk;
	List<ParseObject> ob;
	List<ParseObject> ob2;
	ParseObject nv;
	ParseObject kh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabnhanvien);
		dk = (Button)findViewById(R.id.btnDK);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
dk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), DangkyKH.class);
				startActivity(i);
			}
		});
		
login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String us = username.getText().toString();
				String pw = password.getText().toString();
				if((us.equals(""))||(pw.equals(""))){
					Toast.makeText(getApplicationContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
				}else{
					ParseQuery<ParseObject> query = ParseQuery.getQuery("NhanVien");
					ParseQuery<ParseObject> query2 = ParseQuery.getQuery("KhachHang");
					try {
						ob = query.find();
						boolean kt=false;
						boolean kt2=false;
						for(int i=0;i<ob.size();i++){
							String username = ob.get(i).getString("UserName");
							String password = ob.get(i).getString("Password");
							if((us.equals(username))&&(pw.equals(password))){
								kt=true;
								nv = ob.get(i);
								break;
							}
						}
						ob2 = query2.find();
						for(int i=0;i<ob2.size();i++){
							String username = ob2.get(i).getString("UserKH");
							String password = ob2.get(i).getString("PassKH");
							if((us.equals(username))&&(pw.equals(password))){
								kt2=true;
								kh = ob2.get(i);
								break;
							}
						}
						if(kt==true){
							Intent i = new Intent(Tabnhanvien.this, QuanLy_TabActivity.class);
							i.putExtra("obid", nv.getObjectId());
							startActivity(i);
						}else if(kt2==true){
							Intent i = new Intent(Tabnhanvien.this, Trangchu.class);
							i.putExtra("obid", kh.getObjectId());
							startActivity(i);
						}else{
							Toast.makeText(Tabnhanvien.this, "Sai tài khoản hoặc mật khẩu",
									Toast.LENGTH_LONG).show();
						}
					} catch (ParseException e) {
						Log.e("Error", e.getMessage());
						e.printStackTrace();
					} catch (com.parse.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
					
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

