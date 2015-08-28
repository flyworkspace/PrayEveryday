package org.cathassist.daily.calendar;

public enum day_t
{
   SUN(0), MON(1), TUE(2), WED(3),
   THU(4), FRI(5), SAT(6);
   
   public static day_t valueOf(int v)
   {
	   switch(v)
		{
		case 0:
			return SUN;
		case 1:
			return MON;
		case 2:
			return TUE;
		case 3:
			return WED;
		case 4:
			return THU;
		case 5:
			return FRI;
		case 6:
			return SAT;
		}
		
		return null;
   }
   
   private int val;
   
   // 构造方法
   private day_t(int n)
   {
	   val = n;
   }
   
   public int value()
   {
	   return val;
   }
   
   @Override
   public String toString()
   {
		switch(val)
		{
		case 0:
			return "星期日";
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		}
		
		return "Not define";
   }
};