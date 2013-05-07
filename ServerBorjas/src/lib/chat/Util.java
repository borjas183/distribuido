/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.chat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DTI Gestion
 */
public class Util {
    
    public static String localAddress(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "localhost";
        }
    
    }
}
