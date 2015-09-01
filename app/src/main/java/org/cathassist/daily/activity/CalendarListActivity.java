package org.cathassist.daily.activity;

import java.util.Calendar;
import java.util.Map;

import org.cathassist.daily.PrayInEveryday;
import org.cathassist.daily.R;
import org.cathassist.daily.database.TodoDbAdapter;
import org.cathassist.daily.provider.CalendarManager;
import org.cathassist.daily.provider.DailyListAdapter;
import org.cathassist.daily.util.PublicFunction;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

public class CalendarListActivity extends BaseActivity {
	private PullToRefreshExpandableListView mListDiary;
	private ExpandableListView mListView;
	private DailyListAdapter dailyListAdapter;

	private Calendar mCalendar;
	private static final int TOP_REFRESH = 0;
	private static final int BOTTOM_REFRESH = 1;
	TodoDbAdapter dbHelper;
	PrayInEveryday prayEveryday;
	private Toolbar mToolbar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_list);
		findView();
		initGlobalData();
		initViewData();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void findView() {
		mListDiary = (PullToRefreshExpandableListView) findViewById(R.id.list_calendar);
		mListView = mListDiary.getRefreshableView();
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
	}

	private void backToTime(Calendar calendar) {

		mCalendar = calendar;
		long time = calendar.getTimeInMillis();
		setTitle(PublicFunction.formatDateYYYYMM(time));
		dbHelper.open();
		CalendarManager calendarManager = CalendarManager.getInstance();
		Map<String, Object> diaryListOfMonth = calendarManager
				.getCalendarListByMonth(time);
		dbHelper.close();
		dailyListAdapter = new DailyListAdapter(CalendarListActivity.this,
				diaryListOfMonth, prayEveryday, mCalendar);
		mListView.setAdapter(dailyListAdapter);
		//TODO jpf
		mListView.expandGroup(0);
//		mListView.expandGroup(calendarManager.getOpen());
	}

	private void initViewData() {
		mListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				return false;
			}
		});
		mListDiary
				.setOnRefreshListener(new OnRefreshListener2<ExpandableListView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ExpandableListView> refreshView) {
//						Toast.makeText(CalendarListActivity.this, "Top",
//								Toast.LENGTH_LONG).show();
						new GetDataTask(TOP_REFRESH).execute();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ExpandableListView> refreshView) {
//						Toast.makeText(CalendarListActivity.this, "Bottom",
//								Toast.LENGTH_LONG).show();
						new GetDataTask(BOTTOM_REFRESH).execute();
					}
				});
		

	}

	private void initGlobalData() {
		dbHelper = new TodoDbAdapter(this);
		prayEveryday = (PrayInEveryday) getApplicationContext();
		backToTime(Calendar.getInstance());

	}

	private class GetDataTask extends
			AsyncTask<Void, Void, Map<String, Object>> {
		private int updateType;

		private GetDataTask(int updateType) {
			this.updateType = updateType;
		}

		@Override
		protected Map<String, Object> doInBackground(Void... params) {
			switch (updateType) {
			case TOP_REFRESH:
				mCalendar.add(Calendar.MONTH, -1);
				break;
			case BOTTOM_REFRESH:
				mCalendar.add(Calendar.MONTH, 1);
				break;
			default:
				break;
			}
			dbHelper.open();
			CalendarManager calendarManager = CalendarManager.getInstance();
			Map<String, Object> returnData = calendarManager
					.getCalendarListByMonth(mCalendar.getTimeInMillis());
			dbHelper.close();
			return returnData;
		}

		@Override
		protected void onPostExecute(Map<String, Object> diaryListOfMonth) {

			dailyListAdapter = new DailyListAdapter(CalendarListActivity.this,
					diaryListOfMonth, prayEveryday, mCalendar);

			dailyListAdapter.notifyDataSetChanged();
			mListView.setAdapter(dailyListAdapter);
			setTitle(PublicFunction.formatDateYYYYMM(mCalendar
					.getTimeInMillis()));
			if (dailyListAdapter.getGroupCount() != 0) {
//				mListView.expandGroup(CalendarManager.getInstance().getOpen());
				mListView.expandGroup(0);
			}
			// Call onRefreshComplete when the list has been refreshed.
			mListDiary.onRefreshComplete();

			super.onPostExecute(diaryListOfMonth);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.calendar_select_time:
			DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarListActivity.this, new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					mCalendar.set(Calendar.YEAR, year);
					mCalendar.set(Calendar.MONTH, monthOfYear);
					mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					backToTime(mCalendar);
				}
			}, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
			datePickerDialog.show();
			DatePicker dp = findDatePicker((ViewGroup) datePickerDialog.getWindow().getDecorView());  
			if (dp != null) {  
			    ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);  
			} 
			break;
		case R.id.calendar_back:
			backToTime(Calendar.getInstance());
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	private DatePicker findDatePicker(ViewGroup group) {  
        if (group != null) {  
            for (int i = 0, j = group.getChildCount(); i < j; i++) {  
                View child = group.getChildAt(i);  
                if (child instanceof DatePicker) {  
                    return (DatePicker) child;  
                } else if (child instanceof ViewGroup) {  
                    DatePicker result = findDatePicker((ViewGroup) child);  
                    if (result != null)  
                        return result;  
                }  
            }  
        }  
        return null;  
    } 
}
