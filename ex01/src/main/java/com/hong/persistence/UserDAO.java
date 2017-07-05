package com.hong.persistence;

import com.hong.domain.UserVO;
import com.hong.dto.LoginDTO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;
}
