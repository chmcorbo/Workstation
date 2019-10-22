/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import classes.global;
import classes.global_class;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import classes.*;

/**
 *
 * @author ROBSMAC
 */
public class cd_usuario_editar extends javax.swing.JDialog {

    JTable main_tab;
    int piEdicao = 0;

    //backup dos campos
    String ls_Login_bkp;
    String ls_Nome_bkp;
    String ls_Perfil_bkp;

    public cd_usuario_editar() {
        initComponents();
    }

    /**
     * Creates new form dth_mr_editar
     *
     * @param iEdicao
     * @param sLogin
     * @param sNome
     * @param sPerfil
     */
    
    public cd_usuario_editar(Integer iEdicao, String sLogin, String sNome, String sPerfil) {
        initComponents();

        //backup dos campos
        ls_Login_bkp = sLogin;
        ls_Nome_bkp = sNome;
        ls_Perfil_bkp = sPerfil;

        this.setTitle("Edição de usuário - ");

        if (iEdicao == 0) {
            jtxt_login.setEditable(true);
            this.setTitle(this.getTitle() + "Inclusão");
            jbt_salvar.setText("Incluir");
        } else {
            jtxt_login.setEditable(false);
            this.setTitle(this.getTitle() + "Alteração");
            jbt_salvar.setText("Alterar");
        }

        //login
        jtxt_login.setText(sLogin);
        //nome
        jtxt_nome.setText(sNome);
        //perfil
        jcb_perfil.setSelectedIndex(0);
        for (int i = 0; i < jcb_perfil.getItemCount(); i++) {
            if (sPerfil.trim().toUpperCase().equals(jcb_perfil.getItemAt(i).toString().trim().toUpperCase())) {
                jcb_perfil.setSelectedIndex(i);
                break;
            }
        }

        piEdicao = iEdicao;
        
        global.open_modal(this, this.getTitle());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxt_login = new javax.swing.JTextField();
        jbt_salvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcb_perfil = new javax.swing.JComboBox();
        jtxt_nome = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edição de MR");

        jLabel1.setText("Login");

        jtxt_login.setColumns(15);

        jbt_salvar.setText("Salvar");
        jbt_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_salvarActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome");

        jLabel3.setText("Perfil");

        jcb_perfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Inativo", "Garantia", "Pool", "Supervisor", "CDesk", "DEV_MASTER" }));

        jtxt_nome.setColumns(50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbt_salvar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxt_login, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcb_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxt_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jtxt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jbt_salvar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbt_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_salvarActionPerformed

        StringBuilder sSQL = new StringBuilder();
        StringBuilder sSQL_Log = new StringBuilder();
        StringBuilder sMsg = new StringBuilder();

        String s_login = "";
        String s_nome = "";
        String s_perfil = "";
        String s_acao = "";

        if (piEdicao == 0 || piEdicao == 1) {
            s_login = jtxt_login.getText().trim();
            s_nome = jtxt_nome.getText().trim();
            s_perfil = jcb_perfil.getSelectedItem().toString().trim();

            if (s_login.length() == 0) {
                sMsg.append("Informe o login\n");
            }
            if (s_nome.length() == 0) {
                sMsg.append("Informe o nome\n");
            }
            if (jcb_perfil.getSelectedIndex() < 1) {
                sMsg.append("Informe o perfil \n");
            }

            if (sMsg.length() != 0) {
                global.show_error_message("Faltam informações. Veja abaixo: \n\n" + sMsg.toString());
                return;
            }

            if (ls_Login_bkp.trim().toUpperCase().equals(s_login.toUpperCase())
                    && ls_Nome_bkp.trim().toUpperCase().equals(s_nome.toUpperCase())
                    && ls_Perfil_bkp.trim().toUpperCase().equals(s_perfil.toUpperCase())
                    ) {
                global.show_message("Nenhuma mudança realizada.\n\nA janela será fechada");
                this.dispose();
                return;
            }
        }

        //atualizações - inicio
        try {
            Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
            setCursor(hourglassCursor);
            
            switch (s_perfil.toLowerCase().trim()) {
                case "dev_master" :
                    s_perfil = "0";
                    break;
                case "supervisor" :
                    s_perfil = "1";
                    break;
                case "garantia" :
                    s_perfil = "2";
                    break;
                case "pool" :
                    s_perfil = "3";
                    break;
                case "cdesk" :
                    s_perfil = "4";
                    break;
                default :
                    s_perfil = "-1";
                    break;
            }

            if (piEdicao == 0) {
                sSQL.append("insert into workstation_bcc.tbl_users ");
                sSQL.append("(login, nome, perfil) ");
                sSQL.append("select ");
                sSQL.append("'").append(s_login).append("' as login, ");
                sSQL.append("'").append(s_nome).append("' as nome, ");
                sSQL.append("'").append(s_perfil).append("' as perfil ");
                sSQL.append("from dual ");
                sSQL.append("where not exists ");
                sSQL.append("( ");
                sSQL.append("select distinct login ");
                sSQL.append("from workstation_bcc.tbl_users ");
                sSQL.append("where trim(upper(login)) = trim(upper('").append(s_login).append("')) ");
                sSQL.append(") ");
                sSQL.append("; ");
            } else {
                sSQL.append("update workstation_bcc.tbl_users ");
                sSQL.append("set ");
                sSQL.append("nome = '").append(s_nome).append("', ");
                sSQL.append("perfil = ").append(s_perfil).append(" ");
                sSQL.append("where login = '").append(s_login).append("'; ");
            }

            Connection conn = Db_class.mysql_conn();
            Db_class.mysql_insert(sSQL.toString(), conn);

            //log - inicio
            sSQL_Log.delete(0, sSQL_Log.length());
            sSQL_Log.append("insert into tb_log (login, dt_registro, comando) ");
            sSQL_Log.append("select ");
            sSQL_Log.append("'").append(System.getProperty("user.name")).append("', ");
            sSQL_Log.append("now(), ");
            sSQL_Log.append("trim( ");
            sSQL_Log.append("substr( ");
            sSQL_Log.append("'").append(sSQL.toString().replaceAll("'", "")).append("' ");
            sSQL_Log.append(", 1, 1000) ");
            sSQL_Log.append(") ");
            sSQL_Log.append("; ");

            Db_class.mysql_insert(sSQL_Log.toString(), conn);
            //log - final

            Db_class.close_conn(conn);

            Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
            setCursor(normalCursor);

            global.show_message("Informações gravadas.\n\nNa janela anterior, execute uma nova consulta para\nver o resultado.");
            this.dispose();
            //atualizações - final
        } catch (Exception ex) {
            Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
            setCursor(normalCursor);
            global.show_error_message("Problemas para gravar as informações\n\nErro: " + ex.getMessage());
        }

    }//GEN-LAST:event_jbt_salvarActionPerformed

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
            java.util.logging.Logger.getLogger(cd_usuario_editar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cd_usuario_editar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cd_usuario_editar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cd_usuario_editar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        final Integer iEdicao = Integer.parseInt(args[0]);
        final String sLogin = args[1];
        final String sNome = args[2];
        final String sPerfil = args[3];

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cd_usuario_editar(iEdicao, sLogin, sNome, sPerfil).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbt_salvar;
    private javax.swing.JComboBox jcb_perfil;
    private javax.swing.JTextField jtxt_login;
    private javax.swing.JTextField jtxt_nome;
    // End of variables declaration//GEN-END:variables
}
