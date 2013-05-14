/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacion;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author miguel
 */
public class Request implements Serializable{

    public Request() {
        this.action="";
        this.params= new LinkedHashMap<String,String>();
        
    }

    public Request(String action) {
        this.action = action;
    }
    public Request(String action, String ... params) {
        this.action = action;
        this.params= new LinkedHashMap<String,String>();
        for(int i=0; i< params.length; i+=2){
            this.params.put(params[i], params[i+1]);
        }
    }
    
    public Request(String action, LinkedHashMap<String, String> params) {
        this.action = action;
        this.params = params;
    }
    
    
    
    
    public String action;
    public LinkedHashMap<String,String> params;
    
}
