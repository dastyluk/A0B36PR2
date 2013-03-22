package rodinna_databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Databaze databaze = new Databaze();
        GUI_hl okno1 = new GUI_hl();
        okno1.setVisible(true);

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
        
        String vypisTabulky = "";
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            vypisTabulky = vypisTabulky + String.format("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", pomocnaKniha.getParam1(),
                    pomocnaKniha.getParam2(), pomocnaKniha.getParam3(), pomocnaKniha.getParam4(),
                    pomocnaKniha.getParam5(), pomocnaKniha.getParam6(), pomocnaKniha.getParam7(),
                    pomocnaKniha.getParam8());
        }        
        okno1.vypisTabulkuDoOblastiHlavni(vypisTabulky);
        
        // uzavření dotazu i všech výsledků
        st.close();

        // uzavření spojení
        conn.close();
    }
}
