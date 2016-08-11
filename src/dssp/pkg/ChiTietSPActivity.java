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
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
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
	ArrayList<ItemLoaiSP> listLoaisp;
	int vitri;
	String obid;
	String idloaisp_begin;
	String tmpPath;
	ParseFile imageFile = null;
	byte[] byteArray; //mang chua diem anh
	Bitmap bitmap   = null; //anh sau khi ve
    String path     = ""; // duong dan hinh
    boolean uploaded=false; // kiem tra da upload dc chua
	private Uri mImageCaptureUri; //uri lay hinh tu camera chup truc tiep
	private static final int PICK_FROM_CAMERA = 1; // lay hinh tu album anh
    private static final int PICK_FROM_FILE = 2; // lay hinh tu quan ly file

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
		setSpinnerLoaiSP();
		
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
		//Set dữ liệu lên EditText
		edtId.setText(masp);
		edtTen.setText(tensp);
		edtGia.setText(String.valueOf(giasp));
		edtSoLuong.setText(String.valueOf(sl));
		imgSP.setImageBitmap(bmp);
		int vitriSPn=0;
		for(int i=0;i<listLoaisp.size();i++){
			if(listLoaisp.get(i).maloai.equals(idloaisp_begin)){
				vitriSPn = i;
				break;
			}
		}
		spinLoaiSP.setSelection(vitriSPn);
		chooseAvatar();
		
		btn_Luu = (Button) findViewById(R.id.btnLuu);
		btn_Huy = (Button)findViewById(R.id.btnHuy);
		
// Sửa sản phẩm
		btn_Luu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ParseQuery<ParseObject> query = ParseQuery.getQuery("SANPHAM");
				query.getInBackground(obid, new GetCallback<ParseObject>() {
					  public void done(ParseObject sv, ParseException e) {
					    if (e == null) {
					    	vitri = spinLoaiSP.getSelectedItemPosition();
							String xbc = listLoaisp.get(vitri).maloai;
					    	sv.put("MaSP", edtId.getText().toString());
							sv.put("MaLoaiSP", ParseObject.createWithoutData("LoaiSP", xbc));
							sv.put("TenSP", edtTen.getText().toString());
							sv.put("GiaSP", Integer.parseInt(edtGia.getText().toString()));
							sv.put("SoLuong", Integer.parseInt(edtSoLuong.getText().toString()));
					    	if(uploaded){
					    		sv.put("HinhAnh", imageFile);
					    	}
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
						m.maloai = obloaisp.get(i).getObjectId();
						m.tenloai=obloaisp.get(i).getString("TenLoaiSP");
						listLoaisp.add(m);
						listsp.add(obloaisp.get(i).getString("TenLoaiSP"));
						ArrayAdapter<String> a = new ArrayAdapter<String>(ChiTietSPActivity.this, android.R.layout.simple_spinner_dropdown_item, listsp);
						spinLoaiSP.setAdapter(a);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	//ham chuyen hinh bitmap qua mang diem anh de upload len parse
	public static byte[] bitmapToByteArray(Bitmap bm) {
		int bytes = bm.getWidth()*bm.getHeight()*4;
		ByteBuffer buffer = ByteBuffer.allocate(bytes);
		bm.copyPixelsToBuffer(buffer);
		byte[] array = buffer.array();
		return array;
	}
	// ham chon hinh
	public void chooseAvatar(){
		final String [] items           = new String [] {"From Camera", "From SD Card"};
        ArrayAdapter<String> adapter  = new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
        AlertDialog.Builder builder     = new AlertDialog.Builder(this);
        // tao dialog cho phep ng dung chon noi de chon hinh
        builder.setTitle("Select Image");
        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int item ) {
                if (item == 0) {// nếu chọn từ camera thì mở máy ảnh lên
                    Intent intent    = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file        = new File(Environment.getExternalStorageDirectory(),
                                        "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    mImageCaptureUri = Uri.fromFile(file);
 
                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent.putExtra("return-data", true);
 
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
 
                    dialog.cancel();
                } else {//nếu chọn từ file thì mở quản lý file chỗ image/ lên
                    Intent intent = new Intent();
 
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
 
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                }
            }
        } );
 
        final AlertDialog dialog = builder.create();
 
        ((Button) findViewById(R.id.btnChonHinhXemSP)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
	}
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
       
 
        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery
 
            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager
 
            if (path != null)
                bitmap  = BitmapFactory.decodeFile(path);
        } else {
            path    = mImageCaptureUri.getPath();
            tmpPath = path;
            bitmap  = BitmapFactory.decodeFile(path);
        }
        byteArray = bitmapToByteArray(bitmap);
        imageFile = new ParseFile("hinhsp.png", byteArray);
        imageFile.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub
				if(e==null){
					Toast.makeText(getApplicationContext(), "Tải hình thành công!", Toast.LENGTH_LONG).show();
					imgSP.setImageBitmap(bitmap);
			        uploaded = true;
				}else {
					Toast.makeText(getApplicationContext(), "Tải hình thất bại!"+e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
    }
 
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
