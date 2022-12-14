/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagestionturnos;

import Clases.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Santos
 */
public class JPrincipal extends javax.swing.JFrame {
    Turnos turno= new Turnos();
    /**
     * Creates new form JPrincipal
     */
    public JPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.jMenuItemAtencionClientes.setEnabled(turno.comprobacion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuServicios = new javax.swing.JMenu();
        jMenuItemGenerarTickets = new javax.swing.JMenuItem();
        jMenuItemAtencionClientes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemTimposEspera = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 51, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jMenuServicios.setText("Servicios");

        jMenuItemGenerarTickets.setText("Generar Tickets");
        jMenuItemGenerarTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGenerarTicketsActionPerformed(evt);
            }
        });
        jMenuServicios.add(jMenuItemGenerarTickets);

        jMenuItemAtencionClientes.setText("Atender clientes");
        jMenuItemAtencionClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAtencionClientesActionPerformed(evt);
            }
        });
        jMenuServicios.add(jMenuItemAtencionClientes);

        jMenuBar1.add(jMenuServicios);

        jMenu2.setText("Controlador turnos");

        jMenuItem1.setText("Monitor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Estadisticas");

        jMenuItemTimposEspera.setText("Tiempos de espera");
        jMenuItemTimposEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTimposEsperaActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemTimposEspera);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDesktop.getAccessibleContext().setAccessibleName("");
        jDesktop.getAccessibleContext().setAccessibleDescription("Sistema Gestion Turnos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemGenerarTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGenerarTicketsActionPerformed
        try {
            Generar_ticket genTicket= new Generar_ticket();
            this.jDesktop.add(genTicket);
            genTicket.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(JPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jMenuItemAtencionClientes.setEnabled(true);
    }//GEN-LAST:event_jMenuItemGenerarTicketsActionPerformed

    private void jMenuItemAtencionClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAtencionClientesActionPerformed
       try {
            turno.clienteAtendido(JOptionPane.showInputDialog(null, "Ingrese el ID del ticket", "Atendiendo a cliente"));
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(JPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAtencionClientesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Pantalla pantalla= new Pantalla();
        this.jDesktop.add(pantalla);
        pantalla.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemTimposEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTimposEsperaActionPerformed
        try {
            Estadisticas metricas= new Estadisticas();
            this.jDesktop.add(metricas);
            metricas.setVisible(true);
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(JPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemTimposEsperaActionPerformed

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
            java.util.logging.Logger.getLogger(JPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAtencionClientes;
    private javax.swing.JMenuItem jMenuItemGenerarTickets;
    private javax.swing.JMenuItem jMenuItemTimposEspera;
    private javax.swing.JMenu jMenuServicios;
    // End of variables declaration//GEN-END:variables
}
