package screens;

import classes.global;
import classes.global_class;
import java.util.ArrayList;

public class jDialog_Definir_Equipe extends javax.swing.JDialog {

    scr_Menu Menu04;
    jPanel_ForaField dialog_definir;
    ArrayList<Integer> casos_select = new ArrayList();

    public jDialog_Definir_Equipe(boolean modal, scr_Menu MPrinc00, ArrayList casos, ArrayList uns, jPanel_ForaField definir) {
        super(MPrinc00, true);
        initComponents();

        Menu04 = MPrinc00;
        dialog_definir = definir;
        global_class.preencher_combobox_array(combo_un, "Selecione uma UN", uns);

        this.setTitle("WorkStation BCC");
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

    public jDialog_Definir_Equipe(boolean modal, scr_Menu MPrinc00, ArrayList casos, String sCidade, String sAtividade, jPanel_ForaField definir) {
        super(MPrinc00, true);
        initComponents();

        ArrayList uns = new ArrayList<>();
        uns.add("Caso Sincronizado");
        uns.add("Demanda em andamento - Aguardando cadastro da parceira no WS");
        uns.add("Lider Orientado - Aguardando atualização no Cop Info");
        global_class.preenche_array(uns, "CALL workstation_bcc.app_uns_cid4('" + sCidade + "','" + sAtividade + "')", 1);

        lbl_cidade.setText(sCidade);
        lbl_atividade.setText(sAtividade);
        
        Menu04 = MPrinc00;
        dialog_definir = definir;
        global_class.preencher_combobox_array(combo_un, "Selecione uma UN", uns);

        this.setTitle("WorkStation BCC");
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        combo_un = new javax.swing.JComboBox();
        jbt_definir_equipe = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlbl_clique_aqui = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_cidade = new javax.swing.JLabel();
        lbl_atividade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(360, 120));
        setMinimumSize(new java.awt.Dimension(360, 120));
        setResizable(false);

        combo_un.setMaximumSize(new java.awt.Dimension(300, 25));
        combo_un.setMinimumSize(new java.awt.Dimension(300, 25));
        combo_un.setPreferredSize(new java.awt.Dimension(300, 25));

        jbt_definir_equipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group_add.png"))); // NOI18N
        jbt_definir_equipe.setText("Definir Equipe");
        jbt_definir_equipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_definir_equipeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Selecione uma UN, que é a combinação de Cidade com Parceira");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Caso não encontre a Parceira desejada na Cidade necessária,");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("para acessar recursos para identificar o problema.");

        jlbl_clique_aqui.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbl_clique_aqui.setForeground(new java.awt.Color(51, 51, 255));
        jlbl_clique_aqui.setText("clique aqui");
        jlbl_clique_aqui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbl_clique_aquiMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Cidade");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Atividade");

        lbl_cidade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_cidade.setText("lbl_cidade");

        lbl_atividade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_atividade.setText("lbl_atividade");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbt_definir_equipe, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(combo_un, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_cidade)
                                    .addComponent(lbl_atividade)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jlbl_clique_aqui, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_cidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbl_atividade))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_un, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jlbl_clique_aqui))
                .addGap(18, 18, 18)
                .addComponent(jbt_definir_equipe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jbt_definir_equipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_definir_equipeActionPerformed

        //verificando se a gerar e-mailção de e-mail será possível
        boolean b_email_ok = false;

        if (combo_un.getSelectedIndex() == 0) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            return;
        }

        //verificando se Cidade X Parceira ainda existe
        if (global.Valido_CidadeXParceira(combo_un.getSelectedItem().toString(), lbl_cidade.getText()) == 0) {
            global.show_warning_message(
                    "O relacionamento Cidade X Parceira não existe mais.\n"
                    + "Por favor, acesse o cadastro de parceiras para\n"
                    + "confirmar.\n\n"
                    + "Esse tipo de problema pode acontecer pela parceira\n"
                    + "não existir na cidade.\n\n"
            );
            return;
        }

        b_email_ok = true; //enquanto nao fica pronta a funcionalidade de checkup dos e-mails
        
        //decidindo se Associa ou Não
        if (b_email_ok) {
            dispose();
            dialog_definir.action_definir_eqp(combo_un.getSelectedItem().toString());
        } else {
            
        }

    }//GEN-LAST:event_jbt_definir_equipeActionPerformed

    private void jlbl_clique_aquiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbl_clique_aquiMouseClicked
        localiza_un lun = new localiza_un(Menu04, true, lbl_cidade.getText());
        lun.dispose();
    }//GEN-LAST:event_jlbl_clique_aquiMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox combo_un;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbt_definir_equipe;
    private javax.swing.JLabel jlbl_clique_aqui;
    private javax.swing.JLabel lbl_atividade;
    private javax.swing.JLabel lbl_cidade;
    // End of variables declaration//GEN-END:variables
}
