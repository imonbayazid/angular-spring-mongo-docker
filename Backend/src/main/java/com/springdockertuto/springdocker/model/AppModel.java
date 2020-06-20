package com.springdockertuto.springdocker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AppDocument")
public class AppModel {

    @Id
    private String itemID;
    private String itemName;

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public AppModel(String itemID, String itemName) {
        this.itemID = itemID;
        this.itemName = itemName;
    }
}