/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import javax.swing.ListSelectionModel;
import classes.*;

/**
 *
 * @author ROBSMAC
 */
//public class dth_mr_cadastro extends javax.swing.JFrame {
public class cd_usuario extends javax.swing.JDialog {

    scr_Menu mn;
    JTable main_tab;

    /**
     * Creates new form dth_mr_cadastro
     */
    public cd_usuario() {
        initComponents();
    }

    public cd_usuario(scr_Menu mn, boolean modal) {
        super(mn, modal);
        initComponents();

        this.mn = mn;
        //atualiza_painel();

        global.open_modal(this, "Cadastro de Usuários");
    }

    public void atualiza_painel() {

        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        jpnl_planilha.removeAll();
        jpnl_planilha.repaint();
        jpnl_planilha.revalidate();

        String query = "select id, login as Login, nome as Nome, \n" +
                "ifnull(perfil, '?') as Cod_Perfil,\n" +
                "case\n" +
                "when perfil = -1 then 'Inativo'\n" +
                "when perfil = 0 then 'DEV_MASTER'\n" +
                "when perfil = 1 then 'Supervisor'\n" +
                "when perfil = 2 then 'Garantia'\n" +
                "when perfil = 3 then 'Pool'\n" +
                "when perfil = 4 then 'CDesk'\n" +
                "else '?'\n" +
                "end Perfil\n" +
                "from workstation_bcc.tbl_users\n" +
                "where login like '%" + jtxt_localizar.getText().trim() + "%' " +
                "or nome like '%" + jtxt_localizar.getText().trim() + "%' " +
                "order by nome, login";

        try {
            setCursor(hourglassCursor);
            JTable tab = global.getTable(query, jpnl_planilha);

            int invisible_ids[] = {0};
            int column_widths[] = {50, 50, 200, 50, 100};

            global.hide_columns(invisible_ids, tab);
            global.adjust_columns(column_widths, tab);

            main_tab = tab;

            TableFilterHeader filter = new TableFilterHeader(main_tab, AutoChoices.ENABLED);
            filter.setAdaptiveChoices(true);

            main_tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jlbl_status_bar.setText(main_tab.getRowCount() + " registro(s) encontrado(s)");
            setCursor(normalCursor);

        } catch (Exception ex) {
            setCursor(normalCursor);
            jlbl_status_bar.setText("Erro na última consulta");
            //Logger.getLogger(div_turno.class.getName()).log(Level.SEVERE, null, ex);
            global.show_error_message("Problemas na consulta.\n\nErro original: " + ex.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbt_alterar = new javax.swing.JButton();
        jbt_incluir = new javax.swing.JButton();
        jlbl_status_bar = new javax.swing.JLabel();
        jpnl_busca = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbt_localizar = new javax.swing.JButton();
        jtxt_localizar = new javax.swing.JTextField();
        jpnl_planilha = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmni_servicos_alterar = new javax.swing.JMenuItem();
        jmni_servicos_incluir = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmni_servicos_fechar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de usuários");
        setBackground(new java.awt.Color(255, 51, 204));

        jbt_alterar.setText("Alterar");
        jbt_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_alterarActionPerformed(evt);
            }
        });

        jbt_incluir.setText("Incluir");
        jbt_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_incluirActionPerformed(evt);
            }
        });

        jlbl_status_bar.setText("Aguardando comando");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jbt_alterar)
                .addGap(75, 75, 75)
                .addComponent(jbt_incluir)
                .addGap(0, 338, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbl_status_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbt_alterar)
                    .addComponent(jbt_incluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbl_status_bar))
        );

        jpnl_busca.setBackground(new java.awt.Color(255, 255, 204));
        jpnl_busca.setPreferredSize(new java.awt.Dimension(424, 23));

        jLabel1.setText("Login ou nome a localizar (parte ou todo)");

        jbt_localizar.setText("Localizar");
        jbt_localizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_localizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnl_buscaLayout = new javax.swing.GroupLayout(jpnl_busca);
        jpnl_busca.setLayout(jpnl_buscaLayout);
        jpnl_buscaLayout.setHorizontalGroup(
            jpnl_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnl_buscaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtxt_localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbt_localizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnl_buscaLayout.setVerticalGroup(
            jpnl_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnl_buscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jbt_localizar)
                .addComponent(jtxt_localizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnl_planilha.setBackground(new java.awt.Color(204, 204, 255));
        jpnl_planilha.setToolTipText("");

        javax.swing.GroupLayout jpnl_planilhaLayout = new javax.swing.GroupLayout(jpnl_planilha);
        jpnl_planilha.setLayout(jpnl_planilhaLayout);
        jpnl_planilhaLayout.setHorizontalGroup(
            jpnl_planilhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnl_planilhaLayout.setVerticalGroup(
            jpnl_planilhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        jMenu1.setText("Serviços");

        jmni_servicos_alterar.setText("Alterar");
        jmni_servicos_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmni_servicos_alterarActionPerformed(evt);
            }
        });
        jMenu1.add(jmni_servicos_alterar);

        jmni_servicos_incluir.setText("Incluir");
        jmni_servicos_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmni_servicos_incluirActionPerformed(evt);
            }
        });
        jMenu1.add(jmni_servicos_incluir);
        jMenu1.add(jSeparator1);

        jmni_servicos_fechar.setText("Fechar janela");
        jmni_servicos_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmni_servicos_fecharActionPerformed(evt);
            }
        });
        jMenu1.add(jmni_servicos_fechar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnl_busca, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnl_planilha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnl_busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnl_planilha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmni_servicos_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmni_servicos_fecharActionPerformed
        // TODO add your handling code here:
        //global.show_message("Fechar janela");
        this.dispose();
    }//GEN-LAST:event_jmni_servicos_fecharActionPerformed

    private void jbt_localizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_localizarActionPerformed
        // TODO add your handling code here:
        atualiza_painel();
    }//GEN-LAST:event_jbt_localizarActionPerformed

    private void jmni_servicos_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmni_servicos_alterarActionPerformed
        // TODO add your handling code here:

        if (!testaEdicao()) {
            global.show_error_message("Nada selecionado");
        } else {
            cd_usuario_editar cad_usuario_editar = new cd_usuario_editar(
                    1,
                    main_tab.getModel().getValueAt(main_tab.getSelectedRow(), 1).toString(),
                    main_tab.getModel().getValueAt(main_tab.getSelectedRow(), 2).toString(),
                    main_tab.getModel().getValueAt(main_tab.getSelectedRow(), 4).toString()
            );
        }

    }//GEN-LAST:event_jmni_servicos_alterarActionPerformed

    private boolean testaEdicao() {
        boolean bRetorno = true;

        if (main_tab == null) {
            bRetorno = false;
        } else {
            if (main_tab.getRowCount() < 1) {
                bRetorno = false;
            } else {
                if (main_tab.getSelectedRowCount() != 1) {
                    bRetorno = false;
                }
            }
        }

        return bRetorno;
    }

    private void jmni_servicos_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmni_servicos_incluirActionPerformed
        // TODO add your handling code here:

        cd_usuario_editar cad_usuario_editar = new cd_usuario_editar(0, "", "", "");

    }//GEN-LAST:event_jmni_servicos_incluirActionPerformed

    private void jbt_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_alterarActionPerformed
        // TODO add your handling code here:
        jmni_servicos_alterarActionPerformed(null);
    }//GEN-LAST:event_jbt_alterarActionPerformed

    private void jbt_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_incluirActionPerformed
        // TODO add your handling code here:
        jmni_servicos_incluirActionPerformed(null);
    }//GEN-LAST:event_jbt_incluirActionPerformed

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
            java.util.logging.Logger.getLogger(cd_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cd_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cd_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cd_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cd_usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton jbt_alterar;
    private javax.swing.JButton jbt_incluir;
    private javax.swing.JButton jbt_localizar;
    private javax.swing.JLabel jlbl_status_bar;
    private javax.swing.JMenuItem jmni_servicos_alterar;
    private javax.swing.JMenuItem jmni_servicos_fechar;
    private javax.swing.JMenuItem jmni_servicos_incluir;
    private javax.swing.JPanel jpnl_busca;
    private javax.swing.JPanel jpnl_planilha;
    private javax.swing.JTextField jtxt_localizar;
    // End of variables declaration//GEN-END:variables
}
