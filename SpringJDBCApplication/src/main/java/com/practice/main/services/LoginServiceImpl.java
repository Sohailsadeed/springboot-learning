package com.practice.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.main.beans.Student;
import com.practice.main.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;
	
	@Override
	public List<Student> loginService(String email, String password) {
		List<Student> studList = loginDao.loginStudent(email, password);
		return studList;
	}

}
