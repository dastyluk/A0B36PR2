package rodinna_databaze;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Lukáš Dastych
 */
public class Okna {    
}

class OknoOblastNabidkaNovy extends JFrame { 
    private PrvekDatabaze pomocnaKniha;
    private JLayeredPane oblastZadavaniNovy;
    private JTextArea popisOvladani;
    private JLabel labelNazev;
    private JTextField fieldNazev;
    private JLabel labelAutor;
    private JTextField fieldAutor;
    private JLabel labelRok;
    private JTextField fieldRok;
    private JLabel labelVydavatelstvi;
    private JTextField fieldVydavatelstvi;
    private JLabel labelZanr;
    private JTextField fieldZanr;
    private JLabel labelJazyk;
    private JTextField fieldJazyk;
    private JLabel labelUmisteni;
    private JTextField fieldUmisteni;
    private JButton ulozZmeny = new JButton("Uložit");
    private JButton stornoZmeny = new JButton("Zrušit");
    
    
    public OknoOblastNabidkaNovy(final int pocetZaznamu) {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        setTitle("Nový záznam");
        setBounds(350, 100, 500, 600);
        oblastZadavaniNovy = new JLayeredPane();
        oblastZadavaniNovy.setPreferredSize(new Dimension(400, 500));
        oblastZadavaniNovy.setBorder(BorderFactory.createTitledBorder(""));  //Nabídka
        
        popisOvladani = new JTextArea("• Pro úpravu záznamu zadejte do polí nové údaje. \n" 
                + "• Pro nezměněné údaje ponechte pole prázdná. \n"
                + "• Původní údaje záznamu jsou uvedeny vždy nad \n"
                + "        příslušným polem pro zadání nového údaje. \n"
                + "• Pro uplatnění změn v záznamu stiskněte  'Uložit' .");
        popisOvladani.setEditable(false);
        popisOvladani.setFont(new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        popisOvladani.setBounds(10, 10, 463, 100);
        labelNazev = new JLabel("Název knihy:");
        labelNazev.setBounds(10, 180, 160, 20);
        fieldNazev = new JTextField("");
        fieldNazev.setBounds(180, 180, 290, 20);        
        labelAutor = new JLabel("Autor knihy:");
        labelAutor.setBounds(10, 230, 160, 20);
        fieldAutor = new JTextField("");
        fieldAutor.setBounds(180, 230, 290, 20);        
        labelRok = new JLabel("Rok vydání knihy:");
        labelRok.setBounds(10, 280, 160, 20);
        fieldRok = new JTextField("");
        fieldRok.setBounds(180, 280, 290, 20);        
        labelVydavatelstvi = new JLabel("Vydavatelství knihy:");
        labelVydavatelstvi.setBounds(10, 330, 160, 20);
        fieldVydavatelstvi = new JTextField("");
        fieldVydavatelstvi.setBounds(180, 330, 290, 20);        
        labelZanr = new JLabel("Žánr knihy:");
        labelZanr.setBounds(10, 380, 160, 20);
        fieldZanr = new JTextField("");
        fieldZanr.setBounds(180, 380, 290, 20);        
        labelJazyk = new JLabel("Jazyk knihy:");
        labelJazyk.setBounds(10, 430, 160, 20);
        fieldJazyk = new JTextField("");
        fieldJazyk.setBounds(180, 430, 290, 20);        
        labelUmisteni = new JLabel("Umístění knihy v knihovně:");
        labelUmisteni.setBounds(10, 480, 160, 20);
        fieldUmisteni = new JTextField("");
        fieldUmisteni.setBounds(180, 480, 290, 20);        
        ulozZmeny.setBounds(145, 520, 100, 30);  
        ulozZmeny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pomocnaKniha = new Kniha(pocetZaznamu+1, fieldNazev.getText(), fieldAutor.getText(), 
                            Integer.parseInt(fieldRok.getText()), fieldVydavatelstvi.getText(), 
                            fieldZanr.getText(), fieldJazyk.getText(), fieldUmisteni.getText());
                    Main.novyZaznamVSQLDatabaziAArrayListu(pomocnaKniha);
                    dispose();  //zavrit okno
                    Main.mainVypisTabulkuDoOblastiHlavni();
                } catch (Exception ex) {
                    Logger.getLogger(OknoOblastNabidkaUprav.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        stornoZmeny.setBounds(255, 520, 100, 30); 
        stornoZmeny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  //zavrit okno
            }});
        
