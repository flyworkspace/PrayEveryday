package org.cathassist.daily.calendar;
import java.util.HashMap;

public class CalendarMain
{
	private static HashMap<Integer,LiturgicYear> mapLiturgicYear;
		
	public static LiturgicDay getLiturgicDay(Date d)
	{
	    int year = d.year();
	    LiturgicYear pYear = mapLiturgicYear.get(year);
	    if(pYear == null)
	    {
	        pYear = new LiturgicYear(year);
	        mapLiturgicYear.put(year, pYear);
	    }
	
	    return pYear.getLiturgicDay(d);
	}
	
	public static LiturgicDay getLiturgicDay(int year,int month,int day)
	{
	    return getLiturgicDay(new Date(year,month,day));
	}
	
	public static void initCalendar()
	{
		//初始化礼仪年数据
		mapLiturgicYear = new HashMap<Integer,LiturgicYear>();
		LiturgicYear.initPropers();
	}
	
	public static void releaseCalendar()
	{
		//java不需要释放内存，可以不调用
	    mapLiturgicYear.clear();
	}
	
	
	//打印某年全年的日子
	static void printYear(int year)
	{
	    Date d = new Date(year,1,1);
	    int countDay = Date.isLeapYear(year) ? 366 : 365;
	    for(int i=0;i<countDay;++i)
	    {
	        Date t = d.addDays(i);
	        LiturgicDay ld = getLiturgicDay(t.year(), t.month(), t.day());
			System.out.print(ld.toLiturgicString()+"\n");
	    }
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	    //初始化Calendar，只需在程序入口调用一次
		initCalendar();

		long begin = System.currentTimeMillis();

	    //示例代码,2014年的输出
	    printYear(2014);

		System.out.print("Used time(ms):"+Long.toString(System.currentTimeMillis()-begin)+"\n");
	    
	    /*
		for(int i=2000;i<2010;++i)
		{
			LiturgicYear y(i);
			cout<<y.toString()<<endl;
		}
	    */
	    
	    //释放Calendar资源，只需在程序结束时调用一次（放置内存泄露）
		releaseCalendar();
	}
}
