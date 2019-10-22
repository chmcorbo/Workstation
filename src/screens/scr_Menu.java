package screens;

import classes.task_get_disp;
import classes.global_class;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import classes.*;

public class scr_Menu extends javax.swing.JFrame {

    //global_class DTI = new global_class(this);
    scr_Menu Menu00;
    String check_att;
    String tabela_html;
    public boolean first_update;

    Timer current_timer;
    Timer current_timerff;

    String user_login = System.getProperty("user.name").toUpperCase();
    String perfil;

    boolean garantia_ff_operador = false;
    boolean garantia_risco_operador = false;
    boolean escala_tecnicos_garantia = false;
    boolean escala_tecnicos_pool = false;

    boolean pool_cabo_ff_operador = false;
    boolean pool_cabo_desco_ff_operador = false;
    boolean pool_cabo_pendencia_operador = false;

    boolean gpon_cabo_ff_operador = false;
    boolean gpon_cabo_pendencia_operador = false;
    boolean gpon_tecnicos_garantia = false;

    boolean guia_pool_panel_retornocredencial = false;

    boolean pool_hfc_fa_operador = false;
    boolean pool_hfc_pendencia_operador = false;

    boolean pool_dth_fa_operador = false;
    boolean pool_dth_pendencia_operador = false;
    boolean pool_dth_mr_fechada = false;

    boolean super_garantia_ff = false;
    boolean super_garantia_pendencia = false;

    boolean super_pool_cabo_ff = false;
    boolean super_pool_cabo_desco_ff_operador = false;
    boolean super_pool_hfc_fa = false;
    boolean super_pool_dth_fa = false;
    boolean super_pool_pendencia_cabo_hfc = false;
    boolean super_pool_dth_mr_fechada = false;

    boolean garantia_fora_field = false;

    boolean garantia_pendencia = false;
    boolean garantia_config_os = false;

    boolean pool_pendencia = false;
    boolean pool_fora_field = false;
    boolean pool_fora_activia = false;

    boolean att_risco = false;
    boolean att_risco_hfc = false;
    boolean garantia_att_pend = false;
    boolean att_ff = false;
    boolean att_pend_hfc = false;

    boolean exibir_msg = false;
    String msg_trayicon = "";

    String ult_att_pend = "";
    String garantia_ult_att_ff = "";
    String pool_ult_att_ff = "";
    String pool_ult_att_fa = "";

    String last_upt_pend;
    String garantia_last_upt_ff;
    String pool_last_upt_ff;
    String pool_last_upt_fa;

    jPanel_ForaField garantia_panel_forafield;
    jPanel_ForaField pool_panel_forafield;
    jPanel_ForaField pool_desco_panel_forafield;
    jPanel_ForaField pool_panel_foraactivia;
    jPanel_ForaField gpon_pool_panel_forafield;

    jPanel_Retprev garantia_panel_retornocredencial;
    jPanel_Retprev pool_panel_retornocredencial;
    jPanel_Retprev pool_panel_retornoactivia;
    jPanel_Retprev pool_desco_panel_retornocredencial;
    jPanel_Retprev gpon_pool_panel_retornocredencial;

    jPanel_Pendencia_NETSMS panel_pendencia;
    jPanel_MRFechada pool_dth_mrfechada;

    JFileChooser chooser;
    File arq;
    String name_arq;
    String path_arq;
    String[] cabecalho_arq;
    String[] cabecalho_padrao = {"statussla", "statusos", "cidade", "cliente", "node", "regiao", "areadespacho", "segmento", "os", "contrato", "logradouro", "tipoos", "datasolicitacao", "dataagendamento", "datrotcred", "datadespcred", "datarotequipe", "datdespequipe", "dataanalise", "datadeslocamento", "dataentrada", "datasaida", "dataenviobaixa", "datretws", "retbaixa", "obsbaixa", "imediata", "conveniencia", "periodo", "prazomaxatend", "credenciada", "equipe"};
    boolean ler_arq = false;
    String line = "";

    String nm_user;
    Connection conn = null;

