package org.cathassist.daily.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTool {
	public static boolean isConnectNet(Activity activity) {
		ConnectivityManager cwjManager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cwjManager.getActiveNetworkInfo();
		return info != null && info.isAvailable();
	}
}
