/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ApplicationController;
import Modelo.Nodo;
import Modelo.Proceso;
import Modelo.Reporte;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author miguel
 */
public class AdminNodo extends javax.swing.JDialog {

    
    Nodo nodo;
    /**
     * Creates new form TopProcess
     */
    public AdminNodo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            initComponents();
            
            String[] selected= serverborjas.ServerBorjas.app.chat.getSelectedNodo().split(":");
            nodo=ApplicationController.NodoDao.queryForId(Integer.valueOf(selected[0]));
            
            nodonombre.setText(nodo.getEstado());
            nodoip.setText(nodo.getHost());
            List<Reporte> query = ApplicationController.ReporteDao.queryBuilder().orderBy("id", false).limit(1).where().eq("nodo_id", String.valueOf(nodo.getId()) ).query();
            
            if(query.size()>0){
                Reporte rep=query.get(0);
                List<Proceso> result= ApplicationController.ProcesoDao.queryBuilder().where().eq("reporte_id",""+rep.getId()).query();
                
                LinkedList<String> procesos= new LinkedList<String>();
                for (Proceso p : result) {
                    procesos.add(p.getPid()+":"+p.getCpu()+":"+p.getMem()+":"+p.getCommand());
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    
    public void setProcess(final String[] process){
        
        listProcess.setModel(new javax.swing.AbstractListModel() {
            String[] strings = process;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    
    }
    public void setDirs(final String[] dirs){
        
        listDirectories.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dirs;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listProcess = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        matar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nodonombre = new javax.swing.JLabel();
        nodoip = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDirectories = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        borrar = new javax.swing.JButton();
        TAMAGNO = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(listProcess);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 400, 104));

        jLabel1.setText("NOMBRE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));

        matar.setText("MATAR");
        getContentPane().add(matar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 176, 104, 38));

        jLabel2.setText("NUMERO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, -1, -1));

        jLabel3.setText("CONSUMO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        nodonombre.setText("NOMBRE NODO");
        getContentPane().add(nodonombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        nodoip.setText("ip.ip.ip");
        getContentPane().add(nodoip, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, -1, -1));

        jScrollPane2.setViewportView(listDirectories);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 257, 400, 104));

        jLabel4.setText("NOMBRE");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 257, -1, -1));

        borrar.setText("BORRAR");
        getContentPane().add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 323, 104, 38));

        TAMAGNO.setText("TAMAGNO");
        getContentPane().add(TAMAGNO, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 297, -1, -1));

        jLabel5.setText("DIRECTORIOS");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 232, -1, -1));

        jLabel6.setText("PID : CPU : MEM : COMMAND          ------- PROCESOS");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 400, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AdminNodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminNodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminNodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminNodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdminNodo dialog = new AdminNodo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TAMAGNO;
    private javax.swing.JButton borrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listDirectories;
    private javax.swing.JList listProcess;
    private javax.swing.JButton matar;
    private javax.swing.JLabel nodoip;
    private javax.swing.JLabel nodonombre;
    // End of variables declaration//GEN-END:variables
}
