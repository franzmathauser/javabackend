/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MATHAF
 */
@Entity
@Table( name = "category" )
public class Category implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String name;
    @OneToMany( mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    List<Transaction> transactions;
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "bank_account_id" )
    BankAccount bankAccount;
    @OneToMany( mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    Set<MappedCategory> mappedCategory;

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

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions( List<Transaction> transactions )
    {
        this.transactions = transactions;
    }

    public BankAccount getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount( BankAccount bankAccount )
    {
        this.bankAccount = bankAccount;
    }
}
