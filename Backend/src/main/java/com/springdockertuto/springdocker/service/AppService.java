package com.springdockertuto.springdocker.service;

import java.util.List;

import com.springdockertuto.springdocker.dao.AppDBRepository;
import com.springdockertuto.springdocker.model.AppModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private AppDBRepository _appRepo;

    public List<AppModel> getItemList() {

        return this._appRepo.findAll();
    }

    public AppModel addItem(AppModel model) {
        return this._appRepo.save(model);
    }
}