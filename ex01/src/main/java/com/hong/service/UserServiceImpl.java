package com.hong.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.hong.domain.UserVO;
import com.hong.dto.LoginDTO;
import com.hong.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

}
