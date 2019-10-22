package screens;

import classes.cEmail;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

class CustomRenderer_pend_dth extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 6703872492730589499L;

    @Override
    public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setHorizontalAlignment(CENTER);

        JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

        if (tab.getValueAt(row, 8).equals("NAO") && isSelected) {

            cellComponent.setBackground(global_class.get_color(139, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (tab.getValueAt(row, 8).equals("NAO") && !isSelected) {

            cellComponent.setBackground(global_class.get_color(255, 0, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

        } else if (!(tab.getValueAt(row, 8).equals("NAO")) && isSelected) {

            cellComponent.setBackground(global_class.get_color(0, 128, 0));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        } else if (!(tab.getValueAt(row, 8).equals("NAO")) && !isSelected) {

            cellComponent.setBackground(global_class.get_color(60, 179, 113));
            cellComponent.setForeground(Color.white);
            cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

        }

        return cellComponent;

    }
}

public class jPanel_Pendencia_DTH extends jPanel_Risco_Pendencia {

    ArrayList<String> credenciadas_array = new ArrayList<>();
    ArrayList<String> mrs_array = new ArrayList<>();
    ArrayList<String> gerentes_array = new ArrayList<>();

    public jPanel_Pendencia_DTH(scr_Menu Menu00, boolean dth) {
        super(Menu00, true);

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

                tableRISCO = null;
                tableRISCO = global_class.getTable("CALL workstation_bcc.app_pendencias_dth()");
                tableRISCO.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRenderer_pend_dth tcustom = new CustomRenderer_pend_dth();

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
                        case 8:
                            tableColumn.setPreferredWidth(0);
                            tableColumn.setMinWidth(0);
                            tableColumn.setMaxWidth(0);
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
                tableRISCO.addKeyListener(new att_assistente(tableRISCO));
                tableRISCO.addMouseListener(new tab_click());
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
                Logger.getLogger(jPanel_Pendencia_DTH.class.getName()).log(Level.SEVERE, null, ex);
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
            gerentes_array = new ArrayList<>();
            global_class.preenche_array(credenciadas_array, "CALL workstation_bcc.app_pendencias_dth_gerente()", 1);
            global_class.preenche_array(mrs_array, "CALL workstation_bcc.app_pendencias_dth_gerente()", 2);
            global_class.preenche_array(gerentes_array, "CALL workstation_bcc.app_pendencias_dth_gerente()", 3);

            for (int i = 0; i < credenciadas_array.size(); i++) {

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

                    /*
                    assunto = "*** Pendencias DTH *** " + gerentes_array.get(i);
                    html_code = "<p><em>Caros,</em></p>\n"
                            + "Segue OS's pendentes em ABERTO NO SISTEMA VSMS, que já excederam a janela de atendimento.<br>"
                            + "Solicitamos atenção na tratativa destes contratos, bem como prioridade no atendimento dos mesmos.<br>"
                            + "Ressaltamos que existe um “delay” durante a atualização deste arquivo (comunicação entre os sistemas Activia x Vsms), portanto desconsiderem os contratos nas condições abaixo:<br><br>"
                            + "• Contratos tratados via Atendimento  com devolutiva de chamado.<br>"
                            + "• Contratos tratados ou em processo via Atendimento e/ou via Activia."
                            + "<br><br>";
                    */

                    //identificando Assunto e Corpo de e-mail
                    cEmail email = new cEmail(4);
                    assunto = email.getsAssunto();
                    html_code = email.getsCorpo();
                    email = null;

                    assunto = assunto.replaceAll("<#gerente#>", gerentes_array.get(i)).trim();
                    html_code = html_code.replaceAll("<#saudacao#>", global_class.SaudacaoMomento());
                    //

                    html_code += "<table border=\"1\" cellpadding=\"1\" style=\"table-layout: fixed; font-size:12px\">"
                            + "<tr style=\"background-color: #000080; color: #ffffff;\">"
                            + "<th>CIDADE</th>"
                            + "<th>CODIGO OS</th>"
                            + "<th>TIPO OS</th>"
                            + "<th>DATA AGENDA</th>"
                            + "<th>PERIODO</th>"
                            + "<th>CREDENCIADA</th>"
                            + "<th>MR</th>"
                            + "</tr>";

                    try {

                        table_casos = global_class.getTable("CALL workstation_bcc.app_pendencias_dth_email('" + gerentes_array.get(i) + "','" + credenciadas_array.get(i) + "','" + mrs_array.get(i) + "')");

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

                } catch (IOException ex) {
                    Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            cria_tabela();

        }

    }

    class att_assistente extends KeyAdapter {

        JTable table;

        public att_assistente(JTable tabela) {

            table = tabela;

        }

        @Override
        public void keyReleased(KeyEvent event) {

            if (event.getKeyCode() == KeyEvent.VK_F5) {

                cria_tabela();

            }

        }
    }

    class tab_click extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            JMenuItem jMenuAnalisar;

            if (SwingUtilities.isRightMouseButton(e)) {

                if (!global_class.check_version()) {

                    String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                } else {

                    rows_select = tableRISCO.getSelectedRows();
                    int isselectedrow = tableRISCO.getSelectedColumnCount();
                    ids_select = new ArrayList<>();
                    boolean existe_caso_sem_tratar_selecionado = false;

                    if (isselectedrow > 0) {

                        jPopupMenu = new JPopupMenu();
                        jPopupMenu.removeAll();
                        jPopupMenu.repaint();

                        /*
                         jMenuEmail = new JMenuItem();
                         jMenuEmail.setText("Gerar E-mail");
                         jMenuEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email_go.png")));
                         jPopupMenu.add(jMenuEmail);
                         jMenuEmail.addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent ae) {
                         gerar_email();
                         }
                         });
                         */
                        jMenuAnalisar = new JMenuItem();
                        jMenuAnalisar.setText("Verificar condições do registro");
                        jMenuAnalisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png")));
                        jPopupMenu.add(jMenuAnalisar);
                        jMenuAnalisar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                //gerar_email();

                                //int linha;
                                //linha = tableRISCO.rowAtPoint(e.getPoint());
                                jPanel_Analisador jp_Analisador = new jPanel_Analisador(
                                        "CIDADE=" + tableRISCO.getValueAt(tableRISCO.getSelectedRow(), 0).toString() + ";"
                                        + "OS=" + tableRISCO.getValueAt(tableRISCO.getSelectedRow(), 1).toString() + ";"
                                        + "CREDENCIADA=" + tableRISCO.getValueAt(tableRISCO.getSelectedRow(), 5).toString() + ";"
                                        + "MR=" + tableRISCO.getValueAt(tableRISCO.getSelectedRow(), 6).toString(),
                                        1
                                );
                                jp_Analisador.setVisible(true);
                                //jp_Analisador.jta_analise.setText(ativ);
                            }
                        });

                        //jPopupMenu.repaint();
                        jPopupMenu.repaint();
                        jPopupMenu.show(tableRISCO, e.getX(), e.getY());

                    }
                }

            }
        }

    }

}
