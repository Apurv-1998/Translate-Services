package com.mobileapplication.exposys.translate.dto;

import java.io.Serializable;

public class TranslateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1231457525004163222L;
	

	private String text;
	private String translationLanguage;
	
	public TranslateDto() {
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTranslationLanguage() {
		return translationLanguage;
	}

	public void setTranslationLanguage(String translationLanguage) {
		this.translationLanguage = translationLanguage;
	}
	
	

}
