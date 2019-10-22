package classes;

import java.util.TimerTask;
import screens.scr_Menu;

public class task_get_disp extends TimerTask {

    scr_Menu Menu;
    boolean verify;

    public void set_menu(scr_Menu princ) {

        this.Menu = princ;
        this.Menu.first_update = false;
        
    }

    public void run() {

        Menu.get_disp();

    }

}
