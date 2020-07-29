package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {
	User user = new User("");
	
	@Test
	void 正常系_ユーザー管理コード登録参照() {
		String code = "1000";
		user.setCode(code);
	    assertThat(user.getCode())
	    	.isEqualTo(code);
	}
	@Test
	void 正常系_名前登録参照() {
		String name = "poyoyo~";
		user.setName(name);
	    assertThat(user.getName())
	    	.isEqualTo(name);
	}
	@Test
	void 正常系_年齢登録参照() {
		int age = 10;
		user.setAge(age);
	    assertThat(user.getAge())
	    	.isEqualTo(age);
	}
	@Test
	void 異常系_範囲外年齢登録() {
		int age = 100000000;
		int ageErrorValue = -1;
		user.setAge(age);
	    assertThat(user.getAge())
	    	.isEqualTo(ageErrorValue);
	}
	@Test
	void 異常系_初期化値が範囲外年齢() {
		int normalyAge = 25;
		int initAge = user.getAge();
		user.setAge(normalyAge);
		user.setAge(initAge);
	    assertThat(user.getAge())
	    	.isEqualTo(normalyAge);
	}
}
