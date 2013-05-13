/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DTI Gestion
 */
public class Util {
    private static String priority="192.168.56";
    public static String localAddress(){
        try {
            
            for(String address: locals()){
                if(address.contains(priority)){
                    return address;
                }
            }
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
    
    public static String getNodoId(String output){
        try{ 
            return getTagValues(output, "nodo_id").get(0);
        }catch(Exception e){ e.printStackTrace(); }
        return null ;
    }

    public static List<String> getTagValues( String str,String tag) {
         Pattern regex = Pattern.compile("<"+tag+">(.+?)</"+tag+">");
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = regex.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }
}
