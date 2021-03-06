/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ApplicationController;
import Modelo.Nodo;
import com.jcraft.jsch.JSchException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import lib.Exec;
import lib.Util;
import serverborjas.ServerBorjas;

/**
 *
 * @author borjas
 */
public class ChatGUI extends javax.swing.JFrame {

    
    /**
     * Creates new form ChatGUI
     */
    public ChatGUI( String address,String name) {
            initComponents();
            
            labelName.setText("NAME: "+name+" IP:"+ Util.localAddress());
            labelIp.setText("SERVER: "+address);
            setSize(600, 430);
            DefaultCaret caret = (DefaultCaret)textChat.getCaret();  
            
            
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
            setLocationRelativeTo(null);
            actualizarListaNodos();
    }
    
    public void appendText(String txt){
        
        this.textChat.setText( textChat.getText()+txt+"\n");
    
    }
    
    public void setAdmins(final String admins){
     listAdmins.setModel(new javax.swing.AbstractListModel() {
            String[] strings = admins.split(";");
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });    
    }
    
    public void setNodes(final String nodes){
     listNodos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = nodes.split(";");
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });    
    }
    
    private void setNodes(final Object[] strings) {
     listNodos.setModel(new javax.swing.AbstractListModel() {
         @Override
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });    
    }
    
    public String getSelectedNodo(){
        String str= (String) listNodos.getSelectedValue();
        if(str==null)
            return listNodos.getModel().getElementAt(0).toString();
        return str;
    }
    public void actualizarListaNodos() {

        List<Nodo> models;
        
        LinkedList<String> nombres= new LinkedList<String>();
        try{
            models= ApplicationController.NodoDao.queryBuilder().where().eq("administrador_id", ApplicationController.admin.getId()).query();
            for(Nodo nodo: models){
             nombres.add(nodo.getId()+":"+nodo.getHost());
            }
        
        }catch(SQLException ex){

        }
        
        setNodes(nombres.toArray());
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelName = new javax.swing.JLabel();
        labelIp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listNodos = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listAdmins = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        textChat = new javax.swing.JTextArea();
        mensaje = new javax.swing.JTextField();
        agregarNodo = new javax.swing.JButton();
        botonAdministrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sendMensaje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("ServerBorjas"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        labelName.setText("NAME: XXX");
        getContentPane().add(labelName);
        labelName.setBounds(23, 11, 280, 14);

        labelIp.setText("IP: IP.IP.IP.IP");
        getContentPane().add(labelIp);
        labelIp.setBounds(318, 11, 270, 14);

        jScrollPane1.setViewportView(listNodos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(448, 195, 141, 110);

        jLabel3.setText("USUARIOS");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 30, 131, 14);

        jScrollPane2.setViewportView(listAdmins);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(448, 53, 141, 120);

        textChat.setEditable(false);
        textChat.setColumns(20);
        textChat.setRows(5);
        jScrollPane3.setViewportView(textChat);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 36, 428, 260);

        mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mensajeKeyPressed(evt);
            }
        });
        getContentPane().add(mensaje);
        mensaje.setBounds(10, 310, 281, 41);

        agregarNodo.setText("AGREGAR NODO");
        agregarNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarNodoActionPerformed(evt);
            }
        });
        getContentPane().add(agregarNodo);
        agregarNodo.setBounds(450, 310, 141, 48);

        botonAdministrar.setText("Administrar");
        botonAdministrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAdministrarActionPerformed(evt);
            }
        });
        getContentPane().add(botonAdministrar);
        botonAdministrar.setBounds(10, 365, 579, 38);

        jLabel4.setText("NODOS");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(450, 180, 131, 14);

        sendMensaje.setText("ENVIAR MENSAJE");
        sendMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMensajeActionPerformed(evt);
            }
        });
        getContentPane().add(sendMensaje);
        sendMensaje.setBounds(300, 310, 141, 48);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  JTextField passwordField = (JTextField) new JPasswordField(20);
    private void agregarNodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarNodoActionPerformed
    
        String host=JOptionPane.showInputDialog("Host",ApplicationController.defaultHost);
        String user=JOptionPane.showInputDialog("User",ApplicationController.defaultUser);        
        String passwd=ApplicationController.defaultPassword;
        passwordField.setText(passwd);
            Object[] ob = {passwordField};
            int result =
                    JOptionPane.showConfirmDialog(null, ob, "password",
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) 
                passwd = passwordField.getText();
        
            String output="";
            try {
                  output = Exec.cmd(host, user, passwd, ApplicationController.ComandoInstallSH+" "+host);
                  Thread.sleep(1000);
                  String nodo_id=Util.getNodoId(output);
                  
                  Nodo nodo;
                  
                    List<Nodo> models = ApplicationController.NodoDao.queryBuilder().where().eq("host", host).and().eq("estado", "activo").query();
                    if(models.size()>0)
                        nodo=models.get(0);
                    else
                        nodo = new Nodo();
                  nodo.setEstado("activo");
                  nodo.setPassword(passwd);
                  nodo.setUsuario(user);
                  nodo.setHost(host);
                  nodo.setAdministrador(ApplicationController.admin);
                  ApplicationController.NodoDao.createOrUpdate(nodo);
                  if(!nodo_id.equals( String.valueOf( nodo.getId() ) )){
                      System.out.println("---- :D --- ");                  
                  }
                  actualizarListaNodos();
                  
                JOptionPane.showMessageDialog(null, "Nodo al alcance");
            } catch (InterruptedException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSchException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Nodo no se pudo alcanzar");
            } catch (IOException ex) {
                Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Nodo no se pudo alcanzar");
            } catch (SQLException ex) {
                    ex.printStackTrace();
             }        
    }//GEN-LAST:event_agregarNodoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            ServerBorjas.app.client.Disconnect();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_formWindowClosing

    private void mensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensajeKeyPressed
      
        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
            sendMensajeActionPerformed(null);
        
    }//GEN-LAST:event_mensajeKeyPressed

    private void botonAdministrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAdministrarActionPerformed
        
        AdminNodo admin = new AdminNodo(this, true);
        admin.setVisible(true);
        
        
    }//GEN-LAST:event_botonAdministrarActionPerformed

    private void sendMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMensajeActionPerformed
       
        if(!mensaje.getText().trim().equals("")){
            ServerBorjas.app.client.hablar(mensaje.getText().trim());
            mensaje.setText("");
        }
            
    }//GEN-LAST:event_sendMensajeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatGUI("","").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarNodo;
    private javax.swing.JButton botonAdministrar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelIp;
    private javax.swing.JLabel labelName;
    private javax.swing.JList listAdmins;
    private javax.swing.JList listNodos;
    private javax.swing.JTextField mensaje;
    private javax.swing.JButton sendMensaje;
    private javax.swing.JTextArea textChat;
    // End of variables declaration//GEN-END:variables

}
