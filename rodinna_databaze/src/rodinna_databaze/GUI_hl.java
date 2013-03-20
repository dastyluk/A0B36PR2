package rodinna_databaze;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;
//import java.awt.*;
import javax.swing.*;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class GUI_hl extends JFrame {
    
    static Databaze databaze;
    
    JTextArea hlavniText = new JTextArea (28, 50);

    public GUI_hl() {
        super("Rodinná databáze");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Container kon = getContentPane();
        kon.setBackground(Color.green);
        //kon.setFont(Font.getFont(Font.DIALOG));
        FlowLayout srf = new FlowLayout();
        kon.setLayout(srf);
        //JTextArea to = new JTextArea("Počáteční text", 28, 50);
        kon.add(hlavniText);
        setContentPane(kon);
    }
    
    void vypisTabulkuDoOblastiHlavniText(String str) {
        hlavniText.setText(str);
    }
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        databaze = new Databaze();

        int pocetKnih = 0;
        PrvekDatabaze pomocnaKniha;
        List<PrvekDatabaze> listKnih = new ArrayList<>();

        //JFrame f = new GUI_hl();

        String url = "jdbc:derby://localhost:1527/Datab_hl";
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection conn = DriverManager.getConnection(url, "Rodinna_databaze", "Rodinna_databaze");
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM APP.KNIHY"); //nacteni zaznamu v databazi
        while (rs.next()) { //prevedeni databaze na ArrayList pro dalsi praci s daty
            Collections.addAll(listKnih, new Kniha(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            pocetKnih++;
        }

        // VYVORENI TABULKY V DATABAZI
        //st.executeUpdate("CREATE TABLE APP.KNIHY (PORADOVECISLO INTEGER, NAZEVKNIHY VARCHAR(100), AUTORKNIHY VARCHAR(50), ROKKNIHY INTEGER, VYDAVATELSTVIKNIHY VARCHAR(50), ZANRKNIHY VARCHAR(20), JAZYKKNIHY VARCHAR(20), UMISTENIKNIHY VARCHAR(10))");

        //VLOZENI ZAZNAMU
        //st.executeUpdate("INSERT INTO APP.KNIHY (PORADOVECISLO, NAZEVKNIHY, AUTORKNIHY, ROKKNIHY, VYDAVATELSTVIKNIHY, ZANRKNIHY, JAZYKKNIHY, UMISTENIKNIHY) VALUES (2, 'Kniha 2', 'Autor 2', 1902, 'Vydavatelstvi 2', 'Zanr 2', 'Jazyk 2', 'Umisteni 2')");
        /*pomocnaKniha = databaze.nactiNovouKnihu(pocetKnih);
        pocetKnih++;
        Collections.addAll(listKnih, pomocnaKniha);
        String sql = "INSERT INTO APP.KNIHY (PORADOVECISLO, NAZEVKNIHY, AUTORKNIHY, ROKKNIHY, VYDAVATELSTVIKNIHY, ZANRKNIHY, JAZYKKNIHY, UMISTENIKNIHY) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pomocnaKniha.getParam1());
        ps.setString(2, pomocnaKniha.getParam2());
        ps.setString(3, pomocnaKniha.getParam3());
        ps.setInt(4, pomocnaKniha.getParam4());
        ps.setString(5, pomocnaKniha.getParam5());
        ps.setString(6, pomocnaKniha.getParam6());
        ps.setString(7, pomocnaKniha.getParam7());
        ps.setString(8, pomocnaKniha.getParam8());
        int val = ps.executeUpdate();*/

        //EDITACE ZAZNAMU
        //st.executeUpdate("UPDATE APP.KNIHY SET NAZEVKNIHY='Matematika pro SOŠ' WHERE PORADOVECISLO=15");

        //SMAZANI ZAZNAMU
        //st.executeUpdate("DELETE FROM APP.KNIHY WHERE PORADOVECISLO=15");

        Collections.sort(listKnih, new razeni1());
        System.out.printf("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            System.out.printf("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", pomocnaKniha.getParam1(),
                    pomocnaKniha.getParam2(), pomocnaKniha.getParam3(), pomocnaKniha.getParam4(),
                    pomocnaKniha.getParam5(), pomocnaKniha.getParam6(), pomocnaKniha.getParam7(),
                    pomocnaKniha.getParam8());
        }

        Collections.sort(listKnih, new razeni2());
        System.out.println("");
        System.out.printf("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            System.out.printf("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", pomocnaKniha.getParam1(),
                    pomocnaKniha.getParam2(), pomocnaKniha.getParam3(), pomocnaKniha.getParam4(),
                    pomocnaKniha.getParam5(), pomocnaKniha.getParam6(), pomocnaKniha.getParam7(),
                    pomocnaKniha.getParam8());
        }
        
        String hlavickaTabulky = String.format("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
        String vypisTabulky = hlavickaTabulky;
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            vypisTabulky = vypisTabulky + String.format("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", pomocnaKniha.getParam1(),
                    pomocnaKniha.getParam2(), pomocnaKniha.getParam3(), pomocnaKniha.getParam4(),
                    pomocnaKniha.getParam5(), pomocnaKniha.getParam6(), pomocnaKniha.getParam7(),
                    pomocnaKniha.getParam8());
        }
        //System.out.println(vypisTabulky);
        
        GUI_hl obj = new GUI_hl();
        obj.vypisTabulkuDoOblastiHlavniText(vypisTabulky);
        
        // uzavření dotazu i všech výsledků
        st.close();

        // uzavření spojení
        conn.close();
    }
}
