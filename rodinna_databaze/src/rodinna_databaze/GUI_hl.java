package rodinna_databaze;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class GUI_hl extends JFrame implements TableModelListener {

    private PrvekDatabaze pomocnaKniha;
    //JTextArea to = new JTextArea("Počáteční text", 28, 50);
    //JButton tl1 = new JButton("Test1");
    //JLabel napis = new JLabel("Nadpis nad textovým polem");
    private JMenuBar oblastMenu = new JMenuBar();
    private JMenu submenuMenu = new JMenu("Menu");
    private JMenu submenuNapoveda = new JMenu("Nápověda");
    private JMenuItem polozkaMenu;
    private JScrollPane oblastHlavniScroll = new JScrollPane();
    private DefaultTableModel modelTab;
    private JTable oblastHlavniTab;
    private JButton oblastNabidkaButtonNovy = new JButton("Nový záznam");
    private JLabel oblastNabidkaLabelUprav = new JLabel("Zadej pořadové číslo záznamu:");
    private JTextField oblastNabidkaFieldUprav = new JTextField("");
    private JButton oblastNabidkaButtonUprav = new JButton("Upravit záznam");
    private JLabel oblastNabidkaLabelSmaz = new JLabel("Zadej pořadové číslo záznamu:");
    private JTextField oblastNabidkaFieldSmaz = new JTextField("");
    private JButton oblastNabidkaButtonSmaz = new JButton("Smazat záznam");
    private JLabel oblastNabidkaLabelTisk = new JLabel("Vytisknout aktuálně zobrazené záznamy:");
    private JButton oblastNabidkaButtonTisk = new JButton("Tisk");
    private JLabel oblastNabidkaLabelExport1 = new JLabel("Exportovat aktuálně zobrazené záznamy");
    private JLabel oblastNabidkaLabelExport2 = new JLabel("do souboru:");
    private JButton oblastNabidkaButtonExport = new JButton("Export");
    private JLabel oblastNabidkaLabelHledej = new JLabel("Hledání podle zadaného výrazu:");
    private JTextField oblastNabidkaFieldHledej = new JTextField("");
    private JTextField oblastHlaseni = new JTextField("Připraven");
    private TableRowSorter<TableModel> sorter;
    private boolean provadetZmenyVTab = false;
    private boolean predchoziStavPrepisuTab = false;

    public GUI_hl() {
        super();
        this.setBounds(100, 0, 1100, 740);  //this.setBounds(100, 50, 1100, 750);
        this.setTitle("Rodinná databáze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Container kon = getContentPane();
        BorderLayout srb = new BorderLayout();
        kon.setLayout(srb);

        oblastMenu.add(submenuMenu);
        polozkaMenu = new JMenuItem("Polozka Menu 1 - nefunkcni");
        polozkaMenu.setMnemonic(KeyEvent.VK_P);
        polozkaMenu.addActionListener(new udalostPolozkaMenuPolozka1());
        submenuMenu.add(polozkaMenu);
        polozkaMenu = new JMenuItem("Konec");
        polozkaMenu.setMnemonic(KeyEvent.VK_X);
        polozkaMenu.addActionListener(new udalostPolozkaMenuKonec());
        submenuMenu.add(polozkaMenu);
        oblastMenu.add(submenuNapoveda);
        polozkaMenu = new JMenuItem("Napoveda");
        polozkaMenu.setMnemonic(KeyEvent.VK_N);
        polozkaMenu.addActionListener(new udalostPolozkaMenuNapoveda());
        submenuNapoveda.add(polozkaMenu);
        polozkaMenu = new JMenuItem("O programu");
        polozkaMenu.setMnemonic(KeyEvent.VK_O);
        polozkaMenu.addActionListener(new udalostPolozkaMenuOProgramu());
        submenuNapoveda.add(polozkaMenu);


        JLayeredPane oblastHlavni = new JLayeredPane();
        oblastHlavni.setPreferredSize(new Dimension(800, 50));
        oblastHlavni.setBorder(BorderFactory.createTitledBorder("Zobrazení databáze"));
        String[] columnNames = {"P.Č.", "Název", "Autor", "Rok", "Vydatelství", "Zanr", "Jazyk", "Umístění"};
        modelTab = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class getColumnClass(int column) {  //pro spravne trideni po kliknuti na hlavicku sloupce
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
        oblastHlavniTab = new JTable(modelTab);
        sorter = new TableRowSorter<TableModel>(modelTab);
        oblastHlavniTab.setRowSorter(sorter);
        oblastHlavniTab.setColumnSelectionAllowed(true);
        oblastHlavniTab.setFillsViewportHeight(true);
        oblastHlavniTab.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        oblastHlavniTab.setAutoCreateColumnsFromModel(rootPaneCheckingEnabled);
        oblastHlavniTab.getModel().addTableModelListener(this);
        oblastHlavniTab.getTableHeader().setToolTipText("Klikni pro třídění podle tohoto sloupce.");
        oblastHlavniTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        oblastHlavniScroll.setViewportView(oblastHlavniTab);
        oblastHlavniScroll.setBounds(7, 20, 785, 632);
        TableColumn column;
        for (int i = 0; i < 8; i++) {  //NASTAVENI SIRKY SLOUPCU
            column = oblastHlavniTab.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(30);
                column.setMaxWidth(30);
            } //por. cislo
            else {
                if (i == 1) {
                    column.setMinWidth(250);
                } //nazev
                else {
                    if (i == 2) {
                        column.setMinWidth(120);
                    } //autor
                    else {
                        if (i == 3) {
                            column.setMinWidth(40);
                            column.setMaxWidth(40);
                        } //rok
                        else {
                            if (i == 4) {
                                column.setMinWidth(100);
                            } //vydavat.
                            else {
                                if (i == 5) {
                                    column.setMinWidth(65);
                                    column.setMaxWidth(65);
                                } //zanr
                                else {
                                    if (i == 6) {
                                        column.setMinWidth(60);
                                        column.setMaxWidth(60);
                                    } //jazyk
                                    else {
                                        if (i == 7) {
                                            column.setMinWidth(55);
                                            column.setMaxWidth(55);
                                        }  //umisteni
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        TVORBA SLOUPCU A RADEK
//        // Create a couple of columns 
//        modelTab.addColumn("Col1"); 
//        modelTab.addColumn("Col2"); 
//        // Append a row 
//        modelTab.addRow(new Object[]{"v1", "v2"});
//        modelTab.addRow(new Object[]{"v1", "v2"});
//        ===============================================================
//        ZMENA HODNOTY V DANE BUNCE - PROCHAZENI PO SLOUPCICH V RADKU 3
//        int radek = 2; //3. radek - cislovano od 0
//        int sloupec = 0;
//        for (int j = 0; j < 8; j++) {
//            sloupec = j;
//            oblastHlavniTab.getModel().setValueAt("New Value", radek, sloupec);            
//        }        
        oblastHlavni.add(oblastHlavniScroll);


        JLayeredPane oblastNabidka = new JLayeredPane();
        oblastNabidka.setPreferredSize(new Dimension(280, 50));
        oblastNabidka.setBorder(BorderFactory.createTitledBorder("Operace"));  //Nabídka   
        oblastNabidkaButtonNovy.setBounds(75, 22, 130, 25);
        oblastNabidkaButtonNovy.addActionListener(new udalostOblastNabidkaButtonNovy());
        oblastNabidkaLabelUprav.setBounds(15, 80, 180, 20);
        oblastNabidkaFieldUprav.setBounds(196, 80, 35, 20);
        oblastNabidkaButtonUprav.setBounds(75, 105, 130, 25);
        oblastNabidkaButtonUprav.addActionListener(new udalostOblastNabidkaButtonUprav());
        oblastNabidkaLabelSmaz.setBounds(15, 165, 180, 20);
        oblastNabidkaFieldSmaz.setBounds(196, 165, 35, 20);
        oblastNabidkaButtonSmaz.setBounds(75, 190, 130, 25);
        oblastNabidkaButtonSmaz.addActionListener(new udalostOblastNabidkaButtonSmaz());
        oblastNabidkaLabelTisk.setBounds(15, 300, 280, 25);
        oblastNabidkaButtonTisk.setBounds(105, 330, 70, 25);
        oblastNabidkaButtonTisk.addActionListener(new udalostOblastNabidkaButtonTisk());
        oblastNabidkaLabelExport1.setBounds(15, 380, 280, 25);
        oblastNabidkaLabelExport2.setBounds(15, 395, 280, 25);
        oblastNabidkaButtonExport.setBounds(100, 420, 80, 25);
        oblastNabidkaButtonExport.addActionListener(new udalostOblastNabidkaButtonExport());
        oblastNabidkaLabelHledej.setBounds(15, 596, 200, 20);
        oblastNabidkaFieldHledej.setBounds(15, 621, 250, 30);
        oblastNabidkaFieldHledej.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        sorter.setRowFilter(RowFilter.regexFilter(oblastNabidkaFieldHledej.getText()));
                    }

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        sorter.setRowFilter(RowFilter.regexFilter(oblastNabidkaFieldHledej.getText()));
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        sorter.setRowFilter(RowFilter.regexFilter(oblastNabidkaFieldHledej.getText()));
                    }
                });

        oblastNabidka.add(oblastNabidkaButtonNovy);
        oblastNabidka.add(oblastNabidkaLabelUprav);
        oblastNabidka.add(oblastNabidkaFieldUprav);
        oblastNabidka.add(oblastNabidkaButtonUprav);
        oblastNabidka.add(oblastNabidkaLabelSmaz);
        oblastNabidka.add(oblastNabidkaFieldSmaz);
        oblastNabidka.add(oblastNabidkaButtonSmaz);
        oblastNabidka.add(oblastNabidkaLabelTisk);
        oblastNabidka.add(oblastNabidkaButtonTisk);
        oblastNabidka.add(oblastNabidkaLabelExport1);
        oblastNabidka.add(oblastNabidkaLabelExport2);
        oblastNabidka.add(oblastNabidkaButtonExport);
        oblastNabidka.add(oblastNabidkaLabelHledej);
        oblastNabidka.add(oblastNabidkaFieldHledej);

        kon.add(oblastMenu, BorderLayout.NORTH);
        kon.add(oblastHlavni, BorderLayout.WEST);
        kon.add(oblastNabidka, BorderLayout.EAST);
        kon.add(oblastHlaseni, BorderLayout.SOUTH);

        setContentPane(kon);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (provadetZmenyVTab) {
            if (predchoziStavPrepisuTab) {
                predchoziStavPrepisuTab = false;
            } else {
                int radek = e.getFirstRow();
                int sloupec = e.getColumn();

                if (sloupec != 0) {
                    pomocnaKniha = new Kniha(radek + 1, (String) oblastHlavniTab.getModel().getValueAt(radek, 1),
                            (String) oblastHlavniTab.getModel().getValueAt(radek, 2),
                            Integer.parseInt(oblastHlavniTab.getModel().getValueAt(radek, 3).toString()),
                            (String) oblastHlavniTab.getModel().getValueAt(radek, 4),
                            (String) oblastHlavniTab.getModel().getValueAt(radek, 5),
                            (String) oblastHlavniTab.getModel().getValueAt(radek, 6),
                            (String) oblastHlavniTab.getModel().getValueAt(radek, 7));
                    try {
                        Main.upravZaznamVSQLDatabaziAArrayListu(radek + 1, pomocnaKniha);
                        //            Main.mainVypisTabulkuDoOblastiHlavni();
                    } catch (Exception ex) {
                        Logger.getLogger(GUI_hl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    predchoziStavPrepisuTab = true;
                    oblastHlavniTab.getModel().setValueAt("" + (radek + 1), radek, sloupec);
                }
            }
        }
    }

    void vypisTabulkuDoOblastiHlavni(List listKnih) {
        provadetZmenyVTab = false;
        for (int i = modelTab.getRowCount() - 1; i >= 0; i--) {
            modelTab.removeRow(i);
        }
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            modelTab.addRow(new Object[]{pomocnaKniha.getParam1(), pomocnaKniha.getParam2(), pomocnaKniha.getParam3(),
                        pomocnaKniha.getParam4(), pomocnaKniha.getParam5(), pomocnaKniha.getParam6(),
                        pomocnaKniha.getParam7(), pomocnaKniha.getParam8()});
        }
        provadetZmenyVTab = true;
    }

    class udalostOblastNabidkaButtonNovy implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetOblastNabidkaButtonNovy();
        }
    }

    class udalostOblastNabidkaButtonUprav implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetOblastNabidkaButtonUprav(oblastNabidkaFieldUprav);
        }
    }

    class udalostOblastNabidkaButtonSmaz implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetOblastNabidkaButtonSmaz(oblastNabidkaFieldSmaz);
        }
    }

    class udalostOblastNabidkaButtonTisk implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetOblastNabidkaButtonTisk(oblastHlavniTab);
        }
    }

    class udalostOblastNabidkaButtonExport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetOblastNabidkaButtonExport(oblastHlavniTab);
        }
    }

    class udalostPolozkaMenuPolozka1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetPolozkaMenuPolozka1();
        }
    }

    class udalostPolozkaMenuKonec implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetPolozkaMenuKonec();
        }
    }

    class udalostPolozkaMenuNapoveda implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObsluhaUdalosti.udalostMetPolozkaMenuNapoveda();
        }
    }

    class udalostPolozkaMenuOProgramu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            OknoPolozkaMenuOProgramu okno2 = new OknoPolozkaMenuOProgramu();
            okno2.setVisible(true);
        }
    }
}
