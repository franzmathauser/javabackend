/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.entities.Bank;
import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Category;
import com.nttdata.masterthesis.javabackend.entities.Group;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import com.nttdata.masterthesis.javabackend.entities.User;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author MATHAF
 */
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
@XmlRootElement( name = "responseEnv") 
@JsonSerialize( include = JsonSerialize.Inclusion.NON_NULL)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
@XmlSeeAlso({User.class, Bank.class, BankAccount.class, Category.class, Group.class, Transaction.class})
public class ResponseEnvelope {
    private static final float version = 1.0f;  
 
    
    private String status;
    private String errorMsg;
    private Map<String, Object> fieldErrors;
    private Object data;
 
    public ResponseEnvelope() { 
    }
     
    public ResponseEnvelope(String status) {
        this.status = status;
    }   
    
    public ResponseEnvelope(String status, String errorMessage) {
        this.status = status;
        this.errorMsg = errorMessage;
    } 
     
    public float getVersion() {
        return ResponseEnvelope.version;
    }
         
    public String getStatus() {
        return status;
    }
 
    public ResponseEnvelope setStatus(String status) {
        this.status = status;
        return this;
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
     
    public Object getData() {
        return data;
    }
 
    public ResponseEnvelope setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseEnvelope{" + "status=" + status + ", errorMsg=" + errorMsg + ", fieldErrors=" + fieldErrors + ", data=" + data + '}';
    }
    
    
}
