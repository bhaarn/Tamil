package com.padhuga.tamil.models;

public class SearchRetriever {
    private String title;
    private int position;
    private int groupPosition;
    private int childPosition;

    public SearchRetriever(String title, int position, int groupPosition, int childPosition) {
        this.title = title;
        this.position = position;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public int getChildPosition() {
        return childPosition;
    }
}
