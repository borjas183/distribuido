/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacion;

import lib.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */
public class Client {
    public static int defaultPort=1830;
    public static String defaultHost=Util.localAddress();
    
    
    public static Response send(Request req){
        return send(defaultHost,req);
    }
    public static Response send(String hos ,Request req){
    
        return send(defaultHost,defaultPort,req);
    }
    
    public static boolean ping(String host, int port){
        try {
            _send(host, port , new Request("ping"));
            return true;
        } catch (Exception ex) {
            
        }
        return false;
    }
    public static Response send(String host, int port ,Request req){
        try {
            return _send(host, port ,req);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static Response _send(String host, int port ,Request req) throws UnknownHostException, IOException, ClassNotFoundException{
        String[] splitting= host.split(":");
        if(splitting.length>1){
            try{
                port=Integer.parseInt(splitting[1]);
            }catch(NumberFormatException unused){}
        }
            Socket socket= new Socket(host, port);
            
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(req);
            ObjectInputStream objectInputStream= new ObjectInputStream(socket.getInputStream());
            Response resp= (Response) objectInputStream.readObject();
            
            objectOutputStream.close();
            
            objectInputStream.close();
            
            
            socket.close();
            return resp;
        
        
    }
    
}
