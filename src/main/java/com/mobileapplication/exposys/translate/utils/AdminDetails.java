package com.mobileapplication.exposys.translate.utils;

public enum AdminDetails {
	
	ADMIN_USERNAME("admin"),
	ADMIN_PASSWORD("root");
	
	
	private String adminDetails;

	private AdminDetails(String adminDetails) {
		this.adminDetails = adminDetails;
	}

	public String getAdminDetails() {
		return adminDetails;
	}

	public void setAdminDetails(String adminDetails) {
		this.adminDetails = adminDetails;
	}
	
	
}
