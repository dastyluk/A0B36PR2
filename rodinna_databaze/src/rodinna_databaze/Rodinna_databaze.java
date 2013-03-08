package rodinna_databaze;

import java.sql.*;
import java.util.*;

/**
 * Semestrální práce na A0B36PR2 RODINNÁ DATABÁZE Začátek tvorby: 22.2.2013
 *
 * @author Lukáš Dastych
 */
public class Rodinna_databaze {

    static Scanner vstup = new Scanner(System.in);

    static Kniha nactiNovouKnihu(int pocetKnih) {
        Kniha pomocnaKniha;

        System.out.printf("%n%n DATABAZE KNIH -> NOVY ZAZNAM%n");
        System.out.printf("==============================%n%n");

        System.out.printf("Zadej nazev knihy: ");
        String nazevKnihy = vstup.nextLine();
        System.out.printf("Zadej autora knihy: ");
        String autorKnihy = vstup.nextLine();
        System.out.printf("Zadej rok vydani knihy: ");
        int rokKnihy = vstup.nextInt();
        String ztratZnak = vstup.nextLine();
        System.out.printf("Zadej vydavatelstvi knihy: ");
        String vydavatelstviKnihy = vstup.nextLine();
        System.out.printf("Zadej zanr knihy: ");
        String zanrKnihy = vstup.nextLine();
        System.out.printf("Zadej jazyk textu knihy: ");
        String jazykKnihy = vstup.nextLine();
        System.out.printf("Zadej umisteni knihy v knihovne: ");
        String umisteniKnihy = vstup.nextLine();

        pomocnaKniha = new Kniha(pocetKnih + 1, nazevKnihy, autorKnihy, rokKnihy, vydavatelstviKnihy, zanrKnihy, jazykKnihy, umisteniKnihy);

        System.out.printf("%nNove vytvoreny zaznam: %n");
        System.out.println(pomocnaKniha);  //zobrazeniKnihy

        System.out.printf("%nPro navrat do hlavniho MENU stiskni ENTER.%n");
        //cekejEnter();
        ztratZnak = vstup.nextLine();

        return (pomocnaKniha);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        int pocetKnih = 0;
        Kniha pomocnaKniha;
        List<Kniha> listKnih = new ArrayList<>();

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
        /*pomocnaKniha = nactiNovouKnihu(pocetKnih);
        pocetKnih++;
        Collections.addAll(listKnih, pomocnaKniha);
        String sql = "INSERT INTO APP.KNIHY (PORADOVECISLO, NAZEVKNIHY, AUTORKNIHY, ROKKNIHY, VYDAVATELSTVIKNIHY, ZANRKNIHY, JAZYKKNIHY, UMISTENIKNIHY) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pomocnaKniha.getPoradoveCislo());
        ps.setString(2, pomocnaKniha.getNazevKnihy());
        ps.setString(3, pomocnaKniha.getAutorKnihy());
        ps.setInt(4, pomocnaKniha.getRokKnihy());
        ps.setString(5, pomocnaKniha.getVydavatelstviKnihy());
        ps.setString(6, pomocnaKniha.getZanrKnihy());
        ps.setString(7, pomocnaKniha.getJazykKnihy());
        ps.setString(8, pomocnaKniha.getUmisteniKnihy());
        int val = ps.executeUpdate();*/

        //EDITACE ZAZNAMU
        //st.executeUpdate("UPDATE APP.KNIHY SET NAZEVKNIHY='Matematika pro SOŠ' WHERE PORADOVECISLO=15");

        //SMAZANI ZAZNAMU
        //st.executeUpdate("DELETE FROM APP.KNIHY WHERE PORADOVECISLO=15");

        System.out.printf("%4s  %-20s  %-15s  %-4s  %-17s  %-10s  %-10s  %-8s%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI");
        for (Iterator<Kniha> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            System.out.printf("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", pomocnaKniha.getPoradoveCislo(), 
                    pomocnaKniha.getNazevKnihy(), pomocnaKniha.getAutorKnihy(), pomocnaKniha.getRokKnihy(), 
                    pomocnaKniha.getVydavatelstviKnihy(), pomocnaKniha.getZanrKnihy(), pomocnaKniha.getJazykKnihy(), 
                    pomocnaKniha.getUmisteniKnihy());
        }
        
        // uzavření dotazu i všech výsledků
        st.close();

        // uzavření spojení
        conn.close();
    }
}
