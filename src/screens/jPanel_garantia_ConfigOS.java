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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class jPanel_garantia_ConfigOS extends javax.swing.JPanel {

    scr_Menu Menu06;
    JScrollPane scrollPane;
    boolean verifica_criacao = false;
    JTable tableOS;
    int row_select;
    int col_select;
    int id_caso;
    JPopupMenu jPopupMenu;
    JMenuItem jMenuUse;
    Connection conn;

    class CustomRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public Component getTableCellRendererComponent(JTable tab, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            this.setHorizontalAlignment(CENTER);

            JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(tab, value, isSelected, hasFocus, row, column);

            if ((tab.getValueAt(row, column).equals("NOK"))) {

                if (isSelected) {

                    cellComponent.setBackground(get_color(139, 0, 0));
                    cellComponent.setForeground(Color.white);
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

                } else {

                    cellComponent.setBackground(get_color(255, 0, 0));
                    cellComponent.setForeground(Color.white);
                    cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

                }

            } else if (isSelected) {

                cellComponent.setBackground(get_color(0, 128, 0));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD));

            } else {

                cellComponent.setBackground(get_color(60, 179, 113));
                cellComponent.setForeground(Color.white);
                cellComponent.setFont(cellComponent.getFont().deriveFont(Font.PLAIN));

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

            String info = (String) table.getValueAt(row, 7);

            if (info.equals("") && isSelected) {

                lbl.setBackground(get_color(139, 0, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon2);

            } else if (info.equals("") && !isSelected) {

                lbl.setBackground(get_color(255, 0, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon2);

            } else if (!info.equals("") && isSelected) {

                lbl.setBackground(get_color(0, 128, 0));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon);

            } else if (!info.equals("") && !isSelected) {

                lbl.setBackground(get_color(60, 179, 113));
                lbl.setForeground(Color.WHITE);
                lbl.setIcon(icon);

            }

            lbl.setOpaque(true);
            lbl.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);

            return lbl;
        }
    }

    public jPanel_garantia_ConfigOS(scr_Menu Menu00) {
        initComponents();

        Menu06 = Menu00;
        cria_tabela();

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
            .addGap(0, 893, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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

                tableOS = null;
                tableOS = global_class.getTable("SELECT ID, CIDADE, IFNULL(AREA_DESPACHO,'') AS 'AREA', IFNULL(OS_MP,'') AS 'MUDANCA PACOTE', IFNULL(EQP_RC,'') AS 'RETORNO', IFNULL(OS_MC,'') AS 'MANUT CORRETIVA' FROM workstation_bcc.tbl_config_os_calculo");
                tableOS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRenderer tcustom = new CustomRenderer();

                for (int column = 0; column < tableOS.getColumnCount(); column++) {
                    TableColumn tableColumn = tableOS.getColumnModel().getColumn(column);
                    int preferredWidth = tableColumn.getMinWidth();
                    int maxWidth = tableColumn.getMaxWidth();

                    for (int row = 0; row < tableOS.getRowCount(); row++) {
                        TableCellRenderer cellRenderer = tableOS.getCellRenderer(row, column);
                        Component c = tableOS.prepareRenderer(cellRenderer, row, column);
                        int width = c.getPreferredSize().width + tableOS.getIntercellSpacing().width;
                        preferredWidth = Math.max(preferredWidth, width);

                        if (preferredWidth >= maxWidth) {
                            preferredWidth = maxWidth + 10;
                            break;
                        }
                    }

                    switch (column) {
                        case 0:
                            tableColumn.setPreferredWidth(0);
                            tableColumn.setMinWidth(0);
                            tableColumn.setMaxWidth(0);
                            break;
                        case 3:
                            tableColumn.setPreferredWidth(150);
                            tableColumn.setMinWidth(150);
                            tableColumn.setMaxWidth(150);
                            tableColumn.setCellRenderer(tcustom);
                            break;
                        case 4:
                            tableColumn.setPreferredWidth(120);
                            tableColumn.setMinWidth(120);
                            tableColumn.setMaxWidth(120);
                            tableColumn.setCellRenderer(tcustom);
                            break;
                        default:
                            tableColumn.setPreferredWidth(preferredWidth);
                            tableColumn.setMinWidth(preferredWidth + 10);
                            tableColumn.setMaxWidth(preferredWidth + 10);
                            break;
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tableOS, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tableOS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tableOS.addMouseListener(new tab_click());
                tableOS.addKeyListener(new att_super(tableOS));
                tableOS.setColumnSelectionAllowed(true);
                tableOS.getTableHeader().setReorderingAllowed(false);
                tableOS.setLocation(0, 0);
                tableOS.setSize(screenRect.width - 20, screenRect.height - 115);

                tableOS.setVisible(true);
                scrollPane = new JScrollPane(tableOS);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(screenRect.width - 20, screenRect.height - 130);
                scrollPane.setVisible(true);
                jPanel1.add(scrollPane);
                jPanel1.repaint();

            } catch (Exception ex) {
                Logger.getLogger(jPanel_garantia_ConfigOS.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    class tab_click extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            row_select = tableOS.rowAtPoint(e.getPoint());
            col_select = tableOS.columnAtPoint(e.getPoint());

            if (e.getButton() == MouseEvent.BUTTON3) {

                if (!global_class.check_version()) {

                    String msg = "<html><body><p width='250px'>Você não esta utilizando a versão atual deste sistema. \nPor favor feche e tente novamente!";
                    global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                } else {

                    if (row_select != -1) {

                        tableOS.changeSelection(row_select, col_select, false, false);

                    }

                    if (row_select >= 0) {

                        id_caso = (int) tableOS.getValueAt(row_select, 0);

                        if (tableOS.getValueAt(row_select, col_select).equals("OK")) {

                            jPopupMenu = new JPopupMenu();
                            jPopupMenu.removeAll();
                            jPopupMenu.repaint();

                            jMenuUse = new JMenuItem();
                            jMenuUse.setText("Desabilitar");
                            jMenuUse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png")));
                            jPopupMenu.add(jMenuUse);
                            jMenuUse.addActionListener(new action_return(id_caso));

                            jPopupMenu.repaint();
                            jPopupMenu.show(tableOS, e.getX(), e.getY());

                        } else if (tableOS.getValueAt(row_select, col_select).equals("NOK")) {

                            jPopupMenu = new JPopupMenu();
                            jPopupMenu.removeAll();
                            jPopupMenu.repaint();

                            jMenuUse = new JMenuItem();
                            jMenuUse.setText("Habilitar");
                            jMenuUse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accept.png")));
                            jPopupMenu.add(jMenuUse);
                            jMenuUse.addActionListener(new action_return(id_caso));

                            jPopupMenu.repaint();
                            jPopupMenu.show(tableOS, e.getX(), e.getY());

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

                if (col_select == 3) {

                    if (tableOS.getValueAt(row_select, col_select).equals("OK")) {

                        conn = Db_class.mysql_conn();
                        global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_config_os_calculo SET OS_MP = 'NOK' WHERE ID = " + caso);
                        Db_class.close_conn(conn);

                        String msg = "<html><body><p width='150px'>Realizada alteração!";
                        global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                    } else if (tableOS.getValueAt(row_select, col_select).equals("NOK")) {

                        conn = Db_class.mysql_conn();
                        global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_config_os_calculo SET OS_MP = 'OK' WHERE ID = " + caso);
                        Db_class.close_conn(conn);

                        String msg = "<html><body><p width='150px'>Realizada alteração!";
                        global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                    }
                } else if (col_select == 4) {

                    if (tableOS.getValueAt(row_select, col_select).equals("OK")) {

                        conn = Db_class.mysql_conn();
                        global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_config_os_calculo SET OS_RC = 'NOK' WHERE ID = " + caso);
                        Db_class.close_conn(conn);

                        String msg = "<html><body><p width='150px'>Realizada alteração!";
                        global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                    } else if (tableOS.getValueAt(row_select, col_select).equals("NOK")) {

                        conn = Db_class.mysql_conn();
                        global_class.mysql_insert(conn, "UPDATE workstation_bcc.tbl_config_os_calculo SET OS_RC = 'OK' WHERE ID = " + caso);
                        Db_class.close_conn(conn);

                        String msg = "<html><body><p width='150px'>Realizada alteração!";
                        global_class.msg_dialog(msg, JOptionPane.INFORMATION_MESSAGE);

                    }

                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                Db_class.close_conn(conn);
            }

            cria_tabela();
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

                cria_tabela();

            }

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
