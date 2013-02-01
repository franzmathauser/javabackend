/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest.security;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.sun.jersey.api.representation.Form;

/**
 * Abstract Secure Test provides values for secure REST-Service Tests.
 * @author MATHAF
 */
public abstract class AbstractSecureTest
{
    private final String cnName = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.CN_NAME );
    private final String user = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.JUNIT_TESTUSER1_NAME );
    private final String password = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.JUNIT_TESTUSER1_PASSWORD );
    private final String loginURI = "https://" + cnName + ":8181/JavaBackend/rest/auth/login";
    private final Form loginForm;

    /**
     * Default Construcor.
     */
    public AbstractSecureTest()
    {
        loginForm = new Form();
        loginForm.add( "username", getUser() );
        loginForm.add( "password", getPassword() );
    }

    public String getCnName()
    {
        return cnName;
    }

    public final String getUser()
    {
        return user;
    }

    public final String getPassword()
    {
        return password;
    }

    public String getLoginURI()
    {
        return loginURI;
    }

    public Form getLoginForm()
    {
        return loginForm;
    }
}
