package rodinna_databaze;

import java.io.*;

/**
 *
 * @author Lukáš Dastych
 */
class Kniha implements Serializable{

    int poradoveCislo;
    String nazevKnihy;
    String autorKnihy;
    int rokKnihy;
    String vydavatelstviKnihy;
    String zanrKnihy;
    String jazykKnihy;
    String umisteniKnihy;

    public Kniha() {  //pro vlozeni na konec souboru
        this.poradoveCislo = 0;        
        this.nazevKnihy = "";
        this.autorKnihy = "";
        this.rokKnihy = 0;
        this.vydavatelstviKnihy = "";
        this.zanrKnihy = "";
        this.jazykKnihy = "";
        this.umisteniKnihy = "";
    }

    public Kniha(int poradoveCislo) {  //konstruktor pro smazany zaznam
        this.poradoveCislo = poradoveCislo;
        this.nazevKnihy = "--zaznam smazan--";
        this.autorKnihy = "";
        this.rokKnihy = 0;
        this.vydavatelstviKnihy = "";
        this.zanrKnihy = "";
        this.jazykKnihy = "";
        this.umisteniKnihy = "";
    }
    
    

    public Kniha(int poradoveCislo, String nazevKnihy, String autorKnihy, int rokKnihy, String vydavatelstviKnihy, String zanrKnihy, String jazykKnihy, String umisteniKnihy) {
        //hlavni konstruktor
        this.poradoveCislo = poradoveCislo;
        this.nazevKnihy = nazevKnihy;
        this.autorKnihy = autorKnihy;
        this.rokKnihy = rokKnihy;
        this.vydavatelstviKnihy = vydavatelstviKnihy;
        this.zanrKnihy = zanrKnihy;
        this.jazykKnihy = jazykKnihy;
        this.umisteniKnihy = umisteniKnihy;
    }

    public void setPoradoveCislo(int poradoveCislo) {
        this.poradoveCislo = poradoveCislo;
    }

    public void setNazevKnihy(String nazevKnihy) {
        this.nazevKnihy = nazevKnihy;
    }

    public void setAutorKnihy(String autorKnihy) {
        this.autorKnihy = autorKnihy;
    }

    public void setRokKnihy(int rokKnihy) {
        this.rokKnihy = rokKnihy;
    }

    public void setVydavatelstviKnihy(String vydavatelstviKnihy) {
        this.vydavatelstviKnihy = vydavatelstviKnihy;
    }

    public void setZanrKnihy(String zanrKnihy) {
        this.zanrKnihy = zanrKnihy;
    }

    public void setJazykKnihy(String jazykKnihy) {
        this.jazykKnihy = jazykKnihy;
    }

    public void setUmisteniKnihy(String umisteniKnihy) {
        this.umisteniKnihy = umisteniKnihy;
    }

    public int getPoradoveCislo() {
        return poradoveCislo;
    }

    public String getNazevKnihy() {
        return nazevKnihy;
    }

    public String getAutorKnihy() {
        return autorKnihy;
    }

    public int getRokKnihy() {
        return rokKnihy;
    }

    public String getVydavatelstviKnihy() {
        return vydavatelstviKnihy;
    }

    public String getZanrKnihy() {
        return zanrKnihy;
    }

    public String getJazykKnihy() {
        return jazykKnihy;
    }

    public String getUmisteniKnihy() {
        return umisteniKnihy;
    }

    @Override
    public String toString() {
        if (nazevKnihy.equals("--zaznam smazan--")) {
            return poradoveCislo + "   " + nazevKnihy;
        } else {
            return poradoveCislo + " - " + nazevKnihy + " - " + autorKnihy + " - " + rokKnihy + " - " + vydavatelstviKnihy + " - " + zanrKnihy + " - " + jazykKnihy + " - " + umisteniKnihy;
        }        
    }    
}
