/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.nttdata.masterthesis.javabackend.Constants;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHAF
 */
@XmlRootElement( name = "accesstoken") 
@JsonSerialize( include = Inclusion.NON_NULL)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
public class AccessToken {
    
    

    public AccessToken() {

        this.accessToken = generateSecureAccessToken();
    }
    
    private Long accessToken;

    public Long getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(Long accessToken) {
        this.accessToken = accessToken;
    }

    private Long generateSecureAccessToken() {
        Long token = -1L;
        try {
            // Create a secure random number generator
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            // Get 1024 random bits
            byte[] bytes = new byte[1024 / 8];
            sr.nextBytes(bytes);

            // Create two secure number generators with the same seed
            int seedByteCount = 10;
            byte[] seed = sr.generateSeed(seedByteCount);

            sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(seed);
            SecureRandom sr2 = SecureRandom.getInstance("SHA1PRNG");
            sr2.setSeed(seed);
            
            token = sr2.nextLong();
            
        } catch (NoSuchAlgorithmException e) {
        }
        return token;
    }
}
