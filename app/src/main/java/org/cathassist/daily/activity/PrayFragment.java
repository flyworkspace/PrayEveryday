package org.cathassist.daily.activity;

import org.cathassist.daily.R;
import org.cathassist.daily.bean.DayContent;
import org.cathassist.daily.database.TodoDbAdapter;
import org.cathassist.daily.provider.EnumManager;
import org.cathassist.daily.util.CreateHtmlFile;
import org.cathassist.daily.util.GetSharedPreference;

import com.spreada.utils.chinese.ZHConverter;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PrayFragment extends Fragment {
    private WebView mWebView;
    private TodoDbAdapter dbHelper;
    private Handler handler = new Handler();
    DayContent dayContent;
    private String dateString;
    private int contentType;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("dateString", dateString);
        if (mWebView != null)
            mWebView.saveState(outState);
    }

    public static PrayFragment newInstance(String dateString, int contentType) {
        PrayFragment fragment = new PrayFragment();
        fragment.dateString = dateString;
        fragment.contentType = contentType;
        return fragment;
    }

    public int getContentType() {
        return contentType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null
                && savedInstanceState.getString("dateString") != null) {
            dateString = savedInstanceState.getString("dateString");
        }
        View view = inflater.inflate(R.layout.activity_pray, container, false);
        findViews(view);
        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        if (savedInstanceState != null) {
            if (savedInstanceState.isEmpty())
                Log.i("", "Can't restore state because bundle is empty.");
            else {
                if (mWebView.restoreState(savedInstanceState) == null)
                    Log.i("", "Restoring state FAILED!");
                else
                    Log.i("", "Restoring state succeeded.");
            }

        } else {
            dayContent = new DayContent();
            dbHelper = new TodoDbAdapter(getActivity());
            dbHelper.open();
            dayContent = dbHelper.getContent(dateString, contentType);
            dbHelper.close();

//            setting.setSupportZoom(true);
//            setting.setBuiltInZoomControls(true);
            setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

            if (android.os.Build.VERSION.SDK_INT > 13) {
                setting.setDisplayZoomControls(false);
                setting.setTextZoom(GetSharedPreference.getFontSize(getActivity()));
            }

            if (dayContent != null) {
                Thread thread = new Thread(null, doBackgroundThreadProcessing,
                        "Background");
                thread.start();
            }
        }
        mWebView.setBackgroundColor(0);
        return view;
    }

    public void setTextSize(EnumManager.FontSize fontSize) {
        if (mWebView != null) {
            if (android.os.Build.VERSION.SDK_INT > 13) {
                mWebView.getSettings().setTextZoom(fontSize.getValue());
            }
        }
    }

    public void setDate(String date) {
        dateString = date;
        if (dbHelper == null) {
            return;
        }
        dbHelper.open();
        dayContent = dbHelper.getContent(dateString, contentType);
        dbHelper.close();
        if (dayContent != null) {
            Thread thread = new Thread(null, doBackgroundThreadProcessing,
                    "Background");
            thread.start();
        }
    }

    private void findViews(View view) {
        mWebView = (WebView) view.findViewById(R.id.webview_content);
    }

    public String testCreateHTML(String string) {

        String path = Environment.getExternalStorageDirectory().toString()
                + "/" + System.currentTimeMillis() + "htmltemp";
        CreateHtmlFile.convert(path, string);
        return "file:///" + path;
    }

    private Runnable doBackgroundThreadProcessing = new Runnable() {
        public void run() {
            backgroundThreadProcessing();
        }
    };

    private void backgroundThreadProcessing() {
        handler.post(doUpdateGUI);
    }

    // Runnable that executes the update GUI method.
    private Runnable doUpdateGUI = new Runnable() {
        public void run() {
            updateGUI();
        }
    };

    private void updateGUI() {
        ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
        mWebView.loadUrl(testCreateHTML(converter.convert(dayContent
                .getContent())));
    }
}
