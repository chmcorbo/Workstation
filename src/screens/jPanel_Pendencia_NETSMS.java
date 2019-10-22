package screens;

import classes.Db_class;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

/**
 *
 * @author Victor Marchioretto
 */
public class jPanel_Pendencia_NETSMS extends javax.swing.JPanel {

    scr_Menu Menu03;
    ArrayList<String> janelas = new ArrayList<>();
    ArrayList<String> janelas_net = new ArrayList<>();
    ArrayList<String> janelas_selecionadas = new ArrayList<>();
    DefaultListModel lista;
    boolean verifica_criacao = false;
    boolean atualizacao = false;
    String result2 = "";
    String selects_janelas = "";
    String query_janelas = "";
    JTable tablePend;
    JScrollPane scrollPane;
    JTable tableEXPORT;
    Connection conn;
    String tabela_html;
    String ativ;

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            //if (Integer.parseInt(tab.getValueAt(row, column).toString()) != 0) {
            if (tab.getValueAt(row, column) != null) {

                if (isSelected) {

                    cellComponent.setBackground(Color.RED);
                    cellComponent.setForeground(Color.white);
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

                } else {

                    cellComponent.setBackground(Color.RED);
                    cellComponent.setForeground(Color.white);
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

                }

            } else if (isSelected) {

                cellComponent.setBackground(tab.getSelectionBackground());
                cellComponent.setForeground(tab.getSelectionForeground());
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else {

                cellComponent.setBackground(tab.getBackground());
                cellComponent.setForeground(tab.getForeground());
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            }

            return cellComponent;

        }
    }

    public jPanel_Pendencia_NETSMS(scr_Menu Menu00, String tipo) {
        initComponents();
        Menu03 = Menu00;
        ativ = tipo;
        ListSelectionModel listSelectionModel;

        listSelectionModel = lista_janelas.getSelectionModel();
        listSelectionModel.addListSelectionListener(new lista_action());

        verifica_att();
        Menu03.panel_pendencia = this;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_exibir = new javax.swing.JPanel();
        panel_janelas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_janelas = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btt_aplicar = new javax.swing.JButton();
        exp_excel = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        panel_exibir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panel_exibirLayout = new javax.swing.GroupLayout(panel_exibir);
        panel_exibir.setLayout(panel_exibirLayout);
        panel_exibirLayout.setHorizontalGroup(
            panel_exibirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_exibirLayout.setVerticalGroup(
            panel_exibirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );

        add(panel_exibir, java.awt.BorderLayout.CENTER);

        panel_janelas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_janelas.setMinimumSize(new java.awt.Dimension(200, 606));
        panel_janelas.setPreferredSize(new java.awt.Dimension(200, 606));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 100));
        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 100));

        jScrollPane1.setViewportView(lista_janelas);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Janelas:");

        btt_aplicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_refresh.png"))); // NOI18N
        btt_aplicar.setText("Aplicar");
        btt_aplicar.setMaximumSize(new java.awt.Dimension(85, 30));
        btt_aplicar.setMinimumSize(new java.awt.Dimension(85, 30));
        btt_aplicar.setPreferredSize(new java.awt.Dimension(85, 30));
        btt_aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_aplicarActionPerformed(evt);
            }
        });

        exp_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/export_excel.png"))); // NOI18N
        exp_excel.setText("Exportar Excel");
        exp_excel.setEnabled(false);
        exp_excel.setMaximumSize(new java.awt.Dimension(150, 30));
        exp_excel.setMinimumSize(new java.awt.Dimension(150, 30));
        exp_excel.setPreferredSize(new java.awt.Dimension(150, 30));
        exp_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp_excelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_janelasLayout = new javax.swing.GroupLayout(panel_janelas);
        panel_janelas.setLayout(panel_janelasLayout);
        panel_janelasLayout.setHorizontalGroup(
            panel_janelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_janelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_janelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panel_janelasLayout.createSequentialGroup()
                        .addGroup(panel_janelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btt_aplicar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exp_excel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_janelasLayout.setVerticalGroup(
            panel_janelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_janelasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btt_aplicar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exp_excel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(410, Short.MAX_VALUE))
        );

        add(panel_janelas, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    public void atualiza_janelas() {

        global_class.preenche_array(janelas_net, "CALL workstation_bcc.app_janelas_pendencia('" + ativ + "')", 1);

        lista = new DefaultListModel();
        lista_janelas.setModel(lista);
        lista_janelas.clearSelection();
        limpa();

        if (janelas_net.size() > 0) {
            for (int i = 0; i < janelas_net.size(); i++) {
                lista.addElement(janelas_net.get(i));
            }
        } else {
            global_class.msg_dialog
                        (
                        "Pendências não encontradas.\n\n" +
                        "Por favor, tente novamente em 5 minutos e, caso esta mensagem\n" +
                        "apareça novamente, abra um chamado no Lince.",
                        JOptionPane.WARNING_MESSAGE
                        );
        }

        lista_janelas.setModel(lista);
        lista_janelas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        btt_aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (!global_class.check_version()) {

                    String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                } else if (lista_janelas.isSelectionEmpty() == false) {

                    janelas_selecionadas = (ArrayList<String>) lista_janelas.getSelectedValuesList();
                    result2 = "";
                    query_janelas = "";
                    selects_janelas = "";

                    for (int i = 0; i < janelas_selecionadas.size(); i++) {

                        result2 += "<th>" + janelas_selecionadas.get(i) + "</th>";
                        query_janelas += ",IF(SUM(CASE WHEN PERIODO = '" + janelas_selecionadas.get(i) + "' THEN 1 ELSE 0 END)=0,NULL,SUM(CASE WHEN PERIODO = '" + janelas_selecionadas.get(i) + "' THEN 1 ELSE 0 END)) AS '" + janelas_selecionadas.get(i) + "' ";
                        selects_janelas += janelas_selecionadas.get(i) + ",";
                    }

                    selects_janelas = selects_janelas.substring(0, selects_janelas.length() - 1);

                    cria_tabela();

                } else if (lista_janelas.isSelectionEmpty() == true) {

                    String msg = "<html><body><p width='250px'>Por favor selecione ao menos uma janela para visualização!!!";
                    global_class.msg_dialog(msg, JOptionPane.WARNING_MESSAGE);

                }

            }
        }
        );

    }

    class lista_action implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent lse) {

            exp_excel.setEnabled(false);
            limpa();

        }

    }

    public void verifica_att() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else {

            try {
                conn = Db_class.mysql_conn();
                ResultSet att = null;
                att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_PEND='SIM','true','false') AS 'CHECK' FROM workstation_bcc.tbl_controle_att");

                att.next();
                String a = (String) att.getObject(1);

                if (a.equals("true")) {

                    atualizacao = true;

                } else {

                    atualizacao = false;
                }

                if (!atualizacao) {
                    atualiza_janelas();
                } else {
                    global_class.msg_dialog
                                (
                                "O cálculo de pendências está em execução.\n\n" +
                                "Por favor, tente novamente em 5 minutos e, caso esta mensagem\n" +
                                "apareça novamente, abra um chamado no Lince.",
                                JOptionPane.WARNING_MESSAGE
                                );
                }

                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }

    }

    private void exp_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp_excelActionPerformed

        String arq = "Extracao_Pendencias_NETSMS";

        try {

            tableEXPORT = global_class.getTable("CALL workstation_bcc.app_export_pendencia('" + selects_janelas + "','" + ativ + "')");

        } catch (Exception ex) {
        }

        try {

            String path = System.getProperty("user.home") + "\\Desktop\\";
            String usuario = System.getProperty("user.name");

            global_class.writeCSVfile(tableEXPORT, arq, path, usuario);

            String msg = "O arquivo foi extraído para o local " + path + " com o nome \"" + arq + ".csv\" com sucesso!!!";
            Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
            global_class.msg_dialog_icone(msg, JOptionPane.PLAIN_MESSAGE, (ImageIcon) figura);

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_exp_excelActionPerformed

    private void btt_aplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_aplicarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btt_aplicarActionPerformed

    public void cria_tabela() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else {

            if (verifica_criacao) {

                panel_exibir.remove(scrollPane);
                panel_exibir.repaint();
                verifica_criacao = false;
            }

            tabela_html = result2;

            String consult_interna = "(SELECT REGIONAL,CIDADE,AREA_DESPACHO AS AREA" + query_janelas + " FROM workstation_bcc.tbl_pendencia WHERE ATIVIDADE = '" + ativ + "' AND find_in_set(PERIODO,'" + selects_janelas + "') GROUP BY REGIONAL,CIDADE,AREA_DESPACHO)";
            String consult_total = "(SELECT REGIONAL,'TOTAL','-' AS AREA" + query_janelas + "FROM workstation_bcc.tbl_pendencia WHERE ATIVIDADE = '" + ativ + "' AND find_in_set(PERIODO,'" + selects_janelas + "') GROUP BY REGIONAL)";
            String consult_total1 = "(SELECT 'TOTAL REGIONAIS','TOTAL','-' AS AREA" + query_janelas + "FROM workstation_bcc.tbl_pendencia WHERE ATIVIDADE = '" + ativ + "' AND find_in_set(PERIODO,'" + selects_janelas + "'))";

            String consulta_final = (consult_interna + " UNION ALL " + consult_total + " UNION ALL " + consult_total1);

            try {

                tablePend = global_class.getTable(consulta_final);
                tablePend.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                TableCellRenderer tcustom = new CustomRenderer();

                for (int column = 0; column < tablePend.getColumnCount(); column++) {
                    TableColumn tableColumn = tablePend.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();
                    int maxWidth = tableColumn.getMaxWidth();

                    for (int row = 0; row < tablePend.getRowCount(); row++) {
                        TableCellRenderer cellRenderer = tablePend.getCellRenderer(row, column);
                        Component c = tablePend.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + tablePend.getIntercellSpacing().width;
                        preferredWidth = Math.max(preferredWidth, width);

                        if (preferredWidth >= maxWidth) {
                            preferredWidth = maxWidth + 10;
                            break;
                        }
                    }

                    if (column >= 3) {

                        tableColumn.setCellRenderer(tcustom);

                    }

                    if (preferredWidth <= 40) {

                        tableColumn.setPreferredWidth(100);
                        tableColumn.setMinWidth(100);
                        tableColumn.setMaxWidth(100);

                    } else {

                        tableColumn.setPreferredWidth(preferredWidth);
                        tableColumn.setMinWidth(preferredWidth + 10);
                        tableColumn.setMaxWidth(preferredWidth + 10);
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tablePend, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tablePend.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tablePend.getTableHeader().setReorderingAllowed(false);
                tablePend.setLocation(0, 0);
                tablePend.setSize(screenRect.width - 20, screenRect.height - 80);

                tablePend.setVisible(true);
                scrollPane = new JScrollPane(tablePend);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(panel_exibir.getWidth() - 20, panel_exibir.getHeight() - 20);
                scrollPane.setVisible(true);
                panel_exibir.add(scrollPane);
                panel_exibir.repaint();
                exp_excel.setEnabled(true);

            } catch (Exception ex) {
                Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void limpa() {

        janelas_selecionadas = null;
        result2 = "";
        query_janelas = "";
        selects_janelas = "";
        exp_excel.setEnabled(false);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btt_aplicar;
    private javax.swing.JButton exp_excel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lista_janelas;
    private javax.swing.JPanel panel_exibir;
    private javax.swing.JPanel panel_janelas;
    // End of variables declaration//GEN-END:variables
}
