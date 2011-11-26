package com.runchain.exercise.admin.unit.service.account;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.runchain.exercise.admin.data.AccountData;
import com.runchain.exercise.admin.entity.account.Authority;
import com.runchain.exercise.admin.entity.account.Role;
import com.runchain.exercise.admin.entity.account.User;
import com.runchain.exercise.admin.service.account.AccountManager;
import com.runchain.exercise.admin.service.account.UserDetailsServiceImpl;

/**
 * UserDetailsServiceImpl的测试用例, 测试Service层的业务逻辑. 
 * 
 * 使用EasyMock对AccountManager进行模拟.
 * 
 * @author HenryYan
 */
public class UserDetailsServiceImplTest extends Assert {

	private UserDetailsServiceImpl userDetailService;
	private AccountManager mockAccountManager;

	@Before
	public void setUp() {
		userDetailService = new UserDetailsServiceImpl();
		mockAccountManager = EasyMock.createMock(AccountManager.class);
		userDetailService.setAccountManager(mockAccountManager);
	}

	@After
	public void tearDown() {
		EasyMock.verify(mockAccountManager);
	}

	@Test
	public void loadUserExist() {

		//准备数据
		String authName = "foo";
		User user = AccountData.getRandomUser();
		Role role = AccountData.getRandomRole();
		user.getRoleList().add(role);
		Authority auth = new Authority();
		auth.setName(authName);
		role.getAuthorityList().add(auth);

		//录制脚本
		EasyMock.expect(mockAccountManager.findUserByLoginName(user.getLoginName())).andReturn(user);
		EasyMock.replay(mockAccountManager);

		//执行测试
		UserDetails userDetails = userDetailService.loadUserByUsername(user.getLoginName());

		//校验结果
		assertEquals(user.getLoginName(), userDetails.getUsername());
		assertEquals(user.getPassword(), userDetails.getPassword());
		assertEquals(1, userDetails.getAuthorities().size());
		assertEquals(new GrantedAuthorityImpl(auth.getPrefixedName()), userDetails.getAuthorities().iterator().next());
	}

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserNotExist() {
		//录制脚本
		EasyMock.expect(mockAccountManager.findUserByLoginName("userNameNotExist")).andReturn(null);
		EasyMock.replay(mockAccountManager);
		//执行测试
		userDetailService.loadUserByUsername("userNameNotExist");
	}
}