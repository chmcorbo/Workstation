/*

 Connection ==> Abrir conexão mysql;
 ResultSet ==> Retornar resultado de query;
 mysql_insert ==> Query INSERT;
 close_con ==> Fechar conexão mysql;
 msg_dialog_icone ==> Mensagem com icone;
 msg_dialog ==> Mensagem;
 check_version ==> Verificar versão projeto;
 chama_versao ==> Chamada de verificador de versão;
 get_version ==> Pegar versão no mysql;
 get_color ==> Conversor de cores;
 get_table ==> Transformar resultado query em table;
 DefaultTableModel ==> Modelo de tabela;
 HtmlSelection ==> Manuseio de HTML;
 TrayIcon ==> Projeto na TrayIcon;

 ### importar bibliotecas java ###

 jacob-M2
 


 ###

 */
package classes;

//import static classes.WorkStationBCC.inicio;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import screens.scr_Menu;

public class global_class {

    Image iconImage2;
    public static File[] arqs = null;
    public String link;
    public static TrayIcon trayIcon;
    public static screens.scr_Menu mn;

    //public static String sVersaoFonte = "1.2017.11.03.1";
    //public static String sVersaoFonte = "1.2017.11.13.1";
    //public static String sVersaoFonte = "1.2017.11.27.1";
    //public static String sVersaoFonte = "1.2017.11.28.1";
    //public static String sVersaoFonte = "1.2017.12.22.1";
    //public static String sVersaoFonte = "1.2018.01.09.1";
    //public static String sVersaoFonte = "1.2018.01.26.1";
    //public static String sVersaoFonte = "1.2018.02.08.1";
    //public static String sVersaoFonte = "1.2018.02.20.1";
    //public static String sVersaoFonte = "1.2018.03.01.1";
    //public static String sVersaoFonte = "1.2018.03.13.1";
    //public static String sVersaoFonte = "1.2018.03.28.1";
    //public static String sVersaoFonte = "1.2018.07.04.1";
    //public static String sVersaoFonte = "1.2018.07.10.1";
    //public static String sVersaoFonte = "1.2018.10.04.4";
    //public static String sVersaoFonte = "1.2018.10.05.2";
    //public static String sVersaoFonte = "1.2018.10.18.1";
    //public static String sVersaoFonte = "1.2018.12.17.1"; //erro
    //public static String sVersaoFonte = "1.2018.12.17.2"; //erro
    //public static String sVersaoFonte = "1.2018.12.17.3"; //erro
    //public static String sVersaoFonte = "1.2018.12.17.4";
    //public static String sVersaoFonte = "1.2019.04.01.1";
    //public static String sVersaoFonte = "1.2019.04.08.1";
    //public static String sVersaoFonte = "1.2019.04.25.1";
    //public static String sVersaoFonte = "1.2019.06.26.1";
    //public static String sVersaoFonte = "1.2019.09.02.1";
    //public static String sVersaoFonte = "1.2019.09.12.1";
    public static String sVersaoFonte = "1.2019.09.16.1";

    public static String getsVersaoFonte() {
        return sVersaoFonte;
    }

    public static boolean check_version() {

        //String version = get_version();
        //return version.equals("3.2017.09.28.19");
        ////return version.equals("2.2017.08.21.16");
        /*
         //código dando erro por conta de objeto NULL (menu inicial)
         if (version.equals(getsVersaoFonte())) {
         inicio.setTitle("WorkStation BCC v." + global_class.getsVersaoFonte());
         } else {
         inicio.setTitle("WorkStation BCC v." + global_class.getsVersaoFonte() + " (esta versão não é a oficial)");
         }
         */
        return true; //forçando

    }

    /*
    public static Connection mysql_con() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        String url = "jdbc:mysql://10.5.16.86:3306/";
        //String url = "jdbc:mysql://NAMEDT269088.netservicos.corp:3306/";
        String dbName = "workstation_bcc";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "common_user";
        String password = "Pvm5jrCZWQcUPyhc";

        Connection conn = null;

        Class.forName(driver).newInstance();
        try {
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception ex) {
            msg_dialog("Não foi possível conectar ao banco de dados.\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conn = null;
        }
        return conn;
    }
    */

    public static ResultSet mysql_result(Connection conn, String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        java.sql.Statement st = conn.createStatement();
        ResultSet res = st.executeQuery(query);

        return res;
    }

