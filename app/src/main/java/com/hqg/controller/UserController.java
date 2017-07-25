package com.hqg.controller;
  
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqg.entity.User;
import com.hqg.exception.BizException;
import com.hqg.service.UserService;
import com.hqg.util.StringMVCUtil;
import com.hqg.util.StringTools;
  
@ResponseBody
@Controller  
@RequestMapping("/user")  
public class UserController {
		
	@Autowired
	private UserService userService;
	      
	@RequestMapping("/addRegister")
	public void addRegister(HttpServletResponse resp){
		
		try {
			String userName = StringMVCUtil.getString("USERNAME");
			String pwd = StringMVCUtil.getString("PWD");
			String mobileNo = StringMVCUtil.getString("MOBILENO");
			String realName = StringMVCUtil.getString("RALENAME");
			int sex = StringMVCUtil.getInteger("SEX");
			String cardId = StringMVCUtil.getString("CARDID");
			
//			String md5Pwd = StringTools.encodeByMD5(pwd, "qqq");
			
			User user = new User();
			user.setCardId(cardId);
			user.setMobileNo(mobileNo);
			user.setPwd(pwd);
			user.setRealName(realName);
			user.setSex(sex);
			user.setUserName(userName);
			
			userService.addUser(user);
			StringMVCUtil.outWriteJson(resp, StringMVCUtil.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			StringMVCUtil.outWriteJson(resp, BizException.REGISTER_ERROR);
		}
		
	}
	
	@RequestMapping("/checkLongin")
	public void checkLongin(HttpServletResponse resp){
		try {
			String userId = StringMVCUtil.getString("ID");
			String pwd = StringMVCUtil.getString("PWD");
			User user = userService.getUserById(userId);
			String md5Pwd = user.getPwd();
			if (pwd.equals(md5Pwd)) {
				StringMVCUtil.outWriteJson(resp, StringMVCUtil.getResult());
			} else {
				StringMVCUtil.getErrorResult(resp, "-1", "密码不正确");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			StringMVCUtil.outWriteJson(resp, BizException.LOGIN_ERROR);
		}
		
	}
}
