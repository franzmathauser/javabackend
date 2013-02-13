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
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateSerializer;

/**
 * Entity bean for user.
 * @author MATHAF
 */
@Entity
@Table( name = "account_user" )
@Cacheable( false )
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "username", nullable = false )
    private String userName;

    @Column( nullable = false ) //sha-512 + hex
    private String password;

    private String email;

    @Column( name = "count_login_errors" )
    private int countLoginErrors;

    @Column( name = "last_login_date" )
    @Temporal( javax.persistence.TemporalType.DATE )
    private Date lastLogin;

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "bank_account_id" )
    private BankAccount bankAccount;

    @ElementCollection( targetClass = Group.class )
    @CollectionTable( name = "user_group",
                      joinColumns =
    @JoinColumn( name = "account_user_id", nullable = false, referencedColumnName = "id" ), uniqueConstraints =
    {
        @UniqueConstraint( columnNames =
        {
            "account_user_id", "groupname"
        } )
    } )
    @Enumerated( EnumType.STRING )
    @Column( name = "groupname", length = 64, nullable = false )
    private List<Group> groups;

    // TODO Constructor which hashes the password
    //DigestUtils.sha512Hex
    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    @JsonSerialize( using = CustomJsonDateSerializer.class )
    public Date getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin( Date lastLogin )
    {
        this.lastLogin = lastLogin;
    }

    public int getCountLoginErrors()
    {
        return countLoginErrors;
    }

    public void setCountLoginErrors( int countLoginErrors )
    {
        this.countLoginErrors = countLoginErrors;
    }

    public BankAccount getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount( BankAccount bankAccount )
    {
        this.bankAccount = bankAccount;
    }

    public List<Group> getGroups()
    {
        return groups;
    }

    public void setGroups( List<Group> groups )
    {
        this.groups = groups;
    }
}
