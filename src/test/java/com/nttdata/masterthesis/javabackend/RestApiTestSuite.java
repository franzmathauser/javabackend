/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend;

import com.nttdata.masterthesis.javabackend.filter.HttpToHttpsFilterTest;
import com.nttdata.masterthesis.javabackend.services.rest.AuthenticationTest;
import com.nttdata.masterthesis.javabackend.services.rest.TransactionServiceTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author MATHAF
 */
@RunWith( Suite.class )
@Suite.SuiteClasses(
{
    HttpToHttpsFilterTest.class, AuthenticationTest.class, TransactionServiceTest.class
} )
public class RestApiTestSuite
{

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
}
