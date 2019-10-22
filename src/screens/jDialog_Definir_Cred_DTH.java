package screens;

import classes.global_class;

public class jDialog_Definir_Cred_DTH extends javax.swing.JDialog {

    scr_Menu Menu04;
    jPanel_MRFechada dialog_definir;

    public jDialog_Definir_Cred_DTH(boolean modal, scr_Menu MPrinc00, jPanel_MRFechada definir) {
        super(MPrinc00, true);
        initComponents();

        Menu04 = MPrinc00;
        dialog_definir = definir;
        global_class.preencher_combobox(combo_un, "Selecione uma credenciada", "SELECT NM_PARCEIRA FROM workstation_bcc.tbl_estrutura_dth GROUP BY NM_PARCEIRA ASC");

        this.setTitle("WorkStation BCC");
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        combo_un = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(360, 120));
        setResizable(false);

        combo_un.setMaximumSize(new java.awt.Dimension(300, 25));
        combo_un.setMinimumSize(new java.awt.Dimension(300, 25));
        combo_un.setPreferredSize(new java.awt.Dimension(300, 25));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group_add.png"))); // NOI18N
        jButton1.setText("Definir Equipe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_un, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(combo_un, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        dispose();
        dialog_definir.action_definir_eqp(combo_un.getSelectedItem().toString());

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox combo_un;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
