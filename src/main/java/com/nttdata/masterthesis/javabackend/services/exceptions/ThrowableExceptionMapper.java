/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.exceptions;

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.nttdata.masterthesis.javabackend.services.rest.ResponseMessages;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author MATHAF
 */
@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Response RESPONSE ;
    
    private static final ResponseEnvelope RESPONSE_ENVELOPE = new ResponseEnvelope("ERROR");
    
    static {
        RESPONSE = Response.status(500).entity(RESPONSE_ENVELOPE).build();
    }
    
    @Override
    public Response toResponse(Throwable ex) {
        System.out.println("ThrowableExceptionMapper: "+ex.getClass());
        ex.printStackTrace();
        //usually you don't pass detailed info out (don't do this here in production environments)
        RESPONSE_ENVELOPE.setErrorMsg(ex.getMessage());
         
        return RESPONSE;
    }
    
}
