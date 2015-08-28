package org.cathassist.daily.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "PrayEveryDay.db";
	public static final int DB_VERSION = 1;

	public DataBaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public DataBaseOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TodoDbAdapter.TABLE_BIBLEWORD_CREATE);
			db.execSQL(TodoDbAdapter.TABLE_DAYCONTENT_CREATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
