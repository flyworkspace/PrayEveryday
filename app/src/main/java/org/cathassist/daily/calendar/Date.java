package org.cathassist.daily.calendar;

public class Date
{
	class IntRef
	{
		IntRef(int v)
		{
			val = v;
		}
		
		void setVal(int v)
		{
			val = v;
		}
		
		int getVal()
		{
			return val;
		}
		
		private int val;
	}
	
	static final int FIRST_YEAR = -4713;
	static final int FIRST_MONTH = 1;
	static final int FIRST_DAY = 2;  // ### Qt 5: make FIRST_DAY = 1, by support jd == 0 as valid
	static final int SECS_PER_DAY = 86400;
	static final int MSECS_PER_DAY = 86400000;
	static final int SECS_PER_HOUR = 3600;
	static final int MSECS_PER_HOUR = 3600000;
	static final int SECS_PER_MIN = 60;
	static final int MSECS_PER_MIN = 60000;
	static final int JULIAN_DAY_FOR_EPOCH = 2440588; // result of julianDayFromGregorianDate(1970, 1, 1)
	
	static final byte monthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	

	public static int MinInt(int a,int b)
	{
		return (a < b) ? a : b;
	}

	static Date fixedDate(int y, int m, int d)
	{
		Date result = new Date(y, m, 1);
		result.setDate(y, m, MinInt(d, result.daysInMonth()));
		return result;
	}
	
	static int julianDayFromGregorianDate(int year, int month, int day)
	{
		// Gregorian calendar starting from October 15, 1582
		// Algorithm from Henry F. Fliegel and Thomas C. Van Flandern
		return (1461 * (year + 4800 + (month - 14) / 12)) / 4
			+ (367 * (month - 2 - 12 * ((month - 14) / 12))) / 12
			- (3 * ((year + 4900 + (month - 14) / 12) / 100)) / 4
			+ day - 32075;
	}

	static int julianDayFromDate(int year, int month, int day)
	{
		if (year < 0)
			++year;
	
		if (year > 1582 || (year == 1582 && (month > 10 || (month == 10 && day >= 15))))
		{
			return julianDayFromGregorianDate(year, month, day);
		}
		else if (year < 1582 || (year == 1582 && (month < 10 || (month == 10 && day <= 4))))
		{
			// Julian calendar until October 4, 1582
			// Algorithm from Frequently Asked Questions about Calendars by Claus Toendering
			int a = (14 - month) / 12;
			return (153 * (month + (12 * a) - 3) + 2) / 5
				+ (1461 * (year + 4800 - a)) / 4
				+ day - 32083;
		}
		else
		{
			// the day following October 4, 1582 is October 15, 1582
			return 0;
		}
	}
	
	static void getDateFromJulianDay(int julianDay, IntRef year, IntRef month, IntRef day)
	{
		int y, m, d;

		if (julianDay >= 2299161)
		{
			// Gregorian calendar starting from October 15, 1582
			// This algorithm is from Henry F. Fliegel and Thomas C. Van Flandern
			long ell, n, i, j;
			ell = (long)(julianDay) + 68569;
			n = (4 * ell) / 146097;
			ell = ell - (146097 * n + 3) / 4;
			i = (4000 * (ell + 1)) / 1461001;
			ell = ell - (1461 * i) / 4 + 31;
			j = (80 * ell) / 2447;
			d = (int)(ell - (2447 * j) / 80);
			ell = j / 11;
			m = (int)(j + 2 - (12 * ell));
			y = (int)(100 * (n - 49) + i + ell);
		}
		else
		{
			// Julian calendar until October 4, 1582
			// Algorithm from Frequently Asked Questions about Calendars by Claus Toendering
			julianDay += 32082;
			int dd = (4 * julianDay + 3) / 1461;
			int ee = julianDay - (1461 * dd) / 4;
			int mm = ((5 * ee) + 2) / 153;
			d = ee - (153 * mm + 2) / 5 + 1;
			m = mm + 3 - 12 * (mm / 10);
			y = dd - 4800 + (mm / 10);
			if (y <= 0)
				--y;
		}
		
	    year.setVal(y);
		month.setVal(m);
		day.setVal(d);
	}

