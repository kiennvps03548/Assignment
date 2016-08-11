package com.example.login_ps03528;

import java.util.ArrayList;
import java.util.List;

import HoaDon.pkg.AdapterLvHD;
import HoaDon.pkg.ItemHD;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TabHoaDon extends Activity {
	ListView lv;
	ArrayList<ItemHD> list;
	List<ParseObject> ob;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_hoa_don);
		lv = (ListView)findViewById(R.id.lvHD);
		list = new ArrayList<ItemHD>();
		ParseQuery<ParseObject> query;
		try {
			query = ParseQuery.getQuery("HoaDon");
			ob = query.find();
			for(int i=0;i<ob.size();i++){
				ItemHD m = new ItemHD();
				m.setSohd(ob.get(i).getString("SoHD"));
				m.setTinhtrang(ob.get(i).getInt("TinhTrang"));
				m.setTongtien(ob.get(i).getInt("TongTien"));
				list.add(m);
			}
				AdapterLvHD adapter = new AdapterLvHD(TabHoaDon.this, lv.getId(), list);
				lv.setAdapter(adapter);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_hoa_don, menu);
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
