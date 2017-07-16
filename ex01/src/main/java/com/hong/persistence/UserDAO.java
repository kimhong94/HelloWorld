package com.hong.persistence;

import java.sql.Date;

import com.hong.domain.UserVO;
import com.hong.dto.LoginDTO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String userid, String sessionId, Date next);
	
	public UserVO checkUserWithSessionKey(String value);
}
