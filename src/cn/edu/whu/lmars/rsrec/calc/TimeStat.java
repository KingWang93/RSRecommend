package cn.edu.whu.lmars.rsrec.calc;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javaslang.collection.Tree;

import java.text.*;

/**
 * Created by cqc on 2018/5/28.
 */
public class TimeStat {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Date invalidDate = new Date();
	// 处理输入的时间列表list<string>,时间格式"yyyy-MM-ddTHH:mm:ss"

	public TreeMap<Integer, TreeMap<Integer, Double>> getTimeMap(List<String> list) {
		// 建立Map<Integer,Map<Integer,Float>> 表示 年份 第几周 频率
		TreeMap<Integer, TreeMap<Integer, Double>> timestatMap = new TreeMap<Integer, TreeMap<Integer, Double>>();
		try {
			for (int i = 0; i < list.size(); i++) // 遍历输入时间列表
			{
				Date date = dateFormat.parse(list.get(i)); // 转化为date格式;
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);
				int weekid = cal.get(Calendar.WEEK_OF_YEAR);

				if (timestatMap.containsKey(year)) { // 判断是否存在key:year存在就给weekidMap赋值
					TreeMap<Integer, Double> weekidMap = timestatMap.get(year);
					if (weekidMap.containsKey(weekid)) {
						weekidMap.put(weekid, weekidMap.get(weekid) + 1.0 / list.size());
					} else {
						weekidMap.put(weekid, 1.0 / list.size());
					}
				} else { // 不存在就新建key
					TreeMap<Integer, Double> weekidMap = new TreeMap<Integer, Double>();
					weekidMap.put(weekid, 1.0 / list.size());
					timestatMap.put(year, weekidMap);
				}
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return timestatMap;
	}

	// public TreeMap<Integer,TreeMap<Integer,Integer>> calcWeek(String time){
	// //建立Map<Integer,Map<Integer,Float>> 表示 年份 第几周 频率
	// TreeMap<Integer,TreeMap<Integer,Integer>> timestatMap = new
	// TreeMap<Integer,TreeMap<Integer,Integer>>();
	// try{
	// Date date = dateFormat.parse(time); //转化为date格式;
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(date);
	// int year = cal.get(Calendar.YEAR);
	// int weekid = cal.get(Calendar.WEEK_OF_YEAR);
	// if (timestatMap.containsKey(year)){ //判断是否存在key:year存在就给weekidMap赋值
	// TreeMap<Integer,Integer> weekidMap = timestatMap.get(year);
	// if (weekidMap.containsKey(weekid)) {
	// weekidMap.put(weekid, weekidMap.get(weekid) + 1);
	// } else {
	// weekidMap.put(weekid,1);
	// }
	// } else { //不存在就新建key
	// TreeMap<Integer,Integer> weekidMap = new TreeMap<Integer,Integer>();
	// weekidMap.put(weekid,1);
	// timestatMap.put(year,weekidMap);
	// }
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// return timestatMap;
	// }

	public TreeMap<String, Integer> calcWeek(String time) {
		// 建立Map<Integer,Map<Integer,Float>> 表示 年份 第几周 频率
		TreeMap<String, Integer> timestatMap = new TreeMap<String, Integer>();
		try {
			Date date = dateFormat.parse(time); // 转化为date格式;
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int weekid = cal.get(Calendar.WEEK_OF_YEAR);
			String weekidStr = weekid<10?"0"+String.valueOf(weekid):String.valueOf(weekid);
			if (timestatMap.containsKey(String.valueOf(year) + weekidStr)) { // 判断是否存在key:year存在就给weekidMap赋值
				timestatMap.put(String.valueOf(year) + weekidStr, timestatMap.get(String.valueOf(year) + weekidStr) + 1);
			} else {
				timestatMap.put(String.valueOf(year) + weekidStr, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timestatMap;
	}

	public static void MapOf(TreeMap<Integer, TreeMap<Integer, Double>> timestatMap) {
		double SumRate = 0;
		for (Entry<Integer, TreeMap<Integer, Double>> output : timestatMap.entrySet()) {
			Integer Year = output.getKey();
			TreeMap<Integer, Double> numNameMapValue = output.getValue();
			for (Map.Entry<Integer, Double> nameMapEntry : numNameMapValue.entrySet()) {
				Integer weekid = nameMapEntry.getKey();
				Double weekrate = nameMapEntry.getValue();
				// System.out.println("用户检索元数据中"+ Year + "的第" + weekid +
				// "周出现的频率为： " + weekrate);
				SumRate += weekrate;
				DecimalFormat df = new DecimalFormat("0.00%");
				String rate = df.format(weekrate);
				System.out.println("用户检索元数据中" + Year + "的第" + weekid + "周出现的频率为： " + rate);
			}
		}
		System.out.println("总频率为：" + SumRate);
	}

	public static void main(String[] args) {
		List<String> TimeList = new ArrayList<String>();
		String array[] = { "2010-01-11 00:00:00", "2010-02-02 00:00:00", "2010-02-07 00:00:00", "2010-01-17 00:00:00",
				"2010-03-11 00:00:00", "2010-03-13 00:00:00", "2010-03-15 00:00:00", "2010-03-18 00:00:00",
				"2010-03-20 00:00:00", "2010-03-23 00:00:00", "2010-04-01 00:00:00", "2010-03-18 00:00:00",
				"2010-09-01 00:00:00", "2010-12-01 00:00:00", "2010-10-17 00:00:00", "2011-01-11 00:00:00",
				"2011-02-02 00:00:00", "2011-02-07 00:00:00", "2011-01-17 00:00:00", "2011-03-11 00:00:00",
				"2011-03-13 00:00:00", "2011-03-15 00:00:00", "2011-03-18 00:00:00", "2011-03-20 00:00:00",
				"2011-03-23 00:00:00", "2011-04-01 00:00:00", "2011-03-18 00:00:00", "2011-09-01 00:00:00",
				"2011-12-01 00:00:00", "2011-10-17 00:00:00", "2012-01-11 00:00:00", "2012-02-02 00:00:00",
				"2012-02-07 00:00:00", "2012-01-17 00:00:00", "2012-03-11 00:00:00", "2012-03-13 00:00:00",
				"2012-03-15 00:00:00", "2012-03-18 00:00:00", "2012-03-20 00:00:00", "2012-03-23 00:00:00",
				"2012-04-01 00:00:00", "2012-03-18 00:00:00", "2012-09-01 00:00:00", "2012-12-01 00:00:00",
				"2012-10-17 00:00:00" };
		for (int i = 0; i < array.length; i++) {
			TimeList.add(array[i]);
		}
		TimeStat test = new TimeStat();
		TreeMap<Integer, TreeMap<Integer, Double>> timeMap = new TreeMap<>();
		timeMap = test.getTimeMap(TimeList);
		System.out.println(timeMap);
		test.MapOf(timeMap);
	}

}