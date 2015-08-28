package org.cathassist.daily.calendar;

public enum season_t
{
	   ORDINARY(1),
	   ADVENT(2),
	   CHRISTMAS(3),
	   LENT(4),
	   EASTER(5),
	   PASCHAL(6);	/* Ash Wed., Holy Week & Easter Octave 	*/
	   

	   public static season_t valueOf(int v)
	   {
		   switch(v)
			{
			case 1:
				return ORDINARY;
			case 2:
				return ADVENT;
			case 3:
				return CHRISTMAS;
			case 4:
				return LENT;
			case 5:
				return EASTER;
			case 6:
				return PASCHAL;
			}
			
			return null;
	   }
	   
	   private int val;
	   
	   // 构造方法
	   private season_t(int n)
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
			case 1:
				return "常年期";
			case 2:
				return "将临期";
			case 3:
				return "圣诞期";
			case 4:
				return "四旬期";
			case 5:
				return "复活期";
			case 6:
				return "逾越节";
			}
			
			return "Not define";
	   }
}
