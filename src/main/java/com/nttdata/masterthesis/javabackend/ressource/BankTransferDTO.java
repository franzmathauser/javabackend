/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Resource for banktransfer content.
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@JsonIgnoreProperties( ignoreUnknown = true )
public class BankTransferDTO implements Serializable
{
    private String name;

    private String accountNumber;

    private String bankCode;

    private String amount;

    private String purpose1;

    private String purpose2;

    private String purpose3;

    private String purpose4;

    private String purpose5;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber( String accountNumber )
    {
        this.accountNumber = accountNumber;
    }

    public String getBankCode()
    {
        return bankCode;
    }

    public void setBankCode( String bankCode )
    {
        this.bankCode = bankCode;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount( String amount )
    {
        this.amount = amount;
    }

    public String getPurpose1()
    {
        return purpose1;
    }

    public void setPurpose1( String purpose1 )
    {
        this.purpose1 = purpose1;
    }

    public String getPurpose2()
    {
        return purpose2;
    }

    public void setPurpose2( String purpose2 )
    {
        this.purpose2 = purpose2;
    }

    public String getPurpose3()
    {
        return purpose3;
    }

    public void setPurpose3( String purpose3 )
    {
        this.purpose3 = purpose3;
    }

    public String getPurpose4()
    {
        return purpose4;
    }

    public void setPurpose4( String purpose4 )
    {
        this.purpose4 = purpose4;
    }

    public String getPurpose5()
    {
        return purpose5;
    }

    public void setPurpose5( String purpose5 )
    {
        this.purpose5 = purpose5;
    }
}
