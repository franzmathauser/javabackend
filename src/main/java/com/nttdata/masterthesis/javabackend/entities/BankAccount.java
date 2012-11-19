/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author MATHAF
 */
@Entity
@Table( name = "bank_account" )
public class BankAccount implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    @Column( name = "firstname" )
    private String firstName;
    @Column( name = "lastname" )
    private String lastName;
    @Column( name = "create_date" )
    @Temporal( javax.persistence.TemporalType.DATE )
    private Date createDate;
    @Column( name = "account_number" )
    private String accountNumber;
    @OneToMany( mappedBy = "bankAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Transaction> transactions;
    @OneToMany( mappedBy = "bankAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Category> categories;
    @OneToOne( mappedBy = "bankAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private User user;
    @ManyToOne( fetch = FetchType.LAZY )
    private Bank bank;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate( Date createDate )
    {
        this.createDate = createDate;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber( String accountNumber )
    {
        this.accountNumber = accountNumber;
    }

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions( List<Transaction> transactions )
    {
        this.transactions = transactions;
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void setCategories( List<Category> categories )
    {
        this.categories = categories;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }

    public Bank getBank()
    {
        return bank;
    }

    public void setBank( Bank bank )
    {
        this.bank = bank;
    }
}
