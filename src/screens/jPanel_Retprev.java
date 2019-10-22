package screens;

import classes.Db_class;
import classes.cEmail;
import classes.global_class;
//import static classes.global_class.close_con;
import static classes.global_class.get_color;
//import static classes.global_class.mysql_con;
import static classes.global_class.mysql_result;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class jPanel_Retprev extends javax.swing.JPanel {

    scr_Menu Menu01;
    JScrollPane scrollPane;
    boolean verifica_criacao = false;
    boolean permitir_tratar = false;

    JTable tableFF;
    JTable tableSuper;
    JTable table_casos;

    Connection conn;

    String cidade_selecionada = "-- Selecione uma cidade --";
    int[] rows_select;
    JMenuItem jMenuUN;
    JMenuItem jMenuUNCS;
    JMenuItem jMenuEmail;
    JPopupMenu jPopupMenu;

    ArrayList<Long> ids_select = new ArrayList<>();
    ArrayList<String> nomes_uns_field = new ArrayList<>();
    ArrayList<String> uns_email;

    String email_para;
    String email_cc;
    String titulo;

    int id_cid;
    int id_empresa;
    int id_caso;
    String contrato;
    String cidade;

    String nm_empresa;
    String assunto = "";
    String un_selecionada;

    String html_code;

    String login_tec;
    Boolean bPedir_LoginTec = false;

    boolean login_tec_valid;
    boolean att_ff = false;
    boolean att_fa = false;

    String ativ = "";
    String perfil_ff = "OPERADOR";

    int row_select;
    JMenuItem jMenuReturn;

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            if (tab.getValueAt(row, 9).equals("") && isSelected) {

                cellComponent.setBackground(global_class.get_color(139, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (tab.getValueAt(row, 9).equals("") && !isSelected) {

                cellComponent.setBackground(global_class.get_color(255, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (!(tab.getValueAt(row, 9).equals("")) && isSelected) {

                cellComponent.setBackground(global_class.get_color(0, 128, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (!(tab.getValueAt(row, 9).equals("")) && !isSelected) {

                cellComponent.setBackground(global_class.get_color(60, 179, 113));
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

            String info = tab.getValueAt(row, 8).toString();

            if (info.equals("NAO_TRATADO") && isSelected) {

                cellComponent.setBackground(get_color(255, 165, 0));
                cellComponent.setForeground(Color.BLACK);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("NAO_TRATADO") && !isSelected) {

                cellComponent.setBackground(get_color(255, 255, 0));
                cellComponent.setForeground(Color.BLACK);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (info.equals("") && isSelected) {

                cellComponent.setBackground(get_color(139, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (info.equals("") && !isSelected) {

                cellComponent.setBackground(get_color(255, 0, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

            } else if (!(info.equals("")) && isSelected) {

                cellComponent.setBackground(get_color(0, 128, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else if (!(info.equals("")) && !isSelected) {

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

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            String info = (String) table.getValueAt(row, 10);

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

    public jPanel_Retprev(scr_Menu Menu00, String tipo_ativ, String perfil) {
        initComponents();

        Menu01 = Menu00;
        ativ = tipo_ativ;
        perfil_ff = perfil;
        jSeparator1.setVisible(false);
        jButton1.setVisible(false);

        if (perfil_ff.equals("OPERADOR")) {

            combo_cidade();

        } else if (perfil_ff.equals("SUPERVISOR")) {

            jToolBar1.setVisible(false);
            //cria_tabela_super();

        }

        if (ativ.equals("GARANTIA")) {

            Menu01.garantia_panel_retornocredencial = this;

        } else if (ativ.equals("POOL")) {

            Menu01.pool_panel_retornocredencial = this;

        } else if (ativ.equals("POOL_HFC")) {

            Menu01.pool_panel_retornoactivia = this;

        } else if (ativ.equals("POOL_DESCO")) {

            Menu01.pool_desco_panel_retornocredencial = this;

        } else if (ativ.equals("GPON")) {

            Menu01.gpon_pool_panel_retornocredencial = this;

        }

    }

    public jPanel_Retprev(scr_Menu Menu00, String tipo_ativ, String perfil, boolean foraactivia) {
        initComponents();

        Menu01 = Menu00;
        ativ = tipo_ativ;
        perfil_ff = perfil;
        jSeparator1.setVisible(false);
        jButton1.setVisible(false);

        if (perfil_ff.equals("OPERADOR")) {

            combo_cidade();

        } else if (perfil_ff.equals("SUPERVISOR")) {

            jToolBar1.setVisible(false);
            // cria_tabela_super();

        }

        Menu01.pool_panel_retornoactivia = this;

    }

    public jPanel_Retprev(scr_Menu Menu00, String tipo_ativ, boolean foraactiviadth, String perfil) {
        initComponents();

        Menu01 = Menu00;
        ativ = tipo_ativ;
        perfil_ff = perfil;
        jLabel1.setVisible(false);
        combo_cidades_ff.setVisible(false);

        if (perfil_ff.equals("OPERADOR")) {

            cria_tabela();

        } else if (perfil_ff.equals("SUPERVISOR")) {

            //cria_tabela_super();
            jToolBar1.setVisible(false);

        }

    }

    public void combo_cidade() {

        global_class.preencher_combobox(combo_cidades_ff, "-- Selecione uma cidade --", "CALL workstation_bcc.app_cidades_retprev('" + ativ + "')");

    }

    public void atualiza_casos() {

        if (verifica_criacao) {

            jPanel1.remove(scrollPane);
            jPanel1.repaint();
            verifica_criacao = false;
        }

        if (combo_cidades_ff.getSelectedIndex() == 0) {

            cidade_selecionada = "-- Selecione uma cidade --";

        } else {

            cidade_selecionada = combo_cidades_ff.getSelectedItem().toString();

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

            if (combo_cidades_ff.getSelectedIndex() != 0) {

                try {

                    conn = Db_class.mysql_conn();
                    ResultSet ff = null;
                    ff = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_FF='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");
                    ff.next();
                    att_ff = ff.getBoolean(1);
                    Db_class.close_conn(conn);

                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                    Db_class.close_conn(conn);
                    global_class.msg_dialog("Não foi possível verificar se a carga de FF em andamento.\n\nO processo seguirá como se não houvesse uma.\n\nErro original: " + ex.getMessage(), JOptionPane.WARNING_MESSAGE);

                }

                if (!att_ff) {

                    try {
                        /*
                         nomes_uns_field = new ArrayList<>();
                         nomes_uns_field.add("Caso Sincronizado");
                         global_class.preenche_array(nomes_uns_field, "CALL workstation_bcc.app_uns_cid3('" + cidade_selecionada + "','" + ativ + "')", 1);
                         */

                        tableFF = null;
                        tableFF = global_class.getTable("CALL workstation_bcc.app_casos_retprev('" + cidade_selecionada + "','" + ativ + "')");
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

                            if (column == 0) {

                                tableColumn.setPreferredWidth(0);
                                tableColumn.setMinWidth(0);
                                tableColumn.setMaxWidth(0);

                            } else if (column == 4) {

                                tableColumn.setPreferredWidth(90);
                                tableColumn.setMinWidth(90);
                                tableColumn.setMaxWidth(90);

                            } else if (column == 5) {

                                tableColumn.setPreferredWidth(90);
                                tableColumn.setMinWidth(90);
                                tableColumn.setMaxWidth(90);

                            } else if (column == 6) {

                                tableColumn.setPreferredWidth(150);
                                tableColumn.setMinWidth(150);
                                tableColumn.setMaxWidth(150);
                            } else if (column == 9) {

                                tableColumn.setPreferredWidth(150);
                                tableColumn.setMinWidth(150);
                                tableColumn.setMaxWidth(150);
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
                        Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                        global_class.msg_dialog("Problemas com a consulta a UNs ou a Casos FF.\n\nCidade selecionada: " + cidade_selecionada + "\nAtividade: " + ativ + "\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                    String msg = "<html><body><p width='250px'>O Fora Field está em processo de atualização\n\nTente novamente em 2 minutos.";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);

                }

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

                            if (!tableFF.getValueAt(rows_select[z], 9).equals("")) {
                                permitir_tratar = false;
                            } else {
                                permitir_tratar = true;
                                //existe_caso_sem_tratar_selecionado = true;
                            }
                        }

                        for (int a = 0; a < rows_select.length; a++) {
                            ids_select.add((Long) tableFF.getValueAt(rows_select[a], 0));
                        }

                        if (permitir_tratar) {
                            jPopupMenu = new JPopupMenu();
                            jPopupMenu.removeAll();
                            jPopupMenu.repaint();

                            jMenuUN = new JMenuItem();
                            jMenuUN.setText("Marcar como tratado");
                            jMenuUN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group_add.png")));
                            jPopupMenu.add(jMenuUN);
                            jMenuUN.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {

                                    switch (ativ) {
                                        case "POOL": {
                                            //jDialog_Definir_Equipe definir = new jDialog_Definir_Equipe(true, Menu01, ids_select, nomes_uns_field, Menu01.garantia_panel_forafield);
                                            marcar_tratado(ids_select);
                                            cria_tabela();
                                        }
                                        default:
                                            break;
                                    }

                                }
                            });
                            jMenuUNCS = new JMenuItem();
                            jMenuUNCS.setText("Caso Sincronizado");
                            jMenuUNCS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop_closed.png")));
                            jPopupMenu.add(jMenuUNCS);
                            jMenuUNCS.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {

                                    switch (ativ) {
                                        case "POOL": {
                                            //jDialog_Definir_Equipe definir = new jDialog_Definir_Equipe(true, Menu01, ids_select, nomes_uns_field, Menu01.garantia_panel_forafield);
                                            marcar_sincronizado(ids_select);
                                            cria_tabela();
                                        }
                                        default:
                                            break;
                                    }

                                }
                            });
                            jPopupMenu.repaint();
                            jPopupMenu.show(tableFF, e.getX(), e.getY());
                        }

                    }
                }

            }
        }

        public void marcar_tratado(ArrayList<Long> PI_id) {

            String query_trat_rc;

            try {
                //processo pré-existente

                for (int i = 0; i < PI_id.size(); i++) {
                    Long get = PI_id.get(i);
                    query_trat_rc = "UPDATE workstation_bcc.tbl_field_retprev"
                            + " SET LOGIN_TRAT = '" + Menu01.user_login + "', DT_TRAT = NOW(),"
                            + " STATUS = 'TRATADO'"
                            + " WHERE ID = " + get;

                    conn = Db_class.mysql_conn();
                    global_class.mysql_insert(conn, query_trat_rc);
                    Db_class.close_conn(conn);
                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                String sMsg = "";
                JOptionPane.showMessageDialog(null, sMsg, "Não foi possível realizar a ação", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(jPanel_Retprev.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Db_class.close_conn(conn);
                } catch (Exception ex2) {
                    Logger.getLogger(jPanel_Retprev.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
           public void marcar_sincronizado(ArrayList<Long> PI_id) {

            String query_trat_rc;

            try {
                //processo pré-existente

                for (int i = 0; i < PI_id.size(); i++) {
                    Long get = PI_id.get(i);
                    query_trat_rc = "UPDATE workstation_bcc.tbl_field_retprev"
                            + " SET LOGIN_TRAT = '" + Menu01.user_login + "', DT_TRAT = NOW(),"
                            + " STATUS = 'SINCRONIZADO' "
                            + " WHERE ID = " + get;

                    conn = Db_class.mysql_conn();
                    global_class.mysql_insert(conn, query_trat_rc);
                    Db_class.close_conn(conn);
                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                String sMsg = "";
                JOptionPane.showMessageDialog(null, sMsg, "Não foi possível realizar a ação", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(jPanel_Retprev.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Db_class.close_conn(conn);
                } catch (Exception ex2) {
                    Logger.getLogger(jPanel_Retprev.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class tab_click_super extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (!global_class.check_version()) {

                    String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                } else {

                    row_select = tableSuper.getSelectedRow();

                    Date data = new Date();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                    //if (row_select >= 1) {
                    if (row_select >= 0) { //a primeira linha de dados é a 0 (zero)

                        if (tableSuper.getValueAt(row_select, 12).equals("OK") && !tableSuper.getValueAt(row_select, 8).equals("")) {

                            id_caso = (int) tableSuper.getValueAt(row_select, 0);
                            contrato = tableSuper.getValueAt(row_select, 4).toString();

                            jPopupMenu = new JPopupMenu();
                            jPopupMenu.removeAll();
                            jPopupMenu.repaint();

                            jMenuReturn = new JMenuItem();
                            jMenuReturn.setText("Retornar Caso");
                            jMenuReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bullet_back.png")));
                            jPopupMenu.add(jMenuReturn);
                            jMenuReturn.addActionListener(new jPanel_Retprev.action_return(id_caso));

                            jPopupMenu.repaint();
                            jPopupMenu.show(tableSuper, e.getX(), e.getY());

                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Nenhuma opção disponível para o contrato " + contrato
                                    + "\n\n "
                                    + tableSuper.getColumnName(12) + " = " + ("".equals(tableSuper.getValueAt(row_select, 12).toString().trim()) ? "<vazio>" : tableSuper.getValueAt(row_select, 12).toString().trim()) + " (precisar ser OK)"
                                    + "\n "
                                    + tableSuper.getColumnName(8) + " = " + ("".equals(tableSuper.getValueAt(row_select, 8).toString().trim()) ? "<vazio>" : tableSuper.getValueAt(row_select, 8).toString().trim()) + " (não pode ser vazio)",
                                    "Atenção", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }

        }

    }

    class action_return implements ActionListener {

        int caso;

        public action_return(int id_caso) {

            caso = id_caso;

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                conn = Db_class.mysql_conn();
                global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_forafield SET PARCEIRA_FORA_FIELD = NULL, LOGIN_TRAT_FORA_FIELD = NULL, DH_TRAT_FORA_FIELD = NULL, DT_UPDATE = NOW() WHERE ID_CASO = " + id_caso);
                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }

            jPopupMenu.removeAll();
            jPopupMenu.repaint();

            // cria_tabela_super();
        }

    }

    public void action_definir_eqp(String UN) {

        String un_tabular;
        un_tabular = UN;

        String sCidade_Anterior = "";

        if (!un_tabular.equals("Caso Sincronizado") && un_tabular.contains("TIME")) {

            bPedir_LoginTec = false; //nao gravara o login do tecnico em tabela separada

            login_tec_valid = false;
            login_tec = "";
            question_login_tec();
            un_selecionada = un_tabular + "|Login Técnico: " + login_tec;

            for (int lin = 0; lin < rows_select.length; lin++) {

                id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                cidade = tableFF.getValueAt(rows_select[lin], 2).toString();
                contrato = tableFF.getValueAt(rows_select[lin], 5).toString();
                update_trat_ff();

                if (lin == (rows_select.length - 1)) {

                    String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                    Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                    global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                }

            }
        } else if (!un_tabular.equals("Caso Sincronizado") && !un_tabular.contains("TIME")) {

            un_selecionada = un_tabular;

            login_tec = "";
            for (int lin = 0; lin < rows_select.length; lin++) {
                id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                cidade = tableFF.getValueAt(rows_select[lin], 2).toString();
                contrato = tableFF.getValueAt(rows_select[lin], 5).toString();

                if (!sCidade_Anterior.equals(cidade)) {
                    sCidade_Anterior = cidade;
                    //verificar se é para pedir matricula
                    bPedir_LoginTec = false;
                    bPedir_LoginTec = PedirMatricula_Verificar();
                }

                if (bPedir_LoginTec) {
                    login_tec = "";
                    PedirMatricula_Pedir();
                }
                update_trat_ff();

                if (lin == (rows_select.length - 1)) {

                    String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                    Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                    global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                }

            }
        } else if (un_tabular.equals("Caso Sincronizado")) {

            login_tec = "";
            bPedir_LoginTec = false; //nao gravara o login do tecnico em tabela separada
            for (int lin = 0; lin < rows_select.length; lin++) {

                id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                cidade = tableFF.getValueAt(rows_select[lin], 2).toString();
                contrato = tableFF.getValueAt(rows_select[lin], 5).toString();
                un_selecionada = "CASO SINCRONIZADO";
                update_trat_ff();

                if (lin == (rows_select.length - 1)) {

                    String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                    Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                    global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                }

            }

        }

        jPopupMenu.removeAll();
        jPopupMenu.repaint();
        att_combo_cidade_select();
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
                question_login_tec();

                un_selecionada = un_tabular + "|Login Técnico: " + login_tec.toUpperCase();

                for (int lin = 0; lin < rows_select.length; lin++) {

                    id_caso = (Integer) tableFF.getValueAt(rows_select[lin], 0);
                    update_trat_ff();

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
                    update_trat_ff();

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
                    update_trat_ff();

                    if (lin == (rows_select.length - 1)) {

                        String msg = "<html><body><p width='150px' align='center'>Caso(s) tratado(s) com sucesso.";
                        Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                        global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                    }

                }

            }

            jPopupMenu.removeAll();
            jPopupMenu.repaint();
            att_combo_cidade_select();
            cria_tabela();
        }

    }

    public void att_combo_cidade_select() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (!cidade_selecionada.equals("-- Selecione uma cidade --")) {

            conn = null;
            combo_cidades_ff.removeAllItems();
            combo_cidades_ff.addItem("-- Selecione uma cidade --");

            try {
                conn = Db_class.mysql_conn();
                ResultSet rs = null;
                rs = global_class.mysql_result(conn, "CALL workstation_bcc.app_cidades_ff('" + ativ + "')");

                while (rs.next()) {

                    combo_cidades_ff.addItem(rs.getObject(1));
                    String cid = (String) rs.getObject(1);

                    if (cid.equals(cidade_selecionada)) {

                        combo_cidades_ff.setSelectedItem(cidade_selecionada);

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
            combo_cidades_ff.removeAllItems();
            combo_cidades_ff.addItem("-- Selecione uma cidade --");

            try {
                conn = Db_class.mysql_conn();
                ResultSet rs = null;
                rs = global_class.mysql_result(conn, "CALL workstation_bcc.app_cidades_ff('" + ativ + "')");

                while (rs.next()) {

                    combo_cidades_ff.addItem(rs.getObject(1));

                }
                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }
    }

    public void gerar_email() {

        ResultSet rs = null;
        uns_email = new ArrayList<String>();

        String ids_query = "";

        for (int a = 0; a < ids_select.size(); a++) {

            ids_query += ids_select.get(a) + ", ";

        }

        ids_query = ids_query.substring(0, ids_query.length() - 2);
        global_class.preenche_array(uns_email, "SELECT 	"
                + "	IF(PARCEIRA_FORA_FIELD LIKE '%TIME%',SUBSTRING_INDEX(PARCEIRA_FORA_FIELD,'|',1),PARCEIRA_FORA_FIELD) AS 'PARCEIRA_COPINFO'"
                + " FROM "
                + "    workstation_bcc.tbl_forafield"
                + " WHERE "
                + "     DT_AGENDAMENTO = CURDATE()"
                + "	AND CIDADE = '" + cidade_selecionada + "'"
                + "     AND ID_CASO IN (" + ids_query + ")"
                //+ "	AND DT_UPDATE >= DATE_FORMAT(NOW(), '%Y-%m-%d %H')"
                + "	AND IFNULL(PARCEIRA_FORA_FIELD,'') <> 'CASO SINCRONIZADO'"
                + " GROUP BY IF(PARCEIRA_FORA_FIELD LIKE '%TIME%',SUBSTRING_INDEX(PARCEIRA_FORA_FIELD,'|',1),PARCEIRA_FORA_FIELD)", 1);

        email_para = "";
        email_cc = "";
        table_casos = null;

        for (int i = 0; i < uns_email.size(); i++) {

            try {

                String query = "SELECT ID_CIDADE, ID_EMPRESA, NM_EMPRESA FROM workstation_bcc.tbl_un_copinfo WHERE NM_CIDADE = '" + cidade_selecionada + "' AND NM_UN = '" + uns_email.get(i) + "' GROUP BY ID_CIDADE, ID_EMPRESA, NM_EMPRESA";
                //String query = "SELECT ID_CIDADE, ID_EMPRESA, NM_EMPRESA FROM workstation_bcc.tbl_un_copinfo WHERE NM_CIDADE = 'SAO PAULO - G5' AND NM_UN = 'SPO-PROCISA_39_VT' GROUP BY ID_CIDADE, ID_EMPRESA, NM_EMPRESA";

                conn = Db_class.mysql_conn();
                rs = global_class.mysql_result(conn, query);

                while (rs.next()) {

                    id_cid = (Integer) rs.getObject(1);
                    id_empresa = (Integer) rs.getObject(2);
                    nm_empresa = (String) rs.getObject(3);
                    email_para = "";
                    email_cc = "";

                    //identificando Assunto e Corpo de e-mail
                    cEmail email = new cEmail(2);
                    assunto = email.getsAssunto();
                    html_code = email.getsCorpo();
                    email = null;

                    assunto = assunto.replaceAll("<#cidade#>", cidade_selecionada).replaceAll("<#empresa#>", nm_empresa).trim();
                    html_code = html_code.replaceAll("<#saudacao#>", global_class.SaudacaoMomento());
                    //

                    //como as tabelas tanto para NET quanto para TERCEIROS são idênticas, coloco a montagem do cabeçalho
                    //uma única vez
                    html_code += "<table border=\"1\">"
                            + "<tr>"
                            + "<th>CIDADE</th>"
                            + "<th>DATA</th>"
                            + "<th>PERIODO</th>"
                            + "<th>CONTRATO</th>"
                            + "<th>DESCRIÇÃO OS</th>"
                            + "<th>CODIGO OS</th>"
                            + "<th>AREA DESPACHO</th>"
                            + "<th>EQUIPE/TÉCNICO</th>"
                            + "<th>ENDERECO</th>"
                            + "<th>TELEFONE</th>"
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
                         assunto = "*** FORA FIELD *** " + cidade_selecionada;

                         html_code = "<p><em>Caros,</em></p>\n"
                         + "<p><em>Segue(m) casos Fora Field</em><em>:</em></p>\n"
                         + "<ul>\n"
                         + "<li><em>&nbsp;</em>&nbsp;<em>Técnico(s) sinalizado(s) via <span style=\"background-color: #ffff00;\"><strong>SMS</strong></span> ou <span style=\"background-color: #ffff00;\"><strong>VOZ</strong></span>.</em></li>";

                         html_code += "<table border=\"1\">"
                         + "<tr>"
                         + "<th>CIDADE</th>"
                         + "<th>DATA</th>"
                         + "<th>PERIODO</th>"
                         + "<th>CONTRATO</th>"
                         + "<th>DESCRIÇÃO OS</th>"
                         + "<th>CODIGO OS</th>"
                         + "<th>AREA DESPACHO</th>"
                         + "<th>EQUIPE/TÉCNICO</th>"
                         + "<th>ENDERECO</th>"
                         + "<th>TELEFONE</th>"
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
                                if (ativ.equals("POOL") || ativ.equals("POOL")) {
                                    email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'Pool')", 1);
                                    email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'Pool')", 1);
                                } else {
                                    email_para += global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + "," + id_empresa + ",'PoolDesco')", 1);
                                    email_cc = global_class.get_emails("CALL workstation_bcc.app_email_ferramentas_002(" + id_cid + ",2,'PoolDesco')", 1);
                                }
                            }
                        }

                        /*
                         assunto = "*** FORA FIELD (" + ativ + ") *** " + cidade_selecionada + " " + nm_empresa;

                         html_code = "<p><em>Caros,</em></p>\n"
                         + "<p><em>Segue(m) casos Fora Field</em><em>:</em></p>\n"
                         + "<ul>\n"
                         + "<li><em>&nbsp;</em>&nbsp;<em>Favor sinalizar o(s) caso(s) para um técnico executar o(s) serviço(s).</em></li>";

                         html_code += "<table border=\"1\">"
                         + "<tr>"
                         + "<th>CIDADE</th>"
                         + "<th>DATA</th>"
                         + "<th>PERIODO</th>"
                         + "<th>CONTRATO</th>"
                         + "<th>DESCRIÇÃO OS</th>"
                         + "<th>CODIGO OS</th>"
                         + "<th>AREA DESPACHO</th>"
                         + "<th>EQUIPE/TÉCNICO</th>"
                         + "<th>ENDERECO</th>"
                         + "<th>TELEFONE</th>"
                         + "<th>NOME DO CLIENTE</th>"
                         + "</tr>";
                         */
                    }

                    email_para = email_para.replace(",,,,", ",").replace(",,,", ",").replace(",", ",").replace("null", "");

                    casos_email(i, ids_query);

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
                    tableFF.clearSelection();
                }

                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | IOException ex) {
                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }

        }

    }

    public void casos_email(int num, String ids) {

        try {

            //table_casos = global_class.getTable("CALL workstation_bcc.app_casos_tratados_un_ff4('" + cidade_selecionada + "','" + uns_email.get(num) + "','" + ativ + "')");
            //table_casos = global_class.getTable("CALL workstation_bcc.app_casos_tratados_un_ff4('SAO PAULO - G5','SPO-PROCISA_39_VT','" + ativ + "')");
            table_casos = global_class.getTable("CALL workstation_bcc.app_casos_tratados_un_ff5('" + cidade_selecionada + "','" + uns_email.get(num) + "','" + ativ + "', '" + ids + "')");

        } catch (Exception ex) {
            Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        combo_cidades_ff = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jLabel1.setText("   Cidade:  ");
        jToolBar1.add(jLabel1);

        combo_cidades_ff.setMaximumSize(new java.awt.Dimension(250, 26));
        combo_cidades_ff.setMinimumSize(new java.awt.Dimension(250, 26));
        combo_cidades_ff.setPreferredSize(new java.awt.Dimension(250, 26));
        combo_cidades_ff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_cidades_ffItemStateChanged(evt);
            }
        });
        combo_cidades_ff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_cidades_ffActionPerformed(evt);
            }
        });
        jToolBar1.add(combo_cidades_ff);
        jToolBar1.add(jSeparator1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/email_go.png"))); // NOI18N
        jButton1.setText("Gerar E-mail");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_cidades_ffItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_cidades_ffItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED && combo_cidades_ff.getSelectedIndex() != 0) {

            atualiza_casos();

        } else if (evt.getStateChange() == ItemEvent.SELECTED && combo_cidades_ff.getSelectedIndex() == 0) {

            if (verifica_criacao) {

                jPanel1.remove(scrollPane);
                jPanel1.repaint();
                verifica_criacao = false;
            }
        }
    }//GEN-LAST:event_combo_cidades_ffItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        gerar_email();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void combo_cidades_ffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_cidades_ffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_cidades_ffActionPerformed

    public void question_login_tec() {

        while (!login_tec_valid) {

            login_tec = JOptionPane.showInputDialog(null,
                    "Qual o login do técnico?",
                    "Login do Técnico",
                    JOptionPane.QUESTION_MESSAGE);

            /*String nome = global_class.login_ldap(login_tec);

             if (nome.equals("Desconhecido") || nome.equals("Erro!")) {

             login_tec_valid = false;

             } else {

             login_tec_valid = true;

             }*/
            login_tec = login_tec.toUpperCase().trim();
            login_tec_valid = true;

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

    public void update_trat_ff() {

        String query_trat_ff;
        Integer iBloco = 1;

        try {
            //processo pré-existente
            iBloco = 1;
            query_trat_ff = "UPDATE workstation_bcc.tbl_forafield "
                    + " SET LOGIN_TRAT_FORA_FIELD = '" + Menu01.user_login + "', PARCEIRA_FORA_FIELD = '" + un_selecionada + "', DH_TRAT_FORA_FIELD = NOW()"
                    + " WHERE ID_CASO = " + id_caso;

            conn = Db_class.mysql_conn();
            global_class.mysql_insert(conn, query_trat_ff);
            Db_class.close_conn(conn);

            //processo novo (Demanda 87424)
            if (bPedir_LoginTec && login_tec.length() != 0) {
                iBloco = 2;
                query_trat_ff = "INSERT INTO workstation_bcc.tbl_forafield_login_tec \n"
                        + "( \n"
                        + "ID_CASO,CONTRATO,CIDADE,UN,LOGIN_TECNICO,DT_REGISTRO,LOGIN_REP \n"
                        + ") \n"
                        + "values \n"
                        + "( \n"
                        + id_caso + ", " + contrato + ", '" + cidade + "', '" + un_selecionada + "', \n'"
                        + login_tec + "', now(), '" + Menu01.user_login + "' \n"
                        + ")";

                conn = Db_class.mysql_conn();
                global_class.mysql_insert(conn, query_trat_ff);
                Db_class.close_conn(conn);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            String sMsg = "";
            if (iBloco == 1) {
                sMsg = "Problemas na atualização do Fora Field.\n\nErro: " + ex.getMessage();
            } else {
                sMsg = "Problemas no registro da matrícula do técnico do Fora Field.\n\nErro: " + ex.getMessage();
            }
            JOptionPane.showMessageDialog(null, sMsg, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
            try {
                Db_class.close_conn(conn);
            } catch (Exception ex2) {
                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void PedirMatricula_Pedir() {

        String ls_msg = "";
        ls_msg = ls_msg + "Para a cidade/UN selecionada, há necessidade de informar\n";
        ls_msg = ls_msg + "o login do técnico que fará o atendimento.\n\n";
        ls_msg = ls_msg + "Essa informação aparecerá nos e-mails de FF.\n\n";
        ls_msg = ls_msg + "Cidade: " + cidade + "\n";
        ls_msg = ls_msg + "UN: " + un_selecionada + "\n\n";
        ls_msg = ls_msg + "Qual o login do técnico? (20 caracteres)";

        while (!login_tec_valid) {

            login_tec = JOptionPane.showInputDialog(null, ls_msg, "Login do Técnico", JOptionPane.QUESTION_MESSAGE);

            login_tec = login_tec.toUpperCase().trim();
            if (login_tec.length() > 20) {
                login_tec = login_tec.substring(1, 20).trim();
            }
            login_tec_valid = true;

        }

    }

    private Boolean PedirMatricula_Verificar() {

        Boolean retorno = false;
        String s_query = "";

        s_query = s_query + "select * \n";
        s_query = s_query + "from \n";
        s_query = s_query + "( \n";
        s_query = s_query + "select \n";
        s_query = s_query + "a.*, \n";
        s_query = s_query + "substring('" + cidade + "', 1, a.cid_len) as cidade, \n";
        s_query = s_query + "substring('" + un_selecionada + "', 1, a.un_len) as un \n";
        s_query = s_query + "from \n";
        s_query = s_query + "( \n";
        s_query = s_query + "select \n";
        s_query = s_query + "mascara_cidade, length(mascara_cidade) as cid_len, \n";
        s_query = s_query + "mascara_un, length(mascara_un) as un_len \n";
        s_query = s_query + "from workstation_bcc.tbl_forafield_login_tec_cfg \n";
        s_query = s_query + ") a \n";
        s_query = s_query + ") b \n";
        s_query = s_query + "where mascara_cidade like cidade \n";
        s_query = s_query + "and mascara_un like un \n";

        Connection conn = null;
        try {
            ResultSet rs1 = null;
            conn = Db_class.mysql_conn();
            rs1 = mysql_result(conn, s_query);

            if (rs1.next()) {
                retorno = true;
            }

            Db_class.close_conn(conn);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

        return retorno;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox combo_cidades_ff;
    private javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    public javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
