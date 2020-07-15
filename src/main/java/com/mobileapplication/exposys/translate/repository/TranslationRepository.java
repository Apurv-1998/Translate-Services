package com.mobileapplication.exposys.translate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobileapplication.exposys.translate.entity.TranslateEntity;

@Repository
public interface TranslationRepository extends CrudRepository<TranslateEntity, Long> {

}
