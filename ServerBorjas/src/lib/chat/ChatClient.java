package lib.chat;

import java.rmi.*;
import java.util.*;

public interface ChatClient extends Remote {
  public void receive(String message, String who, char mode) throws RemoteException;
  public String GetName() throws RemoteException; 

    public void Send(String msj, String name, char c) throws RemoteException; 

    public void Disconnect() throws RemoteException; 
}
