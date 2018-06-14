package cn.edu.whu.lmars.rsrec.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.postgis.PostgisNGDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureSource;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeType;

//import cn.edu.whu.lmars.OntologyParserImpl.OntoPathUtil;

import com.vividsolutions.jts.geom.Geometry;

import cn.edu.whu.lmars.rsrec.entity.LayerSet;
public class SpatialConn {
	public static DataStore pgDatastore;
	public static String aNameTable = "CELL_C";
	public static List<LayerSet> layers = new ArrayList<LayerSet>();
//	public static List<LayerSet> layersToFront = new ArrayList<LayerSet>();//给前台的数据
	public  DataStore getpgDataStore(){
		if(pgDatastore==null)
			Init();
		return pgDatastore;
	}
	public static void Init() {
		if(pgDatastore!=null)
			return;
		try {
			conn("postgis", "localhost", "5432", "postgis", "postgres", "lmars");
			System.out.println("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// 连接数据库
	private static void conn(String dbtype, String host, String port,
			String database, String userName, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PostgisNGDataStoreFactory.DBTYPE.key, dbtype);
		params.put(PostgisNGDataStoreFactory.HOST.key, host);
		params.put(PostgisNGDataStoreFactory.PORT.key, new Integer(port));
		params.put(PostgisNGDataStoreFactory.DATABASE.key, database);
		params.put(PostgisNGDataStoreFactory.SCHEMA.key, "public");
		params.put(PostgisNGDataStoreFactory.USER.key, userName);
		params.put(PostgisNGDataStoreFactory.PASSWD.key, password);
		try {
			pgDatastore = DataStoreFinder.getDataStore(params);
			if (pgDatastore != null) {
				System.out.println("系统连接到位于：" + host + "的空间数据库" + database
						+ "成功！");
			} else {
				System.out.println("系统连接到位于：" + host + "的空间数据库" + database
						+ "失败！请检查相关参数");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("系统连接到位于：" + host + "的空间数据库" + database
					+ "失败！请检查相关参数");
		}

	}

	// 获取某一图层的source
	public static SimpleFeatureSource getFeatureSource(String layerName) {
		if (pgDatastore == null) {
			Init();
		}
		SimpleFeatureSource featureSource = null;
		try {
			featureSource = pgDatastore.getFeatureSource(layerName);//liming 直接获取 
		} catch (IOException e) {
			e.printStackTrace();
			Init();
		}
		return featureSource;
	}
	
	public static String getFeatureType(String layerName) {
		try {
			SimpleFeatureType type = pgDatastore.getFeatureSource(layerName)
					.getSchema();
			int count = type.getAttributeCount();
			for (int i = 0; i < count; i++) {
				AttributeType at = type.getType(i);
				// 判断属性类型是否为可分配的几何对象
				if (Geometry.class.isAssignableFrom(at.getBinding()))
					return at.getBinding().getSimpleName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static List<LayerSet> getAllLayerName() {
		try {
			if(pgDatastore==null)
				Init();
			String[] typeName = pgDatastore.getTypeNames();
			layers.clear();
			for (int i = 0; i < typeName.length; i++) {
				LayerSet layer = new LayerSet();
				layer.setLayerName(typeName[i]);
				layer.setLayerType(getFeatureType(typeName[i]));
				
				layers.add(layer);
			}
			return layers;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		getAllLayerName();
		getFeatureSource("CELL_C");
	}
}
