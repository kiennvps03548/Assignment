package dssp.pkg;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ModelLvTopic extends RelativeLayout{ 
	public ImageView img;
	public TextView     title, level;
	public ModelLvTopic(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater =(LayoutInflater)((DSSPActivity)context).getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(com.example.login_ps03528.R.layout.item_lvtopic, this);
		this.img = (ImageView)findViewById(com.example.login_ps03528.R.id.imgItemTopic);
		this.title = (TextView)findViewById(com.example.login_ps03528.R.id.textView1);
		this.level = (TextView)findViewById(com.example.login_ps03528.R.id.textView2);

	}
	public void setListItem(ItemLvTopic m){
		this.img.setImageBitmap(m.hinhanh);
		this.title.setText(m.tensp);
		this.level.setText(String.valueOf(m.giasp)+" VND");
	}
		
}

