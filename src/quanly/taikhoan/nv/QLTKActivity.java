package quanly.taikhoan.nv;

import java.text.Format;
import java.text.SimpleDateFormat;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login_ps03528.R;
import com.example.login_ps03528.Tabnhanvien;
import com.example.login_ps03528.R.drawable;
import com.example.login_ps03528.R.id;
import com.example.login_ps03528.R.layout;
import com.example.login_ps03528.R.menu;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class QLTKActivity extends Activity {
	TextView txtName, txtID, txtUsername, txtDate, txtGender, txtPhone, txtEmail;
	Button btn;
	Bitmap img;
	ImageView avatar;
	String obid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qltk);
		txtName = (TextView)findViewById(R.id.text_nameNV);
		txtID= (TextView)findViewById(R.id.text_idNV);
		txtUsername = (TextView)findViewById(R.id.txtUser);
		txtDate = (TextView)findViewById(R.id.txtDate);
		txtGender= (TextView)findViewById(R.id.txtGender);
		txtPhone = (TextView)findViewById(R.id.txtPhone);
		txtEmail = (TextView)findViewById(R.id.txtMail);
		btn = (Button)findViewById(R.id.btn_Repass);
		avatar = (ImageView)findViewById(R.id.image_NV);
		setData();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QLTKActivity.this, DoiMKActivity.class);
				i.putExtra("obid", obid);
				startActivity(i);
				// chuyển Activity và bắn id qua Actuvity Đổi mật khẩu
			}
		});
		
	}
	
	public void setData(){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("NhanVien");// lấy toàn bộ bảng nhân viên
		obid = getIntent().getStringExtra("obid");
		
				query.getInBackground(obid, new GetCallback<ParseObject>() {// lấy nhân viên theo id
					
					@Override
					public void done(ParseObject nv, ParseException arg1) {// set dữ liệu về TextView
						// TODO Auto-generated method stub
						if(arg1==null){
							txtName.setText(nv.getString("TenNV"));
							txtID.setText(nv.getString("MaNV"));
							txtGender.setText(nv.getString("GioiTinhNV"));
							Format formatter = new SimpleDateFormat("yyyy-MM-dd");
							String s = formatter.format(nv.getCreatedAt());
							txtDate.setText(String.valueOf(nv.getDate("NgaysinhNV")));
							txtUsername.setText(nv.getString("UserName"));
							txtEmail.setText(nv.getString("EmailNV"));
							txtPhone.setText(String.valueOf(nv.getInt("SdtNV")));
							
							if(nv.has("Hinhanh")){//nếu có cột hình ảnh thì lấy về
								ParseFile fileObject = (ParseFile)nv.get("Hinhanh");
								fileObject.getDataInBackground(new GetDataCallback() {
								  public void done(byte[] data, ParseException e) {
								    if (e == null) {//nếu ko có lỗi thì set hình lên ImageView
								      // only a test
								    		Bitmap tmp = BitmapFactory.decodeByteArray(data, 0,
					                                data.length);
								    		avatar.setImageBitmap(tmp);
								    } else {//nếu không có lỗi thì set hình mặc định
								      // something went wrong
								    	Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),R.drawable.icnhanvien1);
										img = tempBMP;
								    }
								  }
								});
							}else{// không có cột hình ảnh thì lấy hình mặc định
								Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),R.drawable.icnhanvien1);
								img = tempBMP;
							}
							avatar.setImageBitmap(img);
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
			Intent i = new Intent(this, Tabnhanvien.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
