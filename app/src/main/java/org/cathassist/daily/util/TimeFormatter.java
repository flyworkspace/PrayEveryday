package org.cathassist.daily.util;

import java.util.Calendar;

public class TimeFormatter {
	public static String formatDateYYYYMMDD(long millisecond) {
		return (String) android.text.format.DateFormat.format("yyyy-MM-dd",
				millisecond);
	}

	public static String formatDateYYYYMMMDD(long millisecond) {
		return (String) android.text.format.DateFormat.format("yyyy  MMM  dd",
				millisecond);
	}

	public static String formatDateYYYYMM(long millisecond) {
		return (String) android.text.format.DateFormat.format("yyyy-MMM",
				millisecond);
	}

	public static String formatDateHHMM(long millisecond) {
		return (String) android.text.format.DateFormat.format("hh:mm a",
				millisecond);
	}

	public static String formatDateYYYY(long millisecond) {
		return (String) android.text.format.DateFormat.format("yyyy",
				millisecond);
	}

	public static String formatDateMMM(long millisecond) {
		return (String) android.text.format.DateFormat.format("MMM",
				millisecond);
	}

	public static String formatDateDD(long millisecond) {
		return (String) android.text.format.DateFormat
				.format("dd", millisecond);
	}

	public static String formatDateWeek(long millisecond) {
		return (String) android.text.format.DateFormat.format("EEE",
				millisecond);
	}

	public static String formatDateYYYYMMMDDEEEHHMM(long millisecond) {
		return formatDateYYYYMMMDD(millisecond) + "  "
				+ formatDateWeek(millisecond) + "  "
				+ formatDateHHMM(millisecond);
	}

	public static long getMinTimeOfMonth(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1, 0, 0, 0);
		return calendar.getTimeInMillis();
	}

	public static long getMaxTimeOfMonth(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return calendar.getTimeInMillis();
	}

	public static String formatSourceName(long miliSeconds) {
		return (String) android.text.format.DateFormat.format(
				"yyyyMMdd_HHmmss", miliSeconds);
	}

	public static boolean isBetweenInTimes(long startTime, long endTime,
			long time) {
		return time >= startTime && time <= endTime;
	}
}