        oblastZadavaniNovy.add(popisOvladani);
        oblastZadavaniNovy.add(labelNazev);
        oblastZadavaniNovy.add(fieldNazev);
        oblastZadavaniNovy.add(labelAutor);
        oblastZadavaniNovy.add(fieldAutor);
        oblastZadavaniNovy.add(labelRok);
        oblastZadavaniNovy.add(fieldRok);
        oblastZadavaniNovy.add(labelVydavatelstvi);
        oblastZadavaniNovy.add(fieldVydavatelstvi);
        oblastZadavaniNovy.add(labelZanr);
        oblastZadavaniNovy.add(fieldZanr);
        oblastZadavaniNovy.add(labelJazyk);
        oblastZadavaniNovy.add(fieldJazyk);
        oblastZadavaniNovy.add(labelUmisteni);
        oblastZadavaniNovy.add(fieldUmisteni);
        oblastZadavaniNovy.add(ulozZmeny);
        oblastZadavaniNovy.add(stornoZmeny);
        getContentPane().add(oblastZadavaniNovy);        
    }
}

class OknoOblastNabidkaUprav extends JFrame { 
    private PrvekDatabaze pomocnaKniha;
    private JLayeredPane oblastZadavaniUprav;
    private JTextArea popisOvladani;
    private JLabel labelPoradoveCislo;
    private JLabel labelNazevStary;
    private JLabel labelNazev;
    private JTextField fieldNazev;
    private JLabel labelAutorStary;
    private JLabel labelAutor;
    private JTextField fieldAutor;
    private JLabel labelRokStary;
    private JLabel labelRok;
    private JTextField fieldRok;
    private JLabel labelVydavatelstviStary;
    private JLabel labelVydavatelstvi;
    private JTextField fieldVydavatelstvi;
    private JLabel labelZanrStary;
    private JLabel labelZanr;
    private JTextField fieldZanr;
    private JLabel labelJazykStary;
    private JLabel labelJazyk;
    private JTextField fieldJazyk;
    private JLabel labelUmisteniStary;
    private JLabel labelUmisteni;
    private JTextField fieldUmisteni;
    private JButton ulozZmeny = new JButton("Uložit");
    private JButton stornoZmeny = new JButton("Zrušit");
    
    String nazev;
    String autor;
    int rok;
    String vydavatelstvi;
    String zanr;
    String jazyk;
    String umisteni;
    
    public OknoOblastNabidkaUprav(final PrvekDatabaze pomKniha) {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        setTitle("Úprava záznamu");
        setBounds(350, 100, 500, 600);
        oblastZadavaniUprav = new JLayeredPane();
        oblastZadavaniUprav.setPreferredSize(new Dimension(400, 500));
        oblastZadavaniUprav.setBorder(BorderFactory.createTitledBorder(""));  //Nabídka
        
        popisOvladani = new JTextArea("• Pro úpravu záznamu zadejte do polí nové údaje. \n" 
                + "• Pro nezměněné údaje ponechte pole prázdná. \n"
                + "• Původní údaje záznamu jsou uvedeny vždy nad \n"
                + "        příslušným polem pro zadání nového údaje. \n"
                + "• Pro uplatnění změn v záznamu stiskněte  'Uložit' .");
        popisOvladani.setEditable(false);
        popisOvladani.setFont(new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        popisOvladani.setBounds(10, 10, 463, 100);
        String stringPoradoveCislo = String.format("Pořadové číslo upravovaného záznamu:  %d", pomKniha.getParam1());        
        labelPoradoveCislo = new JLabel(stringPoradoveCislo);
        labelPoradoveCislo.setBounds(10, 130, 300, 20);
        nazev = pomKniha.getParam2();
        String stringNazevStary = String.format(nazev);        
        labelNazevStary = new JLabel(stringNazevStary);
        labelNazevStary.setBounds(200, 160, 200, 20);
        labelNazev = new JLabel("Název knihy:");
        labelNazev.setBounds(10, 180, 160, 20);
        fieldNazev = new JTextField("");
        fieldNazev.setBounds(180, 180, 290, 20); 
        autor = pomKniha.getParam3();
        String stringAutorStary = String.format(autor);        
        labelAutorStary = new JLabel(stringAutorStary);
        labelAutorStary.setBounds(200, 210, 200, 20);
        labelAutor = new JLabel("Autor knihy:");
        labelAutor.setBounds(10, 230, 160, 20);
        fieldAutor = new JTextField("");
        fieldAutor.setBounds(180, 230, 290, 20); 
        rok = pomKniha.getParam4();
        String stringRokStary = String.format("" + rok);        
        labelRokStary = new JLabel(stringRokStary);
        labelRokStary.setBounds(200, 260, 200, 20);
        labelRok = new JLabel("Rok vydání knihy:");
        labelRok.setBounds(10, 280, 160, 20);
        fieldRok = new JTextField("");
        fieldRok.setBounds(180, 280, 290, 20);       
        vydavatelstvi = pomKniha.getParam5();
        String stringVydavatelstviStary = String.format(vydavatelstvi);        
        labelVydavatelstviStary = new JLabel(stringVydavatelstviStary);
        labelVydavatelstviStary.setBounds(200, 310, 200, 20);
        labelVydavatelstvi = new JLabel("Vydavatelství knihy:");
        labelVydavatelstvi.setBounds(10, 330, 160, 20);
        fieldVydavatelstvi = new JTextField("");
        fieldVydavatelstvi.setBounds(180, 330, 290, 20);     
        zanr = pomKniha.getParam6();
        String stringZanrStary = String.format(zanr);        
        labelZanrStary = new JLabel(stringZanrStary);
        labelZanrStary.setBounds(200, 360, 200, 20);
        labelZanr = new JLabel("Žánr knihy:");
        labelZanr.setBounds(10, 380, 160, 20);
        fieldZanr = new JTextField("");
        fieldZanr.setBounds(180, 380, 290, 20);        
        jazyk = pomKniha.getParam7();
        String stringJazykStary = String.format(jazyk);        
        labelJazykStary = new JLabel(stringJazykStary);
        labelJazykStary.setBounds(200, 410, 200, 20);
        labelJazyk = new JLabel("Jazyk knihy:");
        labelJazyk.setBounds(10, 430, 160, 20);
        fieldJazyk = new JTextField("");
        fieldJazyk.setBounds(180, 430, 290, 20);       
        umisteni = pomKniha.getParam8();
        String stringUmisteniStary = String.format(umisteni);        
        labelUmisteniStary = new JLabel(stringUmisteniStary);
        labelUmisteniStary.setBounds(200, 460, 200, 20);
        labelUmisteni = new JLabel("Umístění knihy v knihovně:");
        labelUmisteni.setBounds(10, 480, 160, 20);
        fieldUmisteni = new JTextField("");
        fieldUmisteni.setBounds(180, 480, 290, 20);        
        ulozZmeny.setBounds(145, 520, 100, 30);  
        ulozZmeny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(fieldNazev.getText().equals(""))) {nazev = fieldNazev.getText();}
                if (!(fieldAutor.getText().equals(""))) {autor = fieldAutor.getText();}                
                if (!(fieldRok.getText().equals(""))) {rok = Integer.parseInt(fieldRok.getText());}  
                if (!(fieldVydavatelstvi.getText().equals(""))) {vydavatelstvi = fieldVydavatelstvi.getText();}  
                if (!(fieldZanr.getText().equals(""))) {zanr = fieldZanr.getText();}  
                if (!(fieldJazyk.getText().equals(""))) {jazyk = fieldJazyk.getText();}  
                if (!(fieldUmisteni.getText().equals(""))) {umisteni = fieldUmisteni.getText();} 
                try {
                    pomocnaKniha = new Kniha(pomKniha.getParam1(), nazev, autor, rok, vydavatelstvi, zanr, jazyk, umisteni);
                    Main.upravZaznamVSQLDatabaziAArrayListu(pomocnaKniha.getParam1(), pomocnaKniha);
                    dispose();  //zavrit okno
                    Main.mainVypisTabulkuDoOblastiHlavni();
                } catch (Exception ex) {
                    Logger.getLogger(OknoOblastNabidkaUprav.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        stornoZmeny.setBounds(255, 520, 100, 30); 
        stornoZmeny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  //zavrit okno
            }});
        
