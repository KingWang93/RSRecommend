package cn.edu.whu.lmars.rsrec.geo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

import cn.edu.whu.lmars.rsrec.entity.SpatialReltionType;

public class GeoProcessor {
	//影像空间坐标串
	private String imageRec;
	
	public GeoProcessor(String imageRec) {
		super();
		this.imageRec = imageRec;
	}

	/**
	 * 输入为网格（五个点构成的网格，第一个点和最后一个点为同一个点）
	 * 输出为网格所覆盖的网格频率
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Integer> calcGridCover() {
		Map<String, Integer> map = new HashMap<>();
		try {
			String[] arr = imageRec.split(", ");
			if(arr.length!=5){
				throw new Exception("输入的坐标串点数不是5");
			}
			Coordinate[] coordinates = new Coordinate[5];
			for (int j = 0; j < 5; j++) {
				Coordinate coor = new Coordinate();
				coor.x = Double.parseDouble(arr[j].split(" ")[0]);
				coor.y = Double.parseDouble(arr[j].split(" ")[1]);
				coordinates[j] = coor;
			}
			CoordinateArraySequence sequence = new CoordinateArraySequence(coordinates);
			GeometryFactory fac = new GeometryFactory();
			LinearRing ring = new LinearRing(sequence, fac);
			Geometry poly = new Polygon(ring, null, fac);
			ArrayList<SimpleFeature> fs = GeoUtil.topoQueryFeature(poly, "CELL_C",
					SpatialReltionType.TopoRelTypeEnum.intersect);
			for (SimpleFeature sf : fs) {
				String cell = (String) sf.getAttribute("cellName");
				map.put(cell, map.containsKey(cell) ? map.get(cell) + 1 : 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		String s="177.1 56.6, 178.1 56.6, 178.1 57.6, 177.1 57.6, 177.1 56.6";
		System.out.println(new GeoProcessor(s).calcGridCover());
	}
}
