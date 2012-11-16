/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateSerializer;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHAF 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionDTO implements Serializable {
    
    private String id;
    
    private Date billingDate;
    
    private Date valueDate;
    
    private float amount;
    
    private String revenueType;
    
    private String name;
    
    private String account;
    
    private String bankCode;
    
    private String purpose;
    
    private String category;
    
    private String categoryIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonSerialize(using=CustomJsonDateSerializer.class)
    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    @JsonSerialize(using=CustomJsonDateSerializer.class)
    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getRevenueType() {
        return revenueType;
    }

    public void setRevenueType(String revenueType) {
        this.revenueType = revenueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" + "id=" + id + ", billingDate=" + billingDate + ", valueDate=" + valueDate + ", amount=" + amount + ", revenueType=" + revenueType + ", name=" + name + ", account=" + account + ", bankCode=" + bankCode + ", purpose=" + purpose + ", category=" + category + ", categoryIcon=" + categoryIcon + '}';
    }

}
