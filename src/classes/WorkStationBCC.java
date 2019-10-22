package classes;

import static classes.global_class.get_version;
import static classes.global_class.getsVersaoFonte;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import screens.scr_Menu;

/**
 *
 * @author N5604916
 */
public class WorkStationBCC {

    static scr_Menu inicio = new scr_Menu();
    
    public static void main(String[] args) throws NoSuchFieldException, IOException {

        global_class.arquivos_jacob();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenRect = ge.getMaximumWindowBounds();

        /*
         if (!global_class.check_version()) {
         String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
         global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
         } else {
         scr_Menu inicio = new scr_Menu();
         inicio.setSize(screenRect.width, screenRect.height);
         inicio.setTitle("WorkStation BCC v." + global_class.get_version());
         inicio.setVisible(true);
         }
         */
        //scr_Menu inicio = new scr_Menu();
        inicio.setSize(screenRect.width, screenRect.height);
        inicio.setTitle("WorkStation BCC");
        inicio.setVisible(true);

        if (get_version().equals(getsVersaoFonte())) {
            inicio.setTitle("WorkStation BCC v." + global_class.getsVersaoFonte());
        } else {
            inicio.setTitle("WorkStation BCC v." + global_class.getsVersaoFonte() + " (Versão incorreta - informe seu supervisor)");
        }

        //if (!global_class.check_version()) {
            //String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
            //global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);
            //inicio.setTitle(inicio.getTitle() + " (esta versão não é a oficial)");
        //}

    }

}
