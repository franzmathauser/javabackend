/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MATHAF
 */
@Entity
@Table( name = "mapped_category" )
public class MappedCategory
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String mappedId;
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

    public String getMappedId()
    {
        return mappedId;
    }

    public void setMappedId( String mappedId )
    {
        this.mappedId = mappedId;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory( Category category )
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "MappedCategory{" + "id=" + id + ", mappedId=" + mappedId + ", category=" + category + '}';
    }
}
