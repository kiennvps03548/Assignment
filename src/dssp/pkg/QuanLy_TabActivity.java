package dssp.pkg;

import com.example.login_ps03528.R;
import com.example.login_ps03528.TabHoaDon;
import com.example.login_ps03528.Tabnhanvien;
import com.example.login_ps03528.R.id;
import com.example.login_ps03528.R.layout;
import com.example.login_ps03528.R.menu;

import quanly.taikhoan.nv.QLTKActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class QuanLy_TabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quanly);
		TabHost tabHost = getTabHost();

		TabSpec menu = tabHost.newTabSpec("Sản phẩm");
		menu.setIndicator(createTabIndicator(getLayoutInflater(), tabHost, "Sản phẩm"));
		Intent menuIntent = new Intent(this, DSSPActivity.class);
		menu.setContent(menuIntent);

		TabSpec giohang = tabHost.newTabSpec("Hóa đơn");
		giohang.setIndicator(createTabIndicator(getLayoutInflater(), tabHost, "Hóa đơn"));
		Intent giohangIntent = new Intent(this, TabHoaDon.class);
		giohang.setContent(giohangIntent);

		tabHost.addTab(menu);
		tabHost.addTab(giohang);
	}

	private TabHost getTabs() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTabIcon(TabHost tabHost, int tabIndex, int iconResource) {
		ImageView tabImageView = (ImageView) tabHost.getTabWidget().getChildTabViewAt(tabIndex)
				.findViewById(android.R.id.icon);
		tabImageView.setVisibility(View.VISIBLE);
		tabImageView.setImageResource(iconResource);
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

	public View createTabIndicator(LayoutInflater inflater, TabHost tabHost, String text) {
		View tabIndicator = inflater.inflate(R.layout.tab_indicator, tabHost.getTabWidget(), false);
		TextView tv = (TextView) tabIndicator.findViewById(R.id.tab_title);
		tv.setText(text);
		return tabIndicator;
	}
}
