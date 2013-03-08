package rodinna_databaze;

import java.sql.*;

/**
 * Semestrální práce na A0B36PR2
 * RODINNÁ DATABÁZE
 * Začátek tvorby: 22.2.2013
 * @author Lukáš Dastych
 */
public class Rodinna_databaze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        String url = "jdbc:derby://localhost:1527/Datab1";
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection conn = DriverManager.getConnection(url, "datab1", "datab1");

        Statement st = conn.createStatement();
        
        //st.executeUpdate("INSERT INTO \"APP\".\"ZAMESTNANCI\" (ZAMESTNANECID,PRIJMENI,JMENO,JMENO2,ZAMESTNAVATEL,DATUM_NAROZENI) VALUES (7,'Novotny','Honza','Jan.',1,{d '1994-07-08'})");        
        //st.executeUpdate("UPDATE APP.ZAMESTNANCI SET JMENO2='Jan' WHERE ZAMESTNANECID=7");
        //st.executeUpdate("DELETE FROM APP.ZAMESTNANCI WHERE ZAMESTNANECID=7");
        
        ResultSet rs = st.executeQuery(
                "SELECT APP.ZAMESTNANCI.ZAMESTNANECID, APP.ZAMESTNANCI.JMENO, APP.ZAMESTNANCI.PRIJMENI, APP.ZAMESTNANCI.DATUM_NAROZENI, APP.FIRMY.NAZEV, APP.FIRMY.MESTO "
                + "FROM APP.ZAMESTNANCI JOIN APP.FIRMY "
                + "ON APP.ZAMESTNANCI.ZAMESTNAVATEL = APP.FIRMY.FIRMAID");

        // vypsání výsledku
        System.out.printf("%14s  %-10s  %-10s %-12s --  %-20s %-40s%n", "ID zamestnance", "jméno", "příjmení", "datum naroz", "zaměstnavatel", "město");
        int pruchod = 0;
        while (rs.next()) {
            pruchod++;
            if (pruchod != 3) {
            System.out.printf("%14s  %-10s  %-10s %-12s --  %-20s %-40s%n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6));
            }
        }
        
        // uzavření dotazu i všech výsledků
        st.close();

        // uzavření spojení
        conn.close();
    }
}
