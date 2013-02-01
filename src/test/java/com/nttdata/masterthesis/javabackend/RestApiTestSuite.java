package com.nttdata.masterthesis.javabackend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.nttdata.masterthesis.javabackend.filter.HttpToHttpsFilterTest;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelopeTest;
import com.nttdata.masterthesis.javabackend.services.rest.security.AuthenticationTest;
import com.nttdata.masterthesis.javabackend.services.rest.security.ServiceAccessTest;

/**
 * Test Suite to setup all REST-API tests.
 * @author MATHAF
 */
@RunWith( Suite.class )
@Suite.SuiteClasses(
{
    HttpToHttpsFilterTest.class, AuthenticationTest.class, ServiceAccessTest.class, ResponseEnvelopeTest.class
} )
public class RestApiTestSuite
{
}
