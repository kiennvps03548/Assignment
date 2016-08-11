package com.example.login_ps03528;

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

public class Trangchu extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trangchu);
		TabHost tabHost = getTabHost();

		TabSpec menu = tabHost.newTabSpec("Menu");
		menu.setIndicator(createTabIndicator(getLayoutInflater(), tabHost, "Menu", R.drawable.icon_tabmenu));
		Intent menuIntent = new Intent(this, dssp.khachhang.Tabmenu.class);
		menu.setContent(menuIntent);

		TabSpec giohang = tabHost.newTabSpec("Giỏ Hàng");
		giohang.setIndicator(createTabIndicator(getLayoutInflater(), tabHost, "Giỏ Hàng", R.drawable.icon_tabgiohang));
		Intent giohangIntent = new Intent(this, Tabgiohang.class);
		giohang.setContent(giohangIntent);

		TabSpec lienhe = tabHost.newTabSpec("Liên Hệ");
		lienhe.setIndicator(createTabIndicator(getLayoutInflater(), tabHost, "Liên Hệ", R.drawable.icon_tablienhe));
		Intent lienheIntent = new Intent(this, MapActivity.class);
		lienhe.setContent(lienheIntent);

		tabHost.addTab(menu);
		tabHost.addTab(giohang);
		tabHost.addTab(lienhe);
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
		getMenuInflater().inflate(R.menu.trangchu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_nv) {
			Intent i = new Intent(this, Tabnhanvien.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public View createTabIndicator(LayoutInflater inflater, TabHost tabHost, String text, int iconResource) {
		View tabIndicator = inflater.inflate(R.layout.tab_indicator, tabHost.getTabWidget(), false);
		TextView tv = (TextView) tabIndicator.findViewById(R.id.tab_title);
		tv.setText(text);
		((ImageView) tabIndicator.findViewById(R.id.tab_icon)).setImageResource(iconResource);
		return tabIndicator;
	}
}
