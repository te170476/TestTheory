package com.example.demo;

/**
 * ユーザー情報
 * @author user01-m
 *
 */
public class User {

	/**
	 * ユーザー管理コード
	 */
	private String code;
	
	/**
	 * 名前
	 */
	private String name;
	
	/**
	 * 年齢
	 */
	private int age = -1;

	/**
	 * コンストラクタ
	 * 
	 * @param code ユーザー管理コード
	 */
	public User(String code) {
		this.code = code;
	}
	
	/**
	 * 名前取得
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前設定
	 * 
	 * @param name 名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 年齢取得
	 * 
	 * @return 年齢
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 年齢設定
	 * 
	 * @param age 年齢（1以上199以下）
	 */
	public void setAge(int age) {
		if(age > 0 && age < 200) {
			this.age = age;
		}
	}

	/**
	 * ユーザー管理コード取得
	 * 
	 * @return ユーザー管理コード
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ユーザー管理コード設定
	 * 
	 * @param code ユーザー管理コード
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
