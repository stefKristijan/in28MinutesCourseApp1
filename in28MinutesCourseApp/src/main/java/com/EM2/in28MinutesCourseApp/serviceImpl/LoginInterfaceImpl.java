package com.EM2.in28MinutesCourseApp.serviceImpl;

import org.springframework.stereotype.Service;

import com.EM2.in28MinutesCourseApp.service.LoginService;

@Service
public class LoginInterfaceImpl implements LoginService{

	@Override
	public boolean validateUser(String username, String password) {
		return username.equalsIgnoreCase("kico")&&password.equalsIgnoreCase("kico");
	}

}
