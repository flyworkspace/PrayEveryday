package org.cathassist.daily.calendar;

import java.util.*;
import java.util.Map.Entry;

import org.cathassist.daily.bean.CalendarDay;
import org.cathassist.daily.provider.EnumManager.rank_t;

import com.google.common.collect.HashMultimap;

public class LiturgicDay extends Date {
	private static String numStr[] = { "十", "一", "二", "三", "四", "五", "六", "七",
			"八", "九", "十", };

	public static String getChineseNumStr(int n) {
		if (n < 1 || n > 39)
			return "Not define";
		if (n <= 10) {
			return numStr[n];
		} else if (n < 20) {
			return "十" + numStr[n % 10];
		} else if (n < 30) {
			return "廿" + numStr[n % 10];
		} else if (n < 40) {
			if (n == 30)
				return "三十";
			else
				return "三十" + numStr[n % 10];
		}

		return "Not define";
	}

	private season_t season = season_t.ORDINARY; // Advent, Lent, Ordinary, etc.
	private int weekOfSeason; // Week of season
	private HashMultimap<rank_t, CellInfo> mapCells = HashMultimap.create();

	LiturgicDay() {
		super();
	}

	LiturgicDay(Date d) {
		super(d);
	}

	LiturgicDay(int year, int month, int day) {
		super(year, month, day);
		season = season_t.ORDINARY;
		mapCells = HashMultimap.create();
	}

	public season_t getSeason() {
		return season;
	}

	public void setSeason(season_t s) {
		season = s;
	}

	// 获取礼仪年第N主日
	public int getWeekOfSeason() {
		return weekOfSeason;
	}

	// 设置礼仪年第N主日
	public void setWeekOfSeason(int w) {
		weekOfSeason = w;
	}

	public color_t getColor() {
		color_t c = color_t.NOCOLOR;

		Iterator<Entry<rank_t, CellInfo>> iter = mapCells.entries().iterator();
		while (iter.hasNext()) {
			Entry<rank_t, CellInfo> entry = (Entry<rank_t, CellInfo>) iter
					.next();
			if (entry.getValue().color != color_t.NOCOLOR) {
				c = entry.getValue().color;
			}
		}

		return c;
	}

	List<CellInfo> getCellInfos() {
		List<CellInfo> cells = new ArrayList<CellInfo>();

		Iterator<Entry<rank_t, CellInfo>> iter = mapCells.entries().iterator();
		while (iter.hasNext()) {
			Entry<rank_t, CellInfo> entry = (Entry<rank_t, CellInfo>) iter
					.next();
			cells.add(0, entry.getValue());
		}

		return cells;
	}

	void appendCell(CellInfo c) {
		mapCells.put(c.rank, c);
	}

	void appendCell(rank_t r, color_t c, String cele) {
		CellInfo cell = new CellInfo(r, c, cele);
		appendCell(cell);
	}

	String toWeekdayString() {
		if (dayOfWeek() == day_t.SUN) {
			return String.format("%s第%s主日", season.toString(),
					getChineseNumStr(weekOfSeason));
		} else {
			return String.format("%s第%s周(%s)", season.toString(),
					getChineseNumStr(weekOfSeason), dayOfWeek().toString());
		}

	}

	String toLiturgicString() {
		// 返回礼仪年日期对应的字符串，格式化输出本身
		String str = "日期\t:\t" + toString() + "\n颜色\t:\t"
				+ getColor().toString() + "\n节日:\n";

		Iterator<Entry<rank_t, CellInfo>> iter = mapCells.entries().iterator();
		while (iter.hasNext()) {
			Entry<rank_t, CellInfo> entry = (Entry<rank_t, CellInfo>) iter
					.next();
			str = str + "\t" + entry.getValue().celebration + "\n";
		}
		return str;
	}

	public CalendarDay getCalendarDay(CalendarDay calendarDay) {
		// mapCells.get(key)
		// Object[] cellInfos = mapCells.get(rank_t.WEEKDAY).toArray();
		// if (cellInfos!=null&&cellInfos.length!=0) {
		// calendarDay.setSummary(((CellInfo)cellInfos[0]).celebration);
		// } else {
		// Object[] cellObjects=mapCells.get(rank_t.SUNDAY).toArray();
		// if (cellObjects!=null&&cellObjects.length!=0) {
		// calendarDay.setSummary(((CellInfo)cellObjects[0]).celebration);
		// }
		// }
		Iterator<Entry<rank_t, CellInfo>> iter = mapCells.entries().iterator();
		while (iter.hasNext()) {
			Entry<rank_t, CellInfo> entry = (Entry<rank_t, CellInfo>) iter
					.next();
			calendarDay.set(entry.getKey(), entry.getValue().celebration);
			// str = str+"\t"+entry.getValue().celebration+"\n";
		}
		calendarDay.setColor(getColor().toString());
		return calendarDay;
	}
}
