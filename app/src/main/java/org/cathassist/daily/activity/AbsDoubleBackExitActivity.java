package org.cathassist.daily.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by jinpengfei on 15-7-31.
 */
public abstract class AbsDoubleBackExitActivity extends BaseActivity {
    private boolean isExit = false;

    static class MyHandler extends Handler {
        WeakReference<AbsDoubleBackExitActivity> mActivity;

        MyHandler(AbsDoubleBackExitActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AbsDoubleBackExitActivity theActivity = mActivity.get();

            theActivity.isExit = false;
        }
    }

    MyHandler mHandler = new MyHandler(this);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            sendBroadcast(new Intent("finish"));
        }
    }
}
