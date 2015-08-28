package org.cathassist.daily;

import org.cathassist.daily.database.TodoDbAdapter;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class PrayInEveryday extends Application {
	public final static String SERVER_URL = "http://jinpengfeie.yupage.com/";
//	public final static String SERVER_URL2 = "http://t.liyake.com/getstuff/getstuff.php?date=";
	public final static String SERVER_URL2 = "http://115.29.170.47/getstuff/getstuff.php?date=";
//	public final static String SERVER_URL ="http://192.168.137.1/web/";
	private TodoDbAdapter dbHelper;

	public TodoDbAdapter openAndGetDbHelper() {
		dbHelper = new TodoDbAdapter(this);
		dbHelper.open();
		return dbHelper;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		getApplicationContext().sendBroadcast(new Intent("finish"));
		Log.e("APPonCreate", "APPonCreate");
		
	}
	
}
