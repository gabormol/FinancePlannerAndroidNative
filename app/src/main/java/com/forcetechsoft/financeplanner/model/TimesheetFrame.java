package com.forcetechsoft.financeplanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimesheetFrame {

    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("year")
    private String year;

    @Expose
    @SerializedName("month")
    private int month;

    @Expose
    @SerializedName("items")
    private List<TimesheetItem> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<TimesheetItem> getItems() {
        return items;
    }

    public void setItems(List<TimesheetItem> items) {
        this.items = items;
    }
}
