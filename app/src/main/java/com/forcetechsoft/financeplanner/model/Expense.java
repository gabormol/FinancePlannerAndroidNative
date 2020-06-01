package com.forcetechsoft.financeplanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expense {

    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("expensename")
    private String expenseName;

    @Expose
    @SerializedName("amount")
    private int amount;

    @Expose
    @SerializedName("frequency")
    private int frequency;

    @Expose
    @SerializedName("nextmonth")
    private int nextMonth;

    @Expose
    @SerializedName("duetomonth")
    private int duetoMonth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getNextMonth() {
        return nextMonth;
    }

    public void setNextMonth(int nextMonth) {
        this.nextMonth = nextMonth;
    }

    public int getDuetoMonth() {
        return duetoMonth;
    }

    public void setDuetoMonth(int duetoMonth) {
        this.duetoMonth = duetoMonth;
    }
}
