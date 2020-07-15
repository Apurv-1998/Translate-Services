package com.mobileapplication.exposys.translate.utils;

import org.springframework.stereotype.Component;

@Component
public class AdminVerification {

	public boolean isVerified(String id,String username,String password) {
		return id.equals("admin_123") && username.equals("admin") && password.equals("root");
	}
}
