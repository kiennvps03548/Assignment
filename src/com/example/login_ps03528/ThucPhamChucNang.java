package com.example.login_ps03528;

import com.parse.Parse;

import android.app.Application;

public class ThucPhamChucNang extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Parse.enableLocalDatastore(getApplicationContext());
		Parse.initialize(this, "keoh547bPVseTdyzoelyKPhRx5q7L3xsumPcScQr", "MM2UD08wsNxtSlsrvbJkTt0dXJ8ApR7WbY8oeiJG");
	}
	
}
