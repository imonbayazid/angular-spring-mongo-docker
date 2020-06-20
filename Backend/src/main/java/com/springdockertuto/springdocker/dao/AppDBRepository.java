package com.springdockertuto.springdocker.dao;

import com.springdockertuto.springdocker.model.AppModel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppDBRepository extends MongoRepository<AppModel, String> {

}