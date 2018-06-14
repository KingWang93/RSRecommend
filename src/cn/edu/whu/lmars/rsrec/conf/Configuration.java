package cn.edu.whu.lmars.rsrec.conf;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import cn.edu.whu.lmars.rsrec.entity.Range;
import cn.edu.whu.lmars.rsrec.util.PropertiesUtil;

public class Configuration {
	/**
	 * 范围
	 */
	//空间分辨率
	private static TreeMap<Integer,Range> spatialResolutionMap;
	//时间分辨率
	private static TreeMap<Integer,Range> timeResolutionMap;
	//光谱分辨率
	private static TreeMap<Integer,Range> spectrumResolutionMap;
	//辐射分辨率
	private static TreeMap<Integer,Range> radiationResolutionMap;
	//幅宽
	private static TreeMap<Integer,Range> widthMap;
	//信噪比
	private static TreeMap<Integer,Range> snrMap;
	//交会角
	private static TreeMap<Integer,Range> angleMap;
	//波段
	private static TreeMap<Integer,Range> waveBandMap;
	
	
	/**
	 * 枚举
	 */
	//比例尺
	private static TreeMap<Integer,String> scaleMap;
	//传感器类型
	private static TreeMap<Integer,String> typeMap;
	//成像模式
	private static TreeMap<Integer,String> modeMap;
	//极化要求
	private static TreeMap<Integer,String> poleDemandMap;
	//极化方式
	private static TreeMap<Integer,String> poleMethodMap;
	
	public boolean isDefault;
	
	public static boolean loaded;
	
	public static void loadConf(boolean isDeafult){
		if(isDeafult==true){
			//范围
			Properties angle = PropertiesUtil.getProperInfo("angle");
			Properties radiationResolution = PropertiesUtil.getProperInfo("radiationResolution");
			Properties snr = PropertiesUtil.getProperInfo("snr");
			Properties spatialResolution = PropertiesUtil.getProperInfo("spatialResolution");
			Properties spectrumResolution = PropertiesUtil.getProperInfo("spectrumResolution");
			Properties timeResolution = PropertiesUtil.getProperInfo("timeResolution");
			Properties waveBand = PropertiesUtil.getProperInfo("waveBand");
			Properties width = PropertiesUtil.getProperInfo("width");
			
			spatialResolutionMap = readRangeConf(spatialResolution);
			timeResolutionMap = readRangeConf(timeResolution);
			spectrumResolutionMap = readRangeConf(spectrumResolution);
			radiationResolutionMap = readRangeConf(radiationResolution);
			widthMap = readRangeConf(width);
			snrMap = readRangeConf(snr);
			angleMap = readRangeConf(angle);
			waveBandMap = readRangeConf(waveBand);
			
			//枚举
			Properties scale = PropertiesUtil.getProperInfo("scale");
			Properties type = PropertiesUtil.getProperInfo("type");
			Properties mode = PropertiesUtil.getProperInfo("mode");
			Properties poleDemand = PropertiesUtil.getProperInfo("poleDemand");
			Properties poleMethod = PropertiesUtil.getProperInfo("poleMethod");
			
			scaleMap = readValueConf(scale);
			typeMap = readValueConf(type);
			modeMap = readValueConf(mode);
			poleDemandMap = readValueConf(poleDemand);
			poleMethodMap = readValueConf(poleMethod);
			loaded = true;
		}
		
	}
	
