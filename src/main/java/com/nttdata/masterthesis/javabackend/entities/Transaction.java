/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Entity bean for transaction.
 * @author MATHAF
 */
@Entity
@Table( name = "bank_account_transaction" )
public class Transaction implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "billing_date" )
    @Temporal( javax.persistence.TemporalType.DATE )
    private Date billingDate;

    @Column( name = "value_date" )
    @Temporal( javax.persistence.TemporalType.DATE )
    private Date valueDate;

    private float amount;

    @Column( name = "revenue_type" )
    private String revenueType;

    private String name;

    private String account;

    @Column( name = "bankcode" )
    private String bankCode;

    private String purpose;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "bank_account_id" )
    private BankAccount bankAccount;

    @ManyToOne( fetch = FetchType.EAGER )
    private Category category;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Date getBillingDate()
    {
        return billingDate;
    }

    public void setBillingDate( Date billingDate )
    {
        this.billingDate = billingDate;
    }

    public Date getValueDate()
    {
        return valueDate;
    }

    public void setValueDate( Date valueDate )
    {
        this.valueDate = valueDate;
    }

    public float getAmount()
    {
        return amount;
    }

    public void setAmount( float amount )
    {
        this.amount = amount;
    }

    public String getRevenueType()
    {
        return revenueType;
    }

    public void setRevenueType( String revenueType )
    {
        this.revenueType = revenueType;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount( String account )
    {
        this.account = account;
    }

    public String getBankCode()
    {
        return bankCode;
    }

    public void setBankCode( String bankCode )
    {
        this.bankCode = bankCode;
    }

    public String getPurpose()
    {
        return purpose;
    }

    public void setPurpose( String purpose )
    {
        this.purpose = purpose;
    }

    public BankAccount getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount( BankAccount bankAccount )
    {
        this.bankAccount = bankAccount;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory( Category category )
    {
        this.category = category;
    }

}
