package screens;

import classes.Db_class;
import classes.global_class;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class jPanel_ForaActivia extends jPanel_ForaField {

    public jPanel_ForaActivia(scr_Menu Menu00, String tipo_ativ, String perfil, boolean fa_hfc) {
        super(Menu00, tipo_ativ, perfil, fa_hfc);
    }

    @Override
    public void combo_cidade() {

        global_class.preencher_combobox(combo_cidades_ff, "-- Selecione uma cidade --", "CALL workstation_bcc.app_cidades_ff('" + ativ + "')");

    }

    @Override
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
                    ff = global_class.mysql_result(conn, "SELECT IF(ATUALIZANDO_FA_HFC='SIM',1,0) AS STS_ATT FROM workstation_bcc.tbl_controle_att");
                    ff.next();
                    att_fa = ff.getBoolean(1);
                    Db_class.close_conn(conn);

                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(jPanel_ForaField.class.getName()).log(Level.SEVERE, null, ex);
                    Db_class.close_conn(conn);
                    global_class.msg_dialog("Não foi possível verificar se há outra carga HFC em andamento.\n\nO processo seguirá como se não houvesse uma.\n\nErro original: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }

                if (!att_fa) {

                    try {

                        nomes_uns_field = new ArrayList<>();
                        nomes_uns_field.add("Caso Sincronizado");
                        global_class.preenche_array(nomes_uns_field, "CALL workstation_bcc.app_uns_cid3('" + cidade_selecionada + "','" + ativ + "')", 1);

                        tableFF = null;
                        tableFF = global_class.getTable("CALL workstation_bcc.app_casos_ff3('" + cidade_selecionada + "','" + ativ + "')");
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

                            } else if (column == 1) {

                                tableColumn.setPreferredWidth(20);
                                tableColumn.setMinWidth(20);
                                tableColumn.setMaxWidth(20);
                                tableColumn.setCellRenderer(icustom);

                            } else if (column == 2 && preferredWidth <= 50) {

                                tableColumn.setPreferredWidth(70);
                                tableColumn.setMinWidth(70);
                                tableColumn.setMaxWidth(70);

                            } else if (column == 8 && preferredWidth <= 60) {

                                tableColumn.setPreferredWidth(90);
                                tableColumn.setMinWidth(90);
                                tableColumn.setMaxWidth(90);

                            } else if (column == 9) {

                                tableColumn.setPreferredWidth(400);
                                tableColumn.setMinWidth(400);
                                tableColumn.setMaxWidth(400);

                            } else if (column == 10) {

                                tableColumn.setPreferredWidth(150);
                                tableColumn.setMinWidth(150);
                                tableColumn.setMaxWidth(150);

                            } else if (preferredWidth == 15) {

                                tableColumn.setPreferredWidth(120);
                                tableColumn.setMinWidth(120);
                                tableColumn.setMaxWidth(120);

                            } else if (column == 5 && preferredWidth <= 70) {

                                tableColumn.setPreferredWidth(90);
                                tableColumn.setMinWidth(90);
                                tableColumn.setMaxWidth(90);

                            } else {

                                tableColumn.setPreferredWidth(preferredWidth);
                                tableColumn.setMinWidth(preferredWidth + 10);
                                tableColumn.setMaxWidth(preferredWidth + 10);
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

                    String msg = "<html><body><p width='250px'>O Fora Activia está em processo de atualização\n\nTente novamente em 2 minutos ou verifique se outro supervisor não executou\na fase de Cálculo do HFC ou se houve algum problema nessa fase.";
                    global_class.msg_dialog(msg, JOptionPane.WARNING_MESSAGE);

                }

            }
        }
    }

    @Override
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
                tableSuper = global_class.getTable("CALL workstation_bcc.app_casos_ff_super4('" + ativ + "')");
                tableSuper.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                CustomRendererSupervisor tcustom = new CustomRendererSupervisor();

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

                    if (column == 0 || column == 12) {

                        tableColumn.setPreferredWidth(0);
                        tableColumn.setMinWidth(0);
                        tableColumn.setMaxWidth(0);

                    } else if (column == 8) {

                        tableColumn.setPreferredWidth(400);
                        tableColumn.setMinWidth(400);
                        tableColumn.setMaxWidth(400);

                    } else if (column == 9) {

                        tableColumn.setPreferredWidth(150);
                        tableColumn.setMinWidth(150);
                        tableColumn.setMaxWidth(150);

                    } else if (preferredWidth == 15) {

                        tableColumn.setPreferredWidth(120);
                        tableColumn.setMinWidth(120);
                        tableColumn.setMaxWidth(120);

                    } else {

                        tableColumn.setPreferredWidth(preferredWidth);
                        tableColumn.setMinWidth(preferredWidth + 10);
                        tableColumn.setMaxWidth(preferredWidth + 10);
                    }

                }

                TableFilterHeader filterHeader = new TableFilterHeader(tableSuper, AutoChoices.ENABLED);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle screenRect = ge.getMaximumWindowBounds();

                tableSuper.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                tableSuper.addMouseListener(new tab_click_super());
                tableSuper.addKeyListener(new att_super(tableSuper));
                tableSuper.setRowSelectionAllowed(true);
                tableSuper.getTableHeader().setReorderingAllowed(false);
                tableSuper.setLocation(0, 0);
                tableSuper.setSize(screenRect.width - 20, screenRect.height - 115);

                tableSuper.setVisible(true);
                scrollPane = new JScrollPane(tableSuper);
                verifica_criacao = true;
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setLocation(5, 5);
                scrollPane.setSize(screenRect.width - 20, screenRect.height - 130);
                scrollPane.setVisible(true);
                jPanel1.add(scrollPane);
                jPanel1.repaint();

            } catch (Exception ex) {
                Logger.getLogger(jPanel_ForaActivia.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
