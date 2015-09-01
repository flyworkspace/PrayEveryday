package org.cathassist.daily.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import org.cathassist.daily.R;
import org.cathassist.daily.util.PublicFunction;


public class Preferences extends PreferenceActivity implements
        OnPreferenceClickListener {
    private Preference aboutPreference, aboutUsPreference, updatePreference;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        findPreference();
    }

    @Override
    public void setContentView(int layoutResID) {
        ViewGroup contentView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.settings_activity, new LinearLayout(this), false);

        mToolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ViewGroup contentWrapper = (ViewGroup) contentView.findViewById(R.id.content_wrapper);
        LayoutInflater.from(this).inflate(layoutResID, contentWrapper, true);

        getWindow().setContentView(contentView);
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
        aboutUsPreference = findPreference("about_us");
        updatePreference = findPreference("update");
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == aboutPreference) {
            PublicFunction.showTipsDialog(Preferences.this, false, null);
        } else if (preference == updatePreference) {
            UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
                @Override
                public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                    switch (updateStatus) {
                        case UpdateStatus.Yes: // has update
                            UmengUpdateAgent.showUpdateDialog(Preferences.this, updateInfo);
                            break;
                        case UpdateStatus.No: // has no update
                            showSnackbar(mToolbar, "已经是最新版本", Snackbar.LENGTH_LONG);
                            break;
                        case UpdateStatus.NoneWifi: // none wifi
                            showSnackbar(mToolbar, "没有wifi连接， 只能在wifi下更新", Snackbar.LENGTH_LONG);
                            break;
                        case UpdateStatus.Timeout: // time out
                            showSnackbar(mToolbar, "超时", Snackbar.LENGTH_LONG);
                            break;
                        default:
                    }
                }
            });
            UmengUpdateAgent.forceUpdate(Preferences.this);
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

    protected void showSnackbar(View view, String text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        TextView tv = (TextView)
                snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