        oblastZadavaniUprav.add(popisOvladani);
        oblastZadavaniUprav.add(labelPoradoveCislo);
        oblastZadavaniUprav.add(labelNazevStary);
        oblastZadavaniUprav.add(labelNazev);
        oblastZadavaniUprav.add(fieldNazev);
        oblastZadavaniUprav.add(labelAutorStary);
        oblastZadavaniUprav.add(labelAutor);
        oblastZadavaniUprav.add(fieldAutor);
        oblastZadavaniUprav.add(labelRokStary);
        oblastZadavaniUprav.add(labelRok);
        oblastZadavaniUprav.add(fieldRok);
        oblastZadavaniUprav.add(labelVydavatelstviStary);
        oblastZadavaniUprav.add(labelVydavatelstvi);
        oblastZadavaniUprav.add(fieldVydavatelstvi);
        oblastZadavaniUprav.add(labelZanrStary);
        oblastZadavaniUprav.add(labelZanr);
        oblastZadavaniUprav.add(fieldZanr);
        oblastZadavaniUprav.add(labelJazykStary);
        oblastZadavaniUprav.add(labelJazyk);
        oblastZadavaniUprav.add(fieldJazyk);
        oblastZadavaniUprav.add(labelUmisteniStary);
        oblastZadavaniUprav.add(labelUmisteni);
        oblastZadavaniUprav.add(fieldUmisteni);
        oblastZadavaniUprav.add(ulozZmeny);
        oblastZadavaniUprav.add(stornoZmeny);
        getContentPane().add(oblastZadavaniUprav);        
    }
}

class OknoPolozkaMenuOProgramu extends JFrame {    
    public OknoPolozkaMenuOProgramu() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        setTitle("O programu");
        setBounds(400, 400, 250, 125);
        JTextArea ta = new JTextArea("Rodinná databáze. \n" 
                + "Především databáze knih. \n"
                + "Lukáš Dastych - 2013.");
        ta.setEditable(false);
        ta.setFont(new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        JPanel p = new JPanel();
        p.add(ta);
        getContentPane().add(p);        
    }
}
