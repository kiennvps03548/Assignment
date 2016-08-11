package com.example.login_ps03528;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements OnMapReadyCallback {

	GoogleMap gMap;
	Spinner spin_position;
	String[] vl_position = {"Cơ sở 1", "Cơ sở 2"};
	ArrayAdapter<String> position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_map);
			MapFragment mapFragment = (MapFragment) getFragmentManager()
					.findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);

			spin_position =(Spinner)findViewById(R.id.spnMap);
			position = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, vl_position);
			spin_position.setAdapter(position);
		}



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

	public void onMapReady(final GoogleMap map) {
    	
		map.getUiSettings().setZoomControlsEnabled(true);
		map.setMyLocationEnabled(true);
		spin_position.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (spin_position.getSelectedItem().toString()) {
				case "Cơ sở 1":
					LatLng hcm1 = new LatLng(10.7909417, 106.68261589999997);
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(hcm1, 17));
					map.addMarker(new MarkerOptions()
							.title("FPT Polytechnic (CS1)")
							.snippet(
									"Cao đẳng thực hành FPT Hồ Chí Minh")
							.position(hcm1));
					break;
				case "Cơ sở 2":
					LatLng hcm2 = new LatLng(10.811758, 106.679880);
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(hcm2, 17));
					map.addMarker(new MarkerOptions()
							.title("FPT Polytechnic (CS2)")
							.snippet(
									"Cao đẳng thực hành FPT Hồ Chí Minh")
							.position(hcm2));
				default:
					break;
				
			
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