	public static boolean isValid(int year,int month,int day)
	{
		if (year < FIRST_YEAR
				|| (year == FIRST_YEAR &&
				(month < FIRST_MONTH
				|| (month == FIRST_MONTH && day < FIRST_DAY)))
				|| year == 0) // there is no year 0 in the Julian calendar
				return false;

			// passage from Julian to Gregorian calendar
			if (year == 1582 && month == 10 && day > 4 && day < 15)
				return false;

			return (day > 0 && month > 0 && month <= 12) &&
				(day <= monthDays[month] || (day == 29 && month == 2 && isLeapYear(year)));
	}
	
	public static boolean isLeapYear(int year)
	{
		int y = year;
		if (y < 1582)
		{
			if ( y < 1)
			{  // No year 0 in Julian calendar, so -1, -5, -9 etc are leap years
				++y;
			}
			return y % 4 == 0;
		}
		else
		{
			return (y % 4 == 0 && y % 100 != 0) || y % 400 == 0;
		}
	}

	private int jd;	//JulianDay
	
	Date()
	{
		jd = 0;
	}
	Date(Date d)
	{
		jd = d.jd;
	}
	public Date(int year,int month,int day)
	{
		setDate(year, month, day);
	}


	public boolean isNull()
	{
		return jd <= 0;
	}
	public boolean isValid()
	{
		return !isNull();
	}
	
	public int year()
	{
		IntRef y = new IntRef(0),m = new IntRef(0),d = new IntRef(0);
		getDateFromJulianDay(jd, y, m, d);
		return y.getVal();
	}
	
	public int month()
	{
		IntRef y = new IntRef(0),m = new IntRef(0),d = new IntRef(0);
		getDateFromJulianDay(jd, y, m, d);
		return m.getVal();
	}
	
	public int day()
	{
		IntRef y = new IntRef(0),m = new IntRef(0),d = new IntRef(0);
		getDateFromJulianDay(jd, y, m, d);
		return d.getVal();
	}
	
	public day_t dayOfWeek()
	{
		int w = (jd % 7) + 1;
		
		return day_t.valueOf(w%7);
	}
	
	public int dayOfYear()
	{
		return jd - julianDayFromDate(year(), 1, 1) + 1;
	}
	
	public int daysInMonth()
	{
		IntRef y = new IntRef(0),m = new IntRef(0),d = new IntRef(0);
		getDateFromJulianDay(jd, y, m, d);
		if (m.getVal() == 2 && isLeapYear(y.getVal()))
			return 29;
		else
			return monthDays[m.getVal()];
	}
	
	public int daysInYear()
	{
		IntRef y = new IntRef(0),m = new IntRef(0),d = new IntRef(0);
		getDateFromJulianDay(jd, y, m, d);
		return isLeapYear(y.getVal()) ? 366 : 365;
	}
	
	public int weekNumber()
	{
	    if (!isValid())
	        return 0;
	
	    int year = year();
	    int yday = dayOfYear() - 1;
	    int wday = dayOfWeek().value();
	    int w;
	
	    for (;;)
		{
	        int len;
	        int bot;
	        int top;
	
	        len = isLeapYear(year) ? 366 : 365;
	        /*
	        ** What yday (-3 ... 3) does
	        ** the ISO year begin on?
	        */
	        bot = ((yday + 11 - wday) % 7) - 3;
	        /*
	        ** What yday does the NEXT
	        ** ISO year begin on?
	        */
	        top = bot - (len % 7);
	        if (top < -3)
	            top += 7;
	        top += len;
	        if (yday >= top) {
	            ++year;
	            w = 1;
	            break;
	        }
	        if (yday >= bot) {
	            w = 1 + ((yday - bot) / 7);
	            break;
	        }
	        --year;
	        yday += isLeapYear(year) ? 366 : 365;
	    }
	
		if(wday == 0)
			w+=1;
	    return w;
	}
	
	
	public boolean setYMD(int year, int month, int day)
	{
		int y = year;
		if (y <= 99)
			y += 1900;
		return setDate(y, month, day);
	}
	
