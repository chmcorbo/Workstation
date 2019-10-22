package screens;

import classes.Db_class;
import classes.global_class;
import static classes.global_class.get_color;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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

public class jPanel_MRFechada extends javax.swing.JPanel {

    scr_Menu Menu01;
    JScrollPane scrollPane;
    boolean verifica_criacao = false;
    boolean permitir_email_cred = false;
    boolean permitir_definir_cred = false;

    JTable tableFF;
    JTable tableSuper;
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
    boolean att_ff = false;
    boolean att_fa = false;

    String ativ = "";
    String perfil_ff = "OPERADOR";

    int row_select;
    JMenuItem jMenuReturn;

    ArrayList<String> mrs_array = new ArrayList<>();
    ArrayList<String> credenciadas_array = new ArrayList<>();

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            String info = tab.getValueAt(row, 13).toString();

            if (info.equals("SIN_OPERACAO") && isSelected) {

                cellComponent.setBackground(get_color(255, 215, 0));
                cellComponent.setForeground(Color.BLACK);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("SIN_OPERACAO") && !isSelected) {

                cellComponent.setBackground(get_color(255, 255, 0));
                cellComponent.setForeground(Color.BLACK);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (info.equals("DEF_CRED") && isSelected) {

                cellComponent.setBackground(get_color(255, 140, 0));
                cellComponent.setForeground(Color.WHITE);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("DEF_CRED") && !isSelected) {

                cellComponent.setBackground(get_color(255, 165, 0));
                cellComponent.setForeground(Color.WHITE);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (info.equals("PENDENTE") && isSelected) {

                cellComponent.setBackground(get_color(139, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("PENDENTE") && !isSelected) {

                cellComponent.setBackground(get_color(255, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if ((info.equals("FINALIZADO") || info.equals("SIN_CRED")) && isSelected) {

                cellComponent.setBackground(get_color(0, 128, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if ((info.equals("FINALIZADO") || info.equals("SIN_CRED")) && !isSelected) {

                cellComponent.setBackground(get_color(60, 179, 113));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            }

            return cellComponent;

        }
    }

    class CustomRendererSupervisor extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            String info = tab.getValueAt(row, 13).toString();

            if (info.equals("SIN_OPERACAO") && isSelected) {

                cellComponent.setBackground(get_color(255, 215, 0));
                cellComponent.setForeground(Color.BLACK);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("SIN_OPERACAO") && !isSelected) {

                cellComponent.setBackground(get_color(255, 255, 0));
                cellComponent.setForeground(Color.BLACK);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (info.equals("DEF_CRED") && isSelected) {

                cellComponent.setBackground(get_color(255, 140, 0));
                cellComponent.setForeground(Color.WHITE);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("DEF_CRED") && !isSelected) {

                cellComponent.setBackground(get_color(255, 165, 0));
                cellComponent.setForeground(Color.WHITE);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (info.equals("PENDENTE") && isSelected) {

                cellComponent.setBackground(get_color(139, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("PENDENTE") && !isSelected) {

                cellComponent.setBackground(get_color(255, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if ((info.equals("FINALIZADO") || info.equals("SIN_CRED")) && isSelected) {

                cellComponent.setBackground(get_color(0, 128, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if ((info.equals("FINALIZADO") || info.equals("SIN_CRED")) && !isSelected) {

                cellComponent.setBackground(get_color(60, 179, 113));
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
        ImageIcon icon3 = new javax.swing.ImageIcon(getClass().getResource("/images/caution_high_voltage.png"));

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            String info = (String) table.getValueAt(row, 13);

            if (info.equals("PENDENTE") && isSelected) {

                lbl.setBackground(global_class.get_color(139, 0, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon2);

            } else if (info.equals("PENDENTE") && !isSelected) {

                lbl.setBackground(global_class.get_color(255, 0, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon2);

            } else if (info.equals("FINALIZADO") || info.equals("SIN_CRED") && isSelected) {

                lbl.setBackground(global_class.get_color(0, 128, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon);

            } else if (info.equals("FINALIZADO") || info.equals("SIN_CRED") && !isSelected) {

                lbl.setBackground(global_class.get_color(60, 179, 113));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon);

            } else if (info.equals("SIN_OPERACAO") && isSelected) {

                lbl.setBackground(global_class.get_color(255, 215, 0));
                lbl.setForeground(Color.BLACK);
                lbl.setIcon(icon3);

            } else if (info.equals("SIN_OPERACAO") && !isSelected) {

                lbl.setBackground(global_class.get_color(255, 255, 0));
                lbl.setForeground(Color.BLACK);
                lbl.setIcon(icon3);

            } else if (info.equals("DEF_CRED") && isSelected) {

                lbl.setBackground(global_class.get_color(255, 140, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon3);

            } else if (info.equals("DEF_CRED") && !isSelected) {

                lbl.setBackground(global_class.get_color(255, 165, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon3);

            }

            lbl.setOpaque(true);
            lbl.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

            return lbl;
        }
    }

    public jPanel_MRFechada(scr_Menu Menu00, String tipo_ativ, String perfil) {
        initComponents();

        Menu01 = Menu00;
        ativ = tipo_ativ;
        perfil_ff = perfil;

        if (perfil_ff.equals("OPERADOR")) {

            cria_tabela();

        } else if (perfil_ff.equals("SUPERVISOR")) {

            cria_tabela_super();

        }

        if (ativ.equals("POOL_HFC")) {

            Menu01.pool_dth_mrfechada = this;

        }

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

            try {

                tableFF = null;
                tableFF = global_class.getTable("CALL workstation_bcc.app_casos_mr_fechada()");
                tableFF.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRenderer tcustom = new CustomRenderer();
                ImageRenderer icustom = new ImageRenderer();

                for (int column = 0; column < tableFF.getColumnCount(); column++) {
                    TableColumn tableColumn = tableFF.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();
                    int maxWidth = tableColumn.getMaxWidth();

                    for (int row = 0; row < tableFF.getRowCount(); row++) {
                        TableCellRenderer cellRenderer = tableFF.getCellRenderer(row, column);
                        Component c = tableFF.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + tableFF.getIntercellSpacing().width;
                        preferredWidth = Math.max(preferredWidth, width);

                        if (preferredWidth >= maxWidth) {
                            preferredWidth = maxWidth + 10;
                            break;
                        }
                    }

                    tableColumn.setCellRenderer(tcustom);

                    switch (column) {
                        case 0:
                        case 13:
                            tableColumn.setPreferredWidth(0);
                            tableColumn.setMinWidth(0);
                            tableColumn.setMaxWidth(0);
                            break;
                        case 1:
                            tableColumn.setPreferredWidth(20);
                            tableColumn.setMinWidth(20);
                            tableColumn.setMaxWidth(20);
                            tableColumn.setCellRenderer(icustom);
                            break;
                        default:
                            tableColumn.setPreferredWidth(preferredWidth);
                            tableColumn.setMinWidth(preferredWidth + 40);
                            tableColumn.setMaxWidth(preferredWidth + 40);
                            break;
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tableFF, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tableFF.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                tableFF.addMouseListener(new tab_click());
                tableFF.addKeyListener(new att_assistente(tableFF));
                tableFF.setRowSelectionAllowed(false);
                tableFF.setCellSelectionEnabled(true);
                tableFF.getTableHeader().setReorderingAllowed(false);
                tableFF.setLocation(0, 0);
                tableFF.setSize(screenRect.width - 20, screenRect.height - 115);

                tableFF.setVisible(true);
                scrollPane = new JScrollPane(tableFF);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(screenRect.width - 20, screenRect.height - 150);
                scrollPane.setVisible(true);
                jPanel1.add(scrollPane);

            } catch (Exception ex) {
                Logger.getLogger(jPanel_MRFechada.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

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
                tableSuper = global_class.getTable("CALL workstation_bcc.app_casos_super_mr_fechada()");
                tableSuper.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRenderer tcustom = new CustomRenderer();
                ImageRenderer icustom = new ImageRenderer();

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

                    switch (column) {
                        case 0:
                        case 13:
                            tableColumn.setPreferredWidth(0);
                            tableColumn.setMinWidth(0);
                            tableColumn.setMaxWidth(0);
                            break;
                        case 1:
                            tableColumn.setPreferredWidth(20);
                            tableColumn.setMinWidth(20);
                            tableColumn.setMaxWidth(20);
                            tableColumn.setCellRenderer(icustom);
                            break;
                        default:
                            tableColumn.setPreferredWidth(preferredWidth);
                            tableColumn.setMinWidth(preferredWidth + 40);
                            tableColumn.setMaxWidth(preferredWidth + 40);
                            break;
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tableSuper, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tableSuper.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                tableSuper.addKeyListener(new att_super(tableSuper));
                tableSuper.setRowSelectionAllowed(false);
                tableSuper.setCellSelectionEnabled(true);
                tableSuper.getTableHeader().setReorderingAllowed(false);
                tableSuper.setLocation(0, 0);
                tableSuper.setSize(screenRect.width - 20, screenRect.height - 115);

                tableSuper.setVisible(true);
                scrollPane = new JScrollPane(tableSuper);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(screenRect.width - 20, screenRect.height - 150);
                scrollPane.setVisible(true);
                jPanel1.add(scrollPane);

            } catch (Exception ex) {
                Logger.getLogger(jPanel_MRFechada.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    class att_super extends KeyAdapter {

        JTable table;

        public att_super(JTable tabela) {

            table = tabela;

        }

        @Override
        public void keyReleased(KeyEvent event) {

            if (event.getKeyCode() == KeyEvent.VK_F5) {

                cria_tabela_super();

            }

        }
    }

    class tab_click extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (SwingUtilities.isRightMouseButton(e)) {

                if (!global_class.check_version()) {

                    String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                } else {

                    rows_select = tableFF.getSelectedRows();
                    int isselectedrow = tableFF.getSelectedColumnCount();
                    ids_select = new ArrayList<>();
                    boolean existe_caso_sem_tratar_selecionado = false;

                    if (isselectedrow > 0) {

                        for (int z = 0; z < rows_select.length; z++) {

                            if (z == 0) {

                                tableFF.setRowSelectionInterval(rows_select[z], rows_select[z]);

                            } else {

                                tableFF.addRowSelectionInterval(rows_select[z], rows_select[z]);

                            }

                            if (tableFF.getValueAt(rows_select[z], 13).equals("PENDENTE")) {

                                permitir_definir_cred = false;
                                existe_caso_sem_tratar_selecionado = true;

                            } else if (!existe_caso_sem_tratar_selecionado) {

                                permitir_definir_cred = true;

                            }

                            if (!tableFF.getValueAt(rows_select[z], 13).equals("DEF_CRED") && !tableFF.getValueAt(rows_select[z], 13).equals("SIN_CRED")) {

                                permitir_email_cred = false;
                                existe_caso_sem_tratar_selecionado = true;

                            } else if (!existe_caso_sem_tratar_selecionado) {

                                permitir_email_cred = true;

                            }

                        }

                        for (int a = 0; a < rows_select.length; a++) {

                            ids_select.add((Integer) tableFF.getValueAt(rows_select[a], 0));

                        }

                        jPopupMenu = new JPopupMenu();
                        jPopupMenu.removeAll();
                        jPopupMenu.repaint();

                        jMenuUN = new JMenuItem();
                        jMenuUN.setText("Sinalizar Operação");
                        jMenuUN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mail_yellow.png")));
                        jPopupMenu.add(jMenuUN);
                        jMenuUN.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {

                                gerar_email_operacao();

                            }
                        });

                        if (permitir_definir_cred) {

                            jMenuEmail = new JMenuItem();
                            jMenuEmail.setText("Definir Credenciada");
                            jMenuEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group_add.png")));
                            jPopupMenu.add(jMenuEmail);
                            jMenuEmail.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {

                                    jDialog_Definir_Cred_DTH definir = new jDialog_Definir_Cred_DTH(true, Menu01, Menu01.pool_dth_mrfechada);

                                }
                            });

                            jPopupMenu.repaint();

                        }

                        if (permitir_email_cred) {

                            jMenuEmail = new JMenuItem();
                            jMenuEmail.setText("Sinalizar Credenciada");
                            jMenuEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email_go.png")));
                            jPopupMenu.add(jMenuEmail);
                            jMenuEmail.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {

                                    gerar_email_credenciada();
                                }
                            });

                            jPopupMenu.repaint();

                        }

                        jPopupMenu.repaint();
                        jPopupMenu.show(tableFF, e.getX(), e.getY());

                    }
                }

            }
        }

    }

    public void action_definir_eqp(String UN) {

        String un_tabular;
        un_tabular = UN;

        for (int lin = 0; lin < rows_select.length; lin++) {

            id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
            upd_trat_caso_cred(id_caso, un_tabular);

            if (lin == (rows_select.length - 1)) {

                String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

            }

        }

        jPopupMenu.removeAll();
        jPopupMenu.repaint();
        cria_tabela();

    }

    class action_un implements ActionListener {

        String un_tabular;

        public action_un(String UN) {

            un_tabular = UN;

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!un_tabular.equals("Caso Sincronizado") && un_tabular.contains("TIME")) {

                login_tec_valid = false;

                un_selecionada = un_tabular + "|Login Técnico: " + login_tec.toUpperCase();

                for (int lin = 0; lin < rows_select.length; lin++) {

                    id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                    //update_trat_ff();

                    if (lin == (rows_select.length - 1)) {

                        String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                        Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                        global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                    }

                }
            } else if (!un_tabular.equals("Caso Sincronizado") && !un_tabular.contains("TIME")) {

                un_selecionada = un_tabular;

                for (int lin = 0; lin < rows_select.length; lin++) {

                    id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                    //update_trat_ff();

                    if (lin == (rows_select.length - 1)) {

                        String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                        Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                        global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                    }

                }
            } else if (un_tabular.equals("Caso Sincronizado")) {

                for (int lin = 0; lin < rows_select.length; lin++) {

                    id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                    un_selecionada = "CASO SINCRONIZADO";
                    //update_trat_ff();

                    if (lin == (rows_select.length - 1)) {

                        String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                        Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                        global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                    }

                }

            }

            jPopupMenu.removeAll();
            jPopupMenu.repaint();
            cria_tabela();
        }

    }

    public void gerar_email_operacao() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            String ids_query = "";

            for (int a = 0; a < ids_select.size(); a++) {

                ids_query += ids_select.get(a) + ", ";

            }

            ids_query = ids_query.substring(0, ids_query.length() - 2);

            mrs_array = new ArrayList<>();
            global_class.preenche_array(mrs_array, "SELECT "
                    + "    GROUP_CONCAT(DISTINCT AREA_DESPACHO SEPARATOR ',') AS 'MRS'"
                    + "FROM"
                    + "    workstation_bcc.tbl_mr_fechada"
                    + "    WHERE status NOT IN ('FINALIZADO','FINALIZADO_SEM_RESPOSTA')"
                    + "    AND id IN (" + ids_query + ") "
                    + "GROUP BY AREA_DESPACHO", 1);

            for (int i = 0; i < mrs_array.size(); i++) {

                email_para = "";
                table_casos = null;

                try {

                    email_para = "";
                    email_para = global_class.get_emails("SELECT  "
                            + "    GROUP_CONCAT(DISTINCT email "
                            + "        SEPARATOR ';') AS 'OPERACAO' "
                            + "FROM "
                            + "    workstation_bcc.tbl_cop_infodth "
                            + "WHERE "
                            + "    FIND_IN_SET(mr, '" + mrs_array.get(i) + "') ", 1);

                    assunto = "*** MR FECHADA DTH *** " + mrs_array.get(i);

                    html_code = "<p><em>Caros,</em></p>\n"
                            + "Segue(m) contrato(s) MR FECHADA para indicação da credenciada a executar o(s) caso(s).<br>"
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
                            + "</tr>";

                    try {

                        table_casos = global_class.getTable("SELECT "
                                + "    CIDADE,"
                                + "	IFNULL(DATE_FORMAT(DT_AGENDA, '%d/%m/%Y'),'') AS 'DATA',"
                                + "	JANELA,"
                                + "	CONTRATO,"
                                + "    IFNULL(COD_OS,'') AS 'CODIGO OS',"
                                + "	IFNULL(DESCRICAO_OS,'') AS 'DESCRICAO OS',"
                                + "	IFNULL(AREA_DESPACHO,'') AS 'MR',"
                                + "    IFNULL(DH_ABERTURA,'') AS 'ABERTURA',"
                                + "    IFNULL(end_completo,'') AS 'ENDEREÇO'"
                                + "FROM"
                                + "    workstation_bcc.tbl_mr_fechada "
                                + "WHERE "
                                + " FIND_IN_SET(AREA_DESPACHO, '" + mrs_array.get(i) + "') "
                                + "    AND id REGEXP('" + ids_query.replace(",", "|").replace(" ", "") + "') "
                                + "    AND status NOT IN ('FINALIZADO','FINALIZADO_SEM_RESPOSTA')");

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
                    upd_trat_caso_operacao(ids_query);

                } catch (IOException ex) {
                    Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            cria_tabela();

        }
    }

    public void gerar_email_credenciada() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            String ids_query = "";

            for (int a = 0; a < ids_select.size(); a++) {

                ids_query += ids_select.get(a) + ", ";

            }

            ids_query = ids_query.substring(0, ids_query.length() - 2);

            credenciadas_array = new ArrayList<>();
            global_class.preenche_array(credenciadas_array, "SELECT "
                    + "    GROUP_CONCAT(DISTINCT CREDENCIADA SEPARATOR ',') AS 'CREDENCIADAS' "
                    + "FROM"
                    + "    workstation_bcc.tbl_mr_fechada"
                    + "    WHERE status IN ('DEF_CRED','SIN_CRED')"
                    + "    AND id IN (" + ids_query + ") "
                    + "GROUP BY CREDENCIADA", 1);

            mrs_array = new ArrayList<>();
            global_class.preenche_array(mrs_array, "SELECT "
                    + "    GROUP_CONCAT(DISTINCT AREA_DESPACHO SEPARATOR ',') AS 'MRS' "
                    + "FROM"
                    + "    workstation_bcc.tbl_mr_fechada"
                    + "    WHERE status IN ('DEF_CRED','SIN_CRED')"
                    + "    AND id IN (" + ids_query + ") "
                    + "GROUP BY AREA_DESPACHO", 1);

            for (int i = 0; i < credenciadas_array.size(); i++) {

                email_para = "";
                email_cc = "";
                table_casos = null;

                try {

                    email_para = "";
                    email_cc = "";

                    email_cc = global_class.get_emails("SELECT  "
                            + "    GROUP_CONCAT(DISTINCT email "
                            + "        SEPARATOR ';') AS 'OPERACAO' "
                            + "FROM "
                            + "    workstation_bcc.tbl_cop_infodth "
                            + "WHERE "
                            + "    FIND_IN_SET(mr, '" + mrs_array.get(i) + "') ", 1);

                    email_para = global_class.get_emails("SELECT  "
                            + "    GROUP_CONCAT(DISTINCT email1 "
                            + "        SEPARATOR ';') AS 'CREDENCIADA' "
                            + "FROM "
                            + "    workstation_bcc.tbl_cop_infodth", 1);

                    assunto = "*** MR FECHADA DTH *** " + credenciadas_array.get(i);

                    html_code = "<p><em>Caros,</p>\n"
                            + "Segue(m) contrato(s) MR FECHADA para ser(em) executado(s).</em><br>"
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
                            + "</tr>";

                    try {

                        table_casos = global_class.getTable("SELECT "
                                + "    CIDADE,"
                                + "	IFNULL(DATE_FORMAT(DT_AGENDA, '%d/%m/%Y'),'') AS 'DATA',"
                                + "	JANELA,"
                                + "	CONTRATO,"
                                + "    IFNULL(COD_OS,'') AS 'CODIGO OS',"
                                + "	IFNULL(DESCRICAO_OS,'') AS 'DESCRICAO OS',"
                                + "	IFNULL(AREA_DESPACHO,'') AS 'MR',"
                                + "    IFNULL(DH_ABERTURA,'') AS 'ABERTURA',"
                                + "    IFNULL(end_completo,'') AS 'ENDEREÇO'"
                                + "FROM"
                                + "    workstation_bcc.tbl_mr_fechada "
                                + "WHERE "
                                + " FIND_IN_SET(CREDENCIADA, '" + credenciadas_array.get(i) + "') "
                                + "    AND id REGEXP('" + ids_query.replace(",", "|").replace(" ", "") + "') "
                                + "    AND status NOT IN ('FINALIZADO','FINALIZADO_SEM_RESPOSTA')");

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
                    upd_sin_caso_cred(ids_query, credenciadas_array.get(i));

                } catch (IOException ex) {
                    Logger.getLogger(jPanel_Risco_Pendencia.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            cria_tabela();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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

    public void upd_trat_caso_operacao(String ids) {

        String query_trat_mr = "UPDATE workstation_bcc.tbl_mr_fechada "
                + " SET status = 'SIN_OPERACAO', ult_user = '" + Menu01.user_login + "', ult_modif = NOW()"
                + " WHERE id IN (" + ids + ")";

        try {
            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_mr);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_ForaActiviaDTH.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

    public void upd_trat_caso_cred(Integer ids, String cred) {

        String query_trat_mr = "UPDATE workstation_bcc.tbl_mr_fechada "
                + " SET status = 'DEF_CRED', ult_user = '" + Menu01.user_login + "', CREDENCIADA = '" + cred + "', ult_modif = NOW()"
                + " WHERE id IN (" + ids + ")";

        try {
            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_mr);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_ForaActiviaDTH.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

    public void upd_sin_caso_cred(String ids, String cred) {

        String query_trat_mr = "UPDATE workstation_bcc.tbl_mr_fechada "
                + " SET status = 'SIN_CRED', ult_user = '" + Menu01.user_login + "', ult_modif = NOW()"
                + " WHERE id IN (" + ids + ")";

        try {
            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_mr);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(jPanel_ForaActiviaDTH.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
