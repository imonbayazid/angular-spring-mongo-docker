package com.springdockertuto.springdocker.controller;

import java.util.List;

import com.springdockertuto.springdocker.model.AppModel;
import com.springdockertuto.springdocker.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private AppService _appService;
    
    @CrossOrigin
    @GetMapping("/getItems")
    public List<AppModel> getItems() {
        return this._appService.getItemList();
    }
    
    @CrossOrigin
    @PostMapping("/addItem")
    public AppModel addItem(@RequestBody AppModel model) {
        return this._appService.addItem(model);
    }
}