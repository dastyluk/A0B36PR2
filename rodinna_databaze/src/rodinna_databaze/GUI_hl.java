package rodinna_databaze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class GUI_hl extends JFrame {
    
    //JTextArea to = new JTextArea("Počáteční text", 28, 50);
    //JButton tl1 = new JButton("Test1");
    //JLabel napis = new JLabel("Nadpis nad textovým polem");
    JMenuBar oblastMenu = new JMenuBar();
    JMenu submenuMenu1 = new JMenu("Menu");
    JTextArea oblastHlavniText = new JTextArea ("Načítání ..."); 
    String popisLista = String.format("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
    JTextArea oblastHlavniLista = new JTextArea (popisLista);  
    
    JTextField oblastNabidkaText = new JTextField("Úvodní text1");
    JLabel oblastNabidkaLabel = new JLabel("1. Label v nabidce");
    
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
        
        oblastMenu.add(submenuMenu1);
        
        JLayeredPane oblastHlavni = new JLayeredPane();
        oblastHlavni.setPreferredSize(new Dimension(800, 50));
        oblastHlavni.setBorder(BorderFactory.createTitledBorder("Zobrazení databáze"));
        oblastHlavniLista.setOpaque(true); //pro zobrazeni barvy
        oblastHlavniLista.setBackground(Color.white);  //barva pozadi
        oblastHlavniLista.setForeground(Color.black);  //barva textu
        oblastHlavniLista.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        oblastHlavniLista.setBounds(7, 20, 785, 20);  
        oblastHlavniLista.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 11));        
        oblastHlavniText.setOpaque(true); //pro zobrazeni barvy
        oblastHlavniText.setBackground(Color.white);  //barva pozadi
        oblastHlavniText.setForeground(Color.black);  //barva textu
        oblastHlavniText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        oblastHlavniText.setBounds(7, 41, 785, 621);  
        oblastHlavniText.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 11));
        oblastHlavni.add(oblastHlavniLista);
        oblastHlavni.add(oblastHlavniText);
        
        JLayeredPane oblastNabidka = new JLayeredPane();
        oblastNabidka.setPreferredSize(new Dimension(280, 50));
        oblastNabidka.setBorder(BorderFactory.createTitledBorder("Operace"));  //Nabídka   
        oblastNabidkaText.setBounds(150, 22, 80, 30);
        oblastNabidkaLabel.setBounds(15, 22, 120, 30);
        oblastNabidka.add(oblastNabidkaText);
        oblastNabidka.add(oblastNabidkaLabel);
        
        kon.add(oblastMenu, BorderLayout.NORTH);
        kon.add(oblastHlavni, BorderLayout.WEST);
        kon.add(oblastNabidka, BorderLayout.EAST);
        kon.add(oblastHlaseni, BorderLayout.SOUTH);
        

        
        
        setContentPane(kon);
    }
    
    void vypisTabulkuDoOblastiHlavni(String str) {
        oblastHlavniText.setText(str);
    }
    
        

    
}
