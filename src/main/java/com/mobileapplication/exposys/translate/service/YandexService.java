package com.mobileapplication.exposys.translate.service;

import java.util.concurrent.Future;

import org.springframework.http.ResponseEntity;

import com.mobileapplication.exposys.translate.yandex.entity.SupportedLanguage;
import com.mobileapplication.exposys.translate.yandex.entity.Translation;

public interface YandexService {
	
	public Future<ResponseEntity<Translation>> getTranslation(String text,String lang);
	
	public ResponseEntity<SupportedLanguage> getSupportedLanguage();
}
