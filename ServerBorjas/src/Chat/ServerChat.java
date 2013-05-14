/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import Comunicacion.Client;
import Comunicacion.Request;
import Comunicacion.Response;
import Comunicacion.Server;
import Comunicacion.ServerListener;
import java.util.LinkedHashMap;

/**
 *
 * @author miguel
 */
public class ServerChat implements ServerListener{

    Server actions;
    LinkedHashMap<String, String> clientes;
    public static int puertoServidor=1830;
    
    
    public ServerChat() {
        clientes= new LinkedHashMap<String,String>();
        actions= new Server(puertoServidor);
        actions.addListener(this);
        actions.start();
        System.out.println("ESPERANDO CLIENTES");
    }
    
    public void send(String host,Request req){
        Client.send(host,ClientChat.puertoCliente,req);
    }
    
    public void sendMessage(String msg){
        sendMessage(msg, "");
    }
    
    public void sendMessage(String msg,String who){
        for (String key :  clientes.keySet()) {
            send(clientes.get(key), new Request("message","contenido",msg,"quien",who));            
        }    
    }
    
    public boolean registrase(String nombre, String ip){
        //if(clientes.containsValue(ip)) return false;
        
        clientes.put(nombre, ip);
        sendMessage(nombre+" ha entrado a la sala");
        return true;
    }
    
    
    private void hablar(String nombre, String mensaje) {
        
        sendMessage( nombre+" dice: "+mensaje,nombre);
        
    }

    private void salir(String nombre, String ip) {
        clientes.remove(nombre);
        sendMessage(nombre+" ha salido de la sala");
    }
    
    
    @Override
    public Response doAction(Request req) {
        String content="";
        if(req.action.equals("registrarse")){
            content="" + registrase(req.params.get("nombre"), req.params.get("ip"));
        }else
        if(req.action.equals("hablar")){
             hablar(req.params.get("nombre"), req.params.get("mensaje"));
        }else
        if(req.action.equals("salir")){
             salir(req.params.get("nombre"), req.params.get("ip"));
        }
        return new Response(content);
    }

    
    
}
