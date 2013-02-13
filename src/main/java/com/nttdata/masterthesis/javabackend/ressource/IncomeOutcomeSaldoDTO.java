/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resource for news content.
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class IncomeOutcomeSaldoDTO
{
    private String name;

    private float income;

    private float outcome;

    private float saldo;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public float getIncome()
    {
        return income;
    }

    public void setIncome( float income )
    {
        this.income = income;
    }

    public float getOutcome()
    {
        return outcome;
    }

    public void setOutcome( float outcome )
    {
        this.outcome = outcome;
    }

    public float getSaldo()
    {
        return saldo;
    }

    public void setSaldo( float saldo )
    {
        this.saldo = saldo;
    }
}
