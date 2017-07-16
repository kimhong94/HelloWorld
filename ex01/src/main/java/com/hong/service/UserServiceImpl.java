package com.hong.service;

import java.sql.Date;

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

	@Override
	public void keepLogin(String userid, String sessionId, Date next) throws Exception {
		dao.keepLogin(userid, sessionId, next);
	}

	@Override
	public UserVO checkLoginBefore(String value) {
		return dao.checkUserWithSessionKey(value);
	}

}
