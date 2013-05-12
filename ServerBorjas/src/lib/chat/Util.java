/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.chat;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
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
    
    public static LinkedList<String> locals(){
        LinkedList<String> list= new LinkedList<String>();
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)){
                Enumeration<InetAddress> inetAddresses= netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    list.add(inetAddress.getHostAddress());
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
