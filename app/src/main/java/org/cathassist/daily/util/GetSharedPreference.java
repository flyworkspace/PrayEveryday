package org.cathassist.daily.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class GetSharedPreference {
	public final static String VERSION_CODE = "version_code";
	public final static String FONT_SIZE = "font_size";

	public static int getVersionCode(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getInt(VERSION_CODE, 0);
	}

	public static void setVersionCode(Context context, int versionCode) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putInt(VERSION_CODE, versionCode);
		editor.apply();
	}

	public static int getFontSize(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getInt(FONT_SIZE, 0);
	}

	public static void setFontSize(Context context, int fontsize){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putInt(FONT_SIZE, fontsize);
		editor.apply();
	}
}
