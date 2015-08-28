package org.cathassist.daily.calendar;

public enum color_t
{
	   NOCOLOR(0), GREEN(1), WHITE(2), RED(3), PURPLE(4), ROSE(5), BLACK(6);

	   public static color_t valueOf(int v)
	   {
		   switch(v)
			{
			case 0:
				return NOCOLOR;
			case 1:
				return GREEN;
			case 2:
				return WHITE;
			case 3:
				return RED;
			case 4:
				return PURPLE;
			case 5:
				return ROSE;
			case 6:
				return BLACK;
			}
			
			return null;
	   }
	   
	   private int val;
	   
	   // 构造方法
	   private color_t(int n)
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
				return "无";
			case 1:
				return "绿";
			case 2:
				return "白";
			case 3:
				return "红";
			case 4:
				return "紫";
			case 5:
				return "玫";
			case 6:
				return "黑";
			}
			
			return "Not define";
	   }
}
