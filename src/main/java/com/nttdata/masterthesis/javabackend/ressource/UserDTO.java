/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resource for user content.
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class UserDTO implements Serializable
{
    private Long id;

    private String userName;

    private String email;

    private int countLoginErrors;

    private Date lastLogin;

    private Long allowedBankAccountId;

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

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public int getCountLoginErrors()
    {
        return countLoginErrors;
    }

    public void setCountLoginErrors( int countLoginErrors )
    {
        this.countLoginErrors = countLoginErrors;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin( Date lastLogin )
    {
        this.lastLogin = lastLogin;
    }

    public Long getAllowedBankAccountId()
    {
        return allowedBankAccountId;
    }

    public void setAllowedBankAccountId( Long allowedBankAccountId )
    {
        this.allowedBankAccountId = allowedBankAccountId;
    }
}
