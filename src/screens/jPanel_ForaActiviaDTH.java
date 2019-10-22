package screens;

import classes.Db_class;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
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

class CustomRenderer_dth extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 6703872492730589499L;

    @Override
    public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setHorizontalAlignment(CENTER);

        JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

        if (tab.getValueAt(row, 12).equals("") && isSelected) {

            cellComponent.setBackground(global_class.get_color(139, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (tab.getValueAt(row, 12).equals("") && !isSelected) {

            cellComponent.setBackground(global_class.get_color(255, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

        } else if (!(tab.getValueAt(row, 12).equals("")) && isSelected) {

            cellComponent.setBackground(global_class.get_color(0, 128, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (!(tab.getValueAt(row, 12).equals("")) && !isSelected) {

            cellComponent.setBackground(global_class.get_color(60, 179, 113));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        }

        return cellComponent;

    }
}

class CustomRenderer_dth_super extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 6703872492730589499L;

    @Override
    public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setHorizontalAlignment(CENTER);

        JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

        String info = tab.getValueAt(row, 12).toString();

        if (info.equals("PENDENTE") && isSelected) {

            cellComponent.setBackground(global_class.get_color(139, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (info.equals("PENDENTE") && !isSelected) {

            cellComponent.setBackground(global_class.get_color(255, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

        } else if (!(info.equals("PENDENTE")) && isSelected) {

            cellComponent.setBackground(global_class.get_color(0, 128, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (!(info.equals("PENDENTE")) && !isSelected) {

            cellComponent.setBackground(global_class.get_color(60, 179, 113));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        }

        return cellComponent;

    }
}

class ImageRenderer_dth extends DefaultTableCellRenderer {

    public ImageRenderer_dth() {
        super();

    }

    JLabel lbl = new JLabel();

    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/images/accept.png"));
    ImageIcon icon2 = new javax.swing.ImageIcon(getClass().getResource("/images/blank.png"));

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        String info = (String) table.getValueAt(row, 12);

        if (info.equals("") && isSelected) {

            lbl.setBackground(global_class.get_color(139, 0, 0));
            lbl.setForeground(Color.WHITE);
            lbl.setIcon(icon2);

        } else if (info.equals("") && !isSelected) {

            lbl.setBackground(global_class.get_color(255, 0, 0));
            lbl.setForeground(Color.WHITE);
            lbl.setIcon(icon2);

        } else if (!info.equals("") && isSelected) {

            lbl.setBackground(global_class.get_color(0, 128, 0));
            lbl.setForeground(Color.WHITE);
            lbl.setIcon(icon);

        } else if (!info.equals("") && !isSelected) {

            lbl.setBackground(global_class.get_color(60, 179, 113));
            lbl.setForeground(Color.WHITE);
            lbl.setIcon(icon);

        }

        lbl.setOpaque(true);
        lbl.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

        return lbl;
    }
}

public class jPanel_ForaActiviaDTH extends jPanel_ForaField {

    JTable tableFA;
    ArrayList<String> credenciadas_array = new ArrayList<>();
    ArrayList<String> mrs_array = new ArrayList<>();

    public jPanel_ForaActiviaDTH(scr_Menu Menu00, boolean fa_dth, String perfil, String tipo_ativ) {
        super(Menu00, tipo_ativ, fa_dth, perfil);
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

            try {

                tableFA = null;
                tableFA = global_class.getTable("CALL workstation_bcc.app_casos_fa_dth('" + ativ + "')");
                tableFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRenderer_dth tcustom = new CustomRenderer_dth();
                ImageRenderer_dth icustom = new ImageRenderer_dth();

                for (int column = 0; column < tableFA.getColumnCount(); column++) {
                    TableColumn tableColumn = tableFA.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();
                    int maxWidth = tableColumn.getMaxWidth();

                    for (int row = 0; row < tableFA.getRowCount(); row++) {
                        TableCellRenderer cellRenderer = tableFA.getCellRenderer(row, column);
                        Component c = tableFA.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + tableFA.getIntercellSpacing().width;
                        preferredWidth = Math.max(preferredWidth, width);

                        if (preferredWidth >= maxWidth) {
                            preferredWidth = maxWidth + 10;
                            break;
                        }
                    }

                    tableColumn.setCellRenderer(tcustom);

                    if (column == 0) {

                        tableColumn.setPreferredWidth(0);
                        tableColumn.setMinWidth(0);
                        tableColumn.setMaxWidth(0);

                    } else if (column == 1) {

                        tableColumn.setPreferredWidth(20);
                        tableColumn.setMinWidth(20);
                        tableColumn.setMaxWidth(20);
                        tableColumn.setCellRenderer(icustom);

                    } else if (column == 2 && preferredWidth <= 50) {

                        tableColumn.setPreferredWidth(70);
                        tableColumn.setMinWidth(70);
                        tableColumn.setMaxWidth(70);

                    } else if (column == 10) {

                        tableColumn.setPreferredWidth(250);
                        tableColumn.setMinWidth(250);
                        tableColumn.setMaxWidth(250);

                    } else if (column == 11) {

                        tableColumn.setPreferredWidth(250);
                        tableColumn.setMinWidth(250);
                        tableColumn.setMaxWidth(250);

                    } else if (column == 12) {

                        tableColumn.setPreferredWidth(150);
                        tableColumn.setMinWidth(150);
                        tableColumn.setMaxWidth(150);

                    } else {

                        tableColumn.setPreferredWidth(preferredWidth);
                        tableColumn.setMinWidth(preferredWidth + 40);
                        tableColumn.setMaxWidth(preferredWidth + 40);
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tableFA, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tableFA.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                tableFA.addKeyListener(new att_assistente(tableFA));
                tableFA.setRowSelectionAllowed(false);
                tableFA.setCellSelectionEnabled(true);
                tableFA.getTableHeader().setReorderingAllowed(false);
                tableFA.setLocation(0, 0);
                tableFA.setSize(screenRect.width - 20, screenRect.height - 115);

                tableFA.setVisible(true);
                scrollPane = new JScrollPane(tableFA);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(screenRect.width - 20, screenRect.height - 150);
                scrollPane.setVisible(true);
                jPanel1.add(scrollPane);

            } catch (Exception ex) {
                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void cria_tabela_super() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else {

            if (verifica_criacao) {

                jPanel1.remove(scrollPane);
                jPanel1.repaint();
                verifica_criacao = false;
            }

            try {

                tableSuper = null;
                tableSuper = global_class.getTable("CALL workstation_bcc.app_casos_ff_super4('" + ativ + "')");
                tableSuper.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRenderer_dth_super tcustom = new CustomRenderer_dth_super();

                for (int column = 0; column < tableSuper.getColumnCount(); column++) {
                    TableColumn tableColumn = tableSuper.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();
                    int maxWidth = tableColumn.getMaxWidth();

                    for (int row = 0; row < tableSuper.getRowCount(); row++) {
                        TableCellRenderer cellRenderer = tableSuper.getCellRenderer(row, column);
                        Component c = tableSuper.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + tableSuper.getIntercellSpacing().width;
                        preferredWidth = Math.max(preferredWidth, width);

                        if (preferredWidth >= maxWidth) {
                            preferredWidth = maxWidth + 10;
                            break;
                        }
                    }

                    tableColumn.setCellRenderer(tcustom);

                    if (column == 0) {

                        tableColumn.setPreferredWidth(0);
                        tableColumn.setMinWidth(0);
                        tableColumn.setMaxWidth(0);

                    } else {

                        tableColumn.setPreferredWidth(preferredWidth);
                        tableColumn.setMinWidth(preferredWidth + 60);
                        tableColumn.setMaxWidth(preferredWidth + 60);
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tableSuper, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tableSuper.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                tableSuper.addMouseListener(new tab_click_super());
                tableSuper.addKeyListener(new att_super(tableSuper));
                tableSuper.setRowSelectionAllowed(true);
                tableSuper.getTableHeader().setReorderingAllowed(false);
                tableSuper.setLocation(0, 0);
                tableSuper.setSize(screenRect.width - 20, screenRect.height - 115);

                tableSuper.setVisible(true);
                scrollPane = new JScrollPane(tableSuper);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(screenRect.width - 20, screenRect.height - 130);
                scrollPane.setVisible(true);
                jPanel1.add(scrollPane);
                jPanel1.repaint();

            } catch (Exception ex) {
                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void gerar_email() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            credenciadas_array = new ArrayList<>();
            mrs_array = new ArrayList<>();
            global_class.preenche_array(credenciadas_array, "CALL workstation_bcc.app_foraactivia_dth()", 1);
            global_class.preenche_array(mrs_array, "CALL workstation_bcc.app_foraactivia_dth()", 2);

            for (int i = 0; i < credenciadas_array.size(); i++) {

                // if (i == 125) {

                    email_para = "";
                    email_cc = "";
                    table_casos = null;

                    try {

                        email_para = "";
                        email_cc = "";

                        email_para = global_class.get_emails("SELECT  "
                                + "    GROUP_CONCAT(DISTINCT email1 "
                                + "        SEPARATOR ';') AS 'CREDENCIADA', "
                                + "    GROUP_CONCAT(DISTINCT email "
                                + "        SEPARATOR ';') AS 'OPERACAO' "
                                + "FROM "
                                + "    workstation_bcc.tbl_cop_infodth "
                                + "WHERE "
                                + "    FIND_IN_SET(mr, '" + mrs_array.get(i) + "') "
                                + "        AND FIND_IN_SET(codigo, "
                                + "            '" + credenciadas_array.get(i) + "')", 1);

                        email_cc = global_class.get_emails("SELECT  "
                                + "    GROUP_CONCAT(DISTINCT email1 "
                                + "        SEPARATOR ';') AS 'CREDENCIADA', "
                                + "    GROUP_CONCAT(DISTINCT email "
                                + "        SEPARATOR ';') AS 'OPERACAO' "
                                + "FROM "
                                + "    workstation_bcc.tbl_cop_infodth "
                                + "WHERE "
                                + "    FIND_IN_SET(mr, '" + mrs_array.get(i) + "') and cargo <> 'Gerente'", 2);

                        assunto = "*** FORA ACTIVIA DTH *** " + mrs_array.get(i);

                        html_code = "<p><em>Caros,</em></p>\n"
                                + "Segue contratos FORA ACTIVIA para serem executados na data de hoje.<br>"
                                + "<br><br>";

                        html_code += "<table border=\"1\" cellpadding=\"1\" style=\"table-layout: fixed; font-size:12px\">"
                                + "<tr style=\"background-color: #000080; color: #ffffff;\">"
                                + "<th>CIDADE</th>"
                                + "<th>DATA</th>"
                                + "<th>JANELA</th>"
                                + "<th>CONTRATO</th>"
                                + "<th>CODIGO OS</th>"
                                + "<th>DESCRICAO OS</th>"
                                + "<th>MR</th>"
                                + "<th>DATA ABERTURA</th>"
                                + "<th>ENDERECO</th>"
                                + "<th>CREDENCIADA</th>"
                                + "<th>TELEFONES</th>"
                                + "<th>LOGRADOURO_INST</th>"
                                + "<th>BAIRRO_INST</th>"
                                + "<th>CEP_INST</th>"
                                + "<th>NODE_INST</th>"
                                + "<th>ASSINANTE</th>"
                                + "</tr>";

                        try {
                            //table_casos = global_class.getTable("CALL workstation_bcc.app_foraactivia_dth_email_02('" + credenciadas_array.get(i) + "','" + mrs_array.get(i) + "')");
                            //table_casos = global_class.getTable("CALL workstation_bcc.app_foraactivia_dth_email_03('" + credenciadas_array.get(i) + "','" + mrs_array.get(i) + "')");
                            table_casos = global_class.getTable("CALL workstation_bcc.app_foraactivia_dth_email_04('" + credenciadas_array.get(i) + "','" + mrs_array.get(i) + "')");
                        } catch (Exception ex) {
                            Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
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
                        upd_trat_caso(credenciadas_array.get(i), mrs_array.get(i));

                    } catch (IOException ex) {
                        Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
            }

            cria_tabela();
        }

    }

    public void upd_trat_caso(String cred, String mrs) {

        String query_trat_ff = "UPDATE workstation_bcc.tbl_fora_activia "
                + " SET status = 'SINALIZADO', ult_user = '" + Menu01.user_login + "', ult_modif = NOW()"
                + " WHERE DT_AGENDA = CURDATE() AND FIND_IN_SET(CREDENCIADA,'" + cred + "') "
                + " AND FIND_IN_SET(AREA_DESPACHO,'" + mrs + "')";

        try {
            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_ff);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_ForaActiviaDTH.class
                    .getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

}
