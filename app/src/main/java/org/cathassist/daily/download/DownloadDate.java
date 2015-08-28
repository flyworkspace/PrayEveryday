package org.cathassist.daily.download;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.cathassist.daily.PrayInEveryday;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadDate extends AsyncTask<String, Integer, String> {
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;

	@Override
	protected String doInBackground(String... params) {
//		try {
//			HttpClient httpclient = new DefaultHttpClient();
//			HttpGet httpget = new HttpGet(PrayInEveryday.SERVER_URL
//					+ "test.php");
//			HttpResponse response = httpclient.execute(httpget);
//			HttpEntity entity = response.getEntity();
//			is = entity.getContent();
//
//			String s = "";
//			if (is != null) {
//				try {
//					BufferedReader reader = new BufferedReader(
//							new InputStreamReader(is, "iso-8859-1"), 8);
//					sb = new StringBuilder();
//					sb.append(reader.readLine() + "\n");
//
//					String line = "0";
//					while ((line = reader.readLine()) != null) {
//						sb.append(line + "\n");
//					}
//					is.close();
//					result = sb.toString();
//				} catch (Exception e) {
//					Log.e("log_tag", "Error converting result " + e.toString());
//				}
//				// paring data
//				int ct_id;
//				String ct_name;
//				try {
//					jArray = new JSONArray(result);
//					JSONObject json_data = null;
//					for (int i = 0; i < jArray.length(); i++) {
//						json_data = jArray.getJSONObject(i);
//						ct_id = json_data.getInt("id");
//						ct_name = json_data.getString("name");
//						s += ct_name;
//					}
//				} catch (JSONException e1) {
//					// Toast.makeText(getBaseContext(), "No City Found"
//					// ,Toast.LENGTH_LONG).show();
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//			}
//			return s;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(String result) {
		// tv.setText(result);
	}

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected void onProgressUpdate(Integer... values) {

	}
}
