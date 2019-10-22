/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.global_class.buildTableModel;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ROBSMAC
 * Métodos do TABULADOR COP
 * Utilizados para compartilhar código com o TABULADOR COP
 */
public class global {

    public static Integer iBDProducao = 1; //0=desenvolvimento; 1=producao
    
    public static void open_modal(JDialog dlg, String title) {

        dlg.toFront();
        dlg.setModal(true);
        dlg.setLocationRelativeTo(null);
        dlg.pack();
        dlg.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dlg.getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dlg.getHeight() / 2);

        dlg.setResizable(false);

        dlg.setTitle(title);

        dlg.setVisible(true);

    }

    public static JTable getTable(String query, JPanel panel) throws Exception {

        Connection conn = null;
        ResultSet rs = null;

        conn = Db_class.mysql_conn();

        rs = Db_class.mysql_result(conn, query);

        JTable tab = new JTable(buildTableModel(rs));

        JScrollPane scroller = new JScrollPane(tab);

        panel.add(scroller);

        scroller.setLocation(0, 0);
        scroller.setSize(panel.getWidth() - 0, panel.getHeight() - 0);
        scroller.setVisible(true);
        tab.setVisible(true);
        tab.setSize(scroller.getWidth() - 0, scroller.getHeight() - 0);
        tab.getTableHeader().setReorderingAllowed(false);

        Db_class.close_conn(conn);

        return tab;

    }

    public static void hide_columns(int[] indexes, JTable tab) {

        for (int i : indexes) {

            tab.getColumnModel().getColumn(i).setMinWidth(0);
            tab.getColumnModel().getColumn(i).setMaxWidth(0);

        }

    }

    public static void adjust_columns(int[] column_width, JTable tab) {

        int tamanho = column_width.length;

        for (int i = 0; i <= tab.getColumnCount() - 1; i++) {

            tab.getColumnModel().getColumn(i).setPreferredWidth(column_width[i]);

        }

    }

    public static void show_message(String msg) {

        JOptionPane.showMessageDialog(null, msg, "Mensagem", JOptionPane.INFORMATION_MESSAGE);

    }

    public static void show_error_message(String msg) {

        JOptionPane.showMessageDialog(null, msg, "Erro!", JOptionPane.ERROR_MESSAGE);

    }

    public static void open_nonmodal(JDialog dlg, String title) {

        dlg.toFront();
        dlg.setModal(false);
        dlg.setLocationRelativeTo(null);
        dlg.pack();
        dlg.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - dlg.getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - dlg.getHeight() / 2);

        dlg.setResizable(false);

        dlg.setTitle(title);

        dlg.setVisible(true);

    }

    public static void show_warning_message(String msg) {

        JOptionPane.showMessageDialog(null, msg, "Erro!", JOptionPane.WARNING_MESSAGE);

    }

    public static Integer Valido_CidadeXParceira(String s_un, String s_cidade) {
        Integer iRetorno = 0;
        Connection conn_ativ;
        try {
            conn_ativ = Db_class.mysql_conn();
            CallableStatement cSP = conn_ativ.prepareCall("{call workstation_bcc.sp_valido_cidadexparceira(?, ?, ?)}");
            cSP.setString(1, s_un);
            cSP.setString(2, s_cidade);
            cSP.registerOutParameter(3, java.sql.Types.INTEGER);
            cSP.execute();
            iRetorno = cSP.getInt(3);
            conn_ativ.close();
            conn_ativ = null;
        } catch (Exception ex) {
            iRetorno = 0;
            global.show_error_message(
                    "Problemas ao validar Cidade X Parceira.\n\n"
                    + "Erro: " + ex.getMessage()
            );
            try {
                conn_ativ = null;
            } catch (Exception ex0) {
            }
        }
        return iRetorno;
    }

}
