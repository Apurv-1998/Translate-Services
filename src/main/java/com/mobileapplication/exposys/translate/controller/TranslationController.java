package com.mobileapplication.exposys.translate.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapplication.exposys.translate.dto.TranslateDto;
import com.mobileapplication.exposys.translate.exceptions.TranslationServiceException;
import com.mobileapplication.exposys.translate.request.model.TranslateDetailModel;
import com.mobileapplication.exposys.translate.response.model.AdminResponseModel;
import com.mobileapplication.exposys.translate.response.model.TranslationResponse;
import com.mobileapplication.exposys.translate.service.TranslationService;
import com.mobileapplication.exposys.translate.utils.AdminDetails;
import com.mobileapplication.exposys.translate.utils.AdminVerification;
import com.mobileapplication.exposys.translate.utils.ErrorMessages;
import com.mobileapplication.exposys.translate.yandex.entity.SupportedLanguage;

@RestController
@RequestMapping("/translate")
public class TranslationController {
	
	@Autowired
	TranslationService translateService;
	
	@Autowired
	AdminVerification adminVerification;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<TranslationResponse> translate(@RequestBody TranslateDetailModel translationDetails) throws Exception {
		/*
		 * 1)Copy the details to the Dto
		 * 2)Use the dto to get the work done
		 * 3)Transfer the result into a response
		 * */
		
		ModelMapper mapper = new ModelMapper();
		
		TranslateDto translateDto = new TranslateDto();
		
		translateDto = mapper.map(translationDetails, TranslateDto.class);
		
		
		ResponseEntity<TranslationResponse> responseReceived = translateService.getTranslation(translateDto); //NULL -> ERROR
		
		if(responseReceived==null)
			throw new TranslationServiceException(ErrorMessages.NULL_REQUEST_BODY_FIELDS.getErrorMessages());
		
/*------------------- Adding Supported Language Link -------------------------------------------------------*/		
		Link supportedLanguages = WebMvcLinkBuilder.linkTo(TranslationController.class).slash("lang").withRel("Supported Languages List");
		
		responseReceived.getBody().add(supportedLanguages);
		
		return responseReceived;
	}
	
	/*
	 * 1) HATEOAS of All Supported Languages in This -> Done
	 * 2) Get All -> History -> After Authorization -> Of Admin -> Sentiment-Analysis -> Apply Paging -> Done
	 * 3) Custom Exception
	 * 4) Swagger Documentation
	 * 5) Get All Supported language -> Done
	 * 
	 * */

	@GetMapping(path = "/lang",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseEntity<SupportedLanguage> getSupportedLanguage(){
		
		ResponseEntity<SupportedLanguage> response = translateService.getSupportedLanguages();
		
		if(response==null)
			throw new TranslationServiceException(ErrorMessages.CONNECTION_TO_SERVER_CANNOT_BE_MADE.getErrorMessages());
			
		return response;
		
	}
	
	
	@GetMapping(path = "/admin/{adminId}/access_databse",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<AdminResponseModel> getAccsessToDatabase(@PathVariable String adminId,@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "2") int limit){
			
		if(!adminVerification.isVerified(adminId,AdminDetails.ADMIN_USERNAME.getAdminDetails(),AdminDetails.ADMIN_PASSWORD.getAdminDetails()))
			throw new TranslationServiceException(ErrorMessages.ADMIN_NOT_VERIFIED.getErrorMessages());
		
		
		List<AdminResponseModel> response = translateService.getDatabaseValues(page,limit);
		
		return response;
		
	}
	
	
}
