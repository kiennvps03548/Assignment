package com.example.dondh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ThongTinKH extends Activity {
	String arr[] = { "HÃ  Ná»™i","Nghá»‡ An","Ä�Ã  Náºµng","PhÃº YÃªn","Há»“ ChÃ­ Minh", "Cáº§n ThÆ¡","VÄ©nh Long" };
	Button btn1,btn2;
	EditText edt1,edt2,edt3,edt4;
	TextView txt1,txt2,txt3,txt4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.login_ps03528.R.layout.activity_thong_tin_kh);
		btn1 = (Button) findViewById(com.example.login_ps03528.R.id.btnXN);
		btn2 = (Button) findViewById(com.example.login_ps03528.R.id.btnEx);
		edt1 = (EditText) findViewById(com.example.login_ps03528.R.id.editText1);
		edt2 = (EditText) findViewById(com.example.login_ps03528.R.id.editText2);
		edt3 = (EditText) findViewById(com.example.login_ps03528.R.id.editText3);
		edt4 = (EditText) findViewById(com.example.login_ps03528.R.id.editText4);
		txt1 = (TextView) findViewById(com.example.login_ps03528.R.id.txt1);
		txt2 = (TextView) findViewById(com.example.login_ps03528.R.id.txt2);
		txt3 = (TextView) findViewById(com.example.login_ps03528.R.id.txt3);
		txt4 = (TextView) findViewById(com.example.login_ps03528.R.id.txt4);
		
		Spinner spin = (Spinner) findViewById(com.example.login_ps03528.R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arr);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		spin.setAdapter(adapter);
				
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), ThongTinKH.class);
				if(edt1.getText().toString().equals("")){
					txt1.setVisibility(View.VISIBLE);
					txt1.setTextColor(Color.RED);
					txt1.setText("Nháº­p tÃªn!");
					
				}
				else if(edt1.getText().toString() != null ){
					txt1.setVisibility(View.INVISIBLE);
					
				}
				if(edt2.getText().toString().equals("")){
					txt2.setVisibility(View.VISIBLE);
					txt2.setTextColor(Color.RED);
					txt2.setText("Nháº­p Email!");
				}
				else if(edt2.getText().toString() != null ){
					txt2.setVisibility(View.INVISIBLE);
					
				}
				if(edt3.getText().toString().equals("")){
					txt3.setVisibility(View.VISIBLE);
					txt3.setTextColor(Color.RED);
					txt3.setText("Nháº­p Ä�á»‹a chá»‰ !");
				}
				else if(edt3.getText().toString() != null ){
					txt3.setVisibility(View.INVISIBLE);
				}
				if(edt4.getText().toString().equals("")){
					txt4.setVisibility(View.VISIBLE);
					txt4.setTextColor(Color.RED);
					txt4.setText("Nháº­p SÄ�T!");
				}
				else if (edt4.getText().toString() != null ){
					txt4.setVisibility(View.INVISIBLE);
					Toast.makeText(getApplicationContext(), "Ä�Æ¡n hÃ ng cá»§a báº£n Ä‘Ã£ Ä‘Æ°á»£c xÃ¡c nháº­n. Sáº£n pháº©m sáº½ Ä‘Æ°á»£c chuyá»ƒn tá»›i trong vÃ²ng 24h.", Toast.LENGTH_LONG).show();;
					startActivity(i);
					
				}
				
			
				
			}
			
			
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.example.login_ps03528.R.menu.thong_tin_kh, menu);
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
