package screens;

import classes.Db_class;
import classes.cEmail;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class jPanel_Risco_Pendencia extends javax.swing.JPanel {

    scr_Menu Menu01;
    JScrollPane scrollPane;
    boolean verifica_criacao = false;
    boolean permitir_email = false;

    JTable tableRISCO;
    JTable table_casos;

    Connection conn;

    String cidade_selecionada = "-- Selecione uma cidade --";
    int[] rows_select;
    JMenuItem jMenuUN;
    JMenuItem jMenuEmail;
    JPopupMenu jPopupMenu;

    ArrayList<Integer> ids_select = new ArrayList<>();
    ArrayList<String> nomes_uns_field = new ArrayList<>();
    ArrayList<String> uns_email;

    String email_para;
    String email_cc;
    String titulo;

    int id_cid;
    int id_empresa;
    int id_caso;

    String nm_empresa;
    String assunto = "";
    String un_selecionada;

    String html_code;

    String login_tec;
    boolean login_tec_valid;
    boolean att_risco = false;

    String ativ = "";

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            if (tab.getValueAt(row, 13).equals("NAO") && isSelected) {

                cellComponent.setBackground(global_class.get_color(139, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (tab.getValueAt(row, 13).equals("NAO") && !isSelected) {

                cellComponent.setBackground(global_class.get_color(255, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (!(tab.getValueAt(row, 13).equals("NAO")) && isSelected) {

                cellComponent.setBackground(global_class.get_color(0, 128, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (!(tab.getValueAt(row, 13).equals("NAO")) && !isSelected) {

                cellComponent.setBackground(global_class.get_color(60, 179, 113));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            }

            return cellComponent;

        }
    }

    class ImageRenderer extends DefaultTableCellRenderer {

        public ImageRenderer() {
            super();

        }

        JLabel lbl = new JLabel();

        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/images/accept.png"));
        ImageIcon icon2 = new javax.swing.ImageIcon(getClass().getResource("/images/blank.png"));

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            String info = (String) table.getValueAt(row, 13);

            if (info.equals("NAO") && isSelected) {

                lbl.setBackground(global_class.get_color(139, 0, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon2);

            } else if (info.equals("NAO") && !isSelected) {

                lbl.setBackground(global_class.get_color(255, 0, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon2);

            } else if (!info.equals("NAO") && isSelected) {

                lbl.setBackground(global_class.get_color(0, 128, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon);

            } else if (!info.equals("NAO") && !isSelected) {

                lbl.setBackground(global_class.get_color(60, 179, 113));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon);

            }

            lbl.setOpaque(true);
            lbl.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

            return lbl;
        }
    }

    public jPanel_Risco_Pendencia(scr_Menu Menu00, String tipo_ativ) {
        initComponents();

        Menu01 = Menu00;
        ativ = tipo_ativ;
        combo_cidade();

    }

    public jPanel_Risco_Pendencia(scr_Menu Menu00, String tipo_ativ, boolean hfc) {
        initComponents();

        Menu01 = Menu00;
        ativ = tipo_ativ;
        combo_cidade();

    }

    public jPanel_Risco_Pendencia(scr_Menu Menu00, boolean dth) {
        initComponents();

        jLabel1.setVisible(false);
        combo_cidades_risco.setVisible(false);
        jSeparator1.setVisible(false);
        //jToolBar1.setVisible(false);

        cria_tabela();

    }

    public void combo_cidade() {

        global_class.preencher_combobox(combo_cidades_risco, "-- Selecione uma cidade --", "CALL workstation_bcc.app_cidades_risco_002('" + ativ + "')");

    }

    public void atualiza_casos() {

        if (verifica_criacao) {

            jPanel1.remove(scrollPane);
            jPanel1.repaint();
            verifica_criacao = false;
        }

        if (combo_cidades_risco.getSelectedIndex() == 0) {

            cidade_selecionada = "-- Selecione uma cidade --";

        } else {

            cidade_selecionada = combo_cidades_risco.getSelectedItem().toString();

        }
        cria_tabela();

    }

    public void cria_tabela() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else {

            if (verifica_criacao) {

                jPanel1.remove(scrollPane);
                jPanel1.repaint();
                verifica_criacao = false;
            }

            if (combo_cidades_risco.getSelectedIndex() != 0) {

                try {

                    conn = Db_class.mysql_conn();
                    ResultSet ff = null;
                    ff = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO='SIM',1,0) AS STS_RISCO FROM workstation_bcc.tbl_controle_att");
                    ff.next();
                    att_risco = ff.getBoolean(1);
                    Db_class.close_conn(conn);

                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                    Db_class.close_conn(conn);

                }

                if (!att_risco) {

                    try {

                        tableRISCO = null;
                        tableRISCO = global_class.getTable("CALL workstation_bcc.app_casos_risco2('" + cidade_selecionada + "','" + ativ + "')");
                        tableRISCO.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                        CustomRenderer tcustom = new CustomRenderer();
                        ImageRenderer icustom = new ImageRenderer();

                        for (int column = 0; column < tableRISCO.getColumnCount(); column++) {
                            TableColumn tableColumn = tableRISCO.getColumnModel().getColumn(column);
                            int preferredWidth = tableColumn.getMinWidth();
                            int maxWidth = tableColumn.getMaxWidth();

                            for (int row = 0; row < tableRISCO.getRowCount(); row++) {
                                TableCellRenderer cellRenderer = tableRISCO.getCellRenderer(row, column);
                                Component c = tableRISCO.prepareRenderer(cellRenderer, row, column);
                                int width = c.getPreferredSize().width + tableRISCO.getIntercellSpacing().width;
                                preferredWidth = Math.max(preferredWidth, width);

                                if (preferredWidth >= maxWidth) {
                                    preferredWidth = maxWidth + 10;
                                    break;
                                }
                            }

                            tableColumn.setCellRenderer(tcustom);

                            switch (column) {
                                case 0:
                                    tableColumn.setPreferredWidth(20);
                                    tableColumn.setMinWidth(20);
                                    tableColumn.setMaxWidth(20);
                                    tableColumn.setCellRenderer(icustom);
                                    break;
                                case 13:
                                    tableColumn.setPreferredWidth(0);
                                    tableColumn.setMinWidth(0);
                                    tableColumn.setMaxWidth(0);
                                    tableColumn.setCellRenderer(icustom);
                                    break;
                                default:
                                    tableColumn.setPreferredWidth(preferredWidth);
                                    tableColumn.setMinWidth(preferredWidth + 40);
                                    tableColumn.setMaxWidth(preferredWidth + 40);
                                    break;
                            }

                        }

                        TableFilterHeader filterHeader = new TableFilterHeader(tableRISCO, AutoChoices.ENABLED);
                        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        Rectangle screenRect = ge.getMaximumWindowBounds();

                        tableRISCO.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        tableRISCO.setRowSelectionAllowed(false);
                        tableRISCO.setCellSelectionEnabled(true);
                        tableRISCO.getTableHeader().setReorderingAllowed(false);
                        tableRISCO.setLocation(0, 0);
                        tableRISCO.setSize(screenRect.width - 20, screenRect.height - 115);

                        tableRISCO.setVisible(true);
                        scrollPane = new JScrollPane(tableRISCO);
                        verifica_criacao = true;
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        scrollPane.setLocation(5, 5);
                        scrollPane.setSize(screenRect.width - 20, screenRect.height - 150);
                        scrollPane.setVisible(true);
                        jPanel1.add(scrollPane);

                    } catch (Exception ex) {
                        Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    String msg = "<html><body><p width='250px'>O Risco TEC1 esta em processo de atualização! \nTente novamente após HH20 ou HH40";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);

                }

            }
        }
    }

    public void att_combo_cidade_select() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (!cidade_selecionada.equals("-- Selecione uma cidade --")) {

            conn = null;
            combo_cidades_risco.removeAllItems();
            combo_cidades_risco.addItem("-- Selecione uma cidade --");

            try {
                conn = Db_class.mysql_conn();
                ResultSet rs = null;
                rs = global_class.mysql_result(conn, "CALL workstation_bcc.app_cidades_risco('" + ativ + "')");

                while (rs.next()) {

                    combo_cidades_risco.addItem(rs.getObject(1));
                    String cid = (String) rs.getObject(1);

                    if (cid.equals(cidade_selecionada)) {

                        combo_cidades_risco.setSelectedItem(cidade_selecionada);

                    } else {

                        jPanel1.remove(scrollPane);
                        jPanel1.repaint();

                    }

                }
                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        } else if (cidade_selecionada.equals("-- Selecione uma cidade --")) {

            conn = null;
            combo_cidades_risco.removeAllItems();
            combo_cidades_risco.addItem("-- Selecione uma cidade --");

            try {
                conn = Db_class.mysql_conn();
                ResultSet rs = null;
                rs = global_class.mysql_result(conn, "CALL workstation_bcc.app_cidades_risco('" + ativ + "')");

                while (rs.next()) {

                    combo_cidades_risco.addItem(rs.getObject(1));

                }
                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        combo_cidades_risco = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btt_gerar_email = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel1.setText("   Cidade:  ");
        jToolBar1.add(jLabel1);

        combo_cidades_risco.setMaximumSize(new java.awt.Dimension(400, 26));
        combo_cidades_risco.setMinimumSize(new java.awt.Dimension(400, 26));
        combo_cidades_risco.setPreferredSize(new java.awt.Dimension(400, 26));
        combo_cidades_risco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_cidades_riscoItemStateChanged(evt);
            }
        });
        jToolBar1.add(combo_cidades_risco);
        jToolBar1.add(jSeparator1);

        btt_gerar_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email_go.png"))); // NOI18N
        btt_gerar_email.setText("Gerar E-mail");
        btt_gerar_email.setFocusable(false);
        btt_gerar_email.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btt_gerar_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt_gerar_emailActionPerformed(evt);
            }
        });
        jToolBar1.add(btt_gerar_email);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_cidades_riscoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_cidades_riscoItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED && combo_cidades_risco.getSelectedIndex() != 0) {

            atualiza_casos();
            btt_gerar_email.setEnabled(true);

        } else if (evt.getStateChange() == ItemEvent.SELECTED && combo_cidades_risco.getSelectedIndex() == 0) {

            if (verifica_criacao) {

                jPanel1.remove(scrollPane);
                jPanel1.repaint();
                verifica_criacao = false;
            }

            btt_gerar_email.setEnabled(false);

        }
    }//GEN-LAST:event_combo_cidades_riscoItemStateChanged

    private void btt_gerar_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt_gerar_emailActionPerformed

        gerar_email();

    }//GEN-LAST:event_btt_gerar_emailActionPerformed

    public void gerar_email() {

        String sSQL;

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            if (combo_cidades_risco.getSelectedIndex() != 0) {

                try {
                    conn = Db_class.mysql_conn();
                    ResultSet att = null;
                    att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

                    att.next();
                    att_risco = att.getBoolean(1);

                    Db_class.close_conn(conn);

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
                    Db_class.close_conn(conn);
                }

                if (!att_risco) {

                    cidade_selecionada = combo_cidades_risco.getSelectedItem().toString();
                    nomes_uns_field = new ArrayList<String>();

                    sSQL = "";
                    sSQL = sSQL + "SELECT TIPO_EMAIL FROM workstation_bcc.tbl_riscotec1 \n";
                    sSQL = sSQL + "WHERE CIDADE_FINAL = '" + cidade_selecionada + "' \n";
                    //sSQL = sSQL + "AND ATIVIDADE = '" + ativ + "' \n";
                    sSQL = sSQL + "and \n";
                    sSQL = sSQL + "( \n";
                    sSQL = sSQL + "(ATIVIDADE = '" + ativ + "' and cidade_final not in (select nm_cidade from workstation_bcc.tbl_gpon_cidades order by nm_cidade)) \n";
                    sSQL = sSQL + "or \n";
                    sSQL = sSQL + "('" + ativ + "' = 'GPON' and cidade_final in (select nm_cidade from workstation_bcc.tbl_gpon_cidades order by nm_cidade)) \n";
                    sSQL = sSQL + ") \n";
                    sSQL = sSQL + "AND TIPO_EMAIL IS NOT NULL GROUP BY TIPO_EMAIL";

                    global_class.preenche_array(nomes_uns_field, sSQL, 1);

                    for (String un : nomes_uns_field) {

                        email_para = "";
                        email_cc = "";
                        table_casos = null;

                        try {

                            String query = "";

                            if (un.equals("PRÓPRIO") || un.equals("") || un.equals("NAO_TRATADO")) {
                                query = "SELECT ID_CIDADE FROM workstation_bcc.tbl_un_copinfo WHERE NM_CIDADE = '" + cidade_selecionada + "' GROUP BY ID_CIDADE";
                            } else {

                                query = "SELECT ID_CIDADE, IF(ID_EMPRESA IS NULL, 100000,ID_EMPRESA) AS 'ID_EMPRESA', IFNULL(NM_EMPRESA,NM_UN) AS 'NM_EMPRESA' FROM workstation_bcc.tbl_un_copinfo WHERE NM_CIDADE = '" + cidade_selecionada + "' AND NM_UN LIKE '" + un + "%' AND IF(ID_EMPRESA IS NULL, 100000,ID_EMPRESA) <> 100000 GROUP BY ID_CIDADE, ID_EMPRESA, NM_EMPRESA";
                            }

                            conn = Db_class.mysql_conn();
                            ResultSet rs = null;
                            rs = global_class.mysql_result(conn, query);

                            while (rs.next()) {

                                id_cid = (Integer) rs.getObject(1);

                                if (un.equals("PRÓPRIO") || un.equals("") || un.equals("NAO_TRATADO")) {

                                    id_empresa = 2;
                                    nm_empresa = "";

                                } else {

                                    id_empresa = (int) (long) rs.getObject(2);
                                    nm_empresa = (String) rs.getObject(3);

                                }
                                email_para = "";
                                email_cc = "";

                                //identificando Assunto e Corpo de e-mail
                                if (ativ.equals("GARANTIA")) {
                                    cEmail email = new cEmail(1);
                                    assunto = email.getsAssunto();
                                    html_code = email.getsCorpo();
                                    email = null;
                                } else {
                                    cEmail email = new cEmail(3);
                                    assunto = email.getsAssunto();
                                    html_code = email.getsCorpo();
                                    email = null;
                                }

                                assunto = assunto.replaceAll("<#cidade#>", cidade_selecionada).replaceAll("<#empresa#>", nm_empresa).trim();
                                html_code = html_code.replaceAll("<#saudacao#>", global_class.SaudacaoMomento());
                                //

                                //como as tabelas tanto para NET quanto para TERCEIROS são idênticas, coloco a montagem do cabeçalho
                                //uma única vez
                                html_code += "<table border=\"1\" cellpadding=\"1\" style=\"table-layout: fixed; font-size:12px\">"
                                        + "<tr style=\"background-color: #000080; color: #ffffff;\">"
                                        + "<th>REGIONAL</th>"
                                        + "<th>CIDADE</th>"
                                        + "<th>DATA AGENDA</th>"
                                        + "<th>PERIODO</th>"
                                        + "<th>TIPO OS</th>"
                                        + "<th>CONTRATO</th>"
                                        + "<th>CODIGO OS</th>"
                                        + "<th>AREA</th>"
                                        + "<th>UNIDADE NEGOCIO</th>"
                                        + "<th>LOGIN TECNICO</th>"
                                        + "<th>STATUS WO</th>"
                                        + "<th>NUMERO WO</th>"
                                        + "<th>NOME DO CLIENTE</th>"
                                        + "</tr>";
                                //

                                if (id_empresa == 2) {
                                    /*
                                     email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas(" + id_cid + "," + id_empresa + ",'GERAL,IAT')", 1);
                                     email_para = email_para.replace("null", "");
                                     */

                                    if (ativ.equals("GPON")) {
                                        email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Garantia,Pool,PoolDesco')", 1);
                                    } else {
                                        email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Garantia')", 1);
                                    }

                                    email_cc = "";

                                    /*
                                     assunto = "*** RISCO TEC1 (" + ativ + ") *** " + cidade_selecionada;

                                     html_code = "<p><em>Caros,</em></p>\n"
                                     + "<p><em>Segue(m) para acompanhamento contratos pendentes com RISCO TEC1 e possível atraso no atendimento.</em></p>\n"
                                     + "<p><em>Ressaltamos que a não tratativa afeta os indicadores abaixo:</em></p>"
                                     + "<ul>\n"
                                     + "<li><em>Contact Rate</em></li>"
                                     + "<li><em>Regulatórios</em></li>"
                                     + "<li><em>TEC1</em></li>"
                                     + "</ul>\n";

                                     html_code += "<table border=\"1\" cellpadding=\"1\" style=\"table-layout: fixed; font-size:12px\">"
                                     + "<tr style=\"background-color: #000080; color: #ffffff;\">"
                                     + "<th>REGIONAL</th>"
                                     + "<th>CIDADE</th>"
                                     + "<th>DATA AGENDA</th>"
                                     + "<th>PERIODO</th>"
                                     + "<th>TIPO OS</th>"
                                     + "<th>CONTRATO</th>"
                                     + "<th>CODIGO OS</th>"
                                     + "<th>AREA</th>"
                                     + "<th>UNIDADE NEGOCIO</th>"
                                     + "<th>LOGIN TECNICO</th>"
                                     + "<th>STATUS WO</th>"
                                     + "<th>NUMERO WO</th>"
                                     + "<th>NOME DO CLIENTE</th>"
                                     + "</tr>";
                                     */
                                } else if (id_empresa >= 4) {
                                    /*
                                     email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas(" + id_cid + "," + id_empresa + ",'GERAL,IAT,EPO')", 1);
                                     email_para = email_para.replace("null", "");
                                     //email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas(" + id_cid + ",2,'GERAL,IAT')", 1);
                                     if (ativ.equals("GARANTIA")) {
                                     email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas(" + id_cid + ",2,'GERAL,IAT')", 1);
                                     } else {
                                     email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas(" + id_cid + ",2,'GERAL,EPO')", 1);
                                     }
                                     */

                                    if (ativ.equals("GPON")) {
                                        email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Garantia,Pool,PoolDesco')", 1);
                                        email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'Garantia,Pool,PoolDesco')", 1);
                                    } else {
                                        if (ativ.equals("GARANTIA")) {
                                            email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Garantia')", 1);
                                            email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'Garantia')", 1);
                                        } else {
                                            email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Pool')", 1);
                                            email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'Pool')", 1);
                                        }
                                    }

                                    /*
                                     assunto = "*** RISCO TEC1 (" + ativ + ") *** " + cidade_selecionada + " " + nm_empresa;

                                     html_code = "<p><em>Caros,</em></p>\n"
                                     + "<p><em>Segue(m) contratos pendentes com RISCO TEC1 e possível atraso no atendimento, por favor nos responder com a devida tratativa e prioridade.</em></p>\n"
                                     + "<p><em>Por favor realizar ativo com todos os clientes para informá-los da previsão de atendimento.</em></p>"
                                     + "<p><em>Ressaltamos que a não tratativa afeta os indicadores abaixo:</em></p>"
                                     + "<ul>\n"
                                     + "<li><em>Contact Rate</em></li>"
                                     + "<li><em>Regulatórios</em></li>"
                                     + "<li><em>TEC1</em></li>"
                                     + "</ul>\n";

                                     html_code += "<table border=\"1\" cellpadding=\"1\" style=\"table-layout: fixed; font-size:12px\">"
                                     + "<tr style=\"background-color: #000080; color: #ffffff;\">"
                                     + "<th>REGIONAL</th>"
                                     + "<th>CIDADE</th>"
                                     + "<th>DATA AGENDA</th>"
                                     + "<th>PERIODO</th>"
                                     + "<th>TIPO OS</th>"
                                     + "<th>CONTRATO</th>"
                                     + "<th>CODIGO OS</th>"
                                     + "<th>AREA</th>"
                                     + "<th>UNIDADE NEGOCIO</th>"
                                     + "<th>LOGIN TECNICO</th>"
                                     + "<th>STATUS WO</th>"
                                     + "<th>NUMERO WO</th>"
                                     + "<th>NOME DO CLIENTE</th>"
                                     + "</tr>";
                                     */
                                }

                                email_para = email_para.replace(",,,,", ",").replace(",,,", ",").replace(",", ",").replace("null", "");

                                try {

                                    table_casos = global_class.getTable("CALL workstation_bcc.app_casos_risco_email3('" + cidade_selecionada + "','" + ativ + "','" + un + "')");

                                } catch (Exception ex) {
                                    Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                                    Db_class.close_conn(conn);
                                }

                                int num_linhas = table_casos.getRowCount();
                                num_linhas = num_linhas - 1;
                                int qtde_colunas = table_casos.getColumnCount();
                                int colunas = qtde_colunas - 1;

                                int y = 0;
                                String linha_completa = "";
                                String todas_linhas = "";

                                while (y <= num_linhas) {

                                    int x = 2;
                                    linha_completa = "<tr><td>" + table_casos.getValueAt(y, 0) + "</td>" + "<td>" + table_casos.getValueAt(y, 1) + "</td>";

                                    while (x <= colunas) {

                                        if (!(table_casos.getValueAt(y, x).equals(""))) {

                                            linha_completa += "<td>" + table_casos.getValueAt(y, x) + "</td>";

                                        } else {

                                            linha_completa += "<td>" + table_casos.getValueAt(y, x) + "</td>";

                                        }

                                        x = x + 1;

                                    }

                                    todas_linhas += linha_completa + "</tr>";
                                    y = y + 1;

                                }

                                html_code += todas_linhas;
                                global_class.send_to(html_code, assunto, email_para, email_cc, "exibir");
                            }

                            Db_class.close_conn(conn);

                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                            Db_class.close_conn(conn);
                        } catch (IOException ex) {
                            Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                            Db_class.close_conn(conn);
                        }

                        //global_class.msg_dialog("Gravação do indicador de e-mail gerado desativado", JOptionPane.WARNING_MESSAGE);
                        update_gerar_email(un);

                    }

                    cria_tabela();

                } else {

                    String msg = "<html><body><p width='130px'>Selecione uma cidade antes de prosseguir.";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                }

            }
        }

    }

    public void update_gerar_email(String tipo_email) {

        //String query_trat_ff = "UPDATE workstation_bcc.tbl_riscotec1 " + " SET EMAIL_GERADO = 'SIM' WHERE ATIVIDADE = '" + ativ + "' AND CIDADE_FINAL = '" + cidade_selecionada + "' AND TIPO_EMAIL = '" + tipo_email + "'";
        String query_trat_ff;
        query_trat_ff = "UPDATE workstation_bcc.tbl_riscotec1 \n";
        query_trat_ff = query_trat_ff + "SET \n";
        query_trat_ff = query_trat_ff + "EMAIL_GERADO = 'SIM' \n";
        query_trat_ff = query_trat_ff + "WHERE (ATIVIDADE = '" + ativ + "' or (ATIVIDADE = 'POOL' and '" + ativ + "' = 'GPON')) \n";
        query_trat_ff = query_trat_ff + "AND CIDADE_FINAL = '" + cidade_selecionada + "' \n";
        query_trat_ff = query_trat_ff + "AND TIPO_EMAIL = '" + tipo_email + "'";
        
        try {
            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_ff);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btt_gerar_email;
    public javax.swing.JComboBox combo_cidades_risco;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JToolBar.Separator jSeparator1;
    public javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
