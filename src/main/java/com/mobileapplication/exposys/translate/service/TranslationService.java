package com.mobileapplication.exposys.translate.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;

import com.mobileapplication.exposys.translate.dto.TranslateDto;
import com.mobileapplication.exposys.translate.response.model.AdminResponseModel;
import com.mobileapplication.exposys.translate.response.model.TranslationResponse;
import com.mobileapplication.exposys.translate.yandex.entity.SupportedLanguage;


public interface TranslationService {

	ResponseEntity<TranslationResponse> getTranslation(TranslateDto translateDto) throws InterruptedException, ExecutionException;
	
	ResponseEntity<SupportedLanguage> getSupportedLanguages();
	
	List<AdminResponseModel> getDatabaseValues(int page,int limit);
	
}
