package rodinna_databaze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    static GUI_hl okno1;
    static PrvekDatabaze pomocnaKniha;
    static List<PrvekDatabaze> listKnih;
    static int pocetKnih = 0;

    public static void prevedSQLDatabaziNaArrayList() throws Exception {
        Connection conn = null;
        Statement st = null;
        String url = "jdbc:derby://localhost:1527/Datab_hl";
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        try {
            conn = DriverManager.getConnection(url, "Rodinna_databaze", "Rodinna_databaze");
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM APP.KNIHY"); //nacteni zaznamu v databazi
            while (rs.next()) { //prevedeni databaze na ArrayList pro dalsi praci s daty
                Collections.addAll(listKnih, new Kniha(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                pocetKnih++;
            }
        } catch (Exception e) {
        } finally {
            // uzavření dotazu i všech výsledků
            st.close();
            // uzavření spojení
            conn.close();
        }

        okno1.vypisTabulkuDoOblastiHlavni(listKnih);
    }

    public static PrvekDatabaze vratZaznamPodleCisla(int poradoveCislo) {
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            if (pomocnaKniha.getParam1() == poradoveCislo) {
                return pomocnaKniha;
            }
        }
        return (new Kniha(0, "", "", 0, "", "", "", ""));
    }

    public static int vratPocetZaznamu() {
        int pocet = 0;
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            pocet++;
        }
        return pocet;
    }
    
    public static int vratRealnyPocetZaznamu() {
        int pocet = 0;
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            if (!pomocnaKniha.getParam2().equals("ZÁZNAM SMAZÁN!")) {
                pocet++;
            }
        }
        return pocet;
    }

    public static void novyZaznamVSQLDatabaziAArrayListu(PrvekDatabaze pomKniha) throws Exception {
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
        Connection conn = null;
        Statement st = null;
        String url = "jdbc:derby://localhost:1527/Datab_hl";
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        try {
            conn = DriverManager.getConnection(url, "Rodinna_databaze", "Rodinna_databaze");
            st = conn.createStatement();

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
        } catch (Exception e) {
        } finally {
            // uzavření dotazu i všech výsledků
            st.close();
            // uzavření spojení
            conn.close();
        }

        okno1.vypisTabulkuDoOblastiHlavni(listKnih);
    }

    public static void upravZaznamVSQLDatabaziAArrayListu(int poradoveCislo, PrvekDatabaze pomKniha) throws Exception {
        //EDITACE ZAZNAMU
        //st.executeUpdate("UPDATE APP.KNIHY SET NAZEVKNIHY='Matematika pro SOŠ' WHERE PORADOVECISLO=15");
        Connection conn = null;
        Statement st = null;
        String url = "jdbc:derby://localhost:1527/Datab_hl";
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        try {
            conn = DriverManager.getConnection(url, "Rodinna_databaze", "Rodinna_databaze");
            st = conn.createStatement();

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
        } catch (Exception e) {
        } finally {
            // uzavření dotazu i všech výsledků
            st.close();
            // uzavření spojení
            conn.close();
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
    }

    public static void razeniPodleNazvu() {
        List<PrvekDatabaze> listKnihKeTrideni = new ArrayList<>(listKnih);
        Collections.sort(listKnihKeTrideni, new razeni2());
        okno1.vypisTabulkuDoOblastiHlavni(listKnihKeTrideni);
    }

    public static void mainVypisTabulkuDoOblastiHlavni() {
        okno1.vypisTabulkuDoOblastiHlavni(listKnih);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        okno1 = new GUI_hl();
        okno1.setVisible(true);

        listKnih = new ArrayList<>();

        prevedSQLDatabaziNaArrayList();


        // VYVORENI TABULKY V DATABAZI
        //st.executeUpdate("CREATE TABLE APP.KNIHY (PORADOVECISLO INTEGER, NAZEVKNIHY VARCHAR(100), AUTORKNIHY VARCHAR(50), ROKKNIHY INTEGER, VYDAVATELSTVIKNIHY VARCHAR(50), ZANRKNIHY VARCHAR(20), JAZYKKNIHY VARCHAR(20), UMISTENIKNIHY VARCHAR(10))");

        //SMAZANI ZAZNAMU
        //st.executeUpdate("DELETE FROM APP.KNIHY WHERE PORADOVECISLO=26");
    }
}
