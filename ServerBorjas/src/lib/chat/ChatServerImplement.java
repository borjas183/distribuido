package lib.chat;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServerImplement extends UnicastRemoteObject implements ChatServer
{
  private LinkedList <ChatClient> clients;
  private ArrayList <String> history;

  public ChatServerImplement() throws RemoteException {
    super();
    clients = new LinkedList<ChatClient>(); 
    history = new ArrayList<String>();//create a history array
  }

  public synchronized void SendMessage(String message, String who, char mode) throws RemoteException {
    
    if (mode == 's') {
      history.add(who + " dijo: " + message);
    }
    
    for (int i = 0; i < clients.size(); i++)
      clients.get(i).receive(message, who, mode);
  }

  public synchronized void Connect(ChatClient c) throws RemoteException {
    clients.add(c); 
    
    if (!history.isEmpty()) {
      for (int i = 0; i < history.size(); i++) {
        c.receive(history.get(i), "", 'h');  
      }  
    }

    SendMessage(" a entrado al chat", c.GetName(), 'e');
    SendMessage(getClientNames(), c.GetName(), 'a');
    
  }
  
  public String getClientNames(){
      String str="";
      for(ChatClient client : clients){
          try {
              str+=client.GetName()+";";
          } catch (RemoteException ex) {
              Logger.getLogger(ChatServerImplement.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      return str; 
  }

  
  
  public void Disconnect(ChatClient c) throws RemoteException {
    clients.remove(c); 
    SendMessage(" ha salido del chat", c.GetName(), 'l');
  }

  public static void main (String[] args) {
    try {
        
      Registry registry = LocateRegistry.createRegistry( 1099 );
      Naming.rebind("ChatServer", new ChatServerImplement() );
    }  
    catch(Exception e) {
      System.err.println("Some problem while rebinding");
      
      e.printStackTrace();
    }
    
  }
  
}
