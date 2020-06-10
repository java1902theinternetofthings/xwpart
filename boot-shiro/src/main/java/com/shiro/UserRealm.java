package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.domain.User;
import com.service.UserService;

public class UserRealm extends AuthorizingRealm{
	
	 @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
	        System.out.println("执行授权");
	        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
	        Subject subject = SecurityUtils.getSubject();
	        User user = (User)subject.getPrincipal();
	        User user1 = userService.findByName(user.getName());
	        simpleAuthorizationInfo.addStringPermission(user1.getPerms());

	        return simpleAuthorizationInfo;
	    }

	    @Autowired
	    private UserService userService;


	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
	        System.out.println("执行认证");
	        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
	        User user = userService.findByName(usernamePasswordToken.getUsername());
//	        System.out.println(user);
	        if(user == null) {
	            return null; 
	        }

	        //2、判断密码
	        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

	    }

}
