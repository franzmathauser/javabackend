/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

/**
 * Interface defines getter and setter for icon url access.
 * This is used for the icon path interceptor.
 * @author MATHAF
 */
public interface CategoryContainer
{

    /**
     * getter to access category name
     * @return url path to icon
     */
    String getCategoryName();


    /**
     * getter to access category icon url.
     * @return url path to icon
     */
    String getCategoryIconUrl();

    /**
     * setter to set category icon url.
     * @param urlPath url path to icon
     */
    void setCategoryIconUrl( String urlPath );


}
