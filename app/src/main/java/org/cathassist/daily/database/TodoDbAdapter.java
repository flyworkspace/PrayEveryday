package org.cathassist.daily.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cathassist.daily.bean.CalendarDay;
import org.cathassist.daily.bean.DateBean;
import org.cathassist.daily.bean.DayContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoDbAdapter {
	public static final Object[] DBLOCK = new Object[0]; // 防止产生死锁
	// public static final String KEY_ROWID = "_id";
	// public static final String KEY_CATEGORY = "category";
	// public static final String KEY_SUMMARY = "summary";
	// public static final String KEY_DESCRIPTION = "description";
	// private static final String DATABASE_TABLE = "todo";
	private Context context;
	private SQLiteDatabase database,readDatebase;
	private DataBaseOpenHelper dbHelper;

	public TodoDbAdapter(Context context) {
		this.context = context;
	}

	public TodoDbAdapter open() throws SQLException {
		dbHelper = new DataBaseOpenHelper(context);
		database = dbHelper.getWritableDatabase();
		readDatebase = dbHelper.getReadableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * ------------------------------Calendar表----------------------
	 */
	public static final String TABLE_CALENDAR = "calendar";
	public static final String CALENDAR_ID = "_calendarid"; // 0
	public static final String CALENDAR_DATA = "date"; // 1
	public static final String CALENDAR_DAYTYPE = "daytype"; // 2
	public static final String CALENDAR_SUMMARY = "summary"; // 3
	public static final String CALENDAR_FESTIVAL = "festival"; // 4
	public static final String CALENDAR_ISMEMORABLEDAY = "ismemorableday"; // 5
	public static final String CALENDAR_SOLARTERMS = "solarterms";// 6
	public static final String CALENDAR_HOLIDAY = "holiday";// 7

	static final String[] CALENDAR_QUERY_COLUMNS = { CALENDAR_ID,
			CALENDAR_DATA, CALENDAR_DAYTYPE, CALENDAR_SUMMARY,
			CALENDAR_FESTIVAL, CALENDAR_ISMEMORABLEDAY, CALENDAR_SOLARTERMS,
			CALENDAR_HOLIDAY};

	public static final String TABLE_BIBLEWORD_CREATE = "CREATE TABLE "
			+ TABLE_CALENDAR + " (" + CALENDAR_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + CALENDAR_DATA
			+ " DATE NOT NULL," + CALENDAR_DAYTYPE + " TINYINT NOT NULL,"
			+ CALENDAR_SUMMARY + " TEXT NOT NULL," + CALENDAR_FESTIVAL
			+ " TEXT," + CALENDAR_ISMEMORABLEDAY + " TINYINT NOT NULL,"
			+ CALENDAR_SOLARTERMS + " TEXT," + CALENDAR_HOLIDAY + " TEXT" + ");";

	public boolean insertCalendarDay(CalendarDay calendarDay) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(CALENDAR_DATA, calendarDay.getDate());
		contentValues.put(CALENDAR_DAYTYPE, calendarDay.getDayType());
		contentValues.put(CALENDAR_SUMMARY, calendarDay.getSummary());
		contentValues.put(CALENDAR_FESTIVAL, calendarDay.getFestival());
		contentValues.put(CALENDAR_ISMEMORABLEDAY,
				calendarDay.getMemorableDay());
		contentValues.put(CALENDAR_SOLARTERMS, calendarDay.getSolarTerms());
		contentValues.put(CALENDAR_HOLIDAY, calendarDay.getHoliday());
		try {
			synchronized (DBLOCK) {
				database.insert(TABLE_CALENDAR, null, contentValues);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public CalendarDay getCalendarDayByDate(String date) {
		try {
			synchronized (DBLOCK) {
				Cursor cursor = database.query(TABLE_CALENDAR,
						CALENDAR_QUERY_COLUMNS, CALENDAR_DATA + "='" + date
								+ "'", null, null, null, null);
				CalendarDay calendarDay = null;
				if (cursor != null && cursor.moveToFirst()) {
					calendarDay = new CalendarDay(cursor);
				}
				return calendarDay;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DateBean> getDateList() {
		List<DateBean> list = new ArrayList<DateBean>();
		try {
			synchronized (DBLOCK) {
				String sql = "SELECT " + CALENDAR_ID + "," + CALENDAR_DATA
						+ " FROM " + TABLE_CALENDAR
						+ " WHERE _calendarid IN (SELECT MAX(" + CALENDAR_ID
						+ ") FROM " + TABLE_CALENDAR + " group by "
						+ CALENDAR_DATA + ")" + " ORDER BY " + CALENDAR_DATA
						+ " ASC";
				Log.e("sql", sql);
				Cursor cursor = database.rawQuery(sql, null);
				if (cursor != null && cursor.moveToFirst()) {
					do {
						DateBean dateBean = new DateBean();
						dateBean.setId(cursor.getLong(0));
						dateBean.setDate(cursor.getString(1));
						list.add(dateBean);
					} while (cursor.moveToNext());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public void deleteOldEveryday(String date) {
		try {
			synchronized (DBLOCK) {
				String sql = "DELETE FROM " + TABLE_CALENDAR + " WHERE "+CALENDAR_DATA+"<='"
						+ date + "'";
				Log.e("sql", sql);
				database.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Object> getDiaryListBetweenTime(long begin, long end) {
		Cursor cursor = null;
		List<Integer> groupData = new ArrayList<Integer>();
		List<List<CalendarDay>> childData = new ArrayList<List<CalendarDay>>();
		try {
			synchronized (DBLOCK) {
				DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				String[] args = { String.valueOf(f.format(new Date(begin))), String.valueOf(f.format(new Date(end))) };
				cursor = readDatebase.query(TABLE_CALENDAR, CALENDAR_QUERY_COLUMNS, 
						CALENDAR_DATA + ">=? and " + CALENDAR_DATA + "<=?",
						args, null, null, null,
						null);
			}
			if (cursor != null && cursor.moveToFirst()) {
				int weekOfMonth = -1;
				List<CalendarDay> diaryList = new ArrayList<CalendarDay>();
				do {
					DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					long date = f.parse(cursor.getString(1)).getTime();
					Calendar c = Calendar.getInstance();
					c.setTimeInMillis(date);
					if (weekOfMonth != c.get(Calendar.WEEK_OF_MONTH)) {
						weekOfMonth = c.get(Calendar.WEEK_OF_MONTH);
						groupData.add(weekOfMonth);
						if (diaryList.size() > 0)
							childData.add(diaryList);
						diaryList = new ArrayList<CalendarDay>();
					}
					CalendarDay day = new CalendarDay(cursor);
					diaryList.add(day);
				} while (cursor.moveToNext());
				childData.add(diaryList);
			}
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("groupData", groupData);
			returnMap.put("childData", childData);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (cursor != null)
				cursor.close();
		}
	}
	// // public void deleteEvents(int eventid){
	// // synchronized (DBLOCK) {
	// // SQLiteDatabase db = this.getWritableDatabase();
	// // String delEvents = "UPDATE " + TABLE_EVENTS + " SET " + EVENT_ISDELETE
	// +
	// // "=1 WHERE " + event
	// // }
	// // }
	/**
	 * 
	 ----------------------------------------DayContent表--------------------
	 * -- -- --------------
	 */
	public static final String TABLE_DAYCONTENT = "daycontent";
	public static final String DAYCONTENT_ID = "_daycontentid";
	public static final String DAYCONTENT_DATE = "date";
	public static final String DAYCONTENT_TYPE = "type";
	public static final String DAYCONTENT_UPDATETIME = "updatetime";
	public static final String DAYCONTENT_CONTENT = "content";

	public static final String TABLE_DAYCONTENT_CREATE = "CREATE TABLE "
			+ TABLE_DAYCONTENT + "(" + DAYCONTENT_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DAYCONTENT_DATE
			+ " DATE NOT NULL," + DAYCONTENT_TYPE + " INTEGER NOT NULL,"
			+ DAYCONTENT_UPDATETIME + " TIMESTAMP NOT NULL,"
			+ DAYCONTENT_CONTENT + " TEXT NOT NULL" + ");";

	public long insertContent(DayContent dayContent) {
		ContentValues values = new ContentValues();
		values.put(DAYCONTENT_DATE, dayContent.getDate().toString());
		values.put(DAYCONTENT_TYPE, dayContent.getContentType());
		values.put(DAYCONTENT_UPDATETIME, dayContent.getUpdateTime().toString());
		values.put(DAYCONTENT_CONTENT, dayContent.getContent());
		synchronized (DBLOCK) {
			long dataColumns = database.insert(TABLE_DAYCONTENT, null, values);
			return dataColumns;
		}
	}

	public DayContent getContent(String date, int contentType) {
		try {
			synchronized (DBLOCK) {
				Cursor cursor = database.query(TABLE_DAYCONTENT,
						new String[] { DAYCONTENT_CONTENT }, DAYCONTENT_DATE
								+ "='" + date + "' and " + DAYCONTENT_TYPE
								+ "=" + contentType, null, null, null,
						DAYCONTENT_UPDATETIME + " DESC");
				DayContent dayContent = null;
				if (cursor != null && cursor.moveToFirst()) {
					dayContent = new DayContent();
					dayContent.setContent(cursor.getString(0));
				}
				return dayContent;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void deleteOldDayContent(String date) {
		try {
			synchronized (DBLOCK) {
				String sql = "DELETE FROM " + TABLE_DAYCONTENT + " WHERE "+DAYCONTENT_DATE+"<='"
						+ date + "'";
				Log.e("sql", sql);
				database.execSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOldData(String date){
		deleteOldEveryday(date);
		deleteOldDayContent(date);
	}
	// /**
	// * 关闭已经过时的提醒
	// *
	// * @return
	// */
	// public boolean closeHadPassedReminder() {
	// ContentValues reminderValues = new ContentValues();
	// reminderValues.put(REMINDER_STATUS, "0");
	// reminderValues.put(REMINDER_PREDICTENDTIME, System.currentTimeMillis());
	// try {
	// synchronized (DBLOCK) {
	// database.update(
	// TABLE_REMINDER,
	// reminderValues,
	// REMINDER_PREDICTENDTIME + "<"
	// + System.currentTimeMillis() + " AND "
	// + REMINDER_STATUS + "=1", null);
	// }
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }
	// }
	//
	// /**
	// * 更新Reminder
	// *
	// * @param reminder
	// * @return
	// */
	// public boolean updateReminder(Reminder reminder) {
	// ContentValues reminderValues = new ContentValues();
	// reminderValues.put(EVENT_ID, reminder.getEventID());
	// reminderValues.put(REMINDER_TYPE, reminder.getReminderType());
	// reminderValues.put(REMINDER_MESSAGE, reminder.getMessage());
	// reminderValues.put(REMINDER_STARTTIME, reminder.getStartTime());
	// reminderValues.put(REMINDER_PREDICTENDTIME,
	// reminder.getPredictendTime());
	// reminderValues.put(REMINDER_VIBERATE, reminder.isViberate());
	// reminderValues.put(REMINDER_STATUS, reminder.getStatus());
	// reminderValues.put(REMINDER_MUSICTYPE, reminder.getMusicUri());
	// reminderValues.put(REMINDER_MUSICURI, reminder.getMusicUri());
	// reminderValues.put(REMINDER_OTHER1, reminder.getOther1());
	// reminderValues.put(REMINDER_OTHER2, reminder.getOther2());
	// try {
	// synchronized (DBLOCK) {
	// database.update(TABLE_REMINDER, reminderValues, REMINDER_ID
	// + "=" + reminder.getReminderID(), null);
	// }
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }
	// }
	//
	// /**
	// * 查找所有的Reminder信息
	// *
	// * @return
	// */
	// public Cursor getReminderInfo() {
	// try {
	// synchronized (DBLOCK) {
	// return database.query(TABLE_REMINDER, null, null, null, null,
	// null, REMINDER_STATUS + " desc");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
}
