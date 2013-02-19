/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import com.nttdata.masterthesis.javabackend.services.rest.security.AbstractSecureTest;

/**
 * Response Envelope Test provide some thes for the DTO Response Envelope.
 * @author MATHAF
 */
public class ResponseEnvelopeTest extends AbstractSecureTest
{
    /**
     * Test ResponseEnvelope Constructors.
     */
    @Test
    public void responseEnvConstructorTest()
    {
        ResponseEnvelope re = new ResponseEnvelope();
        assertNotNull( "Default Constructor", re );

        re = new ResponseEnvelope( "this is a error" );
        assertEquals( "test error message", re.getErrorMsg(), "this is a error" );
        assertEquals( "test success status", re.isSuccess(), false );

        re = new ResponseEnvelope( true );
        assertEquals( "test success true", re.isSuccess(), true );

    }
}
