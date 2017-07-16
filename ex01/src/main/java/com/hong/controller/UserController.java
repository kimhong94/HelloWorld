package com.hong.controller;

import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hong.domain.UserVO;
import com.hong.dto.LoginDTO;
import com.hong.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	private UserService service;
	
	@RequestMapping(value = "/login", method= RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto")LoginDTO dto){
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception{
		
		UserVO vo = service.login(dto);
		
		if(vo == null){
			return ;
		}
		
		model.addAttribute("userVO",vo);
		
		if(dto.isUseCookie()){
			int amount = 60*60*24*7;
			
			Date sessionLimit = new Date(System.currentTimeMillis() + 1000*amount);
			
			service.keepLogin(vo.getUserid(), session.getId(), sessionLimit);
		}
	}
}
