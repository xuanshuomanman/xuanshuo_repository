package com.czx.mall.realm;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm{
//	@Autowired
//	private UserService userService;
//	
	/**
	 * 
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 1. 从PrincipalCollection中来获取登陆用户的信息
		Object principal = principals.getPrimaryPrincipal();
		/*System.out.println("当前登陆的用户:"+principal);*/
		// 2. 利用登陆的用户信息来获取用户当前的角色以及权限(可能查询数据库)		
//		Set<String> set = new HashSet<String>();
//		User user = userService.findUserByUserName((String)principal);
//		Role role = user.getRole();
//		set.add(role.getRoleName());
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//		simpleAuthorizationInfo.addRoles(set);
		return simpleAuthorizationInfo;
	}
	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		//User user = userService.findUserByUserName(userName);
		//Subject currentUser = SecurityUtils.getSubject();
		//Session session = currentUser.getSession();
		//session.setAttribute("username", user.getUserName());
		// 获取盐，通常用账号
		ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
				// 盐值加密
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("root","root",credentialsSalt,getName());
		
		return simpleAuthenticationInfo;
	}

}

