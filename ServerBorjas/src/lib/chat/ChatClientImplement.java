package lib.chat;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverborjas.ServerBorjas;

public class ChatClientImplement extends UnicastRemoteObject implements ChatClient, Runnable
{
    public ChatServer cs;
    private String client_name;
    
    public void Disconnect(){
        try {
            cs.Disconnect(this);
        } catch (RemoteException ex) {
        }
    }
    
    public synchronized String GetName() throws RemoteException {
      return client_name;
    }

    public ChatClientImplement(ChatServer serv) throws RemoteException {
        super();
        cs = serv;
        
        client_name = ServerBorjas.app.name;
        
        cs.Connect(this);
    }

    public synchronized void receive(String message, String who, char mode) throws RemoteException {
      switch(mode) {
        case 'a':
          ServerBorjas.app.chat.setAdmins(message);
          break;        
        case 's':
          ServerBorjas.app.chat.appendText (who + " dice: " + message);
          break;
        case 'e':
          ServerBorjas.app.chat.appendText (who + " entrado al chat");
          break;
        case 'l':
          ServerBorjas.app.chat.appendText (who + " ha salido del chat");
          break;
        case 'h':
          ServerBorjas.app.chat.appendText (message);
          break;
        }
    }
    
    public void run() {
      Scanner in = new Scanner(System.in);
      String msg;

      while(true) {
        try {
          msg = in.nextLine();
          if (msg.equals("exit")) {
            cs.Disconnect(this);
            break;
          }
          cs.SendMessage(msg, this.GetName(), 's');
        }  
        catch (Exception e) {
          System.err.println("Some problem while broadcasting");
        }
      }
    }
    
    public static void main(String[] args) {
      String url = "rmi://localhost/ChatServer";

      try {
        ChatServer chatServ = (ChatServer)Naming.lookup(url);
        Thread thrd = new Thread(new ChatClientImplement(chatServ));
        thrd.start();
      }
      catch (Exception e) {
        System.err.println("Some problem while creating thread");  
      }
    }

    @Override
    public void Send(String msj, String name, char c) throws RemoteException {
     
            cs.SendMessage(msj, name, c);
    }
}
