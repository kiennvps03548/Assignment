package dssp.pkg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.login_ps03528.R;
import com.parse.DeleteCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class DSSPActivity extends Activity {
	ArrayList<ItemLvTopic> list;
	ListView lv;
	List<ParseObject> ob;
	ArrayList<ItemLvTopic> listsp;
	Button add;
	ParseObject obj;
	int vitrixoa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.login_ps03528.R.layout.dssanpham);
		add = (Button)findViewById(R.id.btnAddSP);
		list = new ArrayList<ItemLvTopic>();
		lv = (ListView)findViewById(com.example.login_ps03528.R.id.lvTopic);
		loadList();
		
//Xem chi tiết sản phẩm	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(DSSPActivity.this, ChiTietSPActivity.class);
				Bundle bun  = new Bundle();
				
				bun.putString("obid", ob.get(position).getObjectId());
				bun.putString("masp", ob.get(position).getString("MaSP"));
				ParseObject loaisp = (ParseObject) ob.get(position).get("MaLoaiSP");
				bun.putString("maloaisp", loaisp.getObjectId());
				bun.putString("tensp", ob.get(position).getString("TenSP"));
				bun.putInt("giasp", ob.get(position).getInt("GiaSP"));
				bun.putInt("sl", ob.get(position).getInt("SoLuong"));
				bun.putByteArray("img", listsp.get(position).bmp);
				i.putExtras(bun);
				startActivity(i);
			}
		});
		
//xóa sản phẩm
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				vitrixoa = position;
				new AlertDialog.Builder(DSSPActivity.this)
			    .setTitle("Xóa sản phẩm")
			    .setMessage("Bạn có muốn xóa sản phẩm này?")
			    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {// nút yes để xóa
			        public void onClick(DialogInterface dialog, int which) { 
			            // continue with delete
			        	ParseQuery<ParseObject> query = ParseQuery.getQuery("SANPHAM");
			        	try {
							ob = query.find();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						obj = ob.get(vitrixoa);
						obj.deleteInBackground(new DeleteCallback() {

			                @Override
			                public void done(com.parse.ParseException e) {
			                    // TODO Auto-generated method stub
			                	if (e == null) {// nếu ko có lỗi thì xóa, nếu có lỗi thì ko đc xóa
							    	 Toast.makeText(DSSPActivity.this, "Xóa thành công!", Toast.LENGTH_LONG).show();
							    	 loadList();
							     } else {
							    	 Toast.makeText(DSSPActivity.this, "Xóa thất bại! "+ e.toString(), Toast.LENGTH_LONG).show();
							     }
			                }
			            });
			        }
			     })
			    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {// chọn nút no để tắt dialog
			        public void onClick(DialogInterface dialog, int which) { 
			            dialog.cancel();
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
				
				return false;
			}
		});
		// click vào nút thêm sẽ qua trang thêm sp
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DSSPActivity.this, ThemSPActivity.class);
				startActivity(intent);
			}
		});
	}
	
	// lấy tất cả giữ liệu ở bảng sap3 phẩm để đưa lên ListView
	public void loadList(){
		ParseQuery<ParseObject> query;
		try {
			query = ParseQuery.getQuery("SANPHAM");
			ob = query.find();
			listsp = new ArrayList<ItemLvTopic>();
			for(int i=0;i<ob.size();i++){
				final ItemLvTopic m = new ItemLvTopic();
				m.masp = ob.get(i).getString("MaSP");
				m.maloaisp = ob.get(i).getString("MaLoaiSP");
				m.tensp = ob.get(i).getString("TenSP");
				m.giasp = ob.get(i).getInt("GiaSP");
				m.soluong = ob.get(i).getInt("SoLuong");
				if(ob.get(i).has("HinhAnh")){
					ParseFile fileObject = (ParseFile)ob.get(i).get("HinhAnh");
					fileObject.getDataInBackground(new GetDataCallback() {
					  public void done(byte[] data, ParseException e) {
					    if (e == null) {
					      // only a test
					    		m.bmp = data;
					    		m.hinhanh = BitmapFactory.decodeByteArray(data, 0,
		                                data.length);
					    } else {
					      // something went wrong
					    	Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),R.drawable.sp);
							m.hinhanh = tempBMP;
					    }
					  }
					});
				}else{
					Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),R.drawable.sp);
					m.hinhanh = tempBMP;
				}
				
				listsp.add(m);
			}
				AdapterLvTopic adapter = new AdapterLvTopic(DSSPActivity.this, lv.getId(), listsp);
				lv.setAdapter(adapter);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		loadList();
		super.onStart();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		loadList();
		super.onResume();
	}
}
