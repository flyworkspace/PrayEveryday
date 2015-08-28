package org.cathassist.daily.activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;

import org.cathassist.daily.R;
import org.cathassist.daily.util.PublicFunction;
import org.cathassist.daily.util.PublicFunction.OnClickCancelListener;


public class Preferences extends PreferenceActivity implements
		OnPreferenceClickListener,OnClickCancelListener {
	private Preference aboutPreference,aboutUsPreference, updatePreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		findPreference();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		setListener();
		aboutPreference.setSummary(getString(R.string.version,
				PublicFunction.getVerName(Preferences.this)));
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	private void setListener() {
		aboutPreference.setOnPreferenceClickListener(this);
		updatePreference.setOnPreferenceClickListener(this);
	}

	private void findPreference() {
		aboutPreference = findPreference("about");
		aboutUsPreference =findPreference("about_us");
		updatePreference = findPreference("update");
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (preference == aboutPreference) {
			PublicFunction.getTipsDialog(Preferences.this,false).show();
		} else if (preference == updatePreference) {
		}
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClickCancelDo() {
		// TODO Auto-generated method stub
		
	}

}
