/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.ChatGUI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.chat.ChatClient;
import lib.chat.ChatClientImplement;
import lib.chat.ChatServer;
import lib.chat.ChatServerImplement;
import lib.chat.Util;

/**
 *
 * @author miguel
 */
public class ApplicationController {
    public ChatGUI chat;
    public ChatServer local;
    public ChatClient client;
    public String address;
    public String name;
    
    public static String defaultHost=Util.localAddress();
    public static String defaultUser="xubuntu";
    public static String defaultPassword="xubuntu";



    public static String ComandoInstallSH="rm install.sh* ; wget http://"+defaultHost+":7770/install.sh && chmod +x install.sh && ./install.sh "+defaultHost;
    
    
    public ApplicationController(String address,String name) {
        this.name=name;
        this.address=address;
           
            
    
    }
    
    public void init(){
    
    
        chat= new ChatGUI(address,name);
        chat.setVisible(true);
           
        ChatServer chatServ;
        try {
            chatServ = (ChatServer)Naming.lookup("rmi://"+address+"/ChatServer");
            client=new ChatClientImplement(chatServ);
        } catch (Exception unused) {
            try {
                address="localhost";
                try{
                    Registry registry = LocateRegistry.createRegistry( 1099 );
                }catch(Exception unused1){}
                local=new ChatServerImplement();
                try{
                    Naming.rebind("ChatServer", local );
                }catch(Exception unused2){}
                chatServ = (ChatServer)Naming.lookup("rmi://"+address+"/ChatServer");
                client=new ChatClientImplement(chatServ);              
            } catch(Exception e) {      
              e.printStackTrace();
            }        
        }
    
    }
    
    
}
