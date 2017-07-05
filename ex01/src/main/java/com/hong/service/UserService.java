package com.hong.service;

import com.hong.domain.UserVO;
import com.hong.dto.LoginDTO;

public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
}
