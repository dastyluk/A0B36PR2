package rodinna_databaze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class GUI_hl extends JFrame {
    
    Databaze databaze = new Databaze();
    PrvekDatabaze pomocnaKniha;
    
    //JTextArea to = new JTextArea("Počáteční text", 28, 50);
    //JButton tl1 = new JButton("Test1");
    //JLabel napis = new JLabel("Nadpis nad textovým polem");
    JMenuBar oblastMenu = new JMenuBar();
    JMenu submenuMenu = new JMenu("Menu");
    JMenu submenuNapoveda = new JMenu("Nápověda");
    JMenuItem polozkaMenu;
    JTextArea oblastHlavniText = new JTextArea ("Načítání ..."); 
    String popisLista = String.format("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
    JTextArea oblastHlavniLista = new JTextArea (popisLista);      
    JButton oblastNabidkaButtonNovy = new JButton("Nový záznam");
    JLabel oblastNabidkaLabelUprav = new JLabel("Zadej pořadové číslo záznamu:");
    JTextField oblastNabidkaFieldUprav = new JTextField(""); 
    JButton oblastNabidkaButtonUprav = new JButton("Upravit záznam");
    JLabel oblastNabidkaLabelSmaz= new JLabel("Zadej pořadové číslo záznamu:");
    JTextField oblastNabidkaFieldSmaz = new JTextField(""); 
    JButton oblastNabidkaButtonSmaz = new JButton("Smazat záznam");
    JLabel oblastNabidkaLabel = new JLabel("1. Label v nabidce");
    JTextField oblastNabidkaText = new JTextField("Úvodní text1");    
    
    JTextField oblastHlaseni = new JTextField("Připraven");    

    public GUI_hl() {    
    
        super();
        this.setBounds(100, 100, 1100, 750);
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
//        oblastHlavniLista.setOpaque(true); //pro zobrazeni barvy
        oblastHlavniLista.setBackground(Color.white);  //barva pozadi
        oblastHlavniLista.setForeground(Color.black);  //barva textu
        oblastHlavniLista.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        oblastHlavniLista.setBounds(7, 20, 785, 20);  
        oblastHlavniLista.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 11));  
        oblastHlavniLista.setEditable(false);
//        oblastHlavniText.setOpaque(true); //pro zobrazeni barvy
        oblastHlavniText.setBackground(Color.white);  //barva pozadi
        oblastHlavniText.setForeground(Color.black);  //barva textu
        oblastHlavniText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        oblastHlavniText.setBounds(7, 41, 785, 621);  
        oblastHlavniText.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 11));
        oblastHlavniText.setEditable(false);
        oblastHlavni.add(oblastHlavniLista);
        oblastHlavni.add(oblastHlavniText);
        
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
        oblastNabidka.add(oblastNabidkaButtonNovy);
        oblastNabidka.add(oblastNabidkaLabelUprav);
        oblastNabidka.add(oblastNabidkaFieldUprav);
        oblastNabidka.add(oblastNabidkaButtonUprav);
        oblastNabidka.add(oblastNabidkaLabelSmaz);
        oblastNabidka.add(oblastNabidkaFieldSmaz);
        oblastNabidka.add(oblastNabidkaButtonSmaz);
         
        kon.add(oblastMenu, BorderLayout.NORTH);
        kon.add(oblastHlavni, BorderLayout.WEST);
        kon.add(oblastNabidka, BorderLayout.EAST);
        kon.add(oblastHlaseni, BorderLayout.SOUTH);        
        
        setContentPane(kon);
    }
    
    void vypisTabulkuDoOblastiHlavni(String str) {
        oblastHlavniText.setText(str);
    }        
    
    class udalostOblastNabidkaButtonNovy implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }        
    }
    
    class udalostOblastNabidkaButtonUprav implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cisloKnihy = Integer.parseInt(oblastNabidkaFieldUprav.getText());
            System.out.println(cisloKnihy);
            pomocnaKniha = databaze.upravStavajiciKnihu(cisloKnihy);
            System.out.println(pomocnaKniha);
        }        
    }
    
    class udalostOblastNabidkaButtonSmaz implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
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
                    null,  //nepouzivat zakladni ikony
                    options,  //napisy ikon
                    options[0])){  //defaultni ikona             
                    //showConfirmDialog     JOptionPane.ERROR_MESSAGE)){
            case JOptionPane.OK_OPTION:  //ukonci program
                System.exit(0);break;
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
