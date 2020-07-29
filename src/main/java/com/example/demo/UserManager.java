package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ユーザー情報管理(Bill Pugh シングルトン)
 * @author user01
 *
 */
public class UserManager {

	/**
	 * ユーザー情報管理インスタンスHolder
	 * @author user01
	 *
	 */
	private static class UserManagerHolder {
		
		private static final UserManager instance = new UserManager();
	}
	
	/**
	 * ユーザー情報List
	 */
	private List<User> userList;
	
	/**
	 * ユーザー情報Map
	 */
	private Map<String, User> userMap;
	
	/**
	 * コンストラクタ
	 */
	private UserManager() {
		userList = new ArrayList<User>();
		userMap = new HashMap<String, User>();
	}
	
	/**
	 * ユーザー情報管理インスタンス取得
	 * 
	 * @return ユーザー情報管理インスタンス
	 */
	public static UserManager getInstance() {
		return UserManagerHolder.instance;
	}
	
	/**
	 * ユーザー情報List取得
	 * @return ユーザー情報List
	 */
	public List<User> getUserList() {
		return userList;
	}
	
	/**
	 * ユーザー情報Map取得
	 * @return ユーザー情報Map
	 */
	public Map<String, User> getUserMap() {
		return userMap;
	}
	
	/**
	 * ユーザー情報をListに追加
	 * @param user ユーザー情報
	 */
	public void setUserToList(User user) {
		userList.add(user);
	}

	/**
	 * ユーザー情報をMapに追加
	 * @param user ユーザー情報
	 */
	public void setUserToMap(User user) {
		userMap.put(user.getCode(), user);
	}

	/**
	 * ユーザー情報削除（List/Map共通)
	 * 
	 * @param code ユーザ管理ーコード
	 */
	public void deleteUser(String code) {
		// Mapから削除
		userMap.remove(code);
		// Listから削除
		User deleteUser = null;
		for(User user : userList) {
			if(user.getCode().equals(code)) {
				deleteUser = user;
				break;
			}
		}
		userList.remove(deleteUser);
	}
	
	/**
	 * 全ユーザー情報削除（List/Map共通)
	 * 
	 */
	public void deleteAllUser() {
		userList = new ArrayList<User>();
		userMap = new HashMap<String, User>();
	}
}

// 旧Singleton構成
//public class UserManager {
//
//	private List userList;
//	
//	private Map userMap;
//	
//	private static UserManager instance = null;
//	
//	private UserManager() {
//		userList = new ArrayList<User>();
//		userMap = new HashMap<String, User>();
//	}
//	
//	public static synchronized UserManager getInstance() {
//		if(instance == null) {
//			instance = new UserManager();
//		}
//		return instance;
//	}
//	
//}