package com.bluemongo.springmvcjsontest.model;

import com.google.gson.Gson;

import java.io.Serializable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Greeting")
public class Greeting implements Serializable{


    private static final long serialVersionUID = -1576547040362820422L;
    private final long id;
    private final String content;
    private Status statusNEW;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Greeting(long id, String content, Status status) {
        this.id = id;
        this.content = content;
        this.statusNEW = status;
    }

    @DynamoDBHashKey(attributeName = "Id")
    public long getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "Content")
    public String getContent() {
        return content;
    }

    public enum Status {
        ERROR, OK, WARNING
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public static Greeting constructFromJson(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Greeting.class);
    }
}

