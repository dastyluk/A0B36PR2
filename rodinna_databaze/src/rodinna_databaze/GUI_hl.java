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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class GUI_hl extends JFrame {

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
    private JButton oblastNabidkaButtonTrideniNazev = new JButton ("Třídění podle názvu");
    private JTextField oblastHlaseni = new JTextField("Připraven");
    
    public GUI_hl() {
        super();
        this.setBounds(100, 50, 1100, 750);
        this.setTitle("Rodinná databáze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container kon = getContentPane();
//        kon.setBackground(Color.lightGray);
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
        polozkaMenu = new JMenuItem("Napoveda - nefunkcni");
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
                
        modelTab = new DefaultTableModel(columnNames, 0); 
        oblastHlavniTab = new JTable(modelTab);
        oblastHlavniTab.setColumnSelectionAllowed(true);
        oblastHlavniTab.setFillsViewportHeight(true);
        oblastHlavniTab.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        oblastHlavniTab.setAutoCreateColumnsFromModel(rootPaneCheckingEnabled);
        oblastHlavniTab.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //evt.
            
            }
        });
        oblastHlavniScroll.setViewportView(oblastHlavniTab);//(oblastHlavniTab);
        oblastHlavniScroll.setBounds(7, 20, 785, 641);
        TableColumn column;
        for (int i = 0; i < 8; i++) {  //NASTAVENI SIRKY SLOUPCU
            column = oblastHlavniTab.getColumnModel().getColumn(i);
            if (i == 0) {column.setMinWidth(30); column.setMaxWidth(30);}  //por. cislo
            else {if (i == 1) {column.setMinWidth(250);}   //nazev
            else {if (i == 2) {column.setMinWidth(120);}   //autor
            else {if (i == 3) {column.setMinWidth(40); column.setMaxWidth(40);}   //rok
            else {if (i == 4) {column.setMinWidth(100);}   //vydavat.
            else {if (i == 5) {column.setMinWidth(65); column.setMaxWidth(65);}   //zanr
            else {if (i == 6) {column.setMinWidth(60); column.setMaxWidth(60);}   //jazyk
            else {if (i == 7) {column.setMinWidth(55); column.setMaxWidth(55);}  //umisteni
            }}}}}}}
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
        oblastNabidkaButtonTrideniNazev.setBounds(30, 400, 200, 25);
        oblastNabidkaButtonTrideniNazev.addActionListener(new udalostOblastNabidkaButtonTrideniNazev());
        oblastNabidka.add(oblastNabidkaButtonNovy);
        oblastNabidka.add(oblastNabidkaLabelUprav);
        oblastNabidka.add(oblastNabidkaFieldUprav);
        oblastNabidka.add(oblastNabidkaButtonUprav);
        oblastNabidka.add(oblastNabidkaLabelSmaz);
        oblastNabidka.add(oblastNabidkaFieldSmaz);
        oblastNabidka.add(oblastNabidkaButtonSmaz);
        oblastNabidka.add(oblastNabidkaButtonTrideniNazev);
        
        
        kon.add(oblastMenu, BorderLayout.NORTH);
        kon.add(oblastHlavni, BorderLayout.WEST);
        kon.add(oblastNabidka, BorderLayout.EAST);
        kon.add(oblastHlaseni, BorderLayout.SOUTH);

        setContentPane(kon);
    } 
       
    void vypisTabulkuDoOblastiHlavni(List listKnih) {
        for (int i = modelTab.getRowCount() - 1; i >= 0; i--) {
            modelTab.removeRow(i);
        }
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();            
            modelTab.addRow(new Object[]{pomocnaKniha.getParam1(), pomocnaKniha.getParam2(), pomocnaKniha.getParam3(),
                                         pomocnaKniha.getParam4(), pomocnaKniha.getParam5(), pomocnaKniha.getParam6(), 
                                         pomocnaKniha.getParam7(), pomocnaKniha.getParam8()});
        }
    }

    class udalostOblastNabidkaButtonNovy implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int pocetZaznamu = Main.vratPocetZaznamu();
            //pomocnaKniha = Main.vratZaznamPodleCisla(cisloKnihy);
            OknoOblastNabidkaNovy okno2 = new OknoOblastNabidkaNovy(pocetZaznamu);
            okno2.setVisible(true);            
        }
    }

    class udalostOblastNabidkaButtonUprav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cisloKnihy = Integer.parseInt(oblastNabidkaFieldUprav.getText());
            pomocnaKniha = Main.vratZaznamPodleCisla(cisloKnihy);
            OknoOblastNabidkaUprav okno2 = new OknoOblastNabidkaUprav(pomocnaKniha);
            okno2.setVisible(true);
            oblastNabidkaFieldUprav.setText("");
        }
    }

    class udalostOblastNabidkaButtonSmaz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int cisloKnihy = Integer.parseInt(oblastNabidkaFieldSmaz.getText());                
                oblastNabidkaFieldSmaz.setText("");
                pomocnaKniha = Main.vratZaznamPodleCisla(cisloKnihy);
                String hlaska = String.format("Opravdu chcete smazat tento záznam? \n %d - %s - %s - %d - %s - %s - %s - %s",
                        pomocnaKniha.getParam1(), pomocnaKniha.getParam2(), pomocnaKniha.getParam3(), 
                        pomocnaKniha.getParam4(), pomocnaKniha.getParam5(), pomocnaKniha.getParam6(),
                        pomocnaKniha.getParam7(), pomocnaKniha.getParam8());
                GUI_hl okno2 = new GUI_hl();
                Object[] options = {"Ano", "Ne"};            
                switch (JOptionPane.showOptionDialog(okno2,
                        hlaska,
                        "Mazání záznamu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, //nepouzivat zakladni ikony
                        options, //napisy ikon
                        options[0])) {  //defaultni ikona             
                    //showConfirmDialog     JOptionPane.ERROR_MESSAGE)){
                    case JOptionPane.OK_OPTION:  //ukonci program
                        pomocnaKniha = new Kniha(cisloKnihy, "ZÁZNAM SMAZÁN!", "-", 0, "-", "-", "-", "-");
                        Main.upravZaznamVSQLDatabaziAArrayListu(cisloKnihy, pomocnaKniha);
                        Main.mainVypisTabulkuDoOblastiHlavni();
                    case JOptionPane.CANCEL_OPTION:  //rozmyslel si to, nedelej nic.
                }
            } catch (Exception ex) {
                Logger.getLogger(GUI_hl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class udalostOblastNabidkaButtonTrideniNazev implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.razeniPodleNazvu();
        }
    }

    class udalostPolozkaMenuPolozka1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class udalostPolozkaMenuKonec implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI_hl okno2 = new GUI_hl();
            Object[] options = {"Ano", "Ne"};
            switch (JOptionPane.showOptionDialog(okno2,
                    "Opravdu chcete tento program ukončit?",
                    "Ukončovací dialog",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //nepouzivat zakladni ikony
                    options, //napisy ikon
                    options[0])) {  //defaultni ikona             
                //showConfirmDialog     JOptionPane.ERROR_MESSAGE)){
                case JOptionPane.OK_OPTION:  //ukonci program
                    System.exit(0);
                    break;
                case JOptionPane.CANCEL_OPTION:  //rozmyslel si to, nedelej nic.
            }
        }
    }

    class udalostPolozkaMenuNapoveda implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
