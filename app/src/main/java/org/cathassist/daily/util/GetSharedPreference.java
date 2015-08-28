package org.cathassist.daily.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class GetSharedPreference {
	public final static String VERSION_CODE = "version_code";

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
		editor.commit();
	}
}
