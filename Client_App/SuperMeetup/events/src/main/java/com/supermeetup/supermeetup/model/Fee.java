package com.supermeetup.supermeetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yuxin on 10/13/17.
 *  {
 *      "accepts": "cash",
 *      "amount": 15,
 *      "currency": "USD",
 *      "description": "per person",
 *      "label": "Price",
 *      "required": false
 *  }
 */

public class Fee {

    @SerializedName("accepts")
    @Expose
    private String accepts;
    @SerializedName("amount")
    @Expose
    private long amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("required")
    @Expose
    private boolean required;

    public String getAccepts() {
        return accepts;
    }

    public void setAccepts(String accepts) {
        this.accepts = accepts;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

}
