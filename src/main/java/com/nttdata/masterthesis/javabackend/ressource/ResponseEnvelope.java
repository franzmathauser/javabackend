/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHAF
 */
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
@XmlRootElement( name = "responseEnv") 
@JsonSerialize( include = JsonSerialize.Inclusion.NON_NULL)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
public class ResponseEnvelope {
    private static final float version = 1.0f;  
 
    
    private boolean success = false;
    private String errorMsg;
    private Map<String, Object> fieldErrors;
    
    @XmlAnyElement(lax=true)
    private Object bodyData; 
 
    public ResponseEnvelope() { 
    }
     
    public ResponseEnvelope(boolean success) {
        this.success = success;
    }   
    
    public ResponseEnvelope(boolean success, String errorMessage) {
        this.success = success;
        this.errorMsg = errorMessage;
    } 
     
    public float getVersion() {
        return ResponseEnvelope.version;
    }
          
    public String getErrorMsg() {
        return errorMsg;
    }
 
    public ResponseEnvelope setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
 
    public Map<String, Object> getFieldErrors() {
        return fieldErrors;
    }
 
    public ResponseEnvelope setFieldErrors(Map<String, Object> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }
     
    public Object getBodyData() {
        return bodyData;
    }
 
    public ResponseEnvelope setData(Object data) {
        this.bodyData = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
    
    

    @Override
    public String toString() {
        return "ResponseEnvelope{" + ", errorMsg=" + errorMsg + ", fieldErrors=" + fieldErrors + ", data=" + bodyData + '}';
    }
    
    
}
