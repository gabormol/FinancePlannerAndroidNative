package com.forcetechsoft.financeplanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("admin")
    private boolean admin;

    @Expose
    @SerializedName("currencyDecimals")
    private int currencyDecimals;

    @Expose
    @SerializedName("currencySymbol")
    private String currencySymbol;

    @Expose
    @SerializedName("lastname")
    private String lastname;

    @Expose
    @SerializedName("firstname")
    private String firstname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getCurrencyDecimals() {
        return currencyDecimals;
    }

    public void setCurrencyDecimals(int currencyDecimals) {
        this.currencyDecimals = currencyDecimals;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
