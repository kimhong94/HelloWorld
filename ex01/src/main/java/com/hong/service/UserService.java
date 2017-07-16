package com.hong.service;

import java.sql.Date;

import com.hong.domain.UserVO;
import com.hong.dto.LoginDTO;

public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String userid, String sessionId, Date next) throws Exception;
	
	public UserVO checkLoginBefore(String value);
	
	
}
