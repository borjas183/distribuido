/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ApplicationController;
import Modelo.Carpeta;
import Modelo.Dispositivo;
import Modelo.Nodo;
import Modelo.Proceso;
import Modelo.Reporte;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;
import lib.Exec;

/**
 *
 * @author borjas
 */
public class AdminNodo extends javax.swing.JDialog {

    
    Nodo nodo;
    List<Proceso> result;
    List<Dispositivo> dispositivos;
    List<Carpeta> carpetas;
    Proceso proces;
    String report_id;
    private String carpeta_id;
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
                report_id=rep.getId()+"";
                result= ApplicationController.ProcesoDao.queryBuilder().where().eq("reporte_id",report_id).query();
                
                Object[][] procesos= new Object[result.size()][4];
                int i=0;
                for (Proceso p : result) {
                    procesos[i][0]=p.getPid();
                    procesos[i][1]=p.getCpu();
                    procesos[i][2]=p.getMem();
                    procesos[i][3]=p.getCommand();
                    i++;
                }
                setProcess(procesos);
                
                
                dispositivos= ApplicationController.DispositivoDao.queryBuilder().where().eq("reporte_id",report_id).query();
                
                Object[][] dis= new Object[dispositivos.size()][5];
                i=0;
                for (Dispositivo p : dispositivos) {
                    dis[i][0]=p.getNombre();
                    dis[i][1]=p.getDev();
                    dis[i][2]=p.getTotal()+"";
                    dis[i][3]=p.getUsado()+"";
                    i++;
                }                
                setDispositivos(dis);
                
                
                carpetas= ApplicationController.CarpetaDao.queryBuilder().where().eq("reporte_id",report_id).query();
                
                Object[][] carp= new Object[carpetas.size()][5];
                i=0;
                for (Carpeta p : carpetas) {
                    carp[i][0]=p.getDireccion();
                    carp[i][1]=p.getEspacio();
                    carp[i][2]=p.getUsuario();
                    carp[i][3]=p.getId();
                    i++;
                }                
                setCarpeta(carp);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    public void actualizarbyCpu(){
    try{
                result= ApplicationController.ProcesoDao.queryBuilder().orderBy("cpu", false).where().eq("reporte_id",report_id).query();
                
                Object[][] procesos= new Object[result.size()][4];
                int i=0;
                for (Proceso p : result) {
                    procesos[i][0]=p.getPid();
                    procesos[i][1]=p.getCpu();
                    procesos[i][2]=p.getMem();
                    procesos[i][3]=p.getCommand();
                    i++;
                }
                setProcess(procesos);
    }catch(SQLException except){
    
    }
    }public void actualizarbyMem(){
    try{
                result= ApplicationController.ProcesoDao.queryBuilder().orderBy("mem", false).where().eq("reporte_id",report_id).query();
                
                Object[][] procesos= new Object[result.size()][4];
                int i=0;
                for (Proceso p : result) {
                    procesos[i][0]=p.getPid();
                    procesos[i][1]=p.getCpu();
                    procesos[i][2]=p.getMem();
                    procesos[i][3]=p.getCommand();
                    i++;
                }
                setProcess(procesos);
    }catch(SQLException except){
    
    }
    }
    
