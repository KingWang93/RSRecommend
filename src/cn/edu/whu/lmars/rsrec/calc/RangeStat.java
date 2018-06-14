package cn.edu.whu.lmars.rsrec.calc;

import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

import cn.edu.whu.lmars.rsrec.entity.Range;
import javaslang.collection.Tree;

import java.lang.Math;

/**
 * Created by cqc on 2018/5/30.
 */
public class RangeStat {
	// Range对应的Map为reign，初始化可以得到，调用的时候，初始化以后range.reign即可
	public TreeMap<Integer, Range> range = new TreeMap<Integer, Range>();
	
	
	public RangeStat() {
		super();
	}

	public RangeStat(TreeMap<Integer, Range> range) {
		super();
		this.range = range;
	}

	// 将输入的二维范围list转化为List<Range>
	public List<Range> getRangeList(List<List<String>> list) {
		List<Range> rangeList = new ArrayList<Range>();
		for (int i = 0; i < list.size(); i++) // 遍历输入范围列表
		{
			double min = Double.valueOf(list.get(i).get(0));
			double max = Double.valueOf(list.get(i).get(1));
			rangeList.add(new Range(min, max));
		}
		return rangeList;
	}

	public void initRangeMap(List<Range> RangeList) {
		// 将输入的排好序的List生成Map，每一个范围对应一个Integer：Index
		int Index = 0;
		Collections.sort(RangeList);
		for (Range r : RangeList) {
			range.put(Index, r);
			Index++;
		}
	}
	
	public void setRange(TreeMap<Integer, Range> map){
		range = map;
	}

	// 判断输入的double值是否在区间内
	public boolean InRange(double imput, Range range) {
		if (imput == range.min)
			return true;
		else if (imput == range.max)
			return false;
		else if (Math.max(range.min, imput) == Math.min(imput, range.max))
			return true;
		else
			return false;
	}

	// //若输入区间有重叠，可能出现输入一个数值出现多个区间的情况，则使用这个函数，输出区间表
	// public List<Integer> getimputIndex(double imput){
	// List<Integer> imputIndex = new ArrayList<Integer>();
	// try{
	// if (reign != null){
	// for (Map.Entry<Integer,ArrayList<Range>> entry : reign.entrySet()){
	// if (InRange(imput,entry.getValue().get(0)))
	// imputIndex.add(entry.getKey());
	// }
	// }
	// }catch (Exception e){
	// e.printStackTrace();
	// }
	// return imputIndex;
	// }

	// 假设输入区间为连续的，不会出现输入一个数值，返回多个区间，使用该函数
	public int getSingleIndex(double imput) {
		int imputIndex = 9999;
		if (range != null) {
			for (Map.Entry<Integer, Range> entry : range.entrySet()) {
				if (InRange(imput, entry.getValue()))
					imputIndex = entry.getKey();
			}
		}
		return imputIndex;
	}

	// 生成数值对应的区间频率表
	public TreeMap<Integer, Double> getValueMap(List<String> list) {
		TreeMap<Integer, Double> valueMap = new TreeMap<Integer, Double>();
		try {
			// 统计总key的值的和，方便计算频率
			double ValueSam = 0;
			for (int i = 0; i < list.size(); i++) // 遍历输入数值列表
			{
				double imputvalue = Double.parseDouble(list.get(i));
				int imputindex = getSingleIndex(imputvalue);
				if (valueMap.containsKey(imputindex))
					valueMap.put(imputindex, valueMap.get(imputindex) + 1.0 / list.size());
				else
					valueMap.put(imputindex, 1.0 / list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueMap;
	}

	public void valueMapOf(TreeMap<Integer, Double> valMap) {
		// 测试频率是否有问题
		double SumRate = 0;
		for (Map.Entry<Integer, Double> entry : valMap.entrySet()) {
			DecimalFormat df = new DecimalFormat("0.00%");
			String rate = df.format(entry.getValue());
			SumRate += entry.getValue();
			System.out.println("Key = " + entry.getKey() + " " + range.get(entry.getKey()) + " " + "Value = " + rate);
		}
		System.out.println("总频率为：" + SumRate);
	}
	/**
	 * 当输入是一个范围的情况，读取最小值和最大值对应的ID，取其区间进行累加，放到Map里面
	 * 输出Map<区间ID，频率>
	 * @param rangelist
	 * @return
	 */
	public TreeMap<Integer, Integer> getRangeMap(List<Range> rangelist) {
		TreeMap<Integer, Integer> rangeMap = new TreeMap<Integer, Integer>();
		double valueSum = 0.0;
		try {
			for (int i = 0; i < rangelist.size(); i++) {
				double imputRangemin = rangelist.get(i).getMin();
				double imputRangemax = rangelist.get(i).getMax();
				int imputminIndex = getSingleIndex(imputRangemin);
				int imputmaxIndex = getSingleIndex(imputRangemax);
				for (int j = imputminIndex; i <= imputmaxIndex; i++) {
					valueSum = valueSum + 1.0;
					if (rangeMap.containsKey(j))
						rangeMap.put(j, rangeMap.get(j) + 1);
					else
						rangeMap.put(j, 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rangeMap;
	}
	/**
	 * 根据单个range范围求其频率
	 * @param r
	 * @return
	 */
	public Map<Integer, Double> getRangeMap(Range r) {
		Map<Integer, Double> rangeMap = new HashMap<Integer, Double>();
		try {
			double imputRangemin = r.getMin();
			double imputRangemax = r.getMax();
			int imputminIndex = getSingleIndex(imputRangemin);
			int imputmaxIndex = getSingleIndex(imputRangemax);
			for (int j = imputminIndex; j <= imputmaxIndex; j++) {
				rangeMap.put(j, 1.0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rangeMap;
	}

	public static void main(String[] args) {
		RangeStat r = new RangeStat();
		List<Range> a = new ArrayList<Range>();
		a.add(new Range(1, 20));
		a.add(new Range(20, 30));
		a.add(new Range(30, 40));
		a.add(new Range(40, 150));
		a.add(new Range(150, 200));
		a.add(new Range(200, 500));
		a.add(new Range(500, 850));
		a.add(new Range(850, 1000));
		a.add(new Range(1000, 3000));
		Collections.sort(a);
		r.initRangeMap(a);

		List<String> ValueList = new ArrayList<String>();
		String array[] = { "400", "5", "9", "11", "17", "22", "45", "47", "51", "55", "58", "66", "72", "103", "111",
				"149", "179", "564", "812", "945" };
		for (int i = 0; i < array.length; i++) {
			ValueList.add(array[i]);
		}
		TreeMap<Integer, Double> valMap = new TreeMap<>();
		valMap = r.getValueMap(ValueList);
		r.valueMapOf(valMap);
	}
}