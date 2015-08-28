package org.cathassist.daily.provider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cathassist.daily.PrayInEveryday;
import org.cathassist.daily.R;
import org.cathassist.daily.bean.CalendarDay;
import org.cathassist.daily.util.PublicFunction;
import org.cathassist.daily.util.TimeFormatter;

import com.umeng.analytics.c;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DailyListAdapter extends BaseExpandableListAdapter {
	private Context context;
	private LayoutInflater mInflater;
	List<Integer> groupData = new ArrayList<Integer>();
	List<List<CalendarDay>> childData = new ArrayList<List<CalendarDay>>();
	private Calendar nowTime, pageTime;
	private PrayInEveryday prayEveryday;

	public DailyListAdapter(Context context, Map<String, Object> data,
			PrayInEveryday prayEveryday, Calendar calendar) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.groupData = (List<Integer>) data.get("groupData");
		this.childData = (List<List<CalendarDay>>) data.get("childData");
		this.prayEveryday = prayEveryday;
		nowTime = Calendar.getInstance();
		this.pageTime = calendar;
	}

	public final class ViewHolder {
		TextView txtDay, txtYear, txtMonth, txtWeek, txtTitle, txtColor,
				txtFestival, txtMemorableDay, txtHoliday, txtSolarTerms, txtChineseDate;
	}

	@Override
	public int getGroupCount() {
		return groupData.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		Log.e("childData count=", childData.size() + "");
		Log.e("groupPosition", groupPosition + "");
		return childData.size() == 0 ? childData.size() : childData.get(
				groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupData.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childData.get(groupPosition).get(childPosition).getId();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(0);
		TextView textView = getTextView("     "
				+ getGroupText(Integer.parseInt(getGroup(groupPosition)
						.toString())));
		ll.addView(textView);
		ll.setBackgroundColor(context.getResources().getColor(R.color.white2));
		return ll;
	}

	private String getGroupText(int weekOfMonth) {
		String weekString = "";
		switch (weekOfMonth) {
		case 1:
			weekString = context.getString(R.string.first_week);
			break;
		case 2:
			weekString = context.getString(R.string.second_week);
			break;
		case 3:
			weekString = context.getString(R.string.third_week);
			break;
		case 4:
			weekString = context.getString(R.string.fourth_week);
			break;
		case 5:
			weekString = context.getString(R.string.fifth_week);
			break;
		case 6:
			weekString = context.getString(R.string.sixth_week);
			break;
		default:
			break;
		}
		if (TimeFormatter.isBetweenInTimes(
				TimeFormatter.getMinTimeOfMonth(nowTime.getTimeInMillis()),
				TimeFormatter.getMaxTimeOfMonth(nowTime.getTimeInMillis()),
				pageTime.getTimeInMillis())
				&& nowTime.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
			weekString += " ( " + context.getString(R.string.this_week) + " ) ";
		}
		return weekString;
	}

	TextView getTextView(String showText) {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, 64);
		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setPadding(36, 0, 0, 0);
		textView.setTextSize(20);
		textView.setTextColor(Color.GRAY);
		textView.setText(showText);
		return textView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.diarylist_item, null);
			holder = new ViewHolder();
			holder.txtDay = (TextView) convertView.findViewById(R.id.txt_day);
			holder.txtYear = (TextView) convertView.findViewById(R.id.txt_year);
			holder.txtMonth = (TextView) convertView
					.findViewById(R.id.txt_month);
			holder.txtWeek = (TextView) convertView.findViewById(R.id.txt_week);
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.txt_title);
			holder.txtColor = (TextView) convertView
					.findViewById(R.id.txt_color);
			holder.txtFestival = (TextView) convertView
					.findViewById(R.id.txt_festival);
			holder.txtMemorableDay = (TextView) convertView
					.findViewById(R.id.txt_memorableDay);
			holder.txtHoliday = (TextView) convertView
					.findViewById(R.id.txt_holiday);
			// holder.txtWeather = (TextView) convertView
			// .findViewById(R.id.txt_weather);
			holder.txtSolarTerms = (TextView) convertView
					.findViewById(R.id.txt_solarterms);
			holder.txtChineseDate = (TextView) convertView.findViewById(R.id.txt_chinese_date);
			convertView.setTag(holder);// 绑定ViewHolder对象
		} else {
			holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
		}
		CalendarDay day = childData.get(groupPosition).get(childPosition);
		holder.txtTitle.setText(day.getSummary());
		holder.txtColor.setText(day.getColor());
		holder.txtFestival.setText(day.getFestival());
		holder.txtMemorableDay.setText(PublicFunction.getDayNatureString(
				context, day.getMemorableDay()));
		holder.txtHoliday.setText(day.getHoliday());
		holder.txtChineseDate.setText(day.getChineseDate());
		// holder.txtWeather.setText(diaryApp.getWeatherById(diary.getWeather()));
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		long time = 0;
		try {
			time = f.parse(day.getDate()).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.txtDay.setText(TimeFormatter.formatDateDD(time));
		holder.txtYear.setText(TimeFormatter.formatDateYYYY(time));
		holder.txtMonth.setText(TimeFormatter.formatDateMMM(time));
		holder.txtWeek.setText(TimeFormatter.formatDateWeek(time));
		holder.txtSolarTerms.setText(day.getSolarTerms());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
