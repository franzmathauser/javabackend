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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateDeserializer;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateSerializer;

/**
 * Resource for transacton content.
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class TransactionDTO implements Serializable, CategoryContainer, Comparable<TransactionDTO>
{
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

    private long categoryId;

    private long billingDateMillis;

    public long getBillingDateMillis()
    {
        return billingDateMillis;
    }

    public void setBillingDateMillis( long billingDateMillis )
    {
        this.billingDateMillis = billingDateMillis;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    @JsonSerialize( using = CustomJsonDateSerializer.class )
    public Date getBillingDate()
    {
        return billingDate;
    }

    @JsonDeserialize( using = CustomJsonDateDeserializer.class )
    public void setBillingDate( Date billingDate )
    {
        this.billingDate = billingDate;
    }

    @JsonSerialize( using = CustomJsonDateSerializer.class )
    public Date getValueDate()
    {
        return valueDate;
    }

    @JsonDeserialize( using = CustomJsonDateDeserializer.class )
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

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }

    public String getCategoryIcon()
    {
        return categoryIcon;
    }

    public void setCategoryIcon( String categoryIcon )
    {
        this.categoryIcon = categoryIcon;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId( long categoryId )
    {
        this.categoryId = categoryId;
    }

    @Override
    public String getCategoryName()
    {
        return getCategory();
    }

    @Override
    public String getCategoryIconUrl()
    {
        return getCategoryIcon();
    }

    @Override
    public void setCategoryIconUrl( String urlPath )
    {
        setCategoryIcon( urlPath );
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 47 * hash + ( this.id != null ? this.id.hashCode() : 0 );
        hash = 47 * hash + ( this.billingDate != null ? this.billingDate.hashCode() : 0 );
        hash = 47 * hash + ( this.valueDate != null ? this.valueDate.hashCode() : 0 );
        hash = 47 * hash + Float.floatToIntBits( this.amount );
        hash = 47 * hash + ( this.revenueType != null ? this.revenueType.hashCode() : 0 );
        hash = 47 * hash + ( this.name != null ? this.name.hashCode() : 0 );
        hash = 47 * hash + ( this.account != null ? this.account.hashCode() : 0 );
        hash = 47 * hash + ( this.bankCode != null ? this.bankCode.hashCode() : 0 );
        hash = 47 * hash + ( this.purpose != null ? this.purpose.hashCode() : 0 );
        hash = 47 * hash + ( this.category != null ? this.category.hashCode() : 0 );
        hash = 47 * hash + ( this.categoryIcon != null ? this.categoryIcon.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final TransactionDTO other = (TransactionDTO) obj;
        if ( ( this.id == null ) ? ( other.id != null ) : !this.id.equals( other.id ) )
        {
            return false;
        }
        if ( this.billingDate != other.billingDate && ( this.billingDate == null || !this.billingDate.equals( other.billingDate ) ) )
        {
            return false;
        }
        if ( this.valueDate != other.valueDate && ( this.valueDate == null || !this.valueDate.equals( other.valueDate ) ) )
        {
            return false;
        }
        if ( Float.floatToIntBits( this.amount ) != Float.floatToIntBits( other.amount ) )
        {
            return false;
        }
        if ( ( this.revenueType == null ) ? ( other.revenueType != null ) : !this.revenueType.equals( other.revenueType ) )
        {
            return false;
        }
        if ( ( this.name == null ) ? ( other.name != null ) : !this.name.equals( other.name ) )
        {
            return false;
        }
        if ( ( this.account == null ) ? ( other.account != null ) : !this.account.equals( other.account ) )
        {
            return false;
        }
        if ( ( this.bankCode == null ) ? ( other.bankCode != null ) : !this.bankCode.equals( other.bankCode ) )
        {
            return false;
        }
        if ( ( this.purpose == null ) ? ( other.purpose != null ) : !this.purpose.equals( other.purpose ) )
        {
            return false;
        }
        if ( ( this.category == null ) ? ( other.category != null ) : !this.category.equals( other.category ) )
        {
            return false;
        }
        if ( ( this.categoryIcon == null ) ? ( other.categoryIcon != null ) : !this.categoryIcon.equals( other.categoryIcon ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo( TransactionDTO o )
    {
        long diff = o.getBillingDate().getTime() - this.getBillingDate().getTime();

        if ( diff > 0 )
        {
            return 1;
        }
        else if ( diff < 0 )
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
