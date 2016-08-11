package HoaDon.pkg;

import com.example.login_ps03528.TabHoaDon;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ModelLvHD extends RelativeLayout{ 
	public ImageView img;
	public TextView     title, level;
	public ModelLvHD(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater =(LayoutInflater)((TabHoaDon)context).getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(com.example.login_ps03528.R.layout.item_lvtopic, this);
		this.img = (ImageView)findViewById(com.example.login_ps03528.R.id.imgItemTopic);
		this.title = (TextView)findViewById(com.example.login_ps03528.R.id.textView1);
		this.level = (TextView)findViewById(com.example.login_ps03528.R.id.textView2);

	}
	public void setListItem(ItemHD m){
		if(m.tinhtrang==0){
			this.img.setImageResource(com.example.login_ps03528.R.drawable.tick);
		}else{
			this.img.setImageResource(com.example.login_ps03528.R.drawable.uncheck);
		}
		this.title.setText("Số hóa đơn: "+m.sohd);
		this.level.setText("Tổng tiền: "+String.valueOf(m.tongtien));
	}
		
}

