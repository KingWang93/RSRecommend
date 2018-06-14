package cn.edu.whu.lmars.rsrec.user;

public class UserTab {
	private int id;
	private String userId;
	private UserPreference userPref;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserPreference getUserPref() {
		return userPref;
	}
	public void setUserPref(UserPreference userPref) {
		this.userPref = userPref;
	}
}
