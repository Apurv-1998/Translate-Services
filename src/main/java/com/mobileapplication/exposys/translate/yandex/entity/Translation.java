package com.mobileapplication.exposys.translate.yandex.entity;

import java.util.List;

public class Translation {
	
	private int code;
	private String language;
	private List<String> text;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	
	
	

}
