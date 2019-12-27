package com.system.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.system.po.Userlogin;

/**
 * Created by Jacey on 2017/6/30.
 */
@RestController
@RequestMapping("/examination")
public class LoginController
{

	// 登录表单处理
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Userlogin login(@RequestBody Userlogin userlogin) throws Exception
	{
		userlogin.setUsername("admin改");
		userlogin.setUserid(0);
		userlogin.setPassword("***");
		userlogin.setRole(0);

		return userlogin;
	}

}
