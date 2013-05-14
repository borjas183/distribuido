/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author borjas
 */
public class Server implements Runnable {
    
    ServerSocket serverSocket;
    LinkedList<ServerListener> serversListeners;
    Thread hilo;
    boolean running;
    int port;

    public Server(int port) {
        this.port=port;
        serversListeners= new LinkedList<ServerListener>();
    }
    
    public void start(){
        try {
            serverSocket= new ServerSocket(port); 
            running=true;    
            hilo= new Thread(this);    
            hilo.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop(){
        running=false;
    }
    
    public void addListener(ServerListener listener){
        serversListeners.add(listener);
    }
    
    @Override
    public void run() {
        
        while(running){
            try {
                //serverSocket.setSoTimeout(1000);
                Socket socket=serverSocket.accept();
                if(socket!=null)
                    new Thread(new ServerThread(socket)).start();
            }catch (SocketException ex) {
               // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
               // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
        }
        
    }
    
    class ServerThread implements Runnable{
        Socket socket;
        public ServerThread(Socket socket) {
            this.socket=socket;
        }
        
        public void run() {
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                Request req= (Request) objectInputStream.readObject();
                
                
                Response resp=null;
                for (ServerListener serverListener : serversListeners) {
                    resp=serverListener.doAction(req);
                    if(resp!=null)
                        break;
                }
                
                ObjectOutputStream objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(resp);
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    objectInputStream.close();
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    
    
    }
    
    
}
