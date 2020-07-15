package com.mobileapplication.exposys.translate.request.model;

import java.io.Serializable;

public class TranslateDetailModel implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3959651860521511207L;
	
	
	
	private String text;
	private String translationLanguage;
	
	public TranslateDetailModel() {
		
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
