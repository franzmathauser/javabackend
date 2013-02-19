/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
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
 * Entity bean for category.
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
    private List<Transaction> transactions;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "bank_account_id" )
    private BankAccount bankAccount;

    @OneToMany( mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Set<MappedCategory> mappedCategory;

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

    public Set<MappedCategory> getMappedCategory()
    {
        return mappedCategory;
    }

    public void setMappedCategory( Set<MappedCategory> mappedCategory )
    {
        this.mappedCategory = mappedCategory;
    }
}
