/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ROBSMAC
 * Métodos do TABULADOR COP
 * Utilizados para compartilhar código com o TABULADOR COP
 */
public class Db_class {

    public static Connection mysql_conn() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        //ALTERAR_BCC_OK
        String url;
        String dbName;
        String driver;
        String userName;
        String password;

        if (global.iBDProducao == 1) {
            url = "jdbc:mysql://10.5.16.86:3306/";
            dbName = "workstation_bcc";
            driver = "com.mysql.jdbc.Driver";
            userName = "common_user";
            password = "Pvm5jrCZWQcUPyhc";
        } else {
            //url = "jdbc:mysql://10.5.79.51/";
            url = "jdbc:mysql://10.5.12.193:3306/";
            dbName = "workstation_bcc";
            driver = "com.mysql.jdbc.Driver";
            userName = "common_user";
            password = "Pvm5jrCZWQcUPyhc";
        }

        Connection conn = null;

        Class.forName(driver).newInstance();
        try {
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (SQLException ex) {
            global.show_error_message("Problemas com a conexão ao banco de dados.\n\nErro: " + ex.getMessage());
            conn = null;
        }

        return conn;
    }

    public static ResultSet mysql_result(Connection conn, String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet res = st.executeQuery(query);

        return res;
    }

    public static int mysql_insert(String query, Connection conn) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        Statement st = conn.createStatement();
        int res = st.executeUpdate(query);

        return res;

    }

    public static void close_conn(Connection conn) {

        try {
            conn.close();
        } catch (SQLException ex) {

        }

    }

}
