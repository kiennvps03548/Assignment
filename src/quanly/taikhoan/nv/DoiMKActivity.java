package quanly.taikhoan.nv;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login_ps03528.R;
import com.example.login_ps03528.Tabnhanvien;
import com.example.login_ps03528.R.id;
import com.example.login_ps03528.R.layout;
import com.example.login_ps03528.R.menu;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class DoiMKActivity extends Activity {
	EditText txt_Pass, txt_Newpass, txt_Repass;
	Button btn1, btn2;
	String obid;
	List<ParseObject> ob;
	ParseObject nv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doi_mk);
		txt_Pass = (EditText)findViewById(R.id.editText1);
		txt_Newpass = (EditText)findViewById(R.id.editText2);
		txt_Repass = (EditText)findViewById(R.id.editText3);
		btn1 = (Button)findViewById(R.id.btn_Doi);
		btn2 = (Button)findViewById(R.id.btn_ResetDoi);
		obid = getIntent().getStringExtra("obid");// lấy id về
		ParseQuery<ParseObject> query = ParseQuery.getQuery("NhanVien");
		try {
			ob = query.find();//lấy hết giữ liệu của 1 dòng trong bảng nhân viên rồi đổ lên List
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.getInBackground(obid, new GetCallback<ParseObject>() {// lấy dữ liệu theo id
			
			@Override
			public void done(ParseObject arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
					nv = arg0;
				}else{
					Toast.makeText(getApplicationContext(), arg1.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
		
	// đổi pass
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String oldpw = txt_Pass.getText().toString();//lấy dữ liệu người dùng nhập vào chuyền vào biến
				String newpw = txt_Newpass.getText().toString();
				String repw = txt_Repass.getText().toString();
				if(oldpw.equals(nv.getString("Password"))){//so sánh dữ liệu vừa nhập với pass cũ
					if(newpw.equals(repw)){// so sánh pass và repass
						nv.put("Password", newpw);
						nv.saveInBackground(new SaveCallback() {
							
							@Override
							public void done(ParseException e) {
								// TODO Auto-generated method stub
								if(e==null){// nếu ko có lỗi gì thì đổi pass
									Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_LONG).show();
								}else{
									Toast.makeText(getApplicationContext(), "Đổi mật khẩu không thành công!", Toast.LENGTH_LONG).show();
								}
							}
						});
					}else{
						Toast.makeText(getApplicationContext(), "Sai mật khẩu nhập lại!", Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "Sai mật khẩu!", Toast.LENGTH_LONG).show();
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
			Intent i = new Intent(this, Tabnhanvien.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
