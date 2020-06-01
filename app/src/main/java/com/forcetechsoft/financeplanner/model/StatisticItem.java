package com.forcetechsoft.financeplanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticItem {

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
    @SerializedName("plannedToSpend")
    private int plannedToSpend;

    @Expose
    @SerializedName("totalSpent")
    private int totalSpent;

    @Expose
    @SerializedName("remainToPay")
    private int remainToPay;

    @Expose
    @SerializedName("balance")
    private int balance;

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

    public int getPlannedToSpend() {
        return plannedToSpend;
    }

    public void setPlannedToSpend(int plannedToSpend) {
        this.plannedToSpend = plannedToSpend;
    }

    public int getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    public int getRemainToPay() {
        return remainToPay;
    }

    public void setRemainToPay(int remainToPay) {
        this.remainToPay = remainToPay;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
