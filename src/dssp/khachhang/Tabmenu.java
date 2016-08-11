package dssp.khachhang;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_ps03528.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import dssp.pkg.ItemLoaiSP;

public class Tabmenu extends Activity {
	Spinner spinLoaiSP;
	List<ParseObject> obloaisp;
	ArrayList<String> listsp;
	ArrayList<ItemLoaiSP> listLoaisp;
	int vitri;
	LinearLayout ln;
	ArrayList<ItemLvTC> list;
	ListView lv;
	ArrayList<ItemLvTC> listspp;
	ParseObject obj;
	String idloaisp;
	String idloai_Begin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.login_ps03528.R.layout.activity_tabmenu);
		spinLoaiSP = (Spinner)findViewById(com.example.login_ps03528.R.id.spinLoaiSp);
		lv = (ListView)findViewById(com.example.login_ps03528.R.id.lvsp);
		setSpinnerLoaiSP();
		list = new ArrayList<ItemLvTC>();
		idloai_Begin = obloaisp.get(0).getObjectId();
		loadList(idloai_Begin);
		//sua
		spinLoaiSP.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				idloaisp = obloaisp.get(position).getObjectId();
				loadList(idloaisp);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
			
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ItemLvTC itemSelected = listspp.get(position);
				final Dialog dialog = new Dialog(Tabmenu.this);
				dialog.setContentView(com.example.login_ps03528.R.layout.chitietsp);
				dialog.setTitle("Chi tiết sản phẩm");
				TextView tvten = (TextView) dialog.findViewById(R.id.tvTenSp);
				tvten.setText("Tên SP :" + itemSelected.tensp);
				TextView tvgia = (TextView) dialog.findViewById(R.id.tvGiaSp);
				tvgia.setText("Giá :" + itemSelected.giasp + " VNĐ");
				// set the custom dialog components - text, image and button
				ImageView image = (ImageView) dialog.findViewById(R.id.imgsp);
				image.setImageBitmap(itemSelected.hinhanh);
				dialog.show();
				
			}
		});
		
	}
	public void loadList(String id){
		ParseQuery<ParseObject> query;
		query = ParseQuery.getQuery("SANPHAM");
		ParseObject loaisp = ParseObject.createWithoutData("LoaiSP", id);
		query.whereEqualTo("MaLoaiSP", loaisp);
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> list, ParseException arg1) {
				// TODO Auto-generated method stub
				listspp = new ArrayList<ItemLvTC>();
				if(list!=null){
					for(int i=0;i<list.size();i++){
						final ItemLvTC m = new ItemLvTC();
						m.masp = list.get(i).getString("MaSP");
						m.maloaisp = list.get(i).getString("MaLoaiSP");
						m.tensp = list.get(i).getString("TenSP");
						m.giasp = list.get(i).getInt("GiaSP");
						m.soluong = list.get(i).getInt("SoLuong");
						if(list.get(i).has("HinhAnh")){
							ParseFile fileObject = (ParseFile)list.get(i).get("HinhAnh");
							fileObject.getDataInBackground(new GetDataCallback() {
							  public void done(byte[] data, ParseException e) {
							    if (e == null) {
							      // only a test
							    		
							    		m.hinhanh = BitmapFactory.decodeByteArray(data, 0,
				                                data.length);
							    } else {
							      // something went wrong
							    	Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),com.example.login_ps03528.R.drawable.sp);
									m.hinhanh = tempBMP;
							    }
							  }
							});
						}else{
							Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),com.example.login_ps03528.R.drawable.sp);
							m.hinhanh = tempBMP;
						}
						
						listspp.add(m);
					}
						AdapterLvTC adapter = new AdapterLvTC(Tabmenu.this, lv.getId(), listspp);
						lv.setAdapter(adapter);
				}else{
					Toast.makeText(getApplicationContext(), "Loại sản phẩm đang cập nhật!", Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		loadList(idloai_Begin);
		super.onStart();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		loadList(idloai_Begin);
		super.onResume();
	}
		
	
	
	
	public void setSpinnerLoaiSP(){
		//set spinner loai sp
				ParseQuery<ParseObject> query;
				try {
					query = ParseQuery.getQuery("LoaiSP");
					obloaisp = query.find();
					listsp = new ArrayList<String>();
					listLoaisp = new ArrayList<ItemLoaiSP>();
					for(int i=0;i<obloaisp.size();i++){
						ItemLoaiSP m = new ItemLoaiSP();
						m.setMaloai(obloaisp.get(i).getObjectId());
						m.setTenloai(obloaisp.get(i).getString("TenLoaiSP"));
						listLoaisp.add(m);
						listsp.add(obloaisp.get(i).getString("TenLoaiSP"));
						ArrayAdapter<String> a = new ArrayAdapter<String>(Tabmenu.this, android.R.layout.simple_spinner_dropdown_item, listsp);
						spinLoaiSP.setAdapter(a);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	}
}