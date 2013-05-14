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
import javax.print.attribute.standard.Severity;
import lib.Util;
import serverborjas.ServerBorjas;

/**
 *
 * @author miguel
 */
public class ClientChat implements ServerListener{

    Server actions;
    public static int puertoCliente=1820;
    String nombre;
    String host;
    int port;
    
    public ClientChat(String host, String nombre) {
        actions= new Server(puertoCliente);
        actions.addListener(this);
        actions.start();
        this.host=host;
        this.nombre=nombre;
        this.port=ServerChat.puertoServidor;
    
        send( new Request("registrarse", "nombre", nombre, "ip", Util.localAddress()) );
        
        
    }
    
    
    
    
    public Response send(Request req){
    
        return Client.send(host,port,req);
    
    }
    
    public void hablar(String msj){
        send(new Request("hablar", "nombre", nombre, "mensaje", msj));
    
    }
    public void message(String quien, String contenido){
        ServerBorjas.app.chat.appendText(contenido);
    }
    
    @Override
    public Response doAction(Request req) {
        
        if(req.action.equals("message")){
            String contenido= req.params.get("contenido");
            String quien= req.params.get("quien");
            message(quien, contenido);
            
        }else
        if(req.action.equals("refresh")){
            
            Response resp= send( new Request("listar") );
            ServerBorjas.app.chat.setAdmins(resp.content);
        }
        
        
        return null;
    }

    public void Disconnect() {
        send(new Request("salir", "nombre", nombre, "ip", ServerBorjas.app.address));
    }

    
    
}
