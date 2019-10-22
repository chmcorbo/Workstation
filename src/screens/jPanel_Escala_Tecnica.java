package screens;

import classes.Db_class;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class jPanel_Escala_Tecnica extends javax.swing.JPanel {

    Border soft = new SoftBevelBorder(SoftBevelBorder.RAISED);
    scr_Menu Menu07;
    Connection conn = null;
    JScrollPane scrollPane;
    JTable table_casos;
    String atividade = "";

    String sClusters;

    public jPanel_Escala_Tecnica(scr_Menu main_menu, String ativ) {

        initComponents();
        this.Menu07 = main_menu;
        atividade = ativ;

        clustersAtualizar();

    }

    private void clustersAtualizar() {
        String query_disp;
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        try {
            setCursor(hourglassCursor);
            lista_cluster.removeAll();
            lista_areas.removeAll();

            query_disp = "SELECT cluster \n";
            query_disp = query_disp + "FROM workstation_bcc.tbl_escala_garantia \n";

            if (atividade.equals("GPON")) {
                jckb_vt.setEnabled(false);
                jckb_vt.setSelected(false);
                query_disp = query_disp + "WHERE cidade_net_sms in \n";
            } else {
                if (atividade.equals("GARANTIA")) {
                    jckb_vt.setEnabled(true);
                    jckb_vt.setSelected(true);
                    query_disp = query_disp + "WHERE tipo_tecnico = 'IAT' \n";
                    query_disp = query_disp + "and cidade_net_sms not in \n";
                } else {
                    jckb_vt.setEnabled(false);
                    jckb_vt.setSelected(false);
                    query_disp = query_disp + "WHERE tipo_tecnico = 'EPO' \n";
                    query_disp = query_disp + "and cidade_net_sms not in \n";
                }
            }
            query_disp = query_disp + "( \n";
            query_disp = query_disp + "select nm_cidade \n";
            query_disp = query_disp + "from workstation_bcc.tbl_gpon_cidades \n";
            query_disp = query_disp + "order by nm_cidade \n";
            query_disp = query_disp + ") \n";
            query_disp = query_disp + "GROUP BY cluster ORDER BY cluster";

            DefaultListModel listModel = new DefaultListModel();
            conn = null;
            //conn = global_class.mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = global_class.mysql_result(conn, query_disp);

            while (rs.next()) {
                listModel.addElement(rs.getObject(1));
            }

            lista_cluster.setModel(listModel);

            Db_class.close_conn(conn);
            setCursor(normalCursor);

            if (listModel.getSize() < 50) {
                String ls_mensagem = "";
                if (listModel.getSize() == 0) {
                    ls_mensagem = "Não há Clusters.\n\nTente novamente em 1 minuto e, caso\na situação continue, informe seu supervisor.";
                } else {
                    if (!atividade.equals("GPON")) {
                        ls_mensagem = "A lista de Clusters está menor que o de costume.\n\nTente novamente em 1 minuto e, caso\na situação continue, informe seu supervisor.";
                    }
                }
                if (ls_mensagem.length() > 0) {
                    global_class.msg_dialog(ls_mensagem, JOptionPane.WARNING_MESSAGE);
                }
            }

            jbt_clusters_atualizar.setToolTipText("No momento, há " + listModel.getSize() + " cluster(s) na lista");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            //Logger.getLogger(jPanel_Escala_Tecnica.class.getName()).log(Level.SEVERE, null, ex);
            jbt_clusters_atualizar.setToolTipText("Falha na última consulta. Atualize novamente em 1 minuto.");
            global_class.msg_dialog("Parâmetro Cluster.\n\nProblemas na carga das opções de busca.\n\nErro: " + ex.getMessage() + "\n\nInforme seu supervisor", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                Db_class.close_conn(conn);
            } catch (Exception ex0) {
            }
            setCursor(normalCursor);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpnl_buscapadrao = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_cluster = new javax.swing.JList();
        jbt_busca_padrao_buscar = new javax.swing.JButton();
        jckb_vt = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        lista_areas = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbt_clusters_atualizar = new javax.swing.JButton();
        jpnl_busca_avancada = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtxt_cluster = new javax.swing.JTextField();
        jtxt_area = new javax.swing.JTextField();
        jtxt_tecnico_nome = new javax.swing.JTextField();
        jtxt_tecnico_login = new javax.swing.JTextField();
        jbt_busca_avancada_buscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(714, 257));

        jpnl_buscapadrao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lista_cluster.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_clusterValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista_cluster);

        jbt_busca_padrao_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png"))); // NOI18N
        jbt_busca_padrao_buscar.setText("Buscar Dados");
        jbt_busca_padrao_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_busca_padrao_buscarActionPerformed(evt);
            }
        });

        jckb_vt.setSelected(true);
        jckb_vt.setText("Buscar apenas técnicos de VT");

        jScrollPane4.setViewportView(lista_areas);

        jLabel1.setText("Selecione um ou mais clusters");

        jLabel2.setText("Selecione uma ou mais áreas do(s) cluster(s) selecionado(s)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("(Todas)");
        jLabel3.setToolTipText("Seleciona todas as áreas");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jbt_clusters_atualizar.setText("Atualizar lista de clusters");
        jbt_clusters_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_clusters_atualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnl_buscapadraoLayout = new javax.swing.GroupLayout(jpnl_buscapadrao);
        jpnl_buscapadrao.setLayout(jpnl_buscapadraoLayout);
        jpnl_buscapadraoLayout.setHorizontalGroup(
            jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnl_buscapadraoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbt_clusters_atualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnl_buscapadraoLayout.createSequentialGroup()
                        .addComponent(jckb_vt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbt_busca_padrao_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnl_buscapadraoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnl_buscapadraoLayout.setVerticalGroup(
            jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnl_buscapadraoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbt_busca_padrao_buscar)
                    .addGroup(jpnl_buscapadraoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jckb_vt)
                        .addComponent(jbt_clusters_atualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busca padrão", jpnl_buscapadrao);
        jpnl_buscapadrao.getAccessibleContext().setAccessibleName("");

        jpnl_busca_avancada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(244, 236, 201));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea1.setRows(4);
        jTextArea1.setText("A Busca Avançada é realizada por semelhança tanto de Cluster, quanto de Área, quanto do Nome ou Login do técnico.\nEla pode ser bastante útil no caso da Busca Padrão não apresentar os resultados esperados.\nSempre observe, na planilha de resultados, os campos-chave para verificar se há algo inesperado.\nLembrando que a atualização da Escala ocorre 5 vezes por hora.");
        jScrollPane2.setViewportView(jTextArea1);

        jLabel4.setText("Cluster");

        jLabel5.setText("Área");

        jLabel6.setText("Nome do técnico");

        jLabel7.setText("Login do técnico");

        jtxt_cluster.setColumns(30);

        jtxt_area.setColumns(30);

        jtxt_tecnico_nome.setColumns(30);

        jtxt_tecnico_login.setColumns(30);

        jbt_busca_avancada_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png"))); // NOI18N
        jbt_busca_avancada_buscar.setText("Buscar Dados");
        jbt_busca_avancada_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_busca_avancada_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnl_busca_avancadaLayout = new javax.swing.GroupLayout(jpnl_busca_avancada);
        jpnl_busca_avancada.setLayout(jpnl_busca_avancadaLayout);
        jpnl_busca_avancadaLayout.setHorizontalGroup(
            jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnl_busca_avancadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jpnl_busca_avancadaLayout.createSequentialGroup()
                        .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(34, 34, 34)
                        .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxt_tecnico_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jtxt_area, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jtxt_cluster, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jtxt_tecnico_login, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                        .addComponent(jbt_busca_avancada_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnl_busca_avancadaLayout.setVerticalGroup(
            jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnl_busca_avancadaLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxt_cluster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxt_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtxt_tecnico_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnl_busca_avancadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtxt_tecnico_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbt_busca_avancada_buscar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busca avançada", jpnl_busca_avancada);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Consulta padrão");

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.BorderLayout());
        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbt_busca_padrao_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_busca_padrao_buscarActionPerformed
        search(0);
    }//GEN-LAST:event_jbt_busca_padrao_buscarActionPerformed

    private void lista_clusterValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_clusterValueChanged

        try {
            int[] selected_indexes = lista_cluster.getSelectedIndices();
            boolean control = false;
            String query_part = "";
            String final_query;

            for (int i = 0; i < selected_indexes.length; i++) {
                if (control == false) {
                    query_part = query_part + "'" + lista_cluster.getModel().getElementAt(selected_indexes[i]) + "'";
                    control = true;
                } else {
                    query_part = query_part + ", '" + lista_cluster.getModel().getElementAt(selected_indexes[i]) + "'";
                }
            }

            final_query = "SELECT case when area_net_sms='' THEN '<VAZIO>' else area_net_sms end as area_net_sms \n";
            final_query = final_query + "FROM workstation_bcc.tbl_escala_garantia \n";
            final_query = final_query + "WHERE cluster IN (" + query_part + ") \n";

            if (atividade.equals("GPON")) {
                final_query = final_query + "and cidade_net_sms in \n";
            } else {
                if (atividade.equals("GARANTIA")) {
                    final_query = final_query + "AND tipo_tecnico = 'IAT' \n";
                    final_query = final_query + "and cidade_net_sms not in \n";
                } else {
                    final_query = final_query + "AND tipo_tecnico = 'EPO' \n";
                    final_query = final_query + "and cidade_net_sms not in \n";
                }
            }
            
            final_query = final_query + "( \n";
            final_query = final_query + "select nm_cidade \n";
            final_query = final_query + "from workstation_bcc.tbl_gpon_cidades \n";
            final_query = final_query + "order by nm_cidade \n";
            final_query = final_query + ") \n";

            final_query = final_query + "GROUP BY area_net_sms \n";
            final_query = final_query + "ORDER BY area_net_sms";

            /*
            if (atividade.equals("GARANTIA")) {
                final_query = "SELECT case when area_net_sms='' THEN '<VAZIO>' else area_net_sms end as area_net_sms "
                        + "FROM workstation_bcc.tbl_escala_garantia "
                        + "WHERE cluster IN (" + query_part + ") "
                        + "AND tipo_tecnico = 'IAT' "
                        + "GROUP BY area_net_sms "
                        + "ORDER BY area_net_sms";
            } else {
                final_query = "SELECT case when area_net_sms='' THEN '<VAZIO>' else area_net_sms end as area_net_sms "
                        + "FROM workstation_bcc.tbl_escala_garantia "
                        + "WHERE cluster IN (" + query_part + ") "
                        + "AND tipo_tecnico = 'EPO' "
                        + "GROUP BY area_net_sms "
                        + "ORDER BY area_net_sms";
            }
            */

            lista_areas.removeAll();

            DefaultListModel listModel = new DefaultListModel();

            //conn = global_class.mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = global_class.mysql_result(conn, final_query);

            while (rs.next()) {

                listModel.addElement(rs.getObject(1));

            }

            lista_areas.setModel(listModel);
            Db_class.close_conn(conn);

            sClusters = query_part;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_Escala_Tecnica.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }//GEN-LAST:event_lista_clusterValueChanged

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        global_class.select_all_list(lista_areas);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jbt_clusters_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_clusters_atualizarActionPerformed
        clustersAtualizar();
    }//GEN-LAST:event_jbt_clusters_atualizarActionPerformed

    private void jbt_busca_avancada_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_busca_avancada_buscarActionPerformed
        search(1);
    }//GEN-LAST:event_jbt_busca_avancada_buscarActionPerformed

    public void search(Integer iTipoBusca) {

        String ls_sql = "";
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        try {
            setCursor(hourglassCursor);
            jPanel2.removeAll();

            if (iTipoBusca == 0) {
                int[] selected_indexes = lista_areas.getSelectedIndices();
                boolean control = false;
                String query_part_areas = "";

                for (int i = 0; i < selected_indexes.length; i++) {
                    if (control == false) {
                        query_part_areas = query_part_areas + "'" + lista_areas.getModel().getElementAt(selected_indexes[i]) + ",";
                        control = true;
                    } else {
                        query_part_areas = query_part_areas + lista_areas.getModel().getElementAt(selected_indexes[i]) + ",";
                    }
                }

                if (control == false) {
                    setCursor(normalCursor);
                    String msg = "<html><body><p width='150px'>É necessário selecionar pelo menos uma área!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    query_part_areas = query_part_areas.substring(0, query_part_areas.length() - 1) + "'";
                }

                ls_sql = "CALL workstation_bcc.app_escala_tecnicos_all_003(" + sClusters.replaceAll("', '", ",") + "," + query_part_areas.replaceAll("<VAZIO>", "") + ",'" + atividade + "', " + (jckb_vt.isSelected() ? "1" : "0") + ")";
            } else {
                String ls_cluster;
                String ls_area;
                String ls_tecnico_nome;
                String ls_tecnico_login;

                ls_cluster = jtxt_cluster.getText().trim();
                ls_area = jtxt_area.getText().trim();
                ls_tecnico_nome = jtxt_tecnico_nome.getText().trim();
                ls_tecnico_login = jtxt_tecnico_login.getText().trim();
                
                if (ls_cluster.length() == 0 && ls_area.length() == 0 && ls_tecnico_nome.length() == 0 && ls_tecnico_login.length() == 0) {
                    setCursor(normalCursor);
                    global_class.msg_dialog("Informe ao menos um campo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                ls_sql = "CALL workstation_bcc.app_escala_tecnicos_all_adv('" + ls_cluster + "','" + ls_area + "','" + ls_tecnico_nome + "','" + ls_tecnico_login + "','" + atividade + "')";
            }

            table_casos = null;
            table_casos = global_class.getTable(ls_sql);
            table_casos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            TitledBorder title = BorderFactory.createTitledBorder(soft, "" + table_casos.getRowCount() + " técnicos localizados");
            jPanel2.setBorder(title);

            CustomRenderer tcustom = new CustomRenderer();

            for (int column = 0; column < table_casos.getColumnCount(); column++) {

                TableColumn tableColumn = table_casos.getColumnModel().getColumn(column);
                int preferredWidth = tableColumn.getMinWidth();
                int maxWidth = tableColumn.getMaxWidth();

                for (int row = 0; row < table_casos.getRowCount(); row++) {
                    TableCellRenderer cellRenderer = table_casos.getCellRenderer(row, column);
                    Component c = table_casos.prepareRenderer(cellRenderer, row, column);
                    int width = c.getPreferredSize().width + table_casos.getIntercellSpacing().width;
                    preferredWidth = Math.max(preferredWidth, width);

                    if (preferredWidth >= maxWidth) {
                        preferredWidth = maxWidth + 40;
                        break;
                    }
                }

                tableColumn.setCellRenderer(tcustom);
                tableColumn.setPreferredWidth(preferredWidth);
                tableColumn.setMinWidth(preferredWidth + 60);
                tableColumn.setMaxWidth(preferredWidth + 60);

            }

            TableFilterHeader filterHeader = new TableFilterHeader(table_casos, AutoChoices.ENABLED);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle screenRect = ge.getMaximumWindowBounds();

            table_casos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            table_casos.setRowSelectionAllowed(true);
            table_casos.getTableHeader().setReorderingAllowed(false);
            table_casos.setLocation(0, 0);
            table_casos.setSize(jPanel2.getWidth() - 10, jPanel2.getHeight() - 10);

            table_casos.setVisible(true);
            scrollPane = new JScrollPane(table_casos);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setLocation(5, 5);
            scrollPane.setSize(screenRect.width - 20, screenRect.height - 130);
            scrollPane.setVisible(true);
            jPanel2.add(scrollPane);
            jPanel2.repaint();
            jPanel2.revalidate();
            
            setCursor(normalCursor);

        } catch (Exception ex) {
            setCursor(normalCursor);
            Logger.getLogger(jPanel_Escala_Tecnica.class.getName()).log(Level.SEVERE, null, ex);
            global_class.msg_dialog("Falha na consulta.\n\nErro: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

    }

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            DecimalFormat formatter = new DecimalFormat("#.###");
            formatter.setMinimumFractionDigits(0);

            if (value instanceof Double) {
                value = formatter.format((Number) value);
            }

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected && table.getValueAt(row, 0).equals("FALTA")) {

                cellComponent.setBackground(get_color(255, 102, 102));
                cellComponent.setForeground(table.getSelectionForeground());

            } else if (isSelected && (table.getValueAt(row, 0).equals("Rota Manual")
                    || table.getValueAt(row, 0).equals("Rota Manual - Hibrido"))) {

                cellComponent.setBackground(get_color(204, 204, 0));
                cellComponent.setForeground(Color.black);

            } else if (isSelected && table.getValueAt(row, 0).equals("Contingencia field")) {

                cellComponent.setBackground(get_color(204, 204, 0));
                cellComponent.setForeground(Color.black);

            } else if (isSelected && table.getValueAt(row, 0).equals("APOIO")) {

                cellComponent.setBackground(get_color(204, 204, 0));
                cellComponent.setForeground(Color.black);

            } else if ((table.getValueAt(row, 0).equals("Rota Manual")
                    || table.getValueAt(row, 0).equals("Rota Manual - Hibrido"))) {

                cellComponent.setBackground(Color.YELLOW);
                cellComponent.setForeground(Color.black);

            } else if (table.getValueAt(row, 1).equals("Contingencia field")) {

                cellComponent.setBackground(Color.YELLOW);
                cellComponent.setForeground(Color.black);

            } else if (table.getValueAt(row, 0).equals("APOIO")) {

                cellComponent.setBackground(Color.YELLOW);
                cellComponent.setForeground(Color.black);

            } else if (table.getValueAt(row, 0).equals("FALTA")) {

                cellComponent.setBackground(get_color(255, 153, 153));
                cellComponent.setForeground(Color.black);

            } else if (isSelected) {

                cellComponent.setBackground(table.getSelectionBackground());
                cellComponent.setForeground(table.getSelectionForeground());

            } else {

                cellComponent.setBackground(Color.WHITE);
                cellComponent.setForeground(table.getForeground());
                /*table.setShowHorizontalLines(true);
                 table.setShowVerticalLines(true);*/

            }

            if (column >= 25) {

                cellComponent.setHorizontalAlignment(JLabel.CENTER);
            }

            return cellComponent;
        }
    }

    public static Color get_color(int red, int green, int blue) {

        float[] hsb = Color.RGBtoHSB(red, green, blue, null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];

        Color cor = Color.getHSBColor(hue, saturation, brightness);

        return cor;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbt_busca_avancada_buscar;
    private javax.swing.JButton jbt_busca_padrao_buscar;
    private javax.swing.JButton jbt_clusters_atualizar;
    private javax.swing.JCheckBox jckb_vt;
    private javax.swing.JPanel jpnl_busca_avancada;
    private javax.swing.JPanel jpnl_buscapadrao;
    private javax.swing.JTextField jtxt_area;
    private javax.swing.JTextField jtxt_cluster;
    private javax.swing.JTextField jtxt_tecnico_login;
    private javax.swing.JTextField jtxt_tecnico_nome;
    private javax.swing.JList lista_areas;
    private javax.swing.JList lista_cluster;
    // End of variables declaration//GEN-END:variables
}
