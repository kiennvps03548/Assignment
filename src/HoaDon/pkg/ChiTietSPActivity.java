package HoaDon.pkg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login_ps03528.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class ChiTietSPActivity extends Activity {
	
	EditText edtId, edtTen, edtSoLuong, edtLSP, edtGia;
	ImageView imgSP;
	Button btn_Luu, btn_Huy;
	Spinner spinLoaiSP;
	List<ParseObject> obloaisp;
	ArrayList<String> listsp;
	ArrayList<ItemHD> listLoaisp;
	int vitri;
	String obid;
	String idloaisp_begin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xemsp);

		edtId = (EditText) findViewById(R.id.edtMSP);
		edtTen = (EditText) findViewById(R.id.edtTSP);
		spinLoaiSP = (Spinner)findViewById(R.id.spnLoaiSP);
		edtSoLuong = (EditText) findViewById(R.id.edtSLSP);
		edtGia = (EditText) findViewById(R.id.edtNgay);
		imgSP = (ImageView)findViewById(R.id.imgSP);
		
		//setSpinnerLoaiSP();
		//Lấy dữ liệu từ bundle của DSSP
		Bundle bun = getIntent().getExtras();
		obid = bun.getString("obid");
		String masp = bun.getString("masp");
		idloaisp_begin = bun.getString("maloaisp");
		String tensp = bun.getString("tensp");
		int giasp = bun.getInt("giasp");
		int sl = bun.getInt("sl");
		byte[] img = bun.getByteArray("img");
		Bitmap bmp = BitmapFactory.decodeByteArray(img, 0,img.length);
		//Set dữ liệu
		edtId.setText(masp);
		edtTen.setText(tensp);
		edtGia.setText(String.valueOf(giasp));
		edtSoLuong.setText(String.valueOf(sl));
		imgSP.setImageBitmap(bmp);
		int vitriSPn=0;
		for(int i=0;i<listLoaisp.size();i++){
			if(listLoaisp.get(i).sohd.equals(idloaisp_begin)){
				vitriSPn = i;
				break;
			}
		}
		spinLoaiSP.setSelection(vitriSPn);
		
		btn_Luu = (Button) findViewById(R.id.btnLuu);
		btn_Huy = (Button)findViewById(R.id.btnHuy);
		
		btn_Luu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				ParseQuery<ParseObject> query = ParseQuery.getQuery("SANPHAM");
				query.getInBackground(obid, new GetCallback<ParseObject>() {
					  public void done(ParseObject sv, ParseException e) {
					    if (e == null) {
					    	vitri = spinLoaiSP.getSelectedItemPosition();
							String xbc = listLoaisp.get(vitri).sohd;
					    	sv.put("MaSP", edtId.getText().toString());
							sv.put("MaLoaiSP", ParseObject.createWithoutData("LoaiSP", xbc));
							sv.put("TenSP", edtTen.getText().toString());
							sv.put("GiaSP", Integer.parseInt(edtGia.getText().toString()));
							sv.put("SoLuong", Integer.parseInt(edtSoLuong.getText().toString()));
							sv.saveInBackground(new SaveCallback() {
								   public void done(ParseException e) {
								     if (e == null) {
								    	 Toast.makeText(ChiTietSPActivity.this, "Lưu thành công!", Toast.LENGTH_LONG).show();
								    	 finish();
								     } else {
								    	 Toast.makeText(ChiTietSPActivity.this, "Lưu thất bại! "+ e.toString(), Toast.LENGTH_LONG).show();
								     }
								   }
							 });
					    }else{
					    	Toast.makeText(ChiTietSPActivity.this, "Có lỗi "+e.toString(), Toast.LENGTH_LONG).show();
					    }
					  }
					});
			}
		});
		btn_Huy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
//	public void setSpinnerLoaiSP(){
//		//set spinner loai sp
//				ParseQuery<ParseObject> query;
//				try {
//					query = ParseQuery.getQuery("LoaiSP");
//					obloaisp = query.find();
//					listsp = new ArrayList<String>();
//					listLoaisp = new ArrayList<ItemLoaiSP>();
//					for(int i=0;i<obloaisp.size();i++){
//						ItemLoaiSP m = new ItemLoaiSP();
//						m.maloai = obloaisp.get(i).getObjectId();
//						m.tenloai=obloaisp.get(i).getString("TenLoaiSP");
//						listLoaisp.add(m);
//						listsp.add(obloaisp.get(i).getString("TenLoaiSP"));
//						ArrayAdapter<String> a = new ArrayAdapter<String>(ChiTietSPActivity.this, android.R.layout.simple_spinner_dropdown_item, listsp);
//						spinLoaiSP.setAdapter(a);
//					}
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	}

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
