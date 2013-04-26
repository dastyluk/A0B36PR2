/**
 * Main.java
 * Semestrální práce na A0B36PR2  =  RODINNÁ DATABÁZE
 * @author Lukáš Dastych
 * Začátek tvorby: 22.2.2013
 * Databáze knih určená pro domácí použití.
 */
package rodinna_databaze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Hlavní třída programu - spouštění a vnitřní aplikace databáze.
 */
public class Main {
    static GUI_hl okno1;
    static PrvekDatabaze pomocnaKniha;
    static List<PrvekDatabaze> listKnih;
    static int pocetKnih = 0;
    
    /**
     * Volání metoda pro výpis řetězce do oblasti hlášení ve spodní části okna
     * @param str String - požadovaný řetězec pro výpis
     */
    public static void mainVolaniSetOblastHlaseni(String str) {
        okno1.setOblastHlaseni(str);
    }
    
    /**
     * Vrátí záznam z databáze podle Pořadového čísla pro zobrazení u
     * upravování záznamu pomocí dialogového okna
     * @param poradoveCislo int - Pořadové číslo záznamu - číslování od 1
     * @return PrvekDatabaze - daný záznam
     */
    public static PrvekDatabaze vratZaznamPodleCisla(int poradoveCislo) {
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            if (pomocnaKniha.getParam1() == poradoveCislo) {
                return pomocnaKniha;
            }
        }
        return (new Kniha(0, "", "", 0, "", "", "", ""));
    }
    
    /**
     * Vrátí počet záznamů v databázi - i se smazanými
     * @return int Počet záznamů
     */
    public static int vratPocetZaznamu() {
        int pocet = 0;
        for (Iterator<PrvekDatabaze> it = listKnih.iterator(); it.hasNext();) {
            pomocnaKniha = it.next();
            pocet++;
        }
        return pocet;
    }
    
    /**
     * Vrátí počet záznamů v databázi - bez smazaných
     * @return int Počet záznamů
     */
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
    
    /**
     * Seřadí databázi podle názvů záznamů
     */
    public static void razeniPodleNazvu() {
        List<PrvekDatabaze> listKnihKeTrideni = new ArrayList<>(listKnih);
        Collections.sort(listKnihKeTrideni, new razeni2());
        okno1.vypisTabulkuDoOblastiHlavni(listKnihKeTrideni);
    }
    
    /**
     * Volání metody pro výpis databáze do tabulky v hlavní oblasti
     */
    public static void mainVypisTabulkuDoOblastiHlavni() {
        okno1.vypisTabulkuDoOblastiHlavni(listKnih);
    }
    
    /**
     * Aktualizuje vnitřní databázi po její načtení nebo úpravě
     * @param list List - vnitřní reprezentace databáze v programu
     */
    public static void setListKnih(List list) {
        listKnih = list;
    }

    /**
     * Metoda MAIN pro spuštění programu
     * Vytvoří hlavní okno programu a volá načtení SQL databáze do vnitřní
     * reprezentace databáze v programu - do ArrayListu
     * @param args argumenty z příazové řádku - není aplikováno
     * @throws Exception 
     */
    public static void main(String[] args) {
        okno1 = new GUI_hl();
        okno1.setVisible(true);

        listKnih = new ArrayList<>();

        Databaze.prevedSQLDatabaziNaArrayList();
    }
}
