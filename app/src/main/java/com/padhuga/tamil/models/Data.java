package com.padhuga.tamil.models;

import java.util.ArrayList;

public class Data {
    private String title;
    private String soothiram;
    private String desc;
    private String example;
    private ArrayList<Data> type;

    private String parentCount;
    private ArrayList<String> parentHeading;
    private String parentType;
    private ArrayList<Integer> items;
    private ArrayList<Results> result;

    public String getTitle() {
        return title;
    }

    public String getSoothiram() {
        return soothiram;
    }

    public String getDesc() {
        return desc;
    }

    public String getExample() {
        return example;
    }

    public ArrayList<Data> getType() {
        return type;
    }


    public String getParentCount() {
        return parentCount;
    }

    public ArrayList<String> getParentHeading() {
        return parentHeading;
    }

    public String getParentType() {
        return parentType;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public ArrayList<Results> getResult() {
        return result;
    }
}