    public static int mysql_insert(Connection conn, String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        Statement st = conn.createStatement();
        int res = st.executeUpdate(query);

        return res;

    }

    /*
    public static void close_con(Connection conn) {

        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }
    */

    public static void msg_dialog_icone(String info, int message_type, ImageIcon figura) {

        JOptionPane.showMessageDialog(null, info, "Mensagem", JOptionPane.PLAIN_MESSAGE, figura);

    }

    public static void msg_dialog(String info, int message_type) {

        JOptionPane.showMessageDialog(null, info, "Mensagem", JOptionPane.INFORMATION_MESSAGE);

    }

    public static String get_version() {

        String version_query = "SELECT VERSION FROM workstation_bcc.tbl_version";

        Connection conn = null;
        try {
            ResultSet rs1 = null;
            //conn = mysql_con();
            conn = Db_class.mysql_conn();
            rs1 = mysql_result(conn, version_query);
            String version = "";

            if (rs1.next()) {

                version = (String) rs1.getObject(1);

            }

            Db_class.close_conn(conn);

            return version;

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);

        }
        return "";
    }

    public static String data_ftp(Date data_pasta) {

        SimpleDateFormat fhour = new SimpleDateFormat("HH");
        SimpleDateFormat fdata = new SimpleDateFormat("yyyy-MM-dd");

        int hora = Integer.parseInt(fhour.format(new Date()));
        String data = "";

        if (hora >= 17) {

            data = fdata.format(new Date());

        } else if (hora <= 06) {

            data = fdata.format(new Date());

        } else {

            Calendar c = Calendar.getInstance();
            c.setTime(data_pasta);
            c.add(Calendar.DAY_OF_YEAR, -1);
            data = fdata.format(c.getTime());

        }

        return data;

    }

    public static Color get_color(int red, int green, int blue) {

        float[] hsb = Color.RGBtoHSB(red, green, blue, null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];

        Color cor = Color.getHSBColor(hue, saturation, brightness);

        return cor;

    }

    public static JTable getTable(String consulta) throws Exception {

        Connection conn;
        //conn = mysql_con();
        conn = Db_class.mysql_conn();
        ResultSet rs = null;

        rs = mysql_result(conn, consulta);

        JTable tab = new JTable(buildTableModel(rs));

        Db_class.close_conn(conn);

        return tab;

    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<Object> columnNames = new Vector<Object>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.addElement(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.addElement(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    }

    public static class HtmlSelection implements Transferable {

        private static ArrayList htmlFlavors = new ArrayList();

        static {

            try {

                htmlFlavors.add(new DataFlavor("text/html;class=java.lang.String"));
                htmlFlavors.add(new DataFlavor("text/html;class=java.io.Reader"));
                htmlFlavors.add(new DataFlavor("text/html;charset=unicode;class=java.io.InputStream"));

            } catch (ClassNotFoundException ex) {

                ex.printStackTrace();

            }

        }

        private String html;

        public HtmlSelection(String html) {

            this.html = html;

        }

        public DataFlavor[] getTransferDataFlavors() {

            return (DataFlavor[]) htmlFlavors.toArray(new DataFlavor[htmlFlavors.size()]);

        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {

            return htmlFlavors.contains(flavor);

        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {

            if (String.class.equals(flavor.getRepresentationClass())) {

                return html;

            } else if (Reader.class.equals(flavor.getRepresentationClass())) {

                return new StringReader(html);

            } else if (InputStream.class.equals(flavor.getRepresentationClass())) {

                return new StringBufferInputStream(html);

            }

            throw new UnsupportedFlavorException(flavor);

        }

    }

    public static void send_to(String html_code, String assunto, String email_para, String email_cc, String action) throws IOException {

        ActiveXComponent axcOutlook = new ActiveXComponent("Outlook.Application");
        Dispatch criacaoEmail = Dispatch.invoke(axcOutlook.getObject(), "CreateItem", Dispatch.Get,
                new Object[]{"0"}, new int[0]).toDispatch();

        //Object anexo1 = new Object();  
        //anexo1 = "D:\\Documents and Settings\\N0026925\\Desktop\\ocorr_tc5.xlsb";  
        Dispatch.put(criacaoEmail, "To", email_para);
        Dispatch.put(criacaoEmail, "CC", email_cc);
        Dispatch.put(criacaoEmail, "Subject", assunto);

        if (html_code.equals("")) {

        } else {

            Dispatch.put(criacaoEmail, "HTMLBody", html_code);

        }

        Dispatch.put(criacaoEmail, "ReadReceiptRequested", "false");

        //Dispatch attachs = Dispatch.get(criacaoEmail, "Attachments").toDispatch();
        //Dispatch.call(attachs, "Add", anexo1);  
        if (action.equals("exibir")) {

            Dispatch.call(criacaoEmail, "Display");

        } else if (action.equals("enviar")) {

            Dispatch.call(criacaoEmail, "Send");

        }

    }

    public static void send(String html_code, String assunto) {

        ActiveXComponent axcOutlook = new ActiveXComponent("Outlook.Application");
        Dispatch criacaoEmail = Dispatch.invoke(axcOutlook.getObject(), "CreateItem", Dispatch.Get,
                new Object[]{"0"}, new int[0]).toDispatch();

        //Object anexo1 = new Object();  
        //anexo1 = "D:\\Documents and Settings\\N0026925\\Desktop\\ocorr_tc5.xlsb";  
        //Dispatch.put(criacaoEmail, "To", email_para);
        Dispatch.put(criacaoEmail, "Subject", assunto);

        //Dispatch.put(criacaoEmail, "CC", cc);
        Dispatch.put(criacaoEmail, "HTMLBody", html_code);
        Dispatch.put(criacaoEmail, "ReadReceiptRequested", "false");

        //Dispatch attachs = Dispatch.get(criacaoEmail, "Attachments").toDispatch();
        //Dispatch.call(attachs, "Add", anexo1);  
        Dispatch.call(criacaoEmail, "Display");

    }

    //##### TrayIcon #####
    public global_class(screens.scr_Menu mn) {

        this.mn = mn;

        ShowTrayIcon();

    }

    public static void ShowTrayIcon() {

        if (!SystemTray.isSupported()) {

            System.out.println("Error, your pc sucks");
            System.exit(0);
            return;

        }

        //        URL novaurl = System.class.getResource("/cop_info/bd_st_cl.gif");
        //        Image iconImage2 = Toolkit.getDefaultToolkit().getImage(novaurl);
        final PopupMenu popup = new PopupMenu();
        trayIcon = new TrayIcon(CreateIcon("/images/computer.png", "WorkStation BCC"));
        final SystemTray tray = SystemTray.getSystemTray();

        trayIcon.setToolTip("WorkStation BCC");

        Menu DisplayMenu = new Menu("Menu");
        MenuItem RestoreItem = new MenuItem("Restaurar");
        MenuItem AboutItem = new MenuItem("Sobre");
        MenuItem ExitItem = new MenuItem("Sair");

        MenuItem ErrorItem = new MenuItem("Error");

        popup.add(RestoreItem);
        popup.addSeparator();
        popup.add(AboutItem);
        popup.addSeparator();
        popup.add(ExitItem);

        DisplayMenu.add(ErrorItem);

        trayIcon.setPopupMenu(popup);

        AboutItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "<html><body><p width='250px' align='center'>\nFerramenta do Controlador do COP Americana.\nOs códigos pertecem com todos os direitos a equipe de Relatórios COP AME©.\n Qualquer dúvida, procure algum dos programadores da equipe!!!", "About:", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ExitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });

        RestoreItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                mn.setVisible(true);
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Image CreateIcon(String path, String desc) {

        URL ImageURL = global_class.class.getResource(path);
        return (new ImageIcon(ImageURL, desc)).getImage();
    }

    public static String dh_update_ff(String ativ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        //metodo antigo
        //String verifica_upd = "SELECT IFNULL(DATE_FORMAT(MAX(DT_UPDATE),'%d/%m/%Y %H:%i'),'') AS 'DATA' FROM workstation_bcc.tbl_forafield WHERE ATIVIDADE = '" + ativ + "'";
        //metodo novo
        String verifica_upd = "";
        verifica_upd = verifica_upd + "select atividade, ifnull(date_format(dt_update,'%d/%m/%Y %H:%i'),'') AS 'DATA' \n";
        verifica_upd = verifica_upd + "from \n";
        verifica_upd = verifica_upd + "( \n";
        verifica_upd = verifica_upd + "SELECT atividade, MAX(DT_UPDATE) AS dt_update \n";
        verifica_upd = verifica_upd + "FROM workstation_bcc.tbl_forafield \n";
        verifica_upd = verifica_upd + "where dt_agendamento >= date_add(now(), interval (-32) day) group by atividade \n";
        verifica_upd = verifica_upd + ") a order by atividade \n";

        ResultSet rs = null;
        String liberacao = "";

        String sAtividade = "";
        String sData = "";
        String sGarantia = "";
        String sPool = "";
        String sPool_HFC = "";

        Connection conn;
        //conn = mysql_con();
        conn = Db_class.mysql_conn();
        try {
            rs = mysql_result(conn, verifica_upd);

            /*
             //metodo antigo
             if (rs.next()) {
             liberacao = (String) rs.getObject(1);
             }
             */
            //metodo novo
            while (rs.next()) {
                sAtividade = rs.getString(1);
                sData = rs.getString(2);

                if ("GARANTIA".equals(sAtividade.toUpperCase())) {
                    sGarantia = sData;
                } else {
                    if ("POOL".equals(sAtividade.toUpperCase())) {
                        sPool = sData;
                    } else {
                        if ("POOL_HFC".equals(sAtividade.toUpperCase())) {
                            sPool_HFC = sData;
                        }
                    }
                }
            }

            liberacao = sGarantia + ";" + sPool + ";" + sPool_HFC;

        } catch (SQLException ex) {
            //Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            global_class.msg_dialog("Problemas na consulta ao banco de dados.\n\nErro: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            conn.close();
            conn = null;
        }
        
        return liberacao;

    }

    public static void writeCSVfile(JTable table, String arq, String path, String usuario) throws IOException, ClassNotFoundException, SQLException {
        Writer writer = null;
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount();
        int nCol = dtm.getColumnCount();

        String pasta = "";

        if (path.contains("phoenix")) {

            pasta = path.replace("phoenix", usuario);

        } else if (path.contains("PHOENIX")) {

            pasta = path.replace("PHOENIX", usuario);

        } else {

            pasta = path;

        }

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pasta + arq + ".csv"), "ISO-8859-1"));

            //write the header information
            StringBuffer bufferHeader = new StringBuffer();
            for (int j = 0; j < nCol; j++) {
                bufferHeader.append(dtm.getColumnName(j));
                if (j != nCol) {
                    bufferHeader.append(";");
                }
            }
            writer.write(bufferHeader.toString() + "\r\n");

            //write row information
            for (int i = 0; i < nRow; i++) {
                StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < nCol; j++) {
                    buffer.append(dtm.getValueAt(i, j));
                    if (j != nCol) {
                        buffer.append(";");
                    }
                }
                writer.write(buffer.toString() + "\r\n");
            }
        } finally {
            writer.flush();
            writer.close();

        }
    }

    public static void preenche_array(ArrayList array_popular, String query, int col) {

        Connection conn = null;

        try {
            //conn = mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = null;
            rs = mysql_result(conn, query);

            while (rs.next()) {

                array_popular.add((String) rs.getObject(col));

            }

            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

    public static String get_emails(String consulta, int col) {

        String email = "";
        Connection conn = null;

        try {
            //conn = mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = null;
            rs = mysql_result(conn, consulta);

            rs.next();
            email = (String) rs.getObject(col);

            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

        return email;

    }

    public static String verificar_perfil(String login) {

        String perfil = "";
        String query = "";
        Connection conn = null;

        query = "CALL workstation_bcc.app_perfil_user_03('" + login + "')";

        try {
            //conn = mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = null;
            rs = mysql_result(conn, query);

            if (rs.next()) {
                perfil = (String) rs.getObject(1);
            } else {
                perfil = "INATIVO";
            }
            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            //perfil = "OPERADOR";
            perfil = "ERRO";
            Db_class.close_conn(conn);
        }

        return perfil;

    }

    public static void preencher_combobox(JComboBox combo_popular, String first, String query) {

        Connection conn = null;
        combo_popular.removeAllItems();
        combo_popular.addItem(first);

        try {
            //conn = mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = null;
            rs = mysql_result(conn, query);

            while (rs.next()) {

                combo_popular.addItem(rs.getObject(1));

            }

            Db_class.close_conn(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }

    }

    public static List<cLista_IDxDADO> preencher_array_IDxDADO(Integer iCampo_ID, Integer iCampo_DADO, String sQuery) {

        List<cLista_IDxDADO> lst = new ArrayList<cLista_IDxDADO>();
        Connection conn = null;

        try {
            conn = Db_class.mysql_conn();
            ResultSet rs = null;
            rs = mysql_result(conn, sQuery);

            while (rs.next()) {
                lst.add(new cLista_IDxDADO(rs.getObject(iCampo_ID).toString(), rs.getObject(iCampo_DADO).toString()));
            }

            Db_class.close_conn(conn);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
            Db_class.close_conn(conn);
        }
        
        return lst;
    }

    public static void preencher_combobox_array(JComboBox combo_popular, String first, ArrayList array) {

        combo_popular.removeAllItems();
        combo_popular.addItem(first);

        for (int z = 0; z < array.size(); z++) {

            combo_popular.addItem(array.get(z));

        }

    }

    public static void preencher_combobox_select(JComboBox combo_popular, String first, String query, String selected, JScrollPane scrollPane, JPanel jPanel1) {

        Connection conn = null;
        combo_popular.removeAllItems();
        combo_popular.addItem(first);

        try {
            //conn = mysql_con();
            conn = Db_class.mysql_conn();
            ResultSet rs = null;
            rs = mysql_result(conn, query);

            while (rs.next()) {

                combo_popular.addItem(rs.getObject(1));

                String cid = (String) rs.getObject(1);

                if (cid.equals(selected)) {

                    combo_popular.setSelectedItem(selected);

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

    }

    public static String login_ldap(String login) {

        String ldap = "";

        try {
            String login_search = login;
            LdapContext ctx = null;

            try {
                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.SECURITY_AUTHENTICATION, "Simple");
                env.put(Context.SECURITY_PRINCIPAL, "APP_RelatoriosCOP02");
                env.put(Context.SECURITY_CREDENTIALS, "Relatorios#02");
                env.put(Context.PROVIDER_URL, "ldap://5.8.8.30:389/");
                ctx = new InitialLdapContext(env, null);

                try {
                    SearchControls constraints = new SearchControls();
                    constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
                    String[] attrIDs = {"DisplayName"};
                    constraints.setReturningAttributes(attrIDs);

                    try {

                        NamingEnumeration answer = ctx.search("DC=netservicos, DC=corp", "sAMAccountName=" + login_search, constraints);
                        if (answer.hasMore()) {
                            Attributes attrs = ((SearchResult) answer.next()).getAttributes();

                            Attribute name = attrs.get("displayName");
                            ldap = name.toString().replace("displayName: ", "");

                        } else {
                            ldap = "Desconhecido";
                        }

                    } catch (Exception e) {

                        NamingEnumeration answer = ctx.search("DC=netservicos, DC=corp", "mailnickname=" + login_search, constraints);
                        if (answer.hasMore()) {
                            Attributes attrs = ((SearchResult) answer.next()).getAttributes();

                            Attribute name = attrs.get("displayName");
                            ldap = name.toString().replace("displayName: ", "").toUpperCase();

                        } else {
                            ldap = "Desconhecido";
                        }

                    }

                    ctx.close();

                } catch (Exception ex) {
                    ldap = "Desconhecido";
                }

            } catch (NamingException nex) {
                System.out.println("Erro!");
            }

        } catch (Exception ex) {
        }

        return ldap;

    }

    public static void arquivos_jacob() throws IOException {

        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };

        String bits = System.getProperty("os.arch");
        System.setProperty("java.library.path", "C:\\WORKSTATION_BCC\\");

        final java.lang.reflect.Field sysPathsField;
        try {
            sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(global_class.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (bits.equals("x86")) {

            System.load("C:\\WORKSTATION_BCC\\jacob-1.18-M2-x86.dll");

        } else {

            System.load("C:\\WORKSTATION_BCC\\jacob-1.18-M2-x64.dll");

        }

    }

    public static void select_all_list(JList lista) {

        int start = 0;
        int end = lista.getModel().getSize() - 1;
        if (end >= 0) {
            lista.setSelectionInterval(start, end);
        }
    }
    
    public static String SaudacaoMomento() {
        String sRetorno = "";
        Calendar cData = Calendar.getInstance();
        int iHora = cData.get(Calendar.HOUR_OF_DAY);

        if (iHora < 12) {
            sRetorno = "bom dia";
        } else if (iHora < 18) {
            sRetorno = "boa tarde";
        } else {
            sRetorno = "boa noite";
        }

        return sRetorno;
    }

}
