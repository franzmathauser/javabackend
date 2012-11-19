/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;

/**
 *
 * @author MATHAF
 */
public abstract class AbstractSecureTest
{

    private final String cnName = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.CN_NAME );
    private final String user = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.JUNIT_TESTUSER1_NAME );
    private final String password = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.JUNIT_TESTUSER1_PASSWORD );

    public String getCnName()
    {
        return cnName;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }
}
