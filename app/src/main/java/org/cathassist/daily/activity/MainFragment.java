package org.cathassist.daily.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.cathassist.daily.R;
import org.cathassist.daily.bean.CalendarDay;
import org.cathassist.daily.database.TodoDbAdapter;
import org.cathassist.daily.provider.CalendarManager;
import org.cathassist.daily.util.PublicFunction;

import com.spreada.utils.chinese.ZHConverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public final class MainFragment extends Fragment implements OnClickListener {
	private TextView txtDayTitle, txtDate, txtSolarTerms, txtHoliday,
			txtFestival, txtDayNature, txtColor;
	private TodoDbAdapter dbHelper;
	private CalendarDay calendarDay;
	private String dateString;

	private Button btnLaudes, btnHoramedia, btnMatutinum, btnVesperae,
			btnCompletorium, btnMass, btnCalendar;

	public static MainFragment newInstance(String dateString) {
		MainFragment fragment = new MainFragment();
		fragment.dateString = dateString;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// if ((savedInstanceState != null)
		// && savedInstanceState.containsKey(KEY_CONTENT)) {
		// mContent = savedInstanceState.getString(KEY_CONTENT);
		// }
		View view = inflater.inflate(R.layout.main_view_page_layout, container,
				false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// getActivity().findViewById(R.id.contacts_button_deleteBanned).setOnClickListener(this);
		// getActivity().findViewById(R.id.contacts_button_syncContacts).setOnClickListener(this);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		findViews(view);
		setListener();

		dbHelper = new TodoDbAdapter(getActivity());
		dbHelper.open();
		if (savedInstanceState != null
				&& savedInstanceState.getString("dateString") != null) {
			if (dateString == null) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
			}
			dateString = savedInstanceState.getString("dateString");
		}
		loadData();
	}

	private void findViews(View view) {
		txtDayTitle = (TextView) view.findViewById(R.id.txt_daytitle);
		txtDate = (TextView) view.findViewById(R.id.txt_date);
		txtSolarTerms = (TextView) view.findViewById(R.id.txt_solarterms);
		txtHoliday = (TextView) view.findViewById(R.id.txt_holiday);
		txtFestival = (TextView) view.findViewById(R.id.txt_festival);
		txtDayNature = (TextView) view.findViewById(R.id.txt_daynature);
		btnLaudes = (Button) view.findViewById(R.id.btn_laudes);
		btnHoramedia = (Button) view.findViewById(R.id.btn_horamedia);
		btnMatutinum = (Button) view.findViewById(R.id.btn_matutinum);
		btnVesperae = (Button) view.findViewById(R.id.btn_vesperae);
		btnCompletorium = (Button) view.findViewById(R.id.btn_completorium);
		btnMass = (Button) view.findViewById(R.id.btn_mass);
		btnCalendar = (Button) view.findViewById(R.id.btn_calendar);
		txtColor = (TextView) view.findViewById(R.id.txt_color);
	}

	private void setListener() {
		btnLaudes.setOnClickListener(this);
		btnHoramedia.setOnClickListener(this);
		btnMatutinum.setOnClickListener(this);
		btnVesperae.setOnClickListener(this);
		btnCompletorium.setOnClickListener(this);
		btnMass.setOnClickListener(this);
		btnCalendar.setOnClickListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	private void setTextColor(int dayType, boolean isHoliday) {
		switch (dayType) {
		case 0:
			break;
		case 1:
			int colorRed = getResources().getColor(
					android.R.color.holo_red_dark);
			txtDayTitle.setTextColor(colorRed);
			txtFestival.setTextColor(colorRed);
			txtDayNature.setTextColor(colorRed);
			break;
		case 2:
			int colorBlue = getResources().getColor(
					android.R.color.holo_blue_dark);
			txtDayTitle.setTextColor(colorBlue);
			txtFestival.setTextColor(colorBlue);
			txtDayNature.setTextColor(colorBlue);
			break;
		default:
			break;
		}
		if (isHoliday) {
			int colorRed = getResources().getColor(
					android.R.color.holo_red_dark);
			txtHoliday.setTextColor(colorRed);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("dateString", dateString);
	}

	public void setDate(String date){
		dateString = date;
		loadData();
	}

	private void loadData(){
		txtDate.setText(dateString);
		Log.e("dateString", dateString);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		long date = 0;
		try {
			date = f.parse(dateString).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendarDay = CalendarManager.getInstance().getCalendar(date);
		dbHelper.close();
		txtDayTitle.setText(dateString);
		if (calendarDay != null) {
			txtDayTitle.setText(calendarDay.getSummary());
			txtSolarTerms.setText(calendarDay.getSolarTerms());
			txtHoliday.setText(calendarDay.getHoliday());
			txtFestival.setText(calendarDay.getFestival());
			txtDayNature.setText(PublicFunction.getDayNatureString(
					getActivity(), calendarDay.getMemorableDay()));
			txtColor.setText(calendarDay.getColor());
			ZHConverter converter = ZHConverter
					.getInstance(ZHConverter.SIMPLIFIED);
			// txtBible.setText();
			// txtPray.setText();
			setTextColor(calendarDay.getDayType(),
					calendarDay.getHoliday() != null
							&& !calendarDay.getHoliday().equals(""));

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_laudes:
			mListener.onArticleSelected(1);
			break;
		case R.id.btn_horamedia:
			mListener.onArticleSelected(2);
			break;
		case R.id.btn_matutinum:
			mListener.onArticleSelected(3);
			break;
		case R.id.btn_vesperae:
			mListener.onArticleSelected(4);
			break;
		case R.id.btn_completorium:
			mListener.onArticleSelected(5);
			break;
		case R.id.btn_mass:
			mListener.onArticleSelected(6);
			break;
		case R.id.btn_calendar:
//			if (PublicFunction
//					.isAvilible(getActivity(), "org.cathassist.bible")) {
//				Intent intent = new Intent();
//				intent.setClassName("org.cathassist.bible",
//						"org.cathassist.bible.MainActivity");
//				startActivity(intent);
//			} else {
//				Uri uri = Uri.parse("market://details?id=org.cathassist.bible");
//				Intent it = new Intent(Intent.ACTION_VIEW, uri);
//				startActivity(it);
//			}

			Intent intentCalendar = new Intent(getActivity(),
					CalendarListActivity.class);
			startActivity(intentCalendar);
			break;
		
		default:
			break;
		}
	}

	OnArticleSelectedListener mListener;

	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		try {
			mListener = (OnArticleSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implementOnArticleSelectedListener");
		}
	}

	public interface OnArticleSelectedListener {
		void onArticleSelected(int page);
	}
}
