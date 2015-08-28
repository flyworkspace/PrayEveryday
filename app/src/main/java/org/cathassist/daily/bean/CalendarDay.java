package org.cathassist.daily.bean;

import org.cathassist.daily.provider.EnumManager;
import org.cathassist.daily.provider.EnumManager.rank_t;

import android.database.Cursor;

public class CalendarDay {
	private long id;
	private String date;
	private String chineseDate;
	private int dayType;
	private String summary = "";
	private String festival = "";
	private int memorableDay;
	private String solarTerms = "";
	private String holiday = "";
	private String color = "";

	public CalendarDay() {

	}

	public CalendarDay(long id, String date, int dayType, String summary,
			String festival, int memorableDay, String solarTerms,
			String holiday, String color, String chineseDate) {
		super();
		this.id = id;
		this.date = date;
		this.dayType = dayType;
		this.summary = summary;
		this.festival = festival;
		this.memorableDay = memorableDay;
		this.solarTerms = solarTerms;
		this.holiday = holiday;
		this.color = color;
		this.chineseDate = chineseDate;
	}

	public CalendarDay(Cursor cursor) {
		this.id = cursor.getLong(0);
		this.date = cursor.getString(1);
		this.dayType = cursor.getInt(2);
		this.summary = cursor.getString(3);
		this.festival = cursor.getString(4);
		this.memorableDay = cursor.getInt(5);
		this.solarTerms = cursor.getString(6);
		this.holiday = cursor.getString(7);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDayType() {
		return dayType;
	}

	public void setDayType(int dayType) {
		this.dayType = dayType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public int getMemorableDay() {
		return memorableDay;
	}

	public void setMemorableDay(int memorableDay) {
		this.memorableDay = memorableDay;
	}

	public String getSolarTerms() {
		return solarTerms;
	}

	public void setSolarTerms(String solarTerms) {
		this.solarTerms = solarTerms;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getChineseDate() {
		return chineseDate;
	}

	public void setChineseDate(String chineseDate) {
		this.chineseDate = chineseDate;
	}

	public void set(rank_t rank, String value) {
		switch (rank.getValue()) {
		case EnumManager.WEEKDAY_VALUE:
			if (summary == null || summary.equals("")) {
				this.summary = value;
			}
			break;
		case EnumManager.COMMEMORATION_VALUE:
			break;
		case EnumManager.OPTIONAL_VALUE:
			this.festival += value;
			break;
		case EnumManager.MEMORIAL_VALUE:
			this.festival += value;
			this.memorableDay = 1;
			break;
		case EnumManager.FEAST_VALUE:
			this.summary = value;
			break;
		case EnumManager.SUNDAY_VALUE:
			if (summary == null || summary.equals("")) {
				this.summary = value;
			}
			break;
		case EnumManager.LORD_VALUE:
			this.summary = value;
			break;
		case EnumManager.ASHWED_VALUE:
			// this.summary = value;
			break;
		case EnumManager.HOLYWEEK_VALUE:
			this.summary = value;
			break;
		case EnumManager.TRIDUUM_VALUE:
			this.summary = value;
			break;
		case EnumManager.SOLEMNITY_VALUE:
			this.summary = value;
			break;
		default:
			break;
		}
	}
	
}