    private void setProcess(final Object[][] process) { 
        tableProcess.setModel(new javax.swing.table.DefaultTableModel(
            process,
            new String [] {
                "PID", "CPU", "MEM", "COMMANDO"
            }
        ));
    }
    private void setDispositivos(final Object[][] process) { 
        tableDispositivo.setModel(new javax.swing.table.DefaultTableModel(
            process,
            new String [] {
                "NOMBRE", "DEV", "TOTAL", "USADO"
            }
        ));
    }
    private void setCarpeta(final Object[][] process) { 
        directorios.setModel(new javax.swing.table.DefaultTableModel(
            process,
            new String [] {
                "DIRECCION", "OCUPA", "USUARIO", "ID"
            }
        ));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre_pid = new javax.swing.JLabel();
        matar = new javax.swing.JButton();
        cpu_pid = new javax.swing.JLabel();
        mem_pid = new javax.swing.JLabel();
        nodonombre = new javax.swing.JLabel();
        nodoip = new javax.swing.JLabel();
        nombre_Dir = new javax.swing.JLabel();
        borrar = new javax.swing.JButton();
        TAMAGNO = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProcess = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        desintalar = new javax.swing.JButton();
        cmd = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDispositivo = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        directorios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        nombre_pid.setText("----------");

        matar.setText("MATAR");
        matar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matarActionPerformed(evt);
            }
        });

        cpu_pid.setText("-----------");

        mem_pid.setText("-----------");

        nodonombre.setText("NOMBRE NODO");

        nodoip.setText("ip.ip.ip");

        nombre_Dir.setText("NOMBRE");

        borrar.setText("BORRAR");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        TAMAGNO.setText("TAMAGNO");

        jLabel5.setText("DIRECTORIOS");

        jLabel6.setText("PROCESOS");

        tableProcess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PID", "CPU", "COMMAND", "USER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProcess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProcessMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProcess);

        jButton1.setText("BY MEM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BY CPU");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        desintalar.setText("DESINTALAR");
        desintalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desintalarActionPerformed(evt);
            }
        });

        cmd.setText("CMD");
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        tableDispositivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PID", "CPU", "COMMAND", "USER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDispositivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDispositivoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableDispositivo);

        directorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "PID", "CPU", "COMMAND", "USER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        directorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                directoriosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(directorios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desintalar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(nombre_pid)
                            .addComponent(cpu_pid)
                            .addComponent(mem_pid)
                            .addComponent(matar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jButton2)
                                .addGap(33, 33, 33)
                                .addComponent(jButton1))
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre_Dir)
                                    .addComponent(TAMAGNO)
                                    .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(nodoip)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nodonombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmd)
                                .addGap(122, 122, 122)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nodonombre)
                            .addComponent(cmd))
                        .addGap(6, 6, 6)
                        .addComponent(nodoip))
                    .addComponent(desintalar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombre_pid)
                        .addGap(6, 6, 6)
                        .addComponent(cpu_pid)
                        .addGap(6, 6, 6)
                        .addComponent(mem_pid)
                        .addGap(12, 12, 12)
                        .addComponent(matar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(jLabel5)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombre_Dir)
                        .addGap(26, 26, 26)
                        .addComponent(TAMAGNO)
                        .addGap(12, 12, 12)
                        .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String pid;
    private String host;
    
    private void tableProcessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProcessMouseClicked
        try {
            int row=tableProcess.getSelectedRow();
            pid=tableProcess.getModel().getValueAt(row, 0).toString();
            
            
                String[] selected= serverborjas.ServerBorjas.app.chat.getSelectedNodo().split(":");
                nodo=ApplicationController.NodoDao.queryForId(Integer.valueOf(selected[0]));
                
                nodonombre.setText(nodo.getEstado());
                nodoip.setText(nodo.getHost());
                List<Reporte> query = ApplicationController.ReporteDao.queryBuilder().orderBy("id", false).limit(1).where().eq("nodo_id", String.valueOf(nodo.getId()) ).query();
                
                if(query.size()>0){
                    Reporte rep=query.get(0);
                    List<Proceso> result= ApplicationController.ProcesoDao.queryBuilder().where().eq("reporte_id",""+rep.getId()).and().eq("pid", pid.trim()).query();
                    if(result.size()>0){
                        Proceso p=result.get(0);
                       pid=p.getPid();
                       nombre_pid.setText(p.getCommand());
                       cpu_pid.setText(p.getCpu()+"");
                       mem_pid.setText(p.getMem()+"");
                       proces=p;
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tableProcessMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        actualizarbyMem();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
actualizarbyCpu();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void matarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matarActionPerformed
        if(pid!=null && !pid.equals("")){
            try {
                String ssh="kill -9 "+pid;
                Exec.cmd(nodo, ssh);
                ApplicationController.ProcesoDao.delete(proces);
            } catch (SQLException ex) {
                Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_matarActionPerformed

    private void desintalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desintalarActionPerformed
       if(pid!=null && !pid.equals("")){
            try {
                Exec.cmd(nodo, ApplicationController.ComandoUninstallSH);
                ApplicationController.ProcesoDao.delete(proces);
            } catch (SQLException ex) {
                Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_desintalarActionPerformed

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
       if(pid!=null && !pid.equals("")){
            try {
                String ssh=JOptionPane.showInputDialog("Escriba un comando");
                if(ssh==null || ssh.equals("")) return;
                Exec.cmd(nodo, ssh);
                ApplicationController.ProcesoDao.delete(proces);
            } catch (SQLException ex) {
                Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmdActionPerformed

    private void tableDispositivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDispositivoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDispositivoMouseClicked

    private void directoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_directoriosMouseClicked
       
            int row=directorios.getSelectedRow();
            carpeta_id=directorios.getModel().getValueAt(row, 3).toString();
            
            
                for (Carpeta p : carpetas) {
                    if(carpeta_id==String.valueOf( p.getId() ) ){
                        try {
                            
                            Exec.cmd(nodo, "rm -r "+p.getDireccion());
                            ApplicationController.CarpetaDao.delete(p);
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminNodo.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }
                            break;
                    }
                }  
            
    }//GEN-LAST:event_directoriosMouseClicked

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
       
        
            int row=directorios.getSelectedRow();
            carpeta_id=directorios.getModel().getValueAt(row, 3).toString();
            
            
                for (Carpeta p : carpetas) {
                    if(carpeta_id==String.valueOf( p.getId() ) ){
                        
                        
                        
                        break;
                    }
                }  
        
        
    }//GEN-LAST:event_borrarActionPerformed

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
    private javax.swing.JButton cmd;
    private javax.swing.JLabel cpu_pid;
    private javax.swing.JButton desintalar;
    private javax.swing.JTable directorios;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton matar;
    private javax.swing.JLabel mem_pid;
    private javax.swing.JLabel nodoip;
    private javax.swing.JLabel nodonombre;
    private javax.swing.JLabel nombre_Dir;
    private javax.swing.JLabel nombre_pid;
    private javax.swing.JTable tableDispositivo;
    private javax.swing.JTable tableProcess;
    // End of variables declaration//GEN-END:variables

}
