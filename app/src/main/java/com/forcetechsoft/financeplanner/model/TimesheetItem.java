package com.forcetechsoft.financeplanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimesheetItem {

    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("itemName")
    private String itemName;

    @Expose
    @SerializedName("amountPlanned")
    private int amountPlanned;

    @Expose
    @SerializedName("amountPaid")
    private int amountPaid;

    @Expose
    @SerializedName("paid")
    private boolean paid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmountPlanned() {
        return amountPlanned;
    }

    public void setAmountPlanned(int amountPlanned) {
        this.amountPlanned = amountPlanned;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
