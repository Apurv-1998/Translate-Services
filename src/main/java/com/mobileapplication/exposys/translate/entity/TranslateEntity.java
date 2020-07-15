package com.mobileapplication.exposys.translate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "translate_table")
public class TranslateEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String text;
	@Column(nullable = false,length = 10)
	private String translationLanguage;
	@Column
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
	public String getTranslationLanguage() {
		return translationLanguage;
	}
	public void setTranslationLanguage(String translationLanguage) {
		this.translationLanguage = translationLanguage;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
	
	
	

}