	public static TreeMap<Integer,Range> readRangeConf(Properties pro) {
		TreeMap<Integer,Range> treeMap = new TreeMap<>();
		Set<Entry<Object, Object>> set = pro.entrySet();
		for(Entry<Object, Object> entry:set){
			String value = (String)entry.getValue();
			String[] arr = value.split("-");
			Range r = new Range(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
			treeMap.put(Integer.parseInt(entry.getKey().toString()), r);
		}
		return treeMap;
	}
	
	public static TreeMap<Integer,String> readValueConf(Properties pro) {
		TreeMap<Integer,String> treeMap = new TreeMap<>();
		Set<Entry<Object, Object>> set = pro.entrySet();
		for(Entry<Object, Object> entry:set){
			treeMap.put(Integer.parseInt(entry.getKey().toString()), (String)entry.getValue());
		}
		return treeMap;
	}
	
	public static TreeMap<Integer, Range> getSpatialResolutionMap() {
		return spatialResolutionMap;
	}

	public static void setSpatialResolutionMap(TreeMap<Integer, Range> spatialResolutionMap) {
		Configuration.spatialResolutionMap = spatialResolutionMap;
	}

	public static TreeMap<Integer, Range> getTimeResolutionMap() {
		return timeResolutionMap;
	}

	public static void setTimeResolutionMap(TreeMap<Integer, Range> timeResolutionMap) {
		Configuration.timeResolutionMap = timeResolutionMap;
	}

	public static TreeMap<Integer, Range> getSpectrumResolutionMap() {
		return spectrumResolutionMap;
	}

	public static void setSpectrumResolutionMap(TreeMap<Integer, Range> spectrumResolutionMap) {
		Configuration.spectrumResolutionMap = spectrumResolutionMap;
	}

	public static TreeMap<Integer, Range> getRadiationResolutionMap() {
		return radiationResolutionMap;
	}

	public static void setRadiationResolutionMap(TreeMap<Integer, Range> radiationResolutionMap) {
		Configuration.radiationResolutionMap = radiationResolutionMap;
	}

	public static TreeMap<Integer, Range> getWidthMap() {
		return widthMap;
	}

	public static void setWidthMap(TreeMap<Integer, Range> widthMap) {
		Configuration.widthMap = widthMap;
	}

	public static TreeMap<Integer, Range> getSnrMap() {
		return snrMap;
	}

	public static void setSnrMap(TreeMap<Integer, Range> snrMap) {
		Configuration.snrMap = snrMap;
	}

	public static TreeMap<Integer, Range> getAngleMap() {
		return angleMap;
	}

	public static void setAngleMap(TreeMap<Integer, Range> angleMap) {
		Configuration.angleMap = angleMap;
	}

	public static TreeMap<Integer, Range> getWaveBandMap() {
		return waveBandMap;
	}

	public static void setWaveBandMap(TreeMap<Integer, Range> waveBandMap) {
		Configuration.waveBandMap = waveBandMap;
	}

	public static TreeMap<Integer, String> getScaleMap() {
		return scaleMap;
	}

	public static void setScaleMap(TreeMap<Integer, String> scaleMap) {
		Configuration.scaleMap = scaleMap;
	}

	public static TreeMap<Integer, String> getTypeMap() {
		return typeMap;
	}

	public static void setTypeMap(TreeMap<Integer, String> typeMap) {
		Configuration.typeMap = typeMap;
	}

	public static TreeMap<Integer, String> getModeMap() {
		return modeMap;
	}

	public static void setModeMap(TreeMap<Integer, String> modeMap) {
		Configuration.modeMap = modeMap;
	}

	public static TreeMap<Integer, String> getPoleDemandMap() {
		return poleDemandMap;
	}

	public static void setPoleDemandMap(TreeMap<Integer, String> poleDemandMap) {
		Configuration.poleDemandMap = poleDemandMap;
	}

	public static TreeMap<Integer, String> getPoleMethodMap() {
		return poleMethodMap;
	}

	public static void setPoleMethodMap(TreeMap<Integer, String> poleMethodMap) {
		Configuration.poleMethodMap = poleMethodMap;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public static boolean isLoaded() {
		return loaded;
	}

	public static void setLoaded(boolean loaded) {
		Configuration.loaded = loaded;
	}

	public static void main(String[] args) {
		loadConf(true);
		System.out.println(Configuration.spatialResolutionMap);
		System.out.println(Configuration.timeResolutionMap);
		System.out.println(Configuration.spectrumResolutionMap);
		System.out.println(Configuration.radiationResolutionMap);
		System.out.println(Configuration.widthMap);
		System.out.println(Configuration.snrMap);
		System.out.println(Configuration.angleMap);
		System.out.println(Configuration.waveBandMap);
		System.out.println(Configuration.scaleMap);
		System.out.println(Configuration.typeMap);
		System.out.println(Configuration.modeMap);
		System.out.println(Configuration.poleDemandMap);
		System.out.println(Configuration.poleMethodMap);
	}
	
}
