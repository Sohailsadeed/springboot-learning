package com.practice.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.main.beans.Student;
import com.practice.main.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;
	
	@Override
	public Student loginService(String email, String password) {
		Student stud = loginDao.loginStudent(email, password);
		return stud;
	}

	@Override
	public boolean registerService(Student std) {
		int updatedValues=loginDao.registerStudent(std);
		if(updatedValues>=1)
			return true;
		return false;
	}
	
}
