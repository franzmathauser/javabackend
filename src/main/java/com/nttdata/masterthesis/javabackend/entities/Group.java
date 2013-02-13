/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.entities;

/**
 * Enumeration for user group.
 * @author MATHAF
 */
public enum Group
{
    /**
     * user group administrator.
     */
    ADMINISTRATOR,
    /**
     * user group for registered users.
     */
    USER,
    /**
     * user group for unregistered users.
     */
    DEFAULT;

}
