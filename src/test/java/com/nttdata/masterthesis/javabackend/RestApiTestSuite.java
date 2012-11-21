/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.nttdata.masterthesis.javabackend.filter.HttpToHttpsFilterTest;
import com.nttdata.masterthesis.javabackend.services.rest.AuthenticationTest;
import com.nttdata.masterthesis.javabackend.services.rest.TransactionServiceTest;

/**
 * Test Suite to setup all REST-API tests.
 * @author MATHAF
 */
@RunWith( Suite.class )
@Suite.SuiteClasses(
{
    HttpToHttpsFilterTest.class, AuthenticationTest.class, TransactionServiceTest.class
} )
public class RestApiTestSuite
{
}
