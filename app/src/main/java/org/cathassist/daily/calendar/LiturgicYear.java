package org.cathassist.daily.calendar;

import java.util.Set;

import org.cathassist.daily.provider.EnumManager.rank_t;

import com.google.common.collect.HashMultimap;

public class LiturgicYear
{
	private static HashMultimap<Integer,CellInfo> mapPropers;
	public static void initPropers()
	{
		mapPropers = HashMultimap.create();
		//初始化常用节日（日期固定）
		
		//1月
		mapPropers.put(101,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"天主之母节"));
		mapPropers.put(102,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣巴西略及圣额我略·纳齐安（主教、圣师)"));
		mapPropers.put(103,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"耶稣圣名节"));
		mapPropers.put(106,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"主显节"));
		mapPropers.put(107,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣雷孟（司铎）"));
		mapPropers.put(113,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣依拉略（主教、圣师）"));
		mapPropers.put(117,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣安东尼（院长）"));
		mapPropers.put(120,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣法比盎（教宗、殉道)"));
		mapPropers.put(120,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣巴斯弟盎（殉道）"));
		mapPropers.put(121,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣依搦斯（童贞、殉道)"));
		mapPropers.put(122,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣味增爵（执事、殉道）"));
		mapPropers.put(124,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣方济各·沙雷氏（主教、圣师）"));
		mapPropers.put(125,new CellInfo(rank_t.FEAST,color_t.WHITE,"圣保禄归化（宗徒）"));
		mapPropers.put(126,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣弟茂德与圣弟铎（主教）"));
		mapPropers.put(127,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣安琪拉·美利西（童贞）"));
		mapPropers.put(128,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣多玛斯·阿奎纳（司铎、圣师）"));
		mapPropers.put(131,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣若望·鲍思高（司铎）"));

	    //2月
		mapPropers.put(202,new CellInfo(rank_t.LORD,color_t.WHITE,"献主节"));
		mapPropers.put(203,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣巴拉斯（主教、殉道）"));
		mapPropers.put(203,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣安斯卡（主教）"));
		mapPropers.put(205,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣女亚加大（殉道）"));
		mapPropers.put(206,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣保禄三木及同伴（日本殉道者）"));
		mapPropers.put(208,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣热罗尼莫·艾明廉"));
		mapPropers.put(208,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"St. Josephine Margaret Bakhita（童贞）"));
		mapPropers.put(210,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣思嘉（童贞）"));
		mapPropers.put(211,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"露德圣母"));
		mapPropers.put(214,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣济利禄（隐修士）及圣默多狄（主教）"));
		mapPropers.put(217,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣母忠仆会七位会祖"));
		mapPropers.put(221,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯多禄·达弥盎（主教、圣师）"));
		mapPropers.put(222,new CellInfo(rank_t.FEAST,color_t.WHITE,"建立圣伯多禄宗座"));
		mapPropers.put(223,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣玻里加（主教、殉道）"));
	    
	    //3月
		mapPropers.put(304,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣加西弥禄（殉道）"));
		mapPropers.put(307,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣女伯尔都亚及圣女斐尼丝（殉道)"));
		mapPropers.put(308,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望由天主者（会士）"));
		mapPropers.put(309,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣女方济加·露雯（修女）"));
		mapPropers.put(317,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣博德（主教）"));
		mapPropers.put(318,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"耶路撒冷的圣济利禄（主教、圣师）"));
		mapPropers.put(319,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"大圣若瑟，童贞圣母玛利亚净配"));
		mapPropers.put(323,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣多利坡（主教）"));
		mapPropers.put(325,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"圣母领报"));
	    
	    //4月
		mapPropers.put(402,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣方济各保拉（隐修士）"));
		mapPropers.put(404,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣依西多禄（主教、圣师）"));
		mapPropers.put(405,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣味增爵斐洛（司铎）"));
		mapPropers.put(407,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣若翰·喇沙（司铎）"));
		mapPropers.put(411,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣达尼老（主教、殉道）"));
		mapPropers.put(413,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣玛尔定一世（教宗、殉道）"));
		mapPropers.put(421,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣安瑟莫（主教、圣师）"));
		mapPropers.put(423,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣乔治（殉道）；或布拉格的圣道博"));
		mapPropers.put(424,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣斐德理（司铎、殉道）"));
		mapPropers.put(425,new CellInfo(rank_t.FEAST,color_t.RED,"圣马尔谷（圣史）"));
		mapPropers.put(428,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯多禄·查纳（司铎、殉道）"));
		mapPropers.put(428,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣路易·蒙福（司铎）"));
		mapPropers.put(429,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣女加大利纳（贞女、圣师）"));
		mapPropers.put(430,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣庇护五世（教宗）"));
	    
	    //5月
		mapPropers.put(501,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若瑟劳工主保"));
		mapPropers.put(502,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣亚大纳修（主教、圣师）"));
		mapPropers.put(503,new CellInfo(rank_t.FEAST,color_t.RED,"圣斐理伯与圣雅各伯（宗徒）"));
		mapPropers.put(512,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣庞加爵（殉道）"));
		mapPropers.put(512,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣聂勒及圣亚基略（殉道）"));
		mapPropers.put(513,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"花地玛圣母"));
		mapPropers.put(514,new CellInfo(rank_t.FEAST,color_t.RED,"圣玛弟亚（宗徒）"));
		mapPropers.put(518,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望一世（教宗、殉道）"));
		mapPropers.put(520,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯尔纳定（司铎）"));
		mapPropers.put(521,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣多福·麦哲伦（司铎）及其他殉道者（殉道）"));
		mapPropers.put(522,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣李达（绝望主保）"));
		mapPropers.put(524,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"进教之佑圣母 "));
		mapPropers.put(525,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯达（司铎、圣师）"));
		mapPropers.put(525,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣额我略七世（教宗）"));
		mapPropers.put(525,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣玛达肋纳·巴斯（童贞）"));
		mapPropers.put(526,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣斐理伯·内利（司铎）"));
		mapPropers.put(527,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"坎特伯雷的圣奥思定（主教）"));
		mapPropers.put(531,new CellInfo(rank_t.FEAST,color_t.WHITE,"圣母访亲"));
	    
	    //6月
		mapPropers.put(601,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣犹思定（殉道）"));
		mapPropers.put(602,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣玛策林及圣伯多禄（殉道）"));
		mapPropers.put(603,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣嘉禄·安加及同伴（乌干达殉道者）"));
		mapPropers.put(605,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣玻尼法（主教、殉道）"));
		mapPropers.put(606,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣诺伯多（主教）"));
		mapPropers.put(609,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣义范（执事、圣师）"));
		mapPropers.put(611,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣巴尔纳伯（宗徒）"));
		mapPropers.put(613,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣安多尼·巴都亚（司铎、圣师）"));
		mapPropers.put(619,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣罗慕德（院长）"));
		mapPropers.put(621,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣类思·公撒格（会士）"));
		mapPropers.put(622,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣保林（主教）"));
		mapPropers.put(622,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·费舍（主教、殉道）及圣托马斯·莫尔（殉道）"));
		mapPropers.put(624,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"圣若翰洗者诞生"));
		mapPropers.put(627,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣济利禄（主教、圣师）"));
		mapPropers.put(628,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣爱任纽（主教、殉道）"));
		mapPropers.put(629,new CellInfo(rank_t.SOLEMNITY,color_t.RED,"圣伯多禄及圣保禄（宗徒）"));
		mapPropers.put(630,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"罗马教会初期殉道烈士"));
	    
	    //7月
	    mapPropers.put(703,new CellInfo(rank_t.FEAST,color_t.RED,"圣多默（宗徒）"));
	    mapPropers.put(704,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"葡萄牙的圣依撒伯尔"));
	    mapPropers.put(705,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣安多尼·匝加利（司铎）"));
	    mapPropers.put(706,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣玛利亚·葛莱蒂（童贞、殉道）"));
	    mapPropers.put(709,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"中华诸圣及圣赵荣思定神父（司铎、殉道）"));
	    mapPropers.put(711,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣本笃（院长）"));
	    mapPropers.put(713,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣皇亨利"));
	    mapPropers.put(714,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣加弥禄·弥理（司铎）"));
	    mapPropers.put(715,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣文德（主教、圣师）"));
	    mapPropers.put(716,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"加尔默罗山圣母"));
	    mapPropers.put(721,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"Saint Apollinaris（主教、殉道）"));
	    mapPropers.put(721,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣老楞佐·布林希（司铎、圣师）"));
	    mapPropers.put(722,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣玛利亚·玛达肋纳"));
	    mapPropers.put(723,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣妇彼济大（会士）"));
	    mapPropers.put(725,new CellInfo(rank_t.FEAST,color_t.RED,"圣雅各伯（宗徒）"));
	    mapPropers.put(726,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣若亚敬及圣亚纳（圣母双亲）"));
	    mapPropers.put(729,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣玛尔大"));
	    mapPropers.put(730,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯多禄·金言（主教、圣师）"));
	    mapPropers.put(731,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣依纳爵·罗耀拉（司铎）"));
	    
	    //8月
	    mapPropers.put(801,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣亚丰索（主教、圣师）"));
	    mapPropers.put(802,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣艾伯铎（司铎）"));
	    mapPropers.put(802,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣欧瑟伯（主教）"));
	    mapPropers.put(804,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣若翰·维雅纳（司铎）"));
	    mapPropers.put(805,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣母大殿奉献日"));
	    mapPropers.put(806,new CellInfo(rank_t.LORD,color_t.WHITE,"耶稣显圣容"));
	    mapPropers.put(807,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣嘉耶当（司铎）"));
	    mapPropers.put(807,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣思道二世（教宗）及同伴（殉道）"));
	    mapPropers.put(808,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣道明（司铎）"));
	    mapPropers.put(809,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"St. Teresa Benedicta of the Cross（童贞、殉道）"));
	    mapPropers.put(810,new CellInfo(rank_t.FEAST,color_t.RED,"圣老楞佐（执事、殉道）"));
	    mapPropers.put(811,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣女佳兰"));
	    mapPropers.put(812,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"Saint Jane Frances de Chantal（会士）"));
	    mapPropers.put(813,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣彭谦（教宗、殉道）及圣希玻里（司铎、殉道）"));
	    mapPropers.put(814,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣高比（司铎、殉道）"));
	    mapPropers.put(815,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"圣母蒙召升天节"));
	    mapPropers.put(816,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"匈牙利的圣斯德望国王"));
	    mapPropers.put(819,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·欧德（司铎）"));
	    mapPropers.put(820,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣伯尔纳铎（院长、圣师）"));
	    mapPropers.put(821,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣庇护十世（教宗）"));
	    mapPropers.put(822,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣母天后(圣母无玷圣心瞻礼)"));
	    mapPropers.put(823,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣罗撒"));
	    mapPropers.put(824,new CellInfo(rank_t.FEAST,color_t.RED,"圣巴尔多禄茂（宗徒）"));
	    mapPropers.put(825,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣路易国王；或圣若瑟·加拉桑（司铎）"));
	    mapPropers.put(827,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣莫尼加"));
	    mapPropers.put(828,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣奥思定（主教、圣师）"));
	    mapPropers.put(829,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣若翰洗者受难（殉道）"));
	    
	    //9月
	    mapPropers.put(903,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣额我略一世（教宗、圣师）"));
	    mapPropers.put(908,new CellInfo(rank_t.FEAST,color_t.WHITE,"圣母诞辰"));
	    mapPropers.put(909,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯多禄·高华（司铎）"));
	    mapPropers.put(912,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣母圣名节"));
	    mapPropers.put(913,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣金口若望（主教、圣师）"));
	    mapPropers.put(914,new CellInfo(rank_t.LORD,color_t.RED,"光荣十字圣架"));
	    mapPropers.put(915,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"痛苦圣母"));
	    mapPropers.put(916,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣高尔乃略（教宗、殉道）及圣西彼廉（主教、殉道）"));
	    mapPropers.put(917,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣罗伯·白敏（主教、圣师）"));
	    mapPropers.put(919,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣雅纳略（主教、殉道）"));
	    mapPropers.put(920,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣金大建及同伴（殉道）"));
	    mapPropers.put(921,new CellInfo(rank_t.FEAST,color_t.RED,"圣玛窦（宗徒、圣史）"));
	    mapPropers.put(923,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣比约神父（司铎）"));
	    mapPropers.put(926,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣葛斯及圣达弥盎（殉道）"));
	    mapPropers.put(927,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣云先（司铎）"));
	    mapPropers.put(928,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣文策老（殉道）"));
	    mapPropers.put(928,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣老楞佐·卢斯及同伴（殉道）"));
	    mapPropers.put(929,new CellInfo(rank_t.FEAST,color_t.WHITE,"圣弥额尔大天使、圣加俾额尔大天使、圣辣法尔大天使"));
	    mapPropers.put(930,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣热罗尼莫（司铎、圣师）"));
	    
	    //10月
	    mapPropers.put(1001,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"里修的圣德兰（小德兰）（童贞、圣师）"));
	    mapPropers.put(1002,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"护守天使"));
	    mapPropers.put(1004,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"亚西西的圣方济各"));
	    mapPropers.put(1006,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣博诺（司铎）"));
	    mapPropers.put(1007,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"玫瑰圣母"));
	    mapPropers.put(1009,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣德尼及同伴（殉道）"));
	    mapPropers.put(1009,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·良纳第（司铎）"));
	    mapPropers.put(1011,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣若望廿三世（教宗）"));
	    mapPropers.put(1014,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣加理斯多一世（教宗、殉道）"));
	    mapPropers.put(1015,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"亚维拉的圣德兰（大德兰）（童贞、圣师）"));
	    mapPropers.put(1016,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣玛加利大·亚拉高（童贞）"));
	    mapPropers.put(1016,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣赫德维（圣妇、会士）"));
	    mapPropers.put(1017,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣依纳爵·安提约基亚（主教、殉道）"));
	    mapPropers.put(1018,new CellInfo(rank_t.FEAST,color_t.RED,"圣路加（宗徒、圣史）"));
	    mapPropers.put(1019,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣若望·贝巴（司铎）及圣依撒格·饶觉（司铎）及同伴（殉道）"));
	    mapPropers.put(1020,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣十字保禄瞻礼（司铎）"));
	    mapPropers.put(1022,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣若望·保禄二世（教宗）"));
	    mapPropers.put(1023,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·嘉庇当（司铎）"));
	    mapPropers.put(1024,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣安多尼·加烈)（主教）"));
	    mapPropers.put(1028,new CellInfo(rank_t.FEAST,color_t.RED,"圣西满及圣犹达（宗徒）"));
	    
	    //11月
	    mapPropers.put(1101,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"诸圣节"));
	    mapPropers.put(1102,new CellInfo(rank_t.LORD,color_t.WHITE,"追思亡者"));
	    mapPropers.put(1103,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣玛尔定·包瑞斯（修士）"));
	    mapPropers.put(1104,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣嘉禄·鲍荣茂（主教）"));
	    mapPropers.put(1109,new CellInfo(rank_t.LORD,color_t.WHITE,"祝圣拉特朗大殿"));
	    mapPropers.put(1110,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"大圣良一世（教宗、圣师）"));
	    mapPropers.put(1111,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣玛尔定（都尔主教）"));
	    mapPropers.put(1112,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣若撒法（主教、殉道）"));
	    mapPropers.put(1115,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣亚尔伯（主教、圣师）"));
	    mapPropers.put(1116,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"苏格兰的圣玛加利大"));
	    mapPropers.put(1116,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣日多达（童贞）"));
	    mapPropers.put(1117,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"匈牙利的圣依撒伯尔（会士）"));
	    mapPropers.put(1118,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣伯多禄及圣保禄大殿奉献日"));
	    mapPropers.put(1121,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"献圣母于主堂"));
	    mapPropers.put(1122,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣则济利亚（童贞、殉道）"));
	    mapPropers.put(1123,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣克来孟一世（教宗、殉道）"));
	    mapPropers.put(1123,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣高隆邦（院长）"));
	    mapPropers.put(1124,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣陈安勇乐（司铎）及同伴（殉道）"));
	    mapPropers.put(1125,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"亚历山大的圣凯瑟琳（童贞、殉道）"));
	    mapPropers.put(1130,new CellInfo(rank_t.FEAST,color_t.RED,"圣安德肋（宗徒）"));
	    
	    //12月
	    mapPropers.put(1203,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣方济沙勿略（司铎）"));
	    mapPropers.put(1204,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·达玛森（司铎、圣师）"));
	    mapPropers.put(1206,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣尼各老（主教）"));
	    mapPropers.put(1207,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣安博（主教、圣师）"));
	    mapPropers.put(1208,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"圣母无玷始胎"));
	    mapPropers.put(1209,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·迭戈"));
	    mapPropers.put(1210,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"洛雷托圣母"));
	    mapPropers.put(1211,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣达玛稣一世（教宗）"));
	    mapPropers.put(1212,new CellInfo(rank_t.FEAST,color_t.WHITE,"瓜达卢佩圣母"));
	    mapPropers.put(1213,new CellInfo(rank_t.MEMORIAL,color_t.RED,"圣路济亚（贞女、殉道）"));
	    mapPropers.put(1214,new CellInfo(rank_t.MEMORIAL,color_t.WHITE,"圣十字若望（司铎、圣师）"));
	    mapPropers.put(1221,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣加尼修（司铎、圣师）"));
	    mapPropers.put(1223,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣若望·甘迪"));
	    mapPropers.put(1225,new CellInfo(rank_t.SOLEMNITY,color_t.WHITE,"吾主诞生日（圣诞节）"));
	    mapPropers.put(1226,new CellInfo(rank_t.FEAST,color_t.RED,"圣斯德望（首位殉道）"));
	    mapPropers.put(1227,new CellInfo(rank_t.FEAST,color_t.WHITE,"圣若望（宗徒、圣史）"));
	    mapPropers.put(1228,new CellInfo(rank_t.FEAST,color_t.RED,"诸圣婴孩"));
	    mapPropers.put(1229,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣多玛斯·百克（主教、殉道）"));
	    mapPropers.put(1231,new CellInfo(rank_t.OPTIONAL,color_t.NOCOLOR,"圣西物斯德一世（教宗）"));
	}

	static void releasePropers()
	{
		mapPropers.clear();
	}
	
	//礼仪年中的关键日期
    Date ep = new Date();         // Epiphany of the Lord     主显节
    Date bl = new Date();			// End of Christmas season	上一年圣诞期的结束日（主受洗日）
    Date aw = new Date();			// Ash Wednesday			圣灰礼仪周三（四旬期开始）
	Date easter = new Date();     // Easter sunday            复活节（主日）
	Date ps = new Date();			// Pentecost Sunday			圣神降临节（复活期结束）
	Date av = new Date();			// First Sunday of Advent	将临期第一主日，将临期的开始
	Date cm = new Date();		// Christmas				圣诞节（圣诞期的开始）
	private int year;
	
	public LiturgicYear(int y)
	{
		year = y;
		init();
	}
	
	private void init()
	{
		//主显节
		LiturgicDay t = new LiturgicDay(year,1,2);
		ep = t.addDays((7-t.dayOfWeek().value())%7);

		//主受洗日(圣诞期的结束)
		if(ep.day()>6)
		{
			bl = ep.addDays(1);
		}
		else
		{
			bl = ep.addDays(7);
		}

		//复活节
		{
			int y,c,n,k,i,j,l,m,d;
			y = year;

			c = y/100;
			n = y - 19*(y/19);
			k = (c - 17)/25;
			i = c - c/4 - (c-k)/3 + 19*n + 15;
			i = i - 30*(i/30);
			i = i - (i/28) * (1 - (i/28) * (29/(i+1)) * ((21 - n)/11));
			j = y + y/4 + i + 2 - c + c/4;
			j = j - 7*(j/7);
			l = i - j;

			m = 3 + (l+40)/44;
			d = l + 28 - 31*(m/4);

			easter.setDate(year, m, d);
		}

		//圣灰礼仪（周三 四旬期开始）
		aw = easter.addDays(-46);

		//圣神降临节瞻礼
		ps = easter.addDays(49);

		t.setDate(year,11,30);
		//将临期第一主日
		av = t.addDays(0-t.dayOfWeek().value());

		//圣诞节
		cm.setDate(year,12,25);
	}
	
	public LiturgicDay getLiturgicDay(Date d)
	{
		if(d.year() != year)
			return new LiturgicDay();

	    //填充数据
	    LiturgicDay ld = new LiturgicDay(d);
		if(ld.isLess(bl))
		{
			testChristmas1(ld);
		}
		else if(ld.isLess(aw))
		{
			testOrdinary1(ld);
		}
		else if(ld.isLess(easter))
		{
			testLent(ld);
		}
		else if(ld.isNotGreater(ps))
		{
			testEaster(ld);
		}
		else if(ld.isLess(av))
		{
			testOrdinary2(ld);
		}
		else if(ld.isLess(cm))
		{
			testAdvent(ld);
		}
		else
		{
			testChristmas2(ld);
		}

		testProper(ld);

	    return ld;
	}

	@Override
	public String toString()
	{
		return "";
/*	    std::ostringstream ostr;
	    ostr<<"年份:"<<year<<std::endl;
		ostr<<"主显节\t\t:\t"<<ep.toString()<<std::endl;
		ostr<<"主受洗日\t\t:\t"<<bl.toString()<<std::endl;
		ostr<<"圣灰礼仪\t\t:\t"<<aw.toString()<<std::endl;
		ostr<<"复活节\t\t:\t"<<easter.toString()<<std::endl;
	    ostr<<"圣神降临\t\t:\t"<<ps.toString()<<std::endl;
	    ostr<<"将临期第一主日\t:\t"<<av.toString()<<std::endl;
	    
	    return ostr.str();*/
	}

	private void testChristmas1(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isLess(new LiturgicDay(year,1,1)) || ld.isGreater(bl))
			return;

		ld.setSeason(season_t.CHRISTMAS);

		Date lastChristmas = new Date(year-1,12,25);
		int dayFromLC = lastChristmas.daysTo(ld);
		ld.setWeekOfSeason((dayFromLC-lastChristmas.dayOfWeek().value()-1)/7 + 1);
		if(ld.dayOfWeek()==day_t.SUN)
		{
			ld.appendCell(rank_t.SUNDAY,color_t.WHITE,ld.toWeekdayString());
		}
		else
		{
			ld.appendCell(rank_t.WEEKDAY,color_t.WHITE,ld.toWeekdayString());
		}

		if(ld.isLess(ep))
		{

		}
		else if(ld.isEquel(ep))
		{
			ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"主显节");
		}
		else if(ld.isLess(bl))
		{

		}
	}

	void testOrdinary1(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isLess(bl) || ld.isNotLess(aw))
			return;

		ld.setSeason(season_t.ORDINARY);
		ld.setWeekOfSeason( ld.weekNumber()-bl.weekNumber()+1 );
		if(ld.dayOfWeek()==day_t.SUN)
		{
			ld.appendCell(rank_t.SUNDAY,color_t.GREEN,ld.toWeekdayString());
		}
		else
		{
			ld.appendCell(rank_t.WEEKDAY,color_t.GREEN,ld.toWeekdayString());
		}


		if(ld == bl)
		{
			ld.appendCell(rank_t.LORD,color_t.WHITE,"主受洗日");
		}

	}

	void testLent(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isLess(aw) || ld.isNotLess(easter))
			return;

		ld.setSeason(season_t.LENT);
		ld.setWeekOfSeason( ld.weekNumber()-aw.weekNumber() );
		if(ld.dayOfWeek()==day_t.SUN)
		{
			if(ld.getWeekOfSeason() == 4)
				ld.appendCell(rank_t.SUNDAY,color_t.ROSE,ld.toWeekdayString());
			else
				ld.appendCell(rank_t.SUNDAY,color_t.PURPLE,ld.toWeekdayString());
		}
		else
		{
			ld.appendCell(rank_t.WEEKDAY,color_t.PURPLE,ld.toWeekdayString());
		}

		if(ld == aw)
		{
			ld.appendCell(rank_t.ASHWED,color_t.PURPLE,"圣灰礼仪");
		}
		else if(ld.getWeekOfSeason() == 6)
		{
			//圣周
			switch(ld.dayOfWeek().value())
			{
			case 0:
				ld.appendCell(rank_t.SUNDAY,color_t.RED,"圣枝主日(基督苦难主日)");
				break;
			case 1:
				ld.appendCell(rank_t.HOLYWEEK,color_t.PURPLE,"圣周一");
				break;
			case 2:
				ld.appendCell(rank_t.HOLYWEEK,color_t.PURPLE,"圣周二");
				break;
			case 3:
				ld.appendCell(rank_t.HOLYWEEK,color_t.PURPLE,"圣周三");
				break;
			case 4:
				ld.appendCell(rank_t.TRIDUUM,color_t.WHITE,"主的晚餐");
				break;
			case 5:
				ld.appendCell(rank_t.TRIDUUM,color_t.RED,"耶稣受难");
				break;
			case 6:
				ld.appendCell(rank_t.TRIDUUM,color_t.WHITE,"基督安眠墓中(复活节前夕)");
				break;
			}
		}
	}

	void testEaster(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isLess(easter) || ld.isGreater(ps))
			return;

		ld.setSeason(season_t.EASTER);
		ld.setWeekOfSeason(ld.weekNumber()-easter.weekNumber()+1);

		int dayFromEaster = easter.daysTo(ld);

		if(dayFromEaster>7)
		{
			if(ld.dayOfWeek()==day_t.SUN)
			{
				ld.appendCell(rank_t.SUNDAY,color_t.WHITE,ld.toWeekdayString());
			}
			else
			{
				ld.appendCell(rank_t.WEEKDAY,color_t.WHITE,ld.toWeekdayString());
			}
		}

		if(dayFromEaster<8)
		{
			if(dayFromEaster == 0)
			{
				ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"复活节");
			}
			else if(dayFromEaster < 7)
			{
				ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,
						"复活节庆期第"+LiturgicDay.getChineseNumStr(ld.dayOfWeek().value()+1)+"日");
			}
			else
			{
				ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,ld.toWeekdayString());
			}
		}
		else if(dayFromEaster == 39)
		{
			ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"耶稣升天");
		}
		else if(dayFromEaster == 49)
		{
			ld.appendCell(rank_t.SOLEMNITY,color_t.RED,"圣神降临节");
		}
	}

	void testOrdinary2(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isNotGreater(ps) || ld.isNotLess(av))
			return;

		int weekEnd = av.weekNumber() - 1;		//将临期第一主日为常年期的结束
		ld.setWeekOfSeason( 34-(weekEnd-ld.weekNumber()) );
		if(ld.dayOfWeek()==day_t.SUN)
		{
			ld.appendCell(rank_t.SUNDAY,color_t.GREEN,ld.toWeekdayString());
			if(ld.getWeekOfSeason() == 34)
			{
				ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"基督普世君王节");
			}
		}
		else
		{
			ld.appendCell(rank_t.WEEKDAY,color_t.GREEN,ld.toWeekdayString());
		}



		int dayFromEaster = easter.daysTo(ld);

		if(dayFromEaster == 56)
		{
			ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"圣三主日");
		}
		else if(dayFromEaster == 60)
		{
			//可移至主日庆祝 + 63
			ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"基督圣体圣血节");
		}
		else if(dayFromEaster == 68)
		{
			ld.appendCell(rank_t.SOLEMNITY,color_t.WHITE,"耶稣圣心节");
		}
		else if(dayFromEaster == 69)
		{
			ld.appendCell(rank_t.MEMORIAL,color_t.WHITE,"圣母无玷圣心");
		}

		ld.setSeason(season_t.ORDINARY);
	}

	void testAdvent(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isLess(av) || ld.isNotLess(cm))
			return;

		ld.setSeason(season_t.ADVENT);
		ld.setWeekOfSeason(ld.weekNumber()-av.weekNumber()+1);
		if(ld.dayOfWeek() == day_t.SUN)
		{
			if(ld.getWeekOfSeason() == 3)
			{
				ld.appendCell(rank_t.SUNDAY,color_t.ROSE,ld.toWeekdayString());
			}
			else
			{
				ld.appendCell(rank_t.SUNDAY,color_t.PURPLE,ld.toWeekdayString());
			}
		}
		else
		{
			ld.appendCell(rank_t.SUNDAY,color_t.PURPLE,ld.toWeekdayString());
		}
	}

	void testChristmas2(LiturgicDay ld)
	{
		if((!ld.isValid()) || ld.isLess(cm) || ld.isGreater(new LiturgicDay(year,12,31)))
			return;

		ld.setSeason(season_t.CHRISTMAS);
		if(cm.dayOfWeek() == day_t.SUN && ld.isEquel(new Date(year,12,31)))
		{
			ld.setWeekOfSeason(2);
			ld.appendCell(rank_t.SUNDAY,color_t.WHITE,ld.toWeekdayString());
		}
		else
		{
			ld.setWeekOfSeason(1);
			if(ld.isEquel(new Date(year,12,31)))
			{
				ld.appendCell(rank_t.WEEKDAY,color_t.WHITE,ld.toWeekdayString());
			}
		}

		switch(cm.daysTo(ld))
		{
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			{
				if(ld.dayOfWeek() == day_t.SUN)
				{
					ld.appendCell(rank_t.SUNDAY,color_t.WHITE,
							"圣诞节庆期第"+LiturgicDay.getChineseNumStr(cm.daysTo(ld)+1)+"日");
				}
				else
				{
					ld.appendCell(rank_t.WEEKDAY,color_t.WHITE,
							"圣诞节庆期第"+LiturgicDay.getChineseNumStr(cm.daysTo(ld)+1)+"日");
				}
			}
			break;
		}

		//圣家节
		if(cm.dayOfWeek() == day_t.SUN)
		{
			if(ld.isEquel(new Date(year,12,30)))
				ld.appendCell(rank_t.LORD,color_t.WHITE,"圣家节");
		}
		else
		{
			if(ld.dayOfWeek() == day_t.SUN)
				ld.appendCell(rank_t.LORD,color_t.WHITE,"圣家节");
		}
	}

	void testProper(LiturgicDay ld)
	{
		int v = ld.month()*100+ld.day();
		
		Set<CellInfo> values = mapPropers.get(v);
		for(CellInfo c:values)
	    {
			ld.appendCell(c);
	    }
	}
}
