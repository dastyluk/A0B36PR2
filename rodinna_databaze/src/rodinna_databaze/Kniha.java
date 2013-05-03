/**
 * Kniha.java
 * Semestrální práce na A0B36PR2  =  RODINNÁ DATABÁZE
 * @author Lukáš Dastych
 * Začátek tvorby: 22.2.2013
 * Databáze knih určená pro domácí použití.
 */
package rodinna_databaze;

import java.io.*;

/**
 * Třída podle které se vytváří objekty (Knihy) ukládané do databáze.
 */
public class Kniha extends PrvekDatabaze implements Serializable {
    /**
     * Konstruktor pro vytváření objektů (záznamů) typu Potravina
     * @param poradoveCislo
     * @param nazevKnihy
     * @param autorKnihy
     * @param rokKnihy
     * @param vydavatelstviKnihy
     * @param zanrKnihy
     * @param jazykKnihy
     * @param umisteniKnihy 
     */
    public Kniha(int poradoveCislo, String nazevKnihy, String autorKnihy, int rokKnihy, 
            String vydavatelstviKnihy, String zanrKnihy, String jazykKnihy, String umisteniKnihy) {
        this.param1 = poradoveCislo;
        this.param2 = nazevKnihy;
        this.param3 = autorKnihy;
        this.param4 = rokKnihy;
        this.param5 = vydavatelstviKnihy;
        this.param6 = zanrKnihy;
        this.param7 = jazykKnihy;
        this.param8 = umisteniKnihy;
    }
    
    /**
     * Nastavení Pořadového čísla záznamu
     * @param poradoveCislo 
     */
    @Override
    public void setParam1(int poradoveCislo) {
        this.param1 = poradoveCislo;
    }
    
    /**
     * 
     * @param nazevKnihy 
     */
    @Override
    public void setParam2(String nazevKnihy) {
        this.param2 = nazevKnihy;
    }
    
    /**
     * Nastavení Autora záznamu
     * @param autorKnihy 
     */
    @Override
    public void setParam3(String autorKnihy) {
        this.param3 = autorKnihy;
    }
    
    /**
     * Nastavení Roku vydání záznamu
     * @param rokKnihy 
     */
    @Override
    public void setParam4(int rokKnihy) {
        this.param4 = rokKnihy;
    }
    
    /**
     * Nastavení Vydavatelstvi záznamu
     * @param vydavatelstviKnihy 
     */
    @Override
    public void setParam5(String vydavatelstviKnihy) {
        this.param5 = vydavatelstviKnihy;
    }
    
    /**
     * Nastavení Žánru záznamu
     * @param zanrKnihy 
     */
    @Override
    public void setParam6(String zanrKnihy) {
        this.param6 = zanrKnihy;
    }
    
    /**
     * Nastavení Jazyka záznamu
     * @param jazykKnihy 
     */
    @Override
    public void setParam7(String jazykKnihy) {
        this.param7 = jazykKnihy;
    }
    
    /**
     * Nastavení Umístění záznamu
     * @param umisteniKnihy 
     */
    @Override
    public void setParam8(String umisteniKnihy) {
        this.param8 = umisteniKnihy;
    }
    
    /**
     * Vrátí Pořadové číslo záznamu
     * @return int - Pořadové číslo
     */
    @Override
    public int getParam1() {
        return param1;
    }
    
    /**
     * Vrátí Název záznamu
     * @return String - Název
     */
    @Override
    public String getParam2() {
        return param2;
    }
    
    /**
     * Vrátí Autora záznamu
     * @return String - Autor
     */
    @Override
    public String getParam3() {
        return param3;
    }
    
    /**
     * Vrátí Rok vydání záznamu
     * @return int - Rok vydání
     */
    @Override
    public int getParam4() {
        return param4;
    }
    
    /**
     * Vrátí Vydavatelstvi záznamu
     * @return String - Vydavatelstvi
     */
    @Override
    public String getParam5() {
        return param5;
    }
    
    /**
     * Vrátí Žánr záznamu
     * @return String - Žánr 
     */
    @Override
    public String getParam6() {
        return param6;
    }
    
    /**
     * Vrátí Jazyk záznamu
     * @return String - Jazyk
     */
    @Override
    public String getParam7() {
        return param7;
    }
    
    /**
     * Vrátí Umístění záznamu
     * @return String - Umístění
     */
    @Override
    public String getParam8() {
        return param8;
    }
    
    /**
     * Základní metoda pro řazení podle Pořadového čísla
     * @param o - porovnávaný záznam
     * @return int - rozdíl Pořadových čísel
     */
    @Override
    public int compareTo(PrvekDatabaze o) {
        return param1 - o.param1;
    }
    
    /**
     * Základní výpis záznamu
     * @return String - základní výpis záznamu
     */
    @Override
    public String toString() {
        return param1 + " - " + param2 + " - " + param3 + " - " + param4 + " - " + param5 + " - " + param6 + " - " + param7 + " - " + param8;
    }    
}
