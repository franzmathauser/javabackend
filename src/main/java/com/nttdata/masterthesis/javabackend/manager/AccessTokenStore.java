/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author MATHAF
 */
@Singleton
@LocalBean
public class AccessTokenStore {
    private HashMap<String, Long> sessionStore;
    
    public AccessTokenStore() { 
        if(sessionStore == null){
            sessionStore = new HashMap<String, Long>();
        }
    }
    
    public int size(){
        return sessionStore.size();
    }
    
    public void addToken(String key, Long token){
        sessionStore.put(key, token);

    }
    
    public void removeToken(String key){
        sessionStore.remove(key);
    }
    
    public Long getToken(String user) {
        return sessionStore.get(user);
    }

    public String toString() {
        return "SessionStore{" + "sessionStore=" + sessionStore + '}';
    }
    
}