    localiza_parceiro_tecnico loc_par_tec;
    localiza_un loc_un;

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            if (!(tab.getValueAt(row, column).equals(""))) {

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

    public scr_Menu() {
        initComponents();
        Menu00 = this;

        Operador_Garantia.setVisible(false);
        Operador_Pool.setVisible(false);
        Supervisor.setVisible(false);
        //jmn_geral.setVisible(false);

        acl_menu();
        //nm_user = global_class.login_ldap(user_login);
        label_user_logado.setText(" Usuario Logado : " + user_login + " | " + perfil);
        if (global.iBDProducao == 1) {
            label_user_logado.setText(label_user_logado.getText() + " | " + "Banco de dados de Produção");
        } else {
            label_user_logado.setText(label_user_logado.getText() + " | " + "Banco de dados de Desenvolvimento");
        }

        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        URL imageURL = getClass().getResource("/images/computer.png");
        ImageIcon imageIcon = new ImageIcon(imageURL);
        this.setIconImage(imageIcon.getImage());

        jTabbedPane1.addMouseListener(new guia_aberta(jTabbedPane1));

        //start_schedule(); //aparentemente, desnecessário por enquanto. Parece que Victor estava planejando uma atualização automática dos painéis e desistiu
    }

    class guia_aberta extends MouseAdapter {

        JTabbedPane guia;

        public guia_aberta(JTabbedPane aberta) {

            this.guia = aberta;

        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (SwingUtilities.isRightMouseButton(e)) {

                switch (guia.getTitleAt(guia.getSelectedIndex())) {

                    case "[ GARANTIA ] - Fora Field":
                        garantia_ff_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ GARANTIA ] - Risco TEC1":
                        garantia_risco_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ GARANTIA ] - Escala de Técnicos":
                        escala_tecnicos_garantia = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL ] - Fora Field":
                        pool_cabo_ff_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL Desco ] - Fora Field":
                        pool_cabo_desco_ff_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL ] - Pendências":
                        pool_cabo_pendencia_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL HFC ] - Fora Activia":
                        pool_hfc_fa_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL HFC ] - Pendências":
                        pool_hfc_pendencia_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL DTH ] - Fora Activia":
                        pool_dth_fa_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL DTH ] - Pendências":
                        pool_dth_pendencia_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL DTH ] - MR Fechada":
                        pool_dth_mr_fechada = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL ] - Escala de Técnicos":
                        escala_tecnicos_pool = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR GARANTIA ] - Acomp Fora Field":
                        super_garantia_ff = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR GARANTIA ] - Pendências NETSMS":
                        super_garantia_pendencia = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR POOL ] - Acomp Fora Field":
                        super_pool_cabo_ff = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR POOL Desco ] - Acomp Fora Field":
                        super_pool_cabo_desco_ff_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR POOL HFC ] - Acomp Fora Activia":
                        super_pool_hfc_fa = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR POOL DTH ] - Acomp Fora Activia":
                        super_pool_dth_fa = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR POOL ] - Pendências NETSMS":
                        super_pool_pendencia_cabo_hfc = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ SUPERVISOR POOL DTH ] - MR Fechada":
                        super_pool_dth_mr_fechada = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "Configurações de OS":
                        garantia_config_os = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ GPON ] - Fora Field":
                        gpon_cabo_ff_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ GPON ] - Pendências":
                        gpon_cabo_pendencia_operador = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ GPON ] - Escala de Técnicos":
                        gpon_tecnicos_garantia = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    case "[ POOL ] - Retorno Credenciada":
                        guia_pool_panel_retornocredencial = false;
                        guia.remove(guia.indexAtLocation(e.getX(), e.getY()));
                        break;

                    default:
                        break;
                }

            }

        }

    }

    public void acl_menu() {

        perfil = global_class.verificar_perfil(user_login.trim());

        Supervisor_Config_OS.setVisible(false);

        Operador_PoolDesco_ForaFiel.setVisible(false);
        Supervisor_PoolDesco_ForaField.setVisible(false);
        jsep_geral_001.setVisible(false);
        jmni_geral_cadastros.setVisible(false);
        jmni_geral_cadastros_usuarios.setVisible(false); //a pedido de HCorbo, apenas o CDesk deve acessar

        switch (perfil) {
            case "GARANTIA":
                Operador_Garantia.setVisible(true);
                break;
            case "POOL":
                Operador_Pool.setVisible(true);
                Operador_Pool_HFC_Calculo.setVisible(false);
                break;
            case "SUPERVISAO":
                Operador_Garantia.setVisible(true);
                Operador_Pool.setVisible(true);
                Supervisor.setVisible(true);
                //jmn_geral.setVisible(true);

                //jmni_geral_cadastros.setEnabled(false);
                //jmni_geral_cadastros_usuarios.setVisible(false); //a pedido de HCorbo, apenas o CDesk deve acessar
                break;
            case "DEV_MASTER":
                Operador_Garantia.setVisible(true);
                Operador_Pool.setVisible(true);
                Supervisor.setVisible(true);
                //jmn_geral.setVisible(true);
                jsep_geral_001.setVisible(true);
                jmni_geral_cadastros.setVisible(true);
                jmni_geral_cadastros_usuarios.setVisible(true);
                break;
            case "CDESK":
                Operador_Garantia.setVisible(false);
                Operador_Pool.setVisible(false);
                Supervisor.setVisible(false);
                //jmn_geral.setVisible(true);
                jsep_geral_001.setVisible(true);
                jmni_geral_cadastros.setVisible(true);
                jmni_geral_cadastros_usuarios.setVisible(true); //a pedido de HCorbo, apenas o CDesk deve acessar
                break;
            case "ERRO":
                global.show_error_message("Problemas de acesso.\n\nPor favor, tente se conectar novamente e,\n"
                        + "persistindo o problema, verifique se outras pessoas estão tendo alguma dificuldade.\n\n"
                        + "O Supervisor de Campanha consegue abrir chamado, se for o caso."
                );
                System.exit(0);
                this.dispose();
                break;
            default:
                global.show_error_message("O usuário " + user_login.trim().toUpperCase() + ", logado no sistema operacional,\n"
                        + "não possui acesso ao sistema\n\n"
                        + "Workstation BCC\n\n"
                        + "O Supervisor de Campanha consegue corrigir o caso."
                );
                System.exit(0);
                this.dispose();
                break;
        }
    }

    public void start_schedule() {

        first_update = true;
        get_disp();

        Timer timer = new Timer();

        task_get_disp task = new task_get_disp();
        task.set_menu(Menu00);

        current_timer = timer;
        current_timer.schedule(task, 60000);

    }

    public void get_disp() {

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else {

            conn = null;
            try {
                //conn = global_class.mysql_con();
                conn = Db_class.mysql_conn();
                ResultSet pend = null;
                ResultSet ff = null;

                pend = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_PEND='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");
                ff = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_FF='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

                pend.next();
                ff.next();

                garantia_att_pend = pend.getBoolean(1);
                att_ff = ff.getBoolean(1);

                Db_class.close_conn(conn);

                msg_trayicon = "";

                if (perfil.equals("DEV_MASTER") || perfil.equals("SUPERVISAO")) {

                    if (!garantia_att_pend) {

                        pega_hora_att_pend();

                        if (!ult_att_pend.equals(last_upt_pend)) {

                            last_upt_pend = ult_att_pend;
                            msg_trayicon += "[ SUPERVISOR ] - Pendencias => " + last_upt_pend + "\n\n";
                            exibir_msg = true;

                            if (garantia_pendencia || pool_pendencia) {

                                panel_pendencia.verifica_att();

                            }

                        }

                    }
                }

                if (!att_ff) {

                    pega_hora_att_ff();

                    if (!garantia_ult_att_ff.equals(garantia_last_upt_ff)) {

                        garantia_last_upt_ff = garantia_ult_att_ff;
                        msg_trayicon += "[ GARANTIA ] - Fora Field => " + garantia_last_upt_ff + "\n\n";
                        exibir_msg = true;

                        if (garantia_fora_field) {
                            //global_class.msg_dialog("atualizar Garantia", JOptionPane.INFORMATION_MESSAGE);
                            garantia_panel_forafield.att_combo_cidade_select();

                        }

                    }

                    if (!pool_ult_att_ff.equals(pool_last_upt_ff)) {

                        pool_last_upt_ff = pool_ult_att_ff;
                        msg_trayicon += "[ POOL ] - Fora Field => " + pool_last_upt_ff + "\n\n";
                        exibir_msg = true;

                        if (pool_fora_field) {
                            //global_class.msg_dialog("atualizar Pool", JOptionPane.INFORMATION_MESSAGE);
                            pool_panel_forafield.att_combo_cidade_select();
                        }

                    }

                    if (!pool_ult_att_fa.equals(pool_last_upt_fa) && !pool_ult_att_fa.equals("")) {

                        pool_last_upt_fa = pool_ult_att_fa;
                        msg_trayicon += "[ POOL HFC ] - Fora Activia => " + pool_last_upt_fa + "\n\n";
                        exibir_msg = true;

                        if (pool_fora_activia) {
                            //global_class.msg_dialog("atualizar Pool HFC", JOptionPane.INFORMATION_MESSAGE);
                            pool_panel_foraactivia.att_combo_cidade_select();
                        }

                    }

                } else {
                    String msg = "<html><body><p width='250px'>O Fora Field está em processo de atualização.";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);
                }
                /*if (exibir_msg) {

                 global_class.trayIcon.displayMessage("WorkStation BCC", msg_trayicon, TrayIcon.MessageType.INFO);
                 exibir_msg = false;

                 }*/

                if (!first_update) {

                    task_get_disp task = new task_get_disp();
                    task.set_menu(this);

                    current_timer.schedule(task, 60000);

                }

                Db_class.close_conn(conn);

            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(scr_Menu.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
                global_class.msg_dialog("Problemas com a inicialização do sistema.\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        label_user_logado = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        Operador_Garantia = new javax.swing.JMenu();
        Operador_Garantia_ForaField = new javax.swing.JMenuItem();
        Operador_Garantia_Risco = new javax.swing.JMenuItem();
        Operador_Garantia_Escala = new javax.swing.JMenuItem();
        Operador_Pool = new javax.swing.JMenu();
        Operador_Pool_Cabo = new javax.swing.JMenu();
        Operador_Pool_ForaField = new javax.swing.JMenuItem();
        Operador_PoolDesco_ForaFiel = new javax.swing.JMenuItem();
        Operador_Pool_Risco = new javax.swing.JMenuItem();
        Operador_Pool_RetornoCredenciada = new javax.swing.JMenuItem();
        Operador_Pool_HFC = new javax.swing.JMenu();
        Operador_Pool_HFC_Calculo = new javax.swing.JMenu();
        Operador_Pool_HFC_Import = new javax.swing.JMenuItem();
        Operador_Pool_HFC_Calculo_Info = new javax.swing.JMenuItem();
        Operador_Pool_HFC_FA = new javax.swing.JMenuItem();
        Operador_Pool_HFC_Pendencia = new javax.swing.JMenuItem();
        Operador_Pool_DTH = new javax.swing.JMenu();
        Operador_Pool_DTH_ForaActivia = new javax.swing.JMenuItem();
        Operador_Pool_DTH_Pendencia = new javax.swing.JMenuItem();
        Operador_Pool_DTH_MRFechada = new javax.swing.JMenuItem();
        Operador_Pool_Escala = new javax.swing.JMenuItem();
        jmn_gpon = new javax.swing.JMenu();
        jmn_gpon_pool = new javax.swing.JMenu();
        jmn_gpon_pool_forafield = new javax.swing.JMenuItem();
        jmn_gpon_pool_pendencias = new javax.swing.JMenuItem();
        jmn_gpon_escala = new javax.swing.JMenuItem();
        Supervisor = new javax.swing.JMenu();
        Supervisor_Pool = new javax.swing.JMenu();
        Supervisor_Pool_ForaField = new javax.swing.JMenuItem();
        Supervisor_PoolDesco_ForaField = new javax.swing.JMenuItem();
        Supervisor_Pool_ForaActivia = new javax.swing.JMenuItem();
        Supervisor_Pool_ForaActivia_DTH = new javax.swing.JMenuItem();
        Supervisor_Pool_Pendencia = new javax.swing.JMenuItem();
        Operador_Pool_DTH_MRFechada1 = new javax.swing.JMenuItem();
        Supervisor_Config_OS = new javax.swing.JMenuItem();
        Supervisor_Garantia = new javax.swing.JMenu();
        Supervisor_Garantia_ForaField = new javax.swing.JMenuItem();
        Supervisor_Garantia_Pendencia = new javax.swing.JMenuItem();
        jmn_geral = new javax.swing.JMenu();
        jmni_geral_loc_par_tec = new javax.swing.JMenuItem();
        jmni_geral_loc_un = new javax.swing.JMenuItem();
        jsep_geral_001 = new javax.swing.JPopupMenu.Separator();
        jmni_geral_cadastros = new javax.swing.JMenu();
        jmni_geral_cadastros_usuarios = new javax.swing.JMenuItem();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(1900, 25));
        jToolBar1.setMinimumSize(new java.awt.Dimension(800, 25));
        jToolBar1.setPreferredSize(new java.awt.Dimension(800, 25));

        label_user_logado.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        label_user_logado.setForeground(new java.awt.Color(0, 0, 255));
        label_user_logado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_user_logado.setText(" Usuario Logado : ");
        label_user_logado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToolBar1.add(label_user_logado);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        Operador_Garantia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/safe.png"))); // NOI18N
        Operador_Garantia.setText("Garantia");

        Operador_Garantia_ForaField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_form_delete.png"))); // NOI18N
        Operador_Garantia_ForaField.setText("Fora Field");
        Operador_Garantia_ForaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Garantia_ForaFieldActionPerformed(evt);
            }
        });
        Operador_Garantia.add(Operador_Garantia_ForaField);

        Operador_Garantia_Risco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chart_line.png"))); // NOI18N
        Operador_Garantia_Risco.setText("Risco TEC1");
        Operador_Garantia_Risco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Garantia_RiscoActionPerformed(evt);
            }
        });
        Operador_Garantia.add(Operador_Garantia_Risco);

        Operador_Garantia_Escala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_view_columns.png"))); // NOI18N
        Operador_Garantia_Escala.setText("Escala de Técnicos");
        Operador_Garantia_Escala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Garantia_EscalaActionPerformed(evt);
            }
        });
        Operador_Garantia.add(Operador_Garantia_Escala);

        jMenuBar1.add(Operador_Garantia);

        Operador_Pool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clipboard_invoice.png"))); // NOI18N
        Operador_Pool.setText("POOL");

        Operador_Pool_Cabo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/television.png"))); // NOI18N
        Operador_Pool_Cabo.setText("CABO");

        Operador_Pool_ForaField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_form_delete.png"))); // NOI18N
        Operador_Pool_ForaField.setText("Fora Field");
        Operador_Pool_ForaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_ForaFieldActionPerformed(evt);
            }
        });
        Operador_Pool_Cabo.add(Operador_Pool_ForaField);

        Operador_PoolDesco_ForaFiel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        Operador_PoolDesco_ForaFiel.setText("Fora Field (Desco)");
        Operador_PoolDesco_ForaFiel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_PoolDesco_ForaFielActionPerformed(evt);
            }
        });
        Operador_Pool_Cabo.add(Operador_PoolDesco_ForaFiel);

        Operador_Pool_Risco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_error.png"))); // NOI18N
        Operador_Pool_Risco.setText("Pendências");
        Operador_Pool_Risco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_RiscoActionPerformed(evt);
            }
        });
        Operador_Pool_Cabo.add(Operador_Pool_Risco);

        Operador_Pool_RetornoCredenciada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_view_columns.png"))); // NOI18N
        Operador_Pool_RetornoCredenciada.setText("Retorno de Credenciada");
        Operador_Pool_RetornoCredenciada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_RetornoCredenciadaActionPerformed(evt);
            }
        });
        Operador_Pool_Cabo.add(Operador_Pool_RetornoCredenciada);

        Operador_Pool.add(Operador_Pool_Cabo);

        Operador_Pool_HFC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/satellite_dish.png"))); // NOI18N
        Operador_Pool_HFC.setText("HFC");

        Operador_Pool_HFC_Calculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time.png"))); // NOI18N
        Operador_Pool_HFC_Calculo.setText("Calculo");

        Operador_Pool_HFC_Import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/document_import.png"))); // NOI18N
        Operador_Pool_HFC_Import.setText("Importar Arquivo");
        Operador_Pool_HFC_Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_HFC_ImportActionPerformed(evt);
            }
        });
        Operador_Pool_HFC_Calculo.add(Operador_Pool_HFC_Import);

        Operador_Pool_HFC_Calculo_Info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/time.png"))); // NOI18N
        Operador_Pool_HFC_Calculo_Info.setText("Calculo de Informações");
        Operador_Pool_HFC_Calculo_Info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_HFC_Calculo_InfoActionPerformed(evt);
            }
        });
        Operador_Pool_HFC_Calculo.add(Operador_Pool_HFC_Calculo_Info);

        Operador_Pool_HFC.add(Operador_Pool_HFC_Calculo);

        Operador_Pool_HFC_FA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activia.png"))); // NOI18N
        Operador_Pool_HFC_FA.setText("Fora ACTIVIA");
        Operador_Pool_HFC_FA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_HFC_FAActionPerformed(evt);
            }
        });
        Operador_Pool_HFC.add(Operador_Pool_HFC_FA);

        Operador_Pool_HFC_Pendencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_error.png"))); // NOI18N
        Operador_Pool_HFC_Pendencia.setText("Pendência");
        Operador_Pool_HFC_Pendencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_HFC_PendenciaActionPerformed(evt);
            }
        });
        Operador_Pool_HFC.add(Operador_Pool_HFC_Pendencia);

        Operador_Pool.add(Operador_Pool_HFC);

        Operador_Pool_DTH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/claro-novo-logo-vector-200x200.png"))); // NOI18N
        Operador_Pool_DTH.setText("DTH");

        Operador_Pool_DTH_ForaActivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activia.png"))); // NOI18N
        Operador_Pool_DTH_ForaActivia.setText("Fora Activia");
        Operador_Pool_DTH_ForaActivia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_DTH_ForaActiviaActionPerformed(evt);
            }
        });
        Operador_Pool_DTH.add(Operador_Pool_DTH_ForaActivia);

        Operador_Pool_DTH_Pendencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_error.png"))); // NOI18N
        Operador_Pool_DTH_Pendencia.setText("Pendências");
        Operador_Pool_DTH_Pendencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_DTH_PendenciaActionPerformed(evt);
            }
        });
        Operador_Pool_DTH.add(Operador_Pool_DTH_Pendencia);

        Operador_Pool_DTH_MRFechada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop_closed.png"))); // NOI18N
        Operador_Pool_DTH_MRFechada.setText("MR Fechada");
        Operador_Pool_DTH_MRFechada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_DTH_MRFechadaActionPerformed(evt);
            }
        });
        Operador_Pool_DTH.add(Operador_Pool_DTH_MRFechada);

        Operador_Pool.add(Operador_Pool_DTH);

        Operador_Pool_Escala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_view_columns.png"))); // NOI18N
        Operador_Pool_Escala.setText("Escala de Técnicos");
        Operador_Pool_Escala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_EscalaActionPerformed(evt);
            }
        });
        Operador_Pool.add(Operador_Pool_Escala);

        jMenuBar1.add(Operador_Pool);

        jmn_gpon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_tab_search.png"))); // NOI18N
        jmn_gpon.setText("GPON");

        jmn_gpon_pool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clipboard_invoice.png"))); // NOI18N
        jmn_gpon_pool.setText("POOL (Cabo)");

        jmn_gpon_pool_forafield.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_form_delete.png"))); // NOI18N
        jmn_gpon_pool_forafield.setText("Fora Field");
        jmn_gpon_pool_forafield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmn_gpon_pool_forafieldActionPerformed(evt);
            }
        });
        jmn_gpon_pool.add(jmn_gpon_pool_forafield);

        jmn_gpon_pool_pendencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_error.png"))); // NOI18N
        jmn_gpon_pool_pendencias.setText("Pendências");
        jmn_gpon_pool_pendencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmn_gpon_pool_pendenciasActionPerformed(evt);
            }
        });
        jmn_gpon_pool.add(jmn_gpon_pool_pendencias);

        jmn_gpon.add(jmn_gpon_pool);

        jmn_gpon_escala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_view_columns.png"))); // NOI18N
        jmn_gpon_escala.setText("Escala de Técnicos");
        jmn_gpon_escala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmn_gpon_escalaActionPerformed(evt);
            }
        });
        jmn_gpon.add(jmn_gpon_escala);

        jMenuBar1.add(jmn_gpon);

        Supervisor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_tab_search.png"))); // NOI18N
        Supervisor.setText("Supervisor");

        Supervisor_Pool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clipboard_invoice.png"))); // NOI18N
        Supervisor_Pool.setText("Pool CABO/HFC");

        Supervisor_Pool_ForaField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_form_delete.png"))); // NOI18N
        Supervisor_Pool_ForaField.setText("Acomp. Fora Field");
        Supervisor_Pool_ForaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Pool_ForaFieldActionPerformed(evt);
            }
        });
        Supervisor_Pool.add(Supervisor_Pool_ForaField);

        Supervisor_PoolDesco_ForaField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        Supervisor_PoolDesco_ForaField.setText("Acomp. Fora Field (Desco)");
        Supervisor_PoolDesco_ForaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_PoolDesco_ForaFieldActionPerformed(evt);
            }
        });
        Supervisor_Pool.add(Supervisor_PoolDesco_ForaField);

        Supervisor_Pool_ForaActivia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activia.png"))); // NOI18N
        Supervisor_Pool_ForaActivia.setText("Acomp. Fora Activia");
        Supervisor_Pool_ForaActivia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Pool_ForaActiviaActionPerformed(evt);
            }
        });
        Supervisor_Pool.add(Supervisor_Pool_ForaActivia);

        Supervisor_Pool_ForaActivia_DTH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/claro-novo-logo-vector-200x200.png"))); // NOI18N
        Supervisor_Pool_ForaActivia_DTH.setText("Acomp. Fora Activia DTH");
        Supervisor_Pool_ForaActivia_DTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Pool_ForaActivia_DTHActionPerformed(evt);
            }
        });
        Supervisor_Pool.add(Supervisor_Pool_ForaActivia_DTH);

        Supervisor_Pool_Pendencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alarm_bell.png"))); // NOI18N
        Supervisor_Pool_Pendencia.setText("Pendencias NETSMS");
        Supervisor_Pool_Pendencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Pool_PendenciaActionPerformed(evt);
            }
        });
        Supervisor_Pool.add(Supervisor_Pool_Pendencia);

        Operador_Pool_DTH_MRFechada1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shop_closed.png"))); // NOI18N
        Operador_Pool_DTH_MRFechada1.setText("Acomp. MR Fechada");
        Operador_Pool_DTH_MRFechada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Operador_Pool_DTH_MRFechada1ActionPerformed(evt);
            }
        });
        Supervisor_Pool.add(Operador_Pool_DTH_MRFechada1);

        Supervisor.add(Supervisor_Pool);

        Supervisor_Config_OS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cog.png"))); // NOI18N
        Supervisor_Config_OS.setText("Config OS");
        Supervisor_Config_OS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Config_OSActionPerformed(evt);
            }
        });
        Supervisor.add(Supervisor_Config_OS);

        Supervisor_Garantia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/safe.png"))); // NOI18N
        Supervisor_Garantia.setText("Garantia");

        Supervisor_Garantia_ForaField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_form_delete.png"))); // NOI18N
        Supervisor_Garantia_ForaField.setText("Acomp. Fora Field");
        Supervisor_Garantia_ForaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Garantia_ForaFieldActionPerformed(evt);
            }
        });
        Supervisor_Garantia.add(Supervisor_Garantia_ForaField);

        Supervisor_Garantia_Pendencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alarm_bell.png"))); // NOI18N
        Supervisor_Garantia_Pendencia.setText("Pendencias NETSMS");
        Supervisor_Garantia_Pendencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supervisor_Garantia_PendenciaActionPerformed(evt);
            }
        });
        Supervisor_Garantia.add(Supervisor_Garantia_Pendencia);

        Supervisor.add(Supervisor_Garantia);

        jMenuBar1.add(Supervisor);

        jmn_geral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/computer.png"))); // NOI18N
        jmn_geral.setText("Geral");

        jmni_geral_loc_par_tec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png"))); // NOI18N
        jmni_geral_loc_par_tec.setText("Localizar parceiras ou técnicos");
        jmni_geral_loc_par_tec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmni_geral_loc_par_tecActionPerformed(evt);
            }
        });
        jmn_geral.add(jmni_geral_loc_par_tec);

        jmni_geral_loc_un.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifier.png"))); // NOI18N
        jmni_geral_loc_un.setText("Localizar UNs");
        jmni_geral_loc_un.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmni_geral_loc_unActionPerformed(evt);
            }
        });
        jmn_geral.add(jmni_geral_loc_un);
        jmn_geral.add(jsep_geral_001);

        jmni_geral_cadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/application_view_columns.png"))); // NOI18N
        jmni_geral_cadastros.setText("Cadastros");

        jmni_geral_cadastros_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group_add.png"))); // NOI18N
        jmni_geral_cadastros_usuarios.setText("Usuários");
        jmni_geral_cadastros_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmni_geral_cadastros_usuariosActionPerformed(evt);
            }
        });
        jmni_geral_cadastros.add(jmni_geral_cadastros_usuarios);

        jmn_geral.add(jmni_geral_cadastros);

        jMenuBar1.add(jmn_geral);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Supervisor_Garantia_PendenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Garantia_PendenciaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_Pendencia_NETSMS pendencia_panel = new jPanel_Pendencia_NETSMS(Menu00, "GARANTIA");
            this.jTabbedPane1.addTab("[ SUPERVISOR GARANTIA ] - Pendências NETSMS", pendencia_panel);
            jTabbedPane1.setSelectedComponent(pendencia_panel);

        }

    }//GEN-LAST:event_Supervisor_Garantia_PendenciaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        //this.setVisible(false);
        //global_class.trayIcon.displayMessage("WorkStation BCC", "Oi, ainda continuo trabalhando aqui embaixo, OK?!", TrayIcon.MessageType.INFO);

    }//GEN-LAST:event_formWindowClosing

    private void Operador_Garantia_ForaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Garantia_ForaFieldActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaField forafield = new jPanel_ForaField(Menu00, "GARANTIA", "OPERADOR");
            this.jTabbedPane1.addTab("[ GARANTIA ] - Fora Field", forafield);
            jTabbedPane1.setSelectedComponent(forafield);

        }
    }//GEN-LAST:event_Operador_Garantia_ForaFieldActionPerformed

    private void Supervisor_Garantia_ForaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Garantia_ForaFieldActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaField forafieldsupervisor = new jPanel_ForaField(Menu00, "GARANTIA", "SUPERVISOR");
            this.jTabbedPane1.addTab("[ SUPERVISOR GARANTIA ] - Acomp Fora Field", forafieldsupervisor);
            jTabbedPane1.setSelectedComponent(forafieldsupervisor);

        }

    }//GEN-LAST:event_Supervisor_Garantia_ForaFieldActionPerformed

    private void Operador_Garantia_RiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Garantia_RiscoActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            try {
                //conn = global_class.mysql_con();
                conn = Db_class.mysql_conn();
                ResultSet att = null;
                att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

                att.next();
                att_risco = att.getBoolean(1);

                if (!att_risco) {

                    jPanel_Risco_Pendencia risco = new jPanel_Risco_Pendencia(Menu00, "GARANTIA");
                    this.jTabbedPane1.addTab("[ GARANTIA ] - Risco TEC1", risco);
                    jTabbedPane1.setSelectedComponent(risco);

                } else {

                    String msg = "<html><body><p width='250px'>O Risco TEC1 esta em processo de atualização! \nTente novamente após HH20 ou HH40.";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);

                }

                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }

    }//GEN-LAST:event_Operador_Garantia_RiscoActionPerformed

    private void Supervisor_Config_OSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Config_OSActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            if (!garantia_config_os) {

                jPanel_garantia_ConfigOS config = new jPanel_garantia_ConfigOS(Menu00);
                this.jTabbedPane1.addTab("Configurações de OS", config);
                jTabbedPane1.setSelectedComponent(config);
                garantia_config_os = true;

            } else {

                String msg = "<html><body><p width='170px'>Já existe uma guia aberta!";
                global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

            }
        }

    }//GEN-LAST:event_Supervisor_Config_OSActionPerformed

    private void Supervisor_Pool_PendenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Pool_PendenciaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_Pendencia_NETSMS pendencia_panel = new jPanel_Pendencia_NETSMS(Menu00, "POOL");
            this.jTabbedPane1.addTab("[ SUPERVISOR POOL ] - Pendências NETSMS", pendencia_panel);
            jTabbedPane1.setSelectedComponent(pendencia_panel);

        }

    }//GEN-LAST:event_Supervisor_Pool_PendenciaActionPerformed

    private void Operador_Pool_ForaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_ForaFieldActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaField forafield = new jPanel_ForaField(Menu00, "POOL", "OPERADOR");
            this.jTabbedPane1.addTab("[ POOL ] - Fora Field", forafield);
            jTabbedPane1.setSelectedComponent(forafield);

        }

    }//GEN-LAST:event_Operador_Pool_ForaFieldActionPerformed

    private void Operador_Pool_RiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_RiscoActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            try {
                //conn = global_class.mysql_con();
                conn = Db_class.mysql_conn();
                ResultSet att = null;
                att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

                att.next();
                att_risco = att.getBoolean(1);

                if (!att_risco) {

                    jPanel_Risco_Pendencia risco = new jPanel_Risco_Pendencia(Menu00, "POOL");
                    this.jTabbedPane1.addTab("[ POOL ] - Pendências", risco);
                    jTabbedPane1.setSelectedComponent(risco);

                } else {

                    String msg = "<html><body><p width='250px'>Pendências POOL esta em processo de atualização! \nTente novamente após HH20 ou HH40.";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);

                }

                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }

    }//GEN-LAST:event_Operador_Pool_RiscoActionPerformed

    private void Supervisor_Pool_ForaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Pool_ForaFieldActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaField forafieldsupervisor = new jPanel_ForaField(Menu00, "POOL", "SUPERVISOR");
            this.jTabbedPane1.addTab("[ SUPERVISOR POOL ] - Acomp Fora Field", forafieldsupervisor);
            jTabbedPane1.setSelectedComponent(forafieldsupervisor);

        }

    }//GEN-LAST:event_Supervisor_Pool_ForaFieldActionPerformed

    private void Operador_Pool_HFC_ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_HFC_ImportActionPerformed

        chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione o arquivo - Importar Arquivo");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("csv files (*.csv)", "csv");
        chooser.setFileFilter(csvfilter);
        chooser.addChoosableFileFilter(csvfilter);

        File current = new File(System.getProperty("user.home") + "\\Desktop\\");
        chooser.setCurrentDirectory(current);

        int a = chooser.showOpenDialog(this);

        if (a == JFileChooser.APPROVE_OPTION) {

            arq = chooser.getSelectedFile();
            path_arq = arq.getAbsolutePath();
            name_arq = arq.getName();

            long teste = arq.lastModified();
            SimpleDateFormat hr = new SimpleDateFormat("HH");
            SimpleDateFormat day = new SimpleDateFormat("DD");

            String hr_arq = hr.format(new Date(teste));
            String hr_atual = hr.format(new Date());

            String day_arq = day.format(new Date(teste));
            String day_atual = day.format(new Date());

            if (day_arq.equals(day_atual)) {

                if (hr_arq.equals(hr_atual)) {

                    try (BufferedReader br = new BufferedReader(new FileReader(path_arq))) {

                        line = br.readLine();
                        cabecalho_arq = line.split(";");

                        for (String titulo : cabecalho_arq) {

                            if (Arrays.asList(cabecalho_padrao).contains(titulo)) {

                                ler_arq = true;

                            } else {

                                ler_arq = false;
                                break;

                            }

                        }

                        if (ler_arq) {
                            Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
                            setCursor(hourglassCursor);

                            String query_import = "LOAD DATA LOCAL INFILE '" + path_arq.replace("\\", "//")
                                    + "' INTO TABLE workstation_bcc.tratar_activia_hfc  "
                                    + "CHARACTER SET 'latin1' "
                                    + "FIELDS TERMINATED BY ';' "
                                    + "OPTIONALLY ENCLOSED BY '\"' "
                                    + "LINES TERMINATED BY '\\n'  "
                                    + "IGNORE 1 LINES "
                                    + "(@ignore,status,cidade,@ignore,@ignore,@ignore,@ignore,@ignore,contrato_os,contrato,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,@ignore,periodo,@ignore,credenciada,@ignore)";

                            String query_parcs = "INSERT IGNORE INTO workstation_bcc.tbl_un_copinfo (NM_CIDADE, NM_UN, ATIVIDADE)"
                                    + "SELECT "
                                    + "    agendadas.CIDADE, hfc.credenciada, 'POOL_HFC' "
                                    + "FROM "
                                    + "    workstation_bcc.tratar_os_agend_netsms_index agendadas "
                                    + "        INNER JOIN "
                                    + "    workstation_bcc.tratar_activia_hfc hfc ON agendadas.CONTRATO = hfc.contrato "
                                    + "        AND agendadas.COD_OS = hfc.contrato_os "
                                    + "WHERE "
                                    + "    hfc.credenciada <> '' "
                                    + "GROUP BY agendadas.CIDADE , hfc.credenciada";

                            String query_cid = "UPDATE workstation_bcc.tbl_un_copinfo uns "
                                    + "  INNER JOIN "
                                    + "  (SELECT NM_CIDADE, ID_CIDADE FROM workstation_bcc.tbl_un_copinfo tbl_un WHERE ID_CIDADE IS NOT NULL GROUP BY NM_CIDADE, ID_CIDADE) tbl_join "
                                    + "  ON uns.NM_CIDADE = tbl_join.NM_CIDADE "
                                    + "  SET uns.ID_CIDADE = tbl_join.ID_CIDADE "
                                    + "  WHERE uns.ID_CIDADE IS NULL";

                            try {
                                //conn = global_class.mysql_con();
                                conn = Db_class.mysql_conn();
                                global_class.mysql_insert(conn, "TRUNCATE TABLE workstation_bcc.tratar_activia_hfc");
                                global_class.mysql_insert(conn, query_import);
                                global_class.mysql_insert(conn, query_parcs);
                                global_class.mysql_insert(conn, query_cid);
                                global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_controle_att SET HORA_ARQ_HFC = '" + hr_arq + "' WHERE ID = 1");
                                global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_controle_att SET ATUALIZANDO_RISCO_HFC = 'SIM' WHERE ID = 1");
                                global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_controle_att SET ATUALIZANDO_FA_HFC = 'SIM' WHERE ID = 1");
                                Db_class.close_conn(conn);

                                String msg = "<html><body><p width='200px'>Arquivo importado com sucesso!";
                                Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                                global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);

                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                                try {
                                    Db_class.close_conn(conn);
                                } catch (Exception ex0) {
                                }
                                Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                                setCursor(normalCursor);
                                global_class.msg_dialog("Problemas com a importação do arquivo HFC.\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                            }
                            Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                            setCursor(normalCursor);

                        } else {

                            String msg = "<html><body><p width='250px'>Selecionar o arquivo correto para importação.\nPadrão => AAAA-MM-DD-HHhMMm_Net_Relatorio_OS!";
                            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                        }

                    } catch (IOException e) {
                    }

                } else {

                    String msg = "<html><body><p width='250px'>O horario do arquivo não é o da hora atual. Favor verificar!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                }
            } else {

                String msg = "<html><body><p width='250px'>A data do arquivo não é de hoje. Favor verificar!";
                global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }//GEN-LAST:event_Operador_Pool_HFC_ImportActionPerformed

    private void Operador_Pool_HFC_Calculo_InfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_HFC_Calculo_InfoActionPerformed

        String hr_arq_net = "";
        String hr_arq_hfc = "";

        Boolean lb_erro = false;

        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else {
            setCursor(hourglassCursor);
            conn = null;
            try {
                //conn = global_class.mysql_con();
                conn = Db_class.mysql_conn();
                ResultSet pend = null;
                ResultSet arqs = null;

                pend = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_PEND='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");
                arqs = global_class.mysql_result(conn, "SELECT HORA_ARQ_HFC, HORA_ARQ_NET FROM workstation_bcc.tbl_controle_att");

                pend.next();
                arqs.next();

                att_pend_hfc = pend.getBoolean(1);
                hr_arq_hfc = arqs.getString(1);
                hr_arq_net = arqs.getString(2);

                Db_class.close_conn(conn);
                setCursor(normalCursor);

            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(scr_Menu.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Db_class.close_conn(conn);
                } catch (Exception ex0) {
                }
                setCursor(normalCursor);
                global_class.msg_dialog("Não foi possível verificar se o processo de cálculo de pendência foi concluído.\n\nO processo de cálculo não pode ser executado.\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                lb_erro = true;
            }

            if (!lb_erro) {
                if (!att_pend_hfc) {
                    if (hr_arq_hfc.equals(hr_arq_net)) {
                        //conn = null;
                        setCursor(hourglassCursor);
                        try {
                            String[] sComandos = {
                                "CALL workstation_bcc.proc_calc_risco_hfc_00()",
                                "CALL workstation_bcc.proc_calc_forafield_n_tratados_hfc()",
                                "CALL workstation_bcc.proc_calc_fora_activia_00()",
                                "UPDATE workstation_bcc.tbl_controle_att SET ATUALIZANDO_RISCO_HFC = 'NAO' WHERE ID = 1",
                                "UPDATE workstation_bcc.tbl_controle_att SET ATUALIZANDO_FA_HFC = 'NAO' WHERE ID = 1"};

                            for (int i = 0; i < sComandos.length; i++) {
                                conn = null;
                                //conn = global_class.mysql_con();
                                conn = Db_class.mysql_conn();
                                if (conn != null) {
                                    global_class.mysql_insert(conn, sComandos[i]);
                                    //System.out.println(sComandos[i]);
                                    Db_class.close_conn(conn);
                                } else {
                                    lb_erro = true;
                                    break;
                                }
                            }
                            setCursor(normalCursor);

                            /*
                             conn = global_class.mysql_con();
                             if (conn != null) {
                             global_class.mysql_insert(conn, "CALL workstation_bcc.proc_calc_risco_hfc_00()");
                             global_class.mysql_insert(conn, "CALL workstation_bcc.proc_calc_forafield_n_tratados_hfc()");
                             global_class.mysql_insert(conn, "CALL workstation_bcc.proc_calc_fora_activia_00()");
                             global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_controle_att SET ATUALIZANDO_RISCO_HFC = 'NAO' WHERE ID = 1");
                             global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_controle_att SET ATUALIZANDO_FA_HFC = 'NAO' WHERE ID = 1");
                             Db_class.close_conn(conn);
                             } else {
                             lb_erro = true;
                             }
                             */
                        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                            Logger.getLogger(scr_Menu.class.getName()).log(Level.SEVERE, null, ex);
                            try {
                                Db_class.close_conn(conn);
                            } catch (Exception ex0) {
                            }
                            setCursor(normalCursor);
                            global_class.msg_dialog("Problemas com o cálculo HFC.\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                            lb_erro = true;
                        }

                        if (!lb_erro) {
                            String msg = "<html><body><p width='200px'>Cálculo realizado com sucesso!";
                            Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("/images/apply.png")));
                            global_class.msg_dialog_icone(msg, JOptionPane.INFORMATION_MESSAGE, (ImageIcon) figura);
                        }
                    } else {
                        String msg = "<html><body><p width='250px'>O horário dos arquivos HFC e NET não são iguais.\n\nVerifique se importou o arquivo ACTIVIA correto ou aguarde atualização do arquivo NETSMS.";
                        global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {

                    String msg = "<html><body><p width='250px'>Atualização das informações do NETSMS em andamento.\nTente novamente em 2 minutos.";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }

    }//GEN-LAST:event_Operador_Pool_HFC_Calculo_InfoActionPerformed

    private void Operador_Pool_HFC_FAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_HFC_FAActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaActivia forafield = new jPanel_ForaActivia(Menu00, "POOL_HFC", "OPERADOR", true);
            this.jTabbedPane1.addTab("[ POOL HFC ] - Fora Activia", forafield);
            jTabbedPane1.setSelectedComponent(forafield);
        }

    }//GEN-LAST:event_Operador_Pool_HFC_FAActionPerformed

    private void Operador_Pool_HFC_PendenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_HFC_PendenciaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema.\nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            try {
                //conn = global_class.mysql_con();
                conn = Db_class.mysql_conn();
                ResultSet att = null;
                att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO_HFC='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

                att.next();
                att_risco_hfc = att.getBoolean(1);

                if (!att_risco_hfc) {

                    jPanel_Pendencia_HFC risco = new jPanel_Pendencia_HFC(Menu00, "POOL_HFC", true);
                    this.jTabbedPane1.addTab("[ POOL HFC ] - Pendências", risco);
                    jTabbedPane1.setSelectedComponent(risco);

                } else {

                    String msg = "<html><body><p width='250px'>Pendências POOL esta em processo de atualização!\nTente novamente em alguns minutos.";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);

                }

                Db_class.close_conn(conn);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }

    }//GEN-LAST:event_Operador_Pool_HFC_PendenciaActionPerformed

    private void Operador_Pool_DTH_ForaActiviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_DTH_ForaActiviaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaActiviaDTH forafield = new jPanel_ForaActiviaDTH(Menu00, true, "OPERADOR", "PENDENTE");
            this.jTabbedPane1.addTab("[ POOL DTH ] - Fora Activia", forafield);
            jTabbedPane1.setSelectedComponent(forafield);

        }

    }//GEN-LAST:event_Operador_Pool_DTH_ForaActiviaActionPerformed

    private void Operador_Pool_DTH_PendenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_DTH_PendenciaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_Pendencia_DTH pend_dth = new jPanel_Pendencia_DTH(Menu00, true);
            this.jTabbedPane1.addTab("[ POOL DTH ] - Pendências", pend_dth);
            jTabbedPane1.setSelectedComponent(pend_dth);

        }

    }//GEN-LAST:event_Operador_Pool_DTH_PendenciaActionPerformed

    private void Operador_Garantia_EscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Garantia_EscalaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_Escala_Tecnica escala = new jPanel_Escala_Tecnica(Menu00, "GARANTIA");
            this.jTabbedPane1.addTab("[ GARANTIA ] - Escala de Técnicos", escala);
            jTabbedPane1.setSelectedComponent(escala);

        }

    }//GEN-LAST:event_Operador_Garantia_EscalaActionPerformed

    private void Supervisor_Pool_ForaActiviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Pool_ForaActiviaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaActivia forafieldsupervisor = new jPanel_ForaActivia(Menu00, "POOL_HFC", "SUPERVISOR", true);
            this.jTabbedPane1.addTab("[ SUPERVISOR POOL HFC ] - Acomp Fora Activia", forafieldsupervisor);
            jTabbedPane1.setSelectedComponent(forafieldsupervisor);

        }
    }//GEN-LAST:event_Supervisor_Pool_ForaActiviaActionPerformed

    private void Supervisor_Pool_ForaActivia_DTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_Pool_ForaActivia_DTHActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaActiviaDTH forafieldsupervisor = new jPanel_ForaActiviaDTH(Menu00, true, "SUPERVISOR", "POOL_DTH");
            this.jTabbedPane1.addTab("[ SUPERVISOR POOL DTH ] - Acomp Fora Activia", forafieldsupervisor);
            jTabbedPane1.setSelectedComponent(forafieldsupervisor);

        }

    }//GEN-LAST:event_Supervisor_Pool_ForaActivia_DTHActionPerformed

    private void Operador_Pool_DTH_MRFechadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_DTH_MRFechadaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_MRFechada mr_fechada = new jPanel_MRFechada(Menu00, "POOL_HFC", "OPERADOR");
            this.jTabbedPane1.addTab("[ POOL DTH ] - MR Fechada", mr_fechada);
            jTabbedPane1.setSelectedComponent(mr_fechada);

        }

    }//GEN-LAST:event_Operador_Pool_DTH_MRFechadaActionPerformed

    private void Operador_Pool_DTH_MRFechada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_DTH_MRFechada1ActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_MRFechada mr_fechada = new jPanel_MRFechada(Menu00, "POOL_HFC", "SUPERVISOR");
            this.jTabbedPane1.addTab("[ SUPERVISOR POOL DTH ] - MR Fechada", mr_fechada);
            jTabbedPane1.setSelectedComponent(mr_fechada);

        }

    }//GEN-LAST:event_Operador_Pool_DTH_MRFechada1ActionPerformed

    private void Operador_Pool_EscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_EscalaActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_Escala_Tecnica escala = new jPanel_Escala_Tecnica(Menu00, "POOL");
            this.jTabbedPane1.addTab("[ POOL ] - Escala de Técnicos", escala);
            jTabbedPane1.setSelectedComponent(escala);

        }

    }//GEN-LAST:event_Operador_Pool_EscalaActionPerformed

    private void jmni_geral_cadastros_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmni_geral_cadastros_usuariosActionPerformed
        // TODO add your handling code here:
        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        cd_usuario cad_usuario = new cd_usuario(this, true);
    }//GEN-LAST:event_jmni_geral_cadastros_usuariosActionPerformed

    private void jmni_geral_loc_par_tecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmni_geral_loc_par_tecActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (loc_par_tec == null) {
            loc_par_tec = new localiza_parceiro_tecnico(this, false);
        } else {
            loc_par_tec.setVisible(true);
            loc_par_tec.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - loc_par_tec.getWidth() / 2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - loc_par_tec.getHeight() / 2);

        }

    }//GEN-LAST:event_jmni_geral_loc_par_tecActionPerformed

    private void Operador_PoolDesco_ForaFielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_PoolDesco_ForaFielActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaField forafield = new jPanel_ForaField(Menu00, "POOL_DESCO", "OPERADOR");
            this.jTabbedPane1.addTab("[ POOL Desco ] - Fora Field", forafield);
            jTabbedPane1.setSelectedComponent(forafield);

        }

    }//GEN-LAST:event_Operador_PoolDesco_ForaFielActionPerformed

    private void Supervisor_PoolDesco_ForaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supervisor_PoolDesco_ForaFieldActionPerformed

        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_ForaField forafield = new jPanel_ForaField(Menu00, "POOL_DESCO", "SUPERVISOR");
            this.jTabbedPane1.addTab("[ SUPERVISOR POOL Desco ] - Acomp Fora Field", forafield);
            jTabbedPane1.setSelectedComponent(forafield);

        }

    }//GEN-LAST:event_Supervisor_PoolDesco_ForaFieldActionPerformed

    private void jmni_geral_loc_unActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmni_geral_loc_unActionPerformed
        // TODO add your handling code here:
        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (loc_un == null) {
            loc_un = new localiza_un(this, false);
        } else {
            loc_un.setVisible(true);
            loc_un.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - loc_un.getWidth() / 2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - loc_un.getHeight() / 2);

        }
    }//GEN-LAST:event_jmni_geral_loc_unActionPerformed

    private void jmn_gpon_escalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmn_gpon_escalaActionPerformed

        if (!global_class.check_version()) {
            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
        } else if (global_class.check_version()) {
            jPanel_Escala_Tecnica escala = new jPanel_Escala_Tecnica(Menu00, "GPON");
            this.jTabbedPane1.addTab("[ GPON ] - Escala de Técnicos", escala);
            jTabbedPane1.setSelectedComponent(escala);
        }

    }//GEN-LAST:event_jmn_gpon_escalaActionPerformed

    private void jmn_gpon_pool_forafieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmn_gpon_pool_forafieldActionPerformed

        if (!global_class.check_version()) {
            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
        } else if (global_class.check_version()) {
            jPanel_ForaField forafield = new jPanel_ForaField(Menu00, "GPON", "OPERADOR");
            this.jTabbedPane1.addTab("[ GPON ] - Fora Field", forafield);
            jTabbedPane1.setSelectedComponent(forafield);
        }

    }//GEN-LAST:event_jmn_gpon_pool_forafieldActionPerformed

    private void jmn_gpon_pool_pendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmn_gpon_pool_pendenciasActionPerformed

        if (!global_class.check_version()) {
            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
        } else if (global_class.check_version()) {
            try {
                //conn = global_class.mysql_con();
                conn = Db_class.mysql_conn();
                ResultSet att = null;
                att = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_RISCO='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");

                att.next();
                att_risco = att.getBoolean(1);

                if (!att_risco) {
                    jPanel_Risco_Pendencia risco = new jPanel_Risco_Pendencia(Menu00, "GPON");
                    this.jTabbedPane1.addTab("[ GPON ] - Pendências", risco);
                    jTabbedPane1.setSelectedComponent(risco);
                } else {
                    String msg = "<html><body><p width='250px'>Pendências POOL esta em processo de atualização! \nTente novamente após HH20 ou HH40.";
                    global_class.msg_dialog(msg, JOptionPane.ERROR_MESSAGE);
                }

                Db_class.close_conn(conn);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_Pendencia_NETSMS.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }
        }

    }//GEN-LAST:event_jmn_gpon_pool_pendenciasActionPerformed

    private void Operador_Pool_RetornoCredenciadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Operador_Pool_RetornoCredenciadaActionPerformed
        if (!global_class.check_version()) {

            String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

        } else if (global_class.check_version()) {

            jPanel_Retprev retornocredenciada = new jPanel_Retprev(Menu00, "POOL", "OPERADOR");
            this.jTabbedPane1.addTab("[ POOL ] - Retorno Credenciada", retornocredenciada);
            jTabbedPane1.setSelectedComponent(retornocredenciada);

    }//GEN-LAST:event_Operador_Pool_RetornoCredenciadaActionPerformed
    }

    public void pega_hora_att_pend() {

        try {

            //conn = global_class.mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet att = null;
            att = global_class.mysql_result(conn, "SELECT IFNULL(DATE_FORMAT(MAX(DT_UPDATE),'%d/%m/%Y %H:%i'),'') AS 'DATA' FROM workstation_bcc.tbl_pendencia");
            att.next();
            ult_att_pend = (String) att.getObject(1);
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(scr_Menu.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

    public void pega_hora_att_ff() {

        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);

        setCursor(hourglassCursor);

        try {
            /*
             //metodo antigo
             garantia_ult_att_ff = global_class.dh_update_ff("GARANTIA");
             pool_ult_att_ff = global_class.dh_update_ff("POOL");
             pool_ult_att_fa = global_class.dh_update_ff("POOL_HFC");
             */

            //metodo novo
            global_class.msg_dialog("entrei", JOptionPane.INFORMATION_MESSAGE);
            String[] sAtividades = global_class.dh_update_ff("").split(";");
            if (sAtividades.length >= 3) {
                garantia_ult_att_ff = sAtividades[0];
                pool_ult_att_ff = sAtividades[1];
                pool_ult_att_fa = sAtividades[2];
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(scr_Menu.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

        setCursor(normalCursor);

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(scr_Menu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

                    Thread.sleep(4500);

                    new scr_Menu().setVisible(true);

                } catch (InterruptedException ex) {
                    Logger.getLogger(scr_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Operador_Garantia;
    private javax.swing.JMenuItem Operador_Garantia_Escala;
    private javax.swing.JMenuItem Operador_Garantia_ForaField;
    private javax.swing.JMenuItem Operador_Garantia_Risco;
    private javax.swing.JMenu Operador_Pool;
    private javax.swing.JMenuItem Operador_PoolDesco_ForaFiel;
    private javax.swing.JMenu Operador_Pool_Cabo;
    private javax.swing.JMenu Operador_Pool_DTH;
    private javax.swing.JMenuItem Operador_Pool_DTH_ForaActivia;
    private javax.swing.JMenuItem Operador_Pool_DTH_MRFechada;
    private javax.swing.JMenuItem Operador_Pool_DTH_MRFechada1;
    private javax.swing.JMenuItem Operador_Pool_DTH_Pendencia;
    private javax.swing.JMenuItem Operador_Pool_Escala;
    private javax.swing.JMenuItem Operador_Pool_ForaField;
    private javax.swing.JMenu Operador_Pool_HFC;
    private javax.swing.JMenu Operador_Pool_HFC_Calculo;
    private javax.swing.JMenuItem Operador_Pool_HFC_Calculo_Info;
    private javax.swing.JMenuItem Operador_Pool_HFC_FA;
    private javax.swing.JMenuItem Operador_Pool_HFC_Import;
    private javax.swing.JMenuItem Operador_Pool_HFC_Pendencia;
    private javax.swing.JMenuItem Operador_Pool_RetornoCredenciada;
    private javax.swing.JMenuItem Operador_Pool_Risco;
    private javax.swing.JMenu Supervisor;
    private javax.swing.JMenuItem Supervisor_Config_OS;
    private javax.swing.JMenu Supervisor_Garantia;
    private javax.swing.JMenuItem Supervisor_Garantia_ForaField;
    private javax.swing.JMenuItem Supervisor_Garantia_Pendencia;
    private javax.swing.JMenu Supervisor_Pool;
    private javax.swing.JMenuItem Supervisor_PoolDesco_ForaField;
    private javax.swing.JMenuItem Supervisor_Pool_ForaActivia;
    private javax.swing.JMenuItem Supervisor_Pool_ForaActivia_DTH;
    private javax.swing.JMenuItem Supervisor_Pool_ForaField;
    private javax.swing.JMenuItem Supervisor_Pool_Pendencia;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu jmn_geral;
    private javax.swing.JMenu jmn_gpon;
    private javax.swing.JMenuItem jmn_gpon_escala;
    private javax.swing.JMenu jmn_gpon_pool;
    private javax.swing.JMenuItem jmn_gpon_pool_forafield;
    private javax.swing.JMenuItem jmn_gpon_pool_pendencias;
    private javax.swing.JMenu jmni_geral_cadastros;
    private javax.swing.JMenuItem jmni_geral_cadastros_usuarios;
    private javax.swing.JMenuItem jmni_geral_loc_par_tec;
    private javax.swing.JMenuItem jmni_geral_loc_un;
    private javax.swing.JPopupMenu.Separator jsep_geral_001;
    private javax.swing.JLabel label_user_logado;
    // End of variables declaration//GEN-END:variables
}
