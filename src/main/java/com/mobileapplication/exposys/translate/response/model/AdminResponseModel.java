package com.mobileapplication.exposys.translate.response.model;

public class AdminResponseModel {

	private long id;
	private String text;
	private String translationlanguage;
	private String translatedText;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTranslationlanguage() {
		return translationlanguage;
	}
	public void setTranslationlanguage(String translationlanguage) {
		this.translationlanguage = translationlanguage;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
	
	
	
}
