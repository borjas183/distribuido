/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacion;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author borjas
 */
public class Response implements Serializable{
    
    public LinkedHashMap<String, String> header;
    public String content;

    public Response() {
        content="";
    }

    public Response(String content) {
        this.content = content;
    }
    
}
