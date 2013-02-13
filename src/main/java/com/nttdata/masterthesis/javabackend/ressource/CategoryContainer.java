/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
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
