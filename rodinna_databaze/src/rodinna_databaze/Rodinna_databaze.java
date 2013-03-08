package rodinna_databaze;

import java.sql.*;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class Rodinna_databaze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String url = "jdbc:derby://localhost:1527/Datab_hl";
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection conn = DriverManager.getConnection(url, "Rodinna_databaze", "Rodinna_databaze");
        Statement st = conn.createStatement();

        // VYVORENI TABULKY V DATABAZI
        //st.executeUpdate("CREATE TABLE APP.KNIHY (PORADOVECISLO INTEGER, NAZEVKNIHY VARCHAR(100), AUTORKNIHY VARCHAR(50), ROKKNIHY INTEGER, VYDAVATELSTVIKNIHY VARCHAR(50), ZANRKNIHY VARCHAR(20), JAZYKKNIHY VARCHAR(20), UMISTENIKNIHY VARCHAR(10))");

        //VLOZENI ZAZNAMU
        //st.executeUpdate("INSERT INTO APP.KNIHY (PORADOVECISLO, NAZEVKNIHY, AUTORKNIHY, ROKKNIHY, VYDAVATELSTVIKNIHY, ZANRKNIHY, JAZYKKNIHY, UMISTENIKNIHY) VALUES (2, 'Kniha 2', 'Autor 2', 1902, 'Vydavatelstvi 2', 'Zanr 2', 'Jazyk 2', 'Umisteni 2')");
        /*int cislo = 15;
        String sql = "INSERT INTO APP.KNIHY (PORADOVECISLO, NAZEVKNIHY, AUTORKNIHY, ROKKNIHY, VYDAVATELSTVIKNIHY, ZANRKNIHY, JAZYKKNIHY, UMISTENIKNIHY) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String str1 = String.format("Kniha %d", cislo);
        String str2 = String.format("Autor %d", cislo);
        String str3 = String.format("Vydavatelstvi %d", cislo);
        String str4 = String.format("Zanr %d", cislo);
        String str5 = String.format("Jazyk %d", cislo);
        String str6 = String.format("Umisten %d", cislo);
        ps.setInt(1, cislo);
        ps.setString(2, str1);
        ps.setString(3, str2);
        ps.setInt(4, 1900+cislo);
        ps.setString(5, str3);
        ps.setString(6, str4);
        ps.setString(7, str5);
        ps.setString(8, str6);
        int val = ps.executeUpdate();*/

        //EDITACE ZAZNAMU
        st.executeUpdate("UPDATE APP.KNIHY SET NAZEVKNIHY='Matematika pro SOŠ' WHERE PORADOVECISLO=15");

        //SMAZANI ZAZNAMU
        //st.executeUpdate("DELETE FROM APP.KNIHY WHERE PORADOVECISLO=15");

        //NACTENI ZAZNAMU
        /*ResultSet rs = st.executeQuery(
         "SELECT APP.PORADOVECISLO, APP.NAZEVKNIHY, APP.AUTORKNIHY, APP.ROKKNIHY, APP.VYDAVATELSTVIKNIHY, APP.ZANRKNIHY, APP.JAZYKKNIHY, APP.UMISTENIKNIHY"
         + "FROM APP.KNIHY"
         + "ON APP.KNIHY");*/
        ResultSet rs = st.executeQuery("SELECT * FROM APP.KNIHY");

        // vypsání výsledku
        System.out.printf("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
        while (rs.next()) {
            System.out.printf("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
        }

        // uzavření dotazu i všech výsledků
        st.close();

        // uzavření spojení
        conn.close();
    }
}
