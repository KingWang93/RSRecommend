package cn.edu.whu.lmars.rsrec.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesUtil {
	/**
	 * ��ȡproperties�е���������
	 * 
	 * @param sKey
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getProperties(String propertyname, String sKey) {
		Properties pro = getProperInfo(propertyname);
		String s = pro.getProperty(sKey);
		return s;
	}

	public static Properties getProperInfo(String properName) {
		Properties properties = new Properties();
		try {
			String url = Thread.currentThread().getContextClassLoader().getResource("").toString();
			url = url.substring(url.indexOf("/") + 1);
			url = url.replace("%20", " ");
			url = URLDecoder.decode(url, "UTF-8");
			InputStreamReader insReader = new InputStreamReader(new FileInputStream(url + properName + ".properties"),
					"UTF-8");
			properties.load(insReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		getProperInfo("region");
	}
}