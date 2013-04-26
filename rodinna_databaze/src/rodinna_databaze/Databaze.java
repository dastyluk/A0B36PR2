/**
 * Databaze.java
 * Semestrální práce na A0B36PR2  =  RODINNÁ DATABÁZE
 * @author Lukáš Dastych
 * Začátek tvorby: 22.2.2013
 * Databáze knih určená pro domácí použití.
 */
package rodinna_databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Třída zajištující připojení k SQL databázi a úkony spojené
 * se čtením a ukládáním do SQL databáze
 */
public class Databaze {

    static int pocetKnih = 0;
    static PrvekDatabaze pomocnaKniha;
    static List<PrvekDatabaze> listKnih = new ArrayList<>();
    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;
    static String driver = "org.apache.derby.jdbc.ClientDriver";
    static String url = "jdbc:derby://localhost:1527/Datab_hl";
    static String uzJm = "Rodinna_databaze";
    static String psw = "Rodinna_databaze";
    
    /**
     * Metoda, která naváže spojení s SQL databází
     */
    public static void navazaniSpojeniSDatabazi() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, uzJm, psw);
            st = conn.createStatement();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
            JOptionPane.showMessageDialog(null, "Chyba při načítání databáze! \nDatabáze záznamů nebyla nalezena. \nUkončete program a zkusto ho sputit znovu.",
                    "Načítání databáze", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metoda, která ukončí spojení s SQL databází
     */
    public static void ukonceniSpojeniSDatabazi() {
        try {
            if (rs != null) {
                rs.close();  //uzavření fronty z databáze
            }
            if (st != null) {
                st.close();  // uzavření dotazu i všech výsledků
            }
            if (conn != null) {
                conn.close();  // uzavření spojení
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chyba při uzavírání databáze! \nUkončete program a zkusto ho sputit znovu.",
                    "Načítání databáze", JOptionPane.ERROR_MESSAGE);
            Main.setListKnih(listKnih);
        }
    }
    
    /**
     * Metoda, která načte data z SQL databáze a uloží je do vnitřní databáze
     * programu - ArrayListu
     */
    public static void prevedSQLDatabaziNaArrayList() {
        Main.mainVolaniSetOblastHlaseni("Načítání databáze...");
        Databaze.navazaniSpojeniSDatabazi();
        try {
            rs = st.executeQuery("SELECT * FROM APP.KNIHY"); //nacteni zaznamu v databazi
            while (rs.next()) { //prevedeni databaze na ArrayList pro dalsi praci s daty
                Collections.addAll(listKnih, new Kniha(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                pocetKnih++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chyba při načítání databáze! \nDatabáze záznamů nebyla nalezena. \nUkončete program a zkusto ho sputit znovu.",
                    "Načítání databáze", JOptionPane.ERROR_MESSAGE);
        } finally {
            ukonceniSpojeniSDatabazi();  
        }

        Main.setListKnih(listKnih);
        Main.mainVypisTabulkuDoOblastiHlavni();
        Main.mainVolaniSetOblastHlaseni("Připraven");
    }
    
    /**
     * Metoda, která uloží nově vytvořený záznam do SQL databáze a do vnitřní
     * databáze programu - do ArrayListu
     * @param pomKniha PrvekDatabaze - nově vytvořený záznam
     */
    public static void novyZaznamVSQLDatabaziAArrayListu(PrvekDatabaze pomKniha) {
        Main.mainVolaniSetOblastHlaseni("Vytváření nového záznamu v databázi");
        Databaze.navazaniSpojeniSDatabazi();
        try {
            Collections.addAll(listKnih, pomKniha);
            String sql = "INSERT INTO APP.KNIHY (PORADOVECISLO, NAZEVKNIHY, AUTORKNIHY, ROKKNIHY, VYDAVATELSTVIKNIHY, ZANRKNIHY, JAZYKKNIHY, UMISTENIKNIHY) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pomKniha.getParam1());
            ps.setString(2, pomKniha.getParam2());
            ps.setString(3, pomKniha.getParam3());
            ps.setInt(4, pomKniha.getParam4());
            ps.setString(5, pomKniha.getParam5());
            ps.setString(6, pomKniha.getParam6());
            ps.setString(7, pomKniha.getParam7());
            ps.setString(8, pomKniha.getParam8());
            int val = ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chyba při načítání databáze! \nDatabáze záznamů nebyla nalezena. \nUkončete program a zkusto ho sputit znovu.",
                    "Načítání databáze", JOptionPane.ERROR_MESSAGE);
        } finally {
            ukonceniSpojeniSDatabazi();
        }

        Main.setListKnih(listKnih);
        Main.mainVypisTabulkuDoOblastiHlavni();
        Main.mainVolaniSetOblastHlaseni("Připraven");
    }
    
    /**
     * Metoda, která uloží upravený záznam do SQL databáze a do vnitřní
     * databáze programu - do ArrayListu
     * @param poradoveCislo int - Pořadové číslo upraveného záznamu - číslování od 1
     * @param pomKniha PrvekDatabaze - upravený záznam
     */
    public static void upravZaznamVSQLDatabaziAArrayListu(int poradoveCislo, PrvekDatabaze pomKniha) {
        Main.mainVolaniSetOblastHlaseni("Úprava záznamu v databázi");
        Databaze.navazaniSpojeniSDatabazi();
        try {
            String SQLDotazEdit2 = String.format("UPDATE APP.KNIHY SET NAZEVKNIHY='%s' WHERE PORADOVECISLO=%d", pomKniha.getParam2(), poradoveCislo);
            String SQLDotazEdit3 = String.format("UPDATE APP.KNIHY SET AUTORKNIHY='%s' WHERE PORADOVECISLO=%d", pomKniha.getParam3(), poradoveCislo);
            String SQLDotazEdit4 = String.format("UPDATE APP.KNIHY SET ROKKNIHY=%s WHERE PORADOVECISLO=%d", pomKniha.getParam4(), poradoveCislo);
            String SQLDotazEdit5 = String.format("UPDATE APP.KNIHY SET VYDAVATELSTVIKNIHY='%s' WHERE PORADOVECISLO=%d", pomKniha.getParam5(), poradoveCislo);
            String SQLDotazEdit6 = String.format("UPDATE APP.KNIHY SET ZANRKNIHY='%s' WHERE PORADOVECISLO=%d", pomKniha.getParam6(), poradoveCislo);
            String SQLDotazEdit7 = String.format("UPDATE APP.KNIHY SET JAZYKKNIHY='%s' WHERE PORADOVECISLO=%d", pomKniha.getParam7(), poradoveCislo);
            String SQLDotazEdit8 = String.format("UPDATE APP.KNIHY SET UMISTENIKNIHY='%s' WHERE PORADOVECISLO=%d", pomKniha.getParam8(), poradoveCislo);
            st.executeUpdate(SQLDotazEdit2);
            st.executeUpdate(SQLDotazEdit3);
            st.executeUpdate(SQLDotazEdit4);
            st.executeUpdate(SQLDotazEdit5);
            st.executeUpdate(SQLDotazEdit6);
            st.executeUpdate(SQLDotazEdit7);
            st.executeUpdate(SQLDotazEdit8);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Chyba při načítání databáze! \nDatabáze záznamů nebyla nalezena. \nUkončete program a zkusto ho sputit znovu.",
                    "Načítání databáze", JOptionPane.ERROR_MESSAGE);
        } finally {
            ukonceniSpojeniSDatabazi();
        }

        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            if (pomocnaKniha.getParam1() == poradoveCislo) {
                pomocnaKniha.setParam2(pomKniha.getParam2());
                pomocnaKniha.setParam3(pomKniha.getParam3());
                pomocnaKniha.setParam4(pomKniha.getParam4());
                pomocnaKniha.setParam5(pomKniha.getParam5());
                pomocnaKniha.setParam6(pomKniha.getParam6());
                pomocnaKniha.setParam7(pomKniha.getParam7());
                pomocnaKniha.setParam8(pomKniha.getParam8());
            }
        }
        Main.setListKnih(listKnih);
        Main.mainVolaniSetOblastHlaseni("Připraven");
    }
}
