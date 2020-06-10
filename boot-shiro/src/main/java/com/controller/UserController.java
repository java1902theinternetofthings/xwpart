package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UserController {
	@RequestMapping("/hello")
	@ResponseBody
	 public String hello() {
        System.out.println("UserController.hello()");
        return "hello";
    } 
	@RequestMapping("unAuth")
	public String unAuth() {
		return "/unAuth";
	}
	@RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("name", "Hello Thymeleaf!");
        return "test";
    }
	@RequestMapping("/user/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login1";
    }
	@RequestMapping("/login2")
	public String login(String name, String password, Model model) {
	    //1、获取Subject
	    org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
	    //2、封装用户数据
	    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
	    //3、执行登录方法
	    try {
	        subject.login(usernamePasswordToken);
	        return "/user/realindex";
	    } catch (UnknownAccountException e) {
	        model.addAttribute("msg","用户名不存在");
	        return "/login1";
	    } catch (IncorrectCredentialsException e) {
	        model.addAttribute("msg", "密码错误");
	        return "/login1";
	    }
	}
}