	public boolean setDate(int year,int month,int day)
	{
		if (!isValid(year, month, day))
		{
			jd = 0;
		}
		else
		{
			jd = julianDayFromDate(year, month, day);
		}
		return jd != 0;
	}
	
	public void getDate(IntRef year, IntRef month, IntRef day)
	{
		getDateFromJulianDay(jd, year, month, day);
	}
	
	public Date addDays(int ndays)
	{
		Date d = new Date();
		// this is basically "d.jd = jd + ndays" with checks for integer overflow
		if (ndays >= 0)
			d.jd = (jd + ndays >= jd) ? jd + ndays : 0;
		else
			d.jd = (jd + ndays < jd) ? jd + ndays : 0;
		return d;
	}
	
	public Date addMonths(int nmonths)
	{
		if (!isValid())
			return new Date();
		if (nmonths==0)
			return this;

		IntRef yr = new IntRef(0),mr = new IntRef(0),dr = new IntRef(0);
		int old_y = 0, y = 0, m = 0, d = 0;
		getDateFromJulianDay(jd, yr, mr, dr);
		y = yr.getVal();
		m = mr.getVal();
		d = mr.getVal();
		old_y = y;
	
		boolean increasing = nmonths > 0;
	
		while (nmonths != 0)
		{
			if (nmonths < 0 && nmonths + 12 <= 0)
			{
				y--;
				nmonths+=12;
			}
			else if (nmonths < 0)
			{
				m+= nmonths;
				nmonths = 0;
				if (m <= 0)
				{
					--y;
					m += 12;
				}
			}
			else if (nmonths - 12 >= 0)
			{
				y++;
				nmonths -= 12;
			}
			else if (m == 12)
			{
				y++;
				m = 0;
			}
			else
			{
				m += nmonths;
				nmonths = 0;
				if (m > 12)
				{
					++y;
					m -= 12;
				}
			}
		}
	
		// was there a sign change?
		if ((old_y > 0 && y <= 0) ||
			(old_y < 0 && y >= 0))
			// yes, adjust the date by +1 or -1 years
			y += increasing ? +1 : -1;
	
		// did we end up in the Gregorian/Julian conversion hole?
		if (y == 1582 && m == 10 && d > 4 && d < 15)
			d = increasing ? 15 : 4;
	
		return fixedDate(y, m, d);
	}
	
	public Date addYears(int nyears)
	{
		if (!isValid())
			return new Date();

		IntRef yr = new IntRef(0),mr = new IntRef(0),dr = new IntRef(0);
		int y = 0,m = 0,d = 0;
		getDateFromJulianDay(jd, yr, mr, dr);
		y = yr.getVal();
		m = mr.getVal();
		d = mr.getVal();
	
		int old_y = y;
		y += nyears;
	
		// was there a sign change?
		if ((old_y > 0 && y <= 0) ||
			(old_y < 0 && y >= 0))
			// yes, adjust the date by +1 or -1 years
			y += nyears > 0 ? +1 : -1;
	
		return fixedDate(y, m, d);
	}
	
	public int daysTo(Date d)
	{
		return d.jd - jd;
	}

	@Override
	public String toString()
	{
		if (year() > 9999)
			return "";
	
		//格式化输出
		return String.format("%d-%02d-%02d", year(),month(),day());
	}
	

	boolean isEquel(Date other) { return jd == other.jd; }
	boolean isLess(Date other) { return jd < other.jd; }
	boolean isNotGreater(Date other) { return jd <= other.jd; }
	boolean isGreater(Date other) { return jd > other.jd; }
	boolean isNotLess(Date other) { return jd >= other.jd; }
}
