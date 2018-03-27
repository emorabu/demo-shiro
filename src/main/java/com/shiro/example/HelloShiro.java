package com.shiro.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class HelloShiro {
	@Test
	public void testLogin() {
		
		//1. Load the INI configuration
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

		//2. Create the SecurityManager
		SecurityManager securityManager = factory.getInstance();

		//3. Make it accessible
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
		
		try {
			subject.login(token);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
	    //6、退出
	    subject.logout();
		
	}
	
}
