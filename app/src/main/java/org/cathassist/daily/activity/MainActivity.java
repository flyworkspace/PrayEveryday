package org.cathassist.daily.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.cathassist.daily.PrayInEveryday;
import org.cathassist.daily.R;
import org.cathassist.daily.activity.MainFragment.OnArticleSelectedListener;
import org.cathassist.daily.bean.CalendarDay;
import org.cathassist.daily.bean.DateBean;
import org.cathassist.daily.bean.DayContent;
import org.cathassist.daily.database.TodoDbAdapter;
import org.cathassist.daily.provider.EnumManager.ContentType;
import org.cathassist.daily.util.GetSharedPreference;
import org.cathassist.daily.util.NetworkTool;
import org.cathassist.daily.util.PublicFunction;
import org.cathassist.daily.util.PublicFunction.OnClickCancelListener;
import org.cathassist.daily.util.TimeFormatter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnClickCancelListener, OnArticleSelectedListener {
    private final static String SELECTED_TAG = "selected_index";
    private final static String COLLAPSING_TOOLBAR_FRAGMENT_TAG = "collapsing_toolbar";
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Calendar calendar = Calendar.getInstance();
    private RequestQueue mQueue;
    private static int selectedIndex;
    private int count = 0;
    private TabFragment mTabFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (savedInstanceState != null) {
            navigationView.getMenu().getItem(savedInstanceState.getInt(SELECTED_TAG)).setChecked(true);
            return;
        }

        loadDate();
        selectedIndex = 0;
        mTabFragment = new TabFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mTabFragment, COLLAPSING_TOOLBAR_FRAGMENT_TAG).commit();
        mQueue = Volley.newRequestQueue(this);

        showTipsDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("MainonResume", "MainonResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_actionbar, menu);
        return true;
    }

    private void loadDate() {
        TodoDbAdapter dbHelper = new TodoDbAdapter(this);
        dbHelper.open();
        List<DateBean> list = dbHelper.getDateList();
        dbHelper.close();
        for (DateBean dateBean : list) {
//            if (PublicFunction.getYearMonthDayForSql(calendar.getTime())
//                    .equals(dateBean.getDate())) {
//                navigationView.getMenu().add(dateBean.getDate() + "("
//                        + getString(R.string.today) + ")");
//            } else {
//                navigationView.getMenu().add(dateBean.getDate());
//            }
            navigationView.getMenu().add(dateBean.getDate());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.main_actionbar_calendar:
                Intent intentCalendar = new Intent(MainActivity.this,
                        CalendarListActivity.class);
                startActivity(intentCalendar);
                break;
            case R.id.main_actionbar_update:
                updateDate();
                break;
            case R.id.menu_preferences:
                Intent intent = new Intent(MainActivity.this, Preferences.class);
                startActivity(intent);
                break;
            case R.id.menu_exit:
                getApplicationContext().sendBroadcast(new Intent("finish"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            if (mPager.getCurrentItem() == 0)
            dialog();
//            else
//                mPager.setCurrentItem(0);
        }
        return false;
    }

    protected void dialog() {
        Builder builder = new Builder(this);
        builder.setMessage(R.string.exit_tip);
        builder.setTitle(R.string.tip);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intentExit = new Intent();
                        intentExit.setAction("finish");
                        sendBroadcast(intentExit);
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    protected void updateDialog() {
        Builder builder = new Builder(this);
        builder.setMessage(R.string.first);
        builder.setTitle(R.string.tip);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        updateDate();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void showTipsDialog() {
        if (GetSharedPreference.getVersionCode(MainActivity.this) < PublicFunction
                .getVerCode(MainActivity.this)) {
            PublicFunction.getTipsDialog(MainActivity.this, true).show();
            GetSharedPreference.setVersionCode(MainActivity.this,
                    PublicFunction.getVerCode(MainActivity.this));
        }
    }

    @Override
    public void onClickCancelDo() {
        updateDialog();
    }

    private void updateDate() {
        if (NetworkTool.isConnectNet(this)) {
//            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置风格为圆形进度条
//            progressDialog.setTitle(getString(R.string.hold_on));// 设置标题
//            progressDialog.setMessage(getString(R.string.updating));
//            PublicFunction.showToast(MainActivity.this,
//                    getString(R.string.begin_update));
//            progressDialog.setIndeterminate(false);// 设置进度条是否为不明确
//            progressDialog.setCancelable(false);// 设置进度条是否可以按退回键取消
//            progressDialog.show();
            String deleteTime = PublicFunction
                    .getYearMonthDayForSql(calendar.getTime());
            dbHelper.open();
            dbHelper.deleteOldData(deleteTime);
            final ArrayList<Object> objects = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                final Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_YEAR, i);
                final String dateString = TimeFormatter.formatDateYYYYMMDD(c
                        .getTimeInMillis());
                if (dbHelper.getCalendarDayByDate(dateString) != null) {
                    continue;
                }
                count++;
                String httpUrl2 = PrayInEveryday.SERVER_URL2
                        + dateString;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, httpUrl2, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DayContent dayContent = new DayContent();
                        dayContent.setDate(dateString);
                        dayContent.setUpdateTime(c.getTimeInMillis() + "");
                        for (int j = 0; j < ContentType.values().length; j++) {
                            dayContent.setContentType(j);
                            dayContent
                                    .setContent(response.optString(ContentType
                                            .getContentDataNameFromContentType(j)));
                            dbHelper.insertContent(dayContent);
                        }
                        CalendarDay calendarDay = new CalendarDay();
                        calendarDay.setDate(dateString);
                        dbHelper.insertCalendarDay(calendarDay);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                Object obj = new Object();
                mQueue.add(jsonObjectRequest);
                jsonObjectRequest.setTag(obj);
                objects.add(obj);
                mQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                    @Override
                    public void onRequestFinished(Request<String> request) {
                        objects.remove(request.getTag());
//                        count--;
//                        if (count == 0) {
                        if (objects.isEmpty()) {
                            Snackbar.make(navigationView, "FINISH" + request, Snackbar.LENGTH_SHORT).show();
//                            Toast.makeText(MainActivity.this, "FINISH", Toast.LENGTH_SHORT).show();
//                        }
                        }
                    }
                });
            }
        } else {
            PublicFunction.showToast(MainActivity.this,
                    getString(R.string.no_web));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mTabFragment.setDate(menuItem.getTitle().toString());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onArticleSelected(int page) {
        mTabFragment.setViewPagerCurrentItem(page);
    }

    public void setupNavigationDrawer(Toolbar toolbar) {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
