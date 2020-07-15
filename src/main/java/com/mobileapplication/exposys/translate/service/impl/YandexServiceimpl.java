package com.mobileapplication.exposys.translate.service.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.mobileapplication.exposys.translate.service.YandexService;
import com.mobileapplication.exposys.translate.utils.Constants;
import com.mobileapplication.exposys.translate.yandex.entity.SupportedLanguage;
import com.mobileapplication.exposys.translate.yandex.entity.Translation;


@Service
@Configuration
@EnableAsync
public class YandexServiceimpl implements YandexService {

	final static Logger logger = LoggerFactory.getLogger(YandexServiceimpl.class);
	
	@Value("${yandex.api.key}")
	private String apiKey;
	
	@Value("${yandex.base.url}")
	private String baseURL;
	
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private MetricRegistry registry;
	
	@Override
	@Cacheable(
				value = "gettranslationCache",
				key = "T(java.util.Objects).hash(#text,#lang)"
			)
	@Async
	public Future<ResponseEntity<Translation>> getTranslation(String text, String lang) {
		logger.info("Text -> {}, Lang -> {}",text,lang);
		
		Counter thirdPartyRequests = registry.counter("yandex.requests");
		
		StringBuilder url = new StringBuilder(baseURL);
		
		url.append(Constants.TRANSLATION_URL);
		url.append(Constants.KEY).append(Constants.EQUAL_SIGN).append(apiKey).append(Constants.AMPERSAND_SIGN);
		url.append(Constants.TEXT_TO_TRANSLATE).append(Constants.EQUAL_SIGN).append(text).append(Constants.AMPERSAND_SIGN);
		url.append(Constants.TARGET_LANGUAGE).append(Constants.EQUAL_SIGN).append(lang);
		
		logger.info("Translation Url -> {}",url.toString());
		
		ResponseEntity<Translation> translation = template.getForEntity(url.toString(),Translation.class);
		
		thirdPartyRequests.inc();
		
		return new AsyncResult<ResponseEntity<Translation>>(translation);
	}

	@Override
	@Cacheable(value = "supportedlanguageCache")
	public ResponseEntity<SupportedLanguage> getSupportedLanguage() {

		StringBuilder url = new StringBuilder(baseURL);
		
		url.append(Constants.GET_LANGS_URL);
		url.append(Constants.KEY).append(Constants.EQUAL_SIGN).append(apiKey);
		
		logger.info("Get Supported Languages -> {}",url);
		
		ResponseEntity<SupportedLanguage> supportedLang = template.getForEntity(url.toString(),SupportedLanguage.class);
		
		return supportedLang;
	}

}
