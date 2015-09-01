package org.cathassist.daily.activity;

import org.cathassist.daily.PrayInEveryday;
import org.cathassist.daily.R;
import org.cathassist.daily.database.TodoDbAdapter;
import org.cathassist.daily.util.DensityUtils;

import com.umeng.analytics.MobclickAgent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity implements Handler.Callback{
    private static final int MSG_START_SUBMIT_PROGRESS = 1002;
    private static final int MSG_STOP_SUBMIT_PROGRESS = 1003;
    TodoDbAdapter dbHelper;
    PrayInEveryday prayEveryday;
    private Handler mHandler;
    private PopupWindow mSubmitPopupwindow;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.setDebugMode(true);
        dbHelper = new TodoDbAdapter(this);
        prayEveryday = (PrayInEveryday) getApplicationContext();
        IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(mFinishReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mFinishReceiver);
        Log.e("Destroy", "Destroy");
    }

    @Override
    public void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("finish".equals(intent.getAction())) {
                Log.e("#########", "I am " + getLocalClassName()
                        + ",now finishing myself...");
                finish();
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("stop", "stop");
    }

    protected void showSnackbar(View view, String text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        TextView tv = (TextView)
                snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void startWaitingProgress() {
        if (mHandler == null) {
            mHandler = new Handler(this);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mHandler.sendEmptyMessage(MSG_START_SUBMIT_PROGRESS);
    }
    public void stopWaitingProgress() {
        stopWaitingProgress(null);
    }

    public void stopWaitingProgress(OnWaitingProgressFinishDo onWaitingProgressFinishDo) {
        if (mHandler != null) {
            mHandler.removeMessages(MSG_START_SUBMIT_PROGRESS);
            Message message = new Message();
            message.what = MSG_STOP_SUBMIT_PROGRESS;
            if (onWaitingProgressFinishDo != null) {
                message.obj = onWaitingProgressFinishDo;
            }
            mHandler.sendMessageDelayed(message, 100);
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_START_SUBMIT_PROGRESS:
                if (mSubmitPopupwindow == null) {
                    View view = getLayoutInflater().inflate(R.layout.popupwindow_waiting_loading, null);
//                    mSubmitProgressView = (ProgressView) view.findViewById(R.id.progress_linear);
                    mSubmitPopupwindow = new PopupWindow(view, DensityUtils.dp2px(BaseActivity.this, 50), DensityUtils.dp2px(BaseActivity.this, 50));
                    mSubmitPopupwindow.setBackgroundDrawable(new BitmapDrawable());
                    mSubmitPopupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
//                            WindowManager.LayoutParams lp = getWindow().getAttributes();
//                            lp.alpha = 1f;
//                            getWindow().setAttributes(lp);
                        }
                    });
                }
                if (!mSubmitPopupwindow.isShowing()) {
                    mSubmitPopupwindow.showAtLocation(mToolbar, Gravity.CENTER, 0, 0);

//                    WindowManager.LayoutParams lp = getWindow().getAttributes();
//                    lp.alpha = 0.4f;
//                    getWindow().setAttributes(lp);
                }
                break;
            case MSG_STOP_SUBMIT_PROGRESS:
                if (mSubmitPopupwindow != null) {
                    mSubmitPopupwindow.dismiss();
                    Object obj = msg.obj;
                    if (obj != null) {
                        OnWaitingProgressFinishDo onWaitingProgressFinishDo = (OnWaitingProgressFinishDo) obj;
                        onWaitingProgressFinishDo.onFinish();
                    }
                }
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        mToolbar = toolbar;
    }

    public interface OnWaitingProgressFinishDo {
        void onFinish();
    }
}