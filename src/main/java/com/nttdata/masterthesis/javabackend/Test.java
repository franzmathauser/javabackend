/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class Test {

    public String getName(){
        return "Franz";
    }

}
