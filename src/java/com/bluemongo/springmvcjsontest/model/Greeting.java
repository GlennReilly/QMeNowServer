package com.bluemongo.springmvcjsontest.model;

import com.google.gson.Gson;

import java.io.Serializable;


public class Greeting implements Serializable{


    private static final long serialVersionUID = -1576547040362820422L;
    private final long id;
    private final String content;
    private Status statusNEW;

    public Greeting(long id, String content, Status status) {
        this.id = id;
        this.content = content;
        this.statusNEW = status;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

