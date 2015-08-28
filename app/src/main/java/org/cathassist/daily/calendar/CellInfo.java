package org.cathassist.daily.calendar;

import org.cathassist.daily.provider.EnumManager.rank_t;

public class CellInfo
{
	public rank_t rank;                //优先级
	public color_t color;              //祭衣颜色
	public String celebration;    		//节日
    
	CellInfo(rank_t r,color_t c,String cel)
	{
		rank = r;
		color = c;
		celebration = cel;
	}
}
