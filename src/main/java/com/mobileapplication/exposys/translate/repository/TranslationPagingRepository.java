package com.mobileapplication.exposys.translate.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mobileapplication.exposys.translate.entity.TranslateEntity;

@Repository
public interface TranslationPagingRepository extends PagingAndSortingRepository<TranslateEntity, Long> {

}
