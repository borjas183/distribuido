/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverborjas;

import Controlador.ApplicationController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lib.SimpleWebServer;
import lib.chat.Util;

/**
 *
 * @author miguel
 */
public class ServerBorjas {
    public static ApplicationController app;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = 7770;
            
            for(String host : Util.locals()){
                File wwwroot = new File(".").getAbsoluteFile();
                SimpleWebServer server = new SimpleWebServer(host, port, wwwroot);
                try{
                    server.start();
                }catch(IOException unused){}                
            }
            String address;
            
            address=JOptionPane.showInputDialog(null, "Direccion Server",Util.localAddress());            
            String name;
            do
                name=JOptionPane.showInputDialog(null, "Nombre");
            while(name==null || name.trim().equals(""));
            
            app=new ApplicationController(address, name);
            
            app.init();
        } catch (Exception ex) {
            Logger.getLogger(ServerBorjas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
