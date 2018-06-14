package cn.edu.whu.lmars.rsrec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import cn.edu.whu.lmars.rsrec.db.DBConnectionPool;
import cn.edu.whu.lmars.rsrec.user.UserPreference;
import cn.edu.whu.lmars.rsrec.user.UserTab;

public class UserPrefDao {
	//读取所有用户的喜好
	private List<UserTab> getUserTab() {
		String sql = "select * from userpref";
		List<UserTab> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		try {
			conn = DBConnectionPool.getConnection("localdb");
			pre = conn.prepareStatement(sql);
			res = pre.executeQuery();
			while(res.next()){
				int id = res.getInt("id");
				String userId = res.getString("userid");
				String userPref = res.getString("userpref");
				UserPreference user = parse(userPref);
				UserTab usertab = new UserTab();
				usertab.setId(id);
				usertab.setUserId(userId);
				usertab.setUserPref(user);
				list.add(usertab);
			}
			res.close();
			pre.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				res.close();
				pre.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	private List<UserTab> getUserById(String userid) {
		String sql = "select * from userpref where userid=?";
		List<UserTab> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet res = null;
		try {
			conn = DBConnectionPool.getConnection("localdb");
			pre = conn.prepareStatement(sql);
			pre.setString(1, userid);
			res = pre.executeQuery();
			while(res.next()){
				int id = res.getInt("id");
				String userId = res.getString("userid");
				String userPref = res.getString("userpref");
				UserPreference user = parse(userPref);
				UserTab usertab = new UserTab();
				usertab.setId(id);
				usertab.setUserId(userId);
				usertab.setUserPref(user);
				list.add(usertab);
			}
			res.close();
			pre.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				res.close();
				pre.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	//存用户的喜好
	public void insertUser(UserTab tab){
		int id = tab.getId();
		String userid = tab.getUserId();
		UserPreference pref = tab.getUserPref();
		String prefStr = toString(pref);
		Connection conn = null;
		PreparedStatement pre = null;
		String sql = "insert into userpref(id,userid,userpref) values(?,?,?)";
		try {
			conn = DBConnectionPool.getConnection("localdb");
			conn.setAutoCommit(false);
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setString(2, userid);
			pre.setString(3, prefStr);
			pre.execute();
			conn.commit();
			pre.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				pre.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		try {
			new UserPrefDao().getUserTab();
			UserTab tab=new UserTab();
			tab.setId(1);
			tab.setUserId("id1");
			tab.setUserPref(null);
			new UserPrefDao().insertUser(tab);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UserPreference parse(String userPrefStr){
		Gson gson = new Gson();
		UserPreference userPref = gson.fromJson(userPrefStr, UserPreference.class);
		return userPref;
	}
	
	public String toString(UserPreference pref){
		Gson gson = new Gson();
		String userPrefStr = gson.toJson(pref);
		return userPrefStr;
	}

}
