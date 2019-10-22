/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ROBSMAC
 */
public class cEmail {

    private boolean bAchou = false;
    private String sAssunto = "Sem assunto";
    private String sCorpo = "Sem conteúdo";

    public boolean isbAchou() {
        return bAchou;
    }

    public String getsAssunto() {
        return sAssunto;
    }

    public String getsCorpo() {
        return sCorpo;
    }

    public cEmail(Integer iTipo) {
        Connection conn;
        try {
            ResultSet rDados = null;
            conn = Db_class.mysql_conn();
            rDados = global_class.mysql_result(conn, "select assunto, corpo from tbl_config_mail where id_tipo = " + iTipo.toString() + " and fl_ativo = 1;");
            while (rDados.next()) {
                bAchou = true;
                sAssunto = rDados.getString(1);
                sCorpo = rDados.getString(2);
            }
            Db_class.close_conn(conn);
        } catch (Exception ex) {
            Logger.getLogger(cEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
