package screens;

import classes.Db_class;
import classes.cEmail;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

class CustomRenderer_hfc extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 6703872492730589499L;

    @Override
    public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setHorizontalAlignment(CENTER);

        JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

        if (tab.getValueAt(row, 11).equals("NAO") && isSelected) {

            cellComponent.setBackground(global_class.get_color(139, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (tab.getValueAt(row, 11).equals("NAO") && !isSelected) {

            cellComponent.setBackground(global_class.get_color(255, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

        } else if (!(tab.getValueAt(row, 11).equals("NAO")) && isSelected) {

            cellComponent.setBackground(global_class.get_color(0, 128, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (!(tab.getValueAt(row, 11).equals("NAO")) && !isSelected) {

            cellComponent.setBackground(global_class.get_color(60, 179, 113));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        }

        return cellComponent;

    }
}

class ImageRenderer_hfc extends DefaultTableCellRenderer {

    public ImageRenderer_hfc() {
        super();

    }

    JLabel lbl = new JLabel();

    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/images/accept.png"));
    ImageIcon icon2 = new javax.swing.ImageIcon(getClass().getResource("/images/blank.png"));

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        String info = (String) table.getValueAt(row, 11);

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

public class jPanel_Pendencia_HFC extends jPanel_Risco_Pendencia {

    public jPanel_Pendencia_HFC(scr_Menu Menu00, String tipo_ativ, boolean hfc) {
        super(Menu00, tipo_ativ, true);

    }

    @Override
    public void combo_cidade() {

        global_class.preencher_combobox(combo_cidades_risco, "-- Selecione uma cidade --", "CALL workstation_bcc.app_cidades_risco_hfc('" + ativ + "')");

    }

    @Override
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
                    ff = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO_HFC='SIM',1,0) AS STS_RISCO FROM workstation_bcc.tbl_controle_att");
                    ff.next();
                    att_risco = ff.getBoolean(1);
                    Db_class.close_conn(conn);

                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(jPanel_Pendencia_HFC.class.getName()).log(Level.SEVERE, null, ex);
                    Db_class.close_conn(conn);

                }

                if (!att_risco) {

                    try {

                        tableRISCO = null;
                        tableRISCO = global_class.getTable("CALL workstation_bcc.app_casos_risco_hfc('" + cidade_selecionada + "','" + ativ + "')");
                        tableRISCO.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                        CustomRenderer_hfc tcustom = new CustomRenderer_hfc();
                        ImageRenderer_hfc icustom = new ImageRenderer_hfc();

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
                                case 11:
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
                        Logger.getLogger(jPanel_Pendencia_HFC.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    String msg = "<html><body><p width='250px'>O Risco TEC1 esta em processo de atualização! \nTente novamente após HH20 ou HH40";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);

                }

            }
        }
    }

    @Override
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

    @Override
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
                rs = global_class.mysql_result(conn, "CALL workstation_bcc.app_cidades_risco_hfc('" + ativ + "')");

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
                rs = global_class.mysql_result(conn, "CALL workstation_bcc.app_cidades_risco_hfc('" + ativ + "')");

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

    @Override
    public void gerar_email() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            if (combo_cidades_risco.getSelectedIndex() != 0) {

                try {
                    conn = Db_class.mysql_conn();
                    ResultSet att = null;
                    att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO_HFC='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

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
                    global_class.preenche_array(nomes_uns_field, "SELECT NM_UN FROM workstation_bcc.tbl_riscotec1_hfc WHERE CIDADE = '" + cidade_selecionada + "' AND ATIVIDADE = '" + ativ + "' GROUP BY NM_UN", 1);

                    for (String un : nomes_uns_field) {

                        email_para = "";
                        email_cc = "";
                        table_casos = null;

                        try {

                            String query = "";

                            if (un.equals("PRÓPRIO") || un.equals("") || un.equals("NAO_TRATADO")) {
                                query = "SELECT ID_CIDADE FROM workstation_bcc.tbl_un_copinfo WHERE NM_CIDADE = '" + cidade_selecionada + "' GROUP BY ID_CIDADE";
                            } else {
                                query = "SELECT ID_CIDADE, IF(ID_EMPRESA IS NULL, 100000,ID_EMPRESA) AS 'ID_EMPRESA', IFNULL(NM_EMPRESA,NM_UN) AS 'NM_EMPRESA' FROM workstation_bcc.tbl_un_copinfo WHERE NM_CIDADE = '" + cidade_selecionada + "' AND NM_UN = '" + un + "' GROUP BY ID_CIDADE, ID_EMPRESA, NM_EMPRESA";
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
                                cEmail email = new cEmail(3);
                                assunto = email.getsAssunto();
                                html_code = email.getsCorpo();
                                email = null;
                                
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
                                        + "<th>CREDENCIADA</th>"
                                        + "<th>STATUS ACTIVIA</th>"
                                        + "</tr>";
                                //

                                if (id_empresa == 2) {
                                    /*
                                    email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas(" + id_cid + "," + id_empresa + ",'GERAL,IAT')", 1);
                                    email_para = email_para.replace("null", "");
                                    */
                                    email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Garantia')", 1);
                                    email_cc = "";

                                    /*
                                    assunto = "*** RISCO TEC1 *** " + cidade_selecionada;

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
                                            + "<th>CREDENCIADA</th>"
                                            + "<th>STATUS ACTIVIA</th>"
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
                                    if (ativ.equals("GARANTIA")) {
                                        email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Garantia')", 1);
                                        email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'Garantia')", 1);
                                    } else {
                                        email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Pool')", 1);
                                        email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'Pool')", 1);
                                    }

                                    /*
                                    assunto = "*** RISCO TEC1 *** " + cidade_selecionada + " " + nm_empresa;

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
                                            + "<th>CREDENCIADA</th>"
                                            + "<th>STATUS ACTIVIA</th>"
                                            + "</tr>";
                                    */

                                }

                                email_para = email_para.replace(",,,,", ",").replace(",,,", ",").replace(",", ",").replace("null", "");

                                try {
                                    table_casos = global_class.getTable("CALL workstation_bcc.app_casos_risco_hfc_email('" + cidade_selecionada + "','" + ativ + "','" + un + "')");
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

    @Override
    public void update_gerar_email(String tipo_email) {

        String query_trat_ff = "UPDATE workstation_bcc.tbl_riscotec1_hfc "
                + " SET EMAIL_GERADO = 'SIM' WHERE ATIVIDADE = '" + ativ + "' AND CIDADE = '" + cidade_selecionada + "' AND NM_UN = '" + tipo_email + "'";

        try {
            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_ff);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

}
