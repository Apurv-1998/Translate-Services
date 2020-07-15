package com.mobileapplication.exposys.translate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobileapplication.exposys.translate.dto.TranslateDto;
import com.mobileapplication.exposys.translate.entity.TranslateEntity;
import com.mobileapplication.exposys.translate.exceptions.TranslationServiceException;
import com.mobileapplication.exposys.translate.repository.TranslationPagingRepository;
import com.mobileapplication.exposys.translate.repository.TranslationRepository;
import com.mobileapplication.exposys.translate.response.model.AdminResponseModel;
import com.mobileapplication.exposys.translate.response.model.TranslationResponse;
import com.mobileapplication.exposys.translate.service.TranslationService;
import com.mobileapplication.exposys.translate.service.YandexService;
import com.mobileapplication.exposys.translate.utils.ConvertListToString;
import com.mobileapplication.exposys.translate.utils.ErrorMessages;
import com.mobileapplication.exposys.translate.yandex.entity.SupportedLanguage;
import com.mobileapplication.exposys.translate.yandex.entity.Translation;

@Service
public class TranslationServiceImpl implements TranslationService {

	@Autowired
	public TranslationRepository translateRepository;
	
	@Autowired
	private YandexService thirdPartyService;
	
	@Autowired
	private ConvertListToString convert;
	
	@Autowired
	TranslationPagingRepository pagingRepository;
	
	
	
	

	private final Logger logger = LoggerFactory.getLogger(TranslationServiceImpl.class);
	
	@Override
	public ResponseEntity<TranslationResponse> getTranslation(TranslateDto translateDto)
			throws InterruptedException, ExecutionException {
		
		ModelMapper mapper = new ModelMapper();
		
	
	/*---------------------- Working On The Method -----------------------------------*/
		Future<ResponseEntity<Translation>> response = thirdPartyService.getTranslation(translateDto.getText(), translateDto.getTranslationLanguage());
		
		
		if(translateDto.getText()!=null && translateDto.getTranslationLanguage()!=null && response==null)
			throw new TranslationServiceException(ErrorMessages.NOT_A_SUPPORTED_LANGUAGE.getErrorMessages());
		
		ResponseEntity<Translation> translation = response.get();
		
		
		if(HttpStatus.OK.value() <= translation.getStatusCode().value() && translation.getStatusCode().value() < HttpStatus.MULTIPLE_CHOICES.value()) {
			Map<String, Object> responseMap = new HashMap<>();
			
			
			/*-------------- Saving The Response In The Database -----------------------*/
			TranslateEntity translateEntity = mapper.map(translateDto,TranslateEntity.class);
			
			translateEntity.setTranslatedText(convert.convertListToString(translation.getBody().getText()));
			
			TranslateEntity finalEntity = translateRepository.save(translateEntity);
			
			translateDto = mapper.map(finalEntity, TranslateDto.class);
			
		/*------------------ Continuing The Method ---------------------------------*/	
			responseMap.put("text", translation.getBody().getText());
			
			logger.info("The Response Map -> {}",responseMap);
			
			return new ResponseEntity<TranslationResponse>(new TranslationResponse(HttpStatus.OK.value(), responseMap),HttpStatus.OK);
		}
		
		return new ResponseEntity<TranslationResponse>(new TranslationResponse(translation.getStatusCodeValue()),translation.getStatusCode());
	}

	@Override
	public ResponseEntity<SupportedLanguage> getSupportedLanguages() {
			return thirdPartyService.getSupportedLanguage();
	}
	
	

	@Override
	public List<AdminResponseModel> getDatabaseValues(int page, int limit) {
		
		ModelMapper mapper = new ModelMapper();
		
		List<AdminResponseModel> response = new ArrayList<>();
		
		
		if(page>0)
			page-=1;
		
		
		Pageable pageableRequest = PageRequest.of(page,limit);
		
		Page<TranslateEntity> dataPage = pagingRepository.findAll(pageableRequest);
		
		List<TranslateEntity> translations = dataPage.getContent();
		
		
		for(TranslateEntity entity:translations) {
			AdminResponseModel responseModel = mapper.map(entity,AdminResponseModel.class);
			
			response.add(responseModel);
		}
		
		
		
		
		return response;
	}

	

}
