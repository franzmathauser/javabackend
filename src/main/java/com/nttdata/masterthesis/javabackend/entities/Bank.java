/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bank entity bean for bank.
 * @author MATHAF
 */
@Entity
@Table( name = "bank" )
public class Bank implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String name;

    @Column( name = "bankcode" )
    private String bankCode;

    @OneToMany( mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<BankAccount> bankAccounts;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getBankCode()
    {
        return bankCode;
    }

    public void setBankCode( String bankCode )
    {
        this.bankCode = bankCode;
    }

    public List<BankAccount> getBankAccounts()
    {
        return bankAccounts;
    }

    public void setBankAccounts( List<BankAccount> bankAccounts )
    {
        this.bankAccounts = bankAccounts;
    }
}
