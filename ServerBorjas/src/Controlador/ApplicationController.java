/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Modelo.Carpeta;
import Modelo.Dispositivo;
import Modelo.Nodo;
import Modelo.Proceso;
import Modelo.Reporte;
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
import lib.Util;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.DatabaseTypeUtils;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

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
    public static String defaultHost = "localhost";
    public static String defaultUser = "xubuntu";
    public static String defaultPassword = "xubuntu";
    public static String dbUrl = "jdbc:mysql://localhost/distribuido";
    public static String dbUser = "root";
    public static String dbPassword = "1234";
    public static Dao<Administrador, Integer> AdministradorDao;
    public static Dao<Carpeta, Integer> CarpetaDao;
    public static Dao<Dispositivo, Integer> DispositivoDao;
    public static Dao<Nodo, Integer> NodoDao;
    public static Dao<Proceso, Integer> ProcesoDao;
    public static Dao<Reporte, Integer> ReporteDao;
    public static String ComandoInstallSH = "rm install.sh* ; wget http://" + defaultHost + ":7770/install.sh && chmod +x install.sh && ./install.sh " + defaultHost;

    public static Administrador admin;
    
    public ApplicationController(String address, String name) {
        this.name = name;
        this.address = address;
        defaultHost=address;
        ComandoInstallSH.replaceAll(defaultHost, address);
        initBD();

        
        
        // query for all accounts that have that password
        try{
            List<Administrador> accountList = AdministradorDao.queryBuilder().where().eq("host", address).query();
            if(accountList.size()>0)
                admin=accountList.get(0);
            else
                admin = new Administrador();
            
            admin.setEstado("activo");
            admin.setHost(address);
            admin.setName(name);            
            AdministradorDao.createOrUpdate(admin);
            
        }catch(SQLException exception){
            exception.printStackTrace();
        }
        
    }

    public void initBD() {
        initBD(0);
    }
    public void initBD(int trys) {
        JdbcConnectionSource connectionSource = null;
        try {
            // create our data source
            connectionSource = new JdbcConnectionSource(dbUrl, dbUser, dbPassword);
            // setup our database and DAOs
            setupDatabase(connectionSource);


            System.out.println("\n\nIt seems to have worked\n\n");
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(trys==0){
                dbUrl=dbUrl.replaceAll("localhost", "10.0.4.2");
                initBD(trys+1);
                return;
            }
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Setup our database and DAOs
     */
    private void setupDatabase(ConnectionSource connectionSource) throws SQLException {

        AdministradorDao = DaoManager.createDao(connectionSource, Administrador.class);
        CarpetaDao = DaoManager.createDao(connectionSource, Carpeta.class);
        DispositivoDao = DaoManager.createDao(connectionSource, Dispositivo.class);
        NodoDao = DaoManager.createDao(connectionSource, Nodo.class);
        ProcesoDao = DaoManager.createDao(connectionSource, Proceso.class);
        ReporteDao = DaoManager.createDao(connectionSource, Reporte.class);

    }

    private void createTables(ConnectionSource connectionSource) throws SQLException {
        // if you need to create the table
        TableUtils.createTable(connectionSource, Administrador.class);
        TableUtils.createTable(connectionSource, Carpeta.class);
        TableUtils.createTable(connectionSource, Dispositivo.class);
        TableUtils.createTable(connectionSource, Nodo.class);
        TableUtils.createTable(connectionSource, Proceso.class);
        TableUtils.createTable(connectionSource, Reporte.class);
    }

    public void init() {


        chat = new ChatGUI(address, name);
        chat.setVisible(true);

        ChatServer chatServ;
        try {
            chatServ = (ChatServer) Naming.lookup("rmi://" + address + "/ChatServer");
            client = new ChatClientImplement(chatServ);
        } catch (Exception unused) {
            try {
                address = "localhost";
                try {
                    Registry registry = LocateRegistry.createRegistry(1099);
                } catch (Exception unused1) {
                }
                local = new ChatServerImplement();
                try {
                    Naming.rebind("ChatServer", local);
                } catch (Exception unused2) {
                }
                chatServ = (ChatServer) Naming.lookup("rmi://" + address + "/ChatServer");
                client = new ChatClientImplement(chatServ);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
