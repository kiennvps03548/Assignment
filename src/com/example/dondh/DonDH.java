package com.example.dondh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DonDH extends Activity {
	Button btnTT, btnEx;
	EditText edt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.login_ps03528.R.layout.activity_dondh);
		btnTT = (Button) findViewById(com.example.login_ps03528.R.id.btnTT);
		btnEx = (Button) findViewById(com.example.login_ps03528.R.id.btnEx);
		edt = (EditText) findViewById(com.example.login_ps03528.R.id.editText1);
		
		btnTT.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DonDH.this, ThongTinKH.class);
				if(edt.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Nhập số lượng", Toast.LENGTH_SHORT).show();				
				}
				else if(edt.getText().toString().equals("0")){
					Toast.makeText(getApplicationContext(), "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
				}
				else{
					startActivity(i);
				}
				
			}
		});
		btnEx.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.example.login_ps03528.R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == com.example.login_ps03528.R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
