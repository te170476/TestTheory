package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserManagerTest {
	static final UserManager userManager = UserManager.getInstance();
	static final User[] userArray = {
			 new User("1")
			,new User("2")
			,new User("3")
			,new User("4")
			,new User("5")
		};
	static final List<User> userList = Arrays.asList(userArray);
	static final int iterableTestTargetIndex = 1;
	
	@BeforeAll
	static void beforeAll() {
		if(!Arrays.asList(userArray).equals(userList))
			throw new IllegalStateException("`userList contents` not equals `userArray contents`.");
		if(userList.size() <= iterableTestTargetIndex)
			throw new IllegalStateException("userList size is less than " + iterableTestTargetIndex);
	}
	@BeforeEach
	void beforeEach() {
		userManager.deleteAllUser();
	}
	
	@Test
	@Order(value = 1)
	void 正常系_MapList初期生成() {
		assertThat(userManager.getUserList())
			.isEmpty();
		assertThat(userManager.getUserMap())
			.isEmpty();
	}
	
	@Test // 処理の無駄が多すぎてﾜﾛ(同じ比較がたくさんある)
	void 正常系_UserManagerインスタンス同一() {
		List<UserManager> instances =
				Arrays.asList(
						 userManager
						,UserManager.getInstance()
						,UserManager.getInstance()
						,UserManager.getInstance()
						);
		Boolean allMatch = 
			instances.parallelStream().allMatch(outer ->
				instances.parallelStream().allMatch(inner -> outer.equals(inner))
			);
	    assertThat(allMatch)
	    	.isEqualTo(true);
	}

	@Test
	void 正常系_userList登録参照() {
		userList.forEach(user->
			userManager.setUserToList(user)
		);
		assertThat(userManager.getUserList())
			.isEqualTo(userList);
	}

	@Test
	void 正常系_userMap登録参照() {
		userList.forEach(user->
			userManager.setUserToMap(user)
		);
		assertThat(userManager.getUserMap())
			.containsValues(userArray);
	}
	
	@Test
	void 正常系_user全削除() {
		userList.forEach(user-> {
			userManager.setUserToList(user);
			userManager.setUserToMap(user);
		});
		userManager.deleteAllUser();
		assertThat(userManager.getUserList())
			.isEmpty();
		assertThat(userManager.getUserMap())
			.isEmpty();
	}

	@Test
	void 正常系_code指定user削除() {
		User targetUser = userList.get(iterableTestTargetIndex);
		userList.forEach(user-> {
			userManager.setUserToList(user);
			userManager.setUserToMap(user);
		});
		userManager.deleteUser(targetUser.getCode());
		assertThat(userManager.getUserList())
			.doesNotContain(targetUser);
		assertThat(userManager.getUserMap())
			.doesNotContainValue(targetUser);
	}

	@Test
	void 異常系_userList内部値重複時の削除異常() {
		User user = new User("重複");
		userManager.setUserToList(user);
		userManager.setUserToList(user);
		
		userManager.deleteUser(user.getCode());
		assertThat(userManager.getUserList())
			.contains(user);
	}
	
	@Test
	void 正常系_List登録順序保持() {
		List<User> suffled = Arrays.asList(userArray);
		Collections.shuffle(suffled);
		suffled.forEach(user->
			userManager.setUserToList(user)
		);
		assertThat(userManager.getUserList())
			.isEqualTo(suffled);
	}
	
	@Test
	void 正常系_Mapキー確認() {
		userList.forEach(user->
			userManager.setUserToMap(user)
		);
		Map<String, User> userMap = userManager.getUserMap();
		userList.forEach(user ->
			assertThat(userMap.get(user.getCode()))
				.isEqualTo(user)
		);
	}
}
