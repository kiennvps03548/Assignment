package dssp.pkg;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import quanly.taikhoan.nv.QLTKActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.example.login_ps03528.Tabnhanvien;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class ThemSPActivity extends Activity {
	
	EditText edtId, edtTen, edtSoLuong, edtLSP, edtGia;
	Button btn_Them, btn_Xem, btn_ChonHinh;
	ImageView imgsp;
	Spinner spinLoaiSP;
	List<ParseObject> ob;
	ArrayList<String> listsp;
	ArrayList<ItemLoaiSP> listLoaisp;
	int vitri;
	String tmpPath;
	ParseFile imageFile = null;
	byte[] byteArray;
	Bitmap bitmap   = null;
    String path     = "";
    boolean uploaded=false;
    boolean changepass=false;
	private Uri mImageCaptureUri;
	private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_themsp);

		edtId = (EditText) findViewById(R.id.edtMSP);
		edtTen = (EditText) findViewById(R.id.edtTSP);
		spinLoaiSP = (Spinner)findViewById(R.id.spnLoaiSP);
		edtSoLuong = (EditText) findViewById(R.id.edtSLSP);
		edtGia = (EditText) findViewById(R.id.edtNgay);
		imgsp = (ImageView)findViewById(R.id.imgSPadd);
		ParseQuery<ParseObject> query;
		chooseAvatar();// hàm chọn hình
		try {
			query = ParseQuery.getQuery("LoaiSP");// lấy hết bảng loại sp
			ob = query.find();//lấy tất cả dữ liệu trên 1 dòng đưa vào list ob
			listsp = new ArrayList<String>();
			listLoaisp = new ArrayList<ItemLoaiSP>();
			for(int i=0;i<ob.size();i++){//lấy tất cả dữ liệu từ mảng ob qua mảng Loaisp để load lên spiner
				ItemLoaiSP m = new ItemLoaiSP();
				m.maloai = ob.get(i).getObjectId();
				m.tenloai=ob.get(i).getString("TenLoaiSP");
				listLoaisp.add(m);//
				listsp.add(ob.get(i).getString("TenLoaiSP"));
				
			}
			ArrayAdapter<String> a = new ArrayAdapter<String>(ThemSPActivity.this, android.R.layout.simple_spinner_dropdown_item, listsp);
			spinLoaiSP.setAdapter(a);// đổ dữ liệu từ bảng loại sp lên spinner
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chooseAvatar();
		btn_Them = (Button) findViewById(R.id.btnThem);
		btn_Xem = (Button)findViewById(R.id.btnHuy);
		
		btn_Them.setOnClickListener(new OnClickListener() {
 
			// Thêm sp
			@Override
			public void onClick(View v) {
				String masp = edtId.getText().toString();
				String tensp = edtTen.getText().toString();
				String loaisp = listLoaisp.get(vitri).maloai;
				String giasp = edtGia.getText().toString();
				String Soluongsp = edtSoLuong.getText().toString();
			if(masp.equals("")||tensp.equals("")||loaisp.equals("")||giasp.equals("")||Soluongsp.equals("")){
				Toast.makeText(getApplicationContext(), "Tất cả các trường không được để trống!", Toast.LENGTH_LONG).show();
			}else{// lấy dữ liệu người dùng vừa nhập thêm vào bảng loại sp
				vitri = spinLoaiSP.getSelectedItemPosition();
				ParseObject sv = new ParseObject("SANPHAM");
				sv.put("MaSP", masp);
				sv.put("MaLoaiSP", ParseObject.createWithoutData("LoaiSP", loaisp));
				sv.put("TenSP",tensp );
				sv.put("GiaSP", Integer.parseInt(giasp));
				sv.put("SoLuong", Integer.parseInt(Soluongsp));
				if (uploaded)
				sv.put("HinhAnh", imageFile);
				sv.saveInBackground(new SaveCallback() {
					   public void done(ParseException e) {
					     if (e == null) {
					    	 Toast.makeText(ThemSPActivity.this, "Thêm sản phẩm thành công!", Toast.LENGTH_LONG).show();
					    	 finish();
					     } else {
					    	 Toast.makeText(ThemSPActivity.this, "Thêm sản phẩm thất bại! "+ e.toString(), Toast.LENGTH_LONG).show();
					     }
					   }
					 });
			}
				
			}
		});
		btn_Xem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	public static byte[] bitmapToByteArray(Bitmap bm) {
		int bytes = bm.getWidth()*bm.getHeight()*4;
		ByteBuffer buffer = ByteBuffer.allocate(bytes);
		bm.copyPixelsToBuffer(buffer);
		byte[] array = buffer.array();
		return array;
	}
	// hàm lấy hình từ trong máy để up lên 
	public void chooseAvatar(){
		final String [] items           = new String [] {"From Camera", "From SD Card"};
        ArrayAdapter<String> adapter  = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
        AlertDialog.Builder builder     = new AlertDialog.Builder(this);

        builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {
                    Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file        = new File(Environment.getExternalStorageDirectory(),
                                        "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    mImageCaptureUri = Uri.fromFile(file);
 
                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent.putExtra("return-data", true);
          // mở quản lý file
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
                    dialog.cancel();
                } else {
                    Intent intent = new Intent();
 
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
 
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                }
            }
        } );
 
        final AlertDialog dialog = builder.create();
 
        ((Button) findViewById(R.id.btnChonHinhAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
	}
	// lấy kết quả người dùng đã chọn
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
       
        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery
 
            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager
 
            if (path != null)
            	try{
            		bitmap  = BitmapFactory.decodeFile(path);
            	}catch (Exception e) {
            		Toast.makeText(getApplicationContext(), "Hình ảnh vượt quá kích thước!", Toast.LENGTH_LONG).show();
				}
                
        } else {
            path    = mImageCaptureUri.getPath();
            tmpPath = path;
            try{
        		bitmap  = BitmapFactory.decodeFile(path);
        	}catch (Exception e) {
        		Toast.makeText(getApplicationContext(), "Hình ảnh vượt quá kích thước!", Toast.LENGTH_LONG).show();
			}
        }
        byteArray = bitmapToByteArray(bitmap);
        imageFile = new ParseFile("hinhsp.png", byteArray);
        imageFile.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub
				if(e==null){
					Toast.makeText(getApplicationContext(), "Tải hình thành công!", Toast.LENGTH_LONG).show();
					imgsp.setImageBitmap(bitmap);
			        uploaded = true;
				}else {
					Toast.makeText(getApplicationContext(), "Tải hình thất bại!"+e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
    }
 // hàm lấy đường dẫn hình vừa chọn
    public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = managedQuery( contentUri, proj, null, null,null);
 
        if (cursor == null) return null;
 
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 
        cursor.moveToFirst();
 
        return cursor.getString(column_index);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quanly, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_qltk) {
			Intent i = new Intent(getApplicationContext(), QLTKActivity.class);
			String nvid = getIntent().getStringExtra("obid");
			i.putExtra("obid", nvid);
			startActivity(i);
			return true;
		}else if(id==R.id.action_dx){
			Intent i = new Intent(this, Tabnhanvien.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
