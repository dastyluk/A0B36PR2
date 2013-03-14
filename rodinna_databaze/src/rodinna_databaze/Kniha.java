package rodinna_databaze;

import java.io.*;
//import java.util.Comparator;
//
//class razeni1Kniha extends rodinna_databaze.razeni1 implements Comparator<Kniha> {
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.poradoveCislo - o2.poradoveCislo;
//    }
//}
//
//class razeni2 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//       return o1.nazevKnihy.compareTo(o2.nazevKnihy);
//       // return o1.compareTo(o2);
//   //           return o1.nazev - o2.nazev;
//    }
//}
//
//class razeni3 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.autorKnihy.compareTo(o2.autorKnihy);
//    }
//}
//
//class razeni4 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.rokKnihy - o2.rokKnihy;
//    }
//}
//
//class razeni5 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.vydavatelstviKnihy.compareTo(o2.vydavatelstviKnihy);
//    }
//}
//
//class razeni6 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.zanrKnihy.compareTo(o2.zanrKnihy);
//    }
//}
//
//class razeni7 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.jazykKnihy.compareTo(o2.jazykKnihy);
//    }
//}
//
//class razeni8 implements Comparator<Kniha>{
//    @Override
//    public int compare(Kniha o1, Kniha o2) {
//      return o1.umisteniKnihy.compareTo(o2.umisteniKnihy);
//    }
//}

/**
 *
 * @author Lukáš Dastych
 */
class Kniha extends PrvekDatabaze implements Serializable {

    int poradoveCislo;
    String nazevKnihy;
    String autorKnihy;
    int rokKnihy;
    String vydavatelstviKnihy;
    String zanrKnihy;
    String jazykKnihy;
    String umisteniKnihy;

    /*public Kniha() {  //pro vlozeni na konec souboru
        this.poradoveCislo = 0;        
        this.nazevKnihy = "";
        this.autorKnihy = "";
        this.rokKnihy = 0;
        this.vydavatelstviKnihy = "";
        this.zanrKnihy = "";
        this.jazykKnihy = "";
        this.umisteniKnihy = "";
    }*/

    /*public Kniha(int poradoveCislo) {  //konstruktor pro smazany zaznam
        this.poradoveCislo = poradoveCislo;
        this.nazevKnihy = "--zaznam smazan--";
        this.autorKnihy = "";
        this.rokKnihy = 0;
        this.vydavatelstviKnihy = "";
        this.zanrKnihy = "";
        this.jazykKnihy = "";
        this.umisteniKnihy = "";
    }*/
    
    

    public Kniha(int poradoveCislo, String nazevKnihy, String autorKnihy, int rokKnihy, String vydavatelstviKnihy, String zanrKnihy, String jazykKnihy, String umisteniKnihy) {
        //hlavni konstruktor
        /*this.poradoveCislo = poradoveCislo;
        this.nazevKnihy = nazevKnihy;
        this.autorKnihy = autorKnihy;
        this.rokKnihy = rokKnihy;
        this.vydavatelstviKnihy = vydavatelstviKnihy;
        this.zanrKnihy = zanrKnihy;
        this.jazykKnihy = jazykKnihy;
        this.umisteniKnihy = umisteniKnihy;*/
        super(poradoveCislo, nazevKnihy, autorKnihy, rokKnihy, vydavatelstviKnihy, zanrKnihy, jazykKnihy, umisteniKnihy);
    }

//    @Override
//    public void setParam1(int poradoveCislo) {
//        this.poradoveCislo = poradoveCislo;
//    }
//
//    @Override
//    public void setParam2(String nazevKnihy) {
//        this.nazevKnihy = nazevKnihy;
//    }
//
//    @Override
//    public void setParam3(String autorKnihy) {
//        this.autorKnihy = autorKnihy;
//    }
//
//    @Override
//    public void setParam4(int rokKnihy) {
//        this.rokKnihy = rokKnihy;
//    }
//
//    @Override
//    public void setParam5(String vydavatelstviKnihy) {
//        this.vydavatelstviKnihy = vydavatelstviKnihy;
//    }
//
//    @Override
//    public void setParam6(String zanrKnihy) {
//        this.zanrKnihy = zanrKnihy;
//    }
//
//    @Override
//    public void setParam7(String jazykKnihy) {
//        this.jazykKnihy = jazykKnihy;
//    }
//
//    @Override
//    public void setParam8(String umisteniKnihy) {
//        this.umisteniKnihy = umisteniKnihy;
//    }
//
//    @Override
//    public int getParam1() {
//        return poradoveCislo;
//    }
//
//    @Override
//    public String getParam2() {
//        return nazevKnihy;
//    }
//
//    @Override
//    public String getParam3() {
//        return autorKnihy;
//    }
//
//    @Override
//    public int getParam4() {
//        return rokKnihy;
//    }
//
//    @Override
//    public String getParam5() {
//        return vydavatelstviKnihy;
//    }
//
//    @Override
//    public String getParam6() {
//        return zanrKnihy;
//    }
//
//    @Override
//    public String getParam7() {
//        return jazykKnihy;
//    }
//
//    @Override
//    public String getParam8() {
//        return umisteniKnihy;
//    }
//    
//    public int compareTo(Kniha o) {
//        return poradoveCislo - o.poradoveCislo;
//    }

    @Override
    public String toString() {
        /*if (nazevKnihy.equals("--zaznam smazan--")) {
            return poradoveCislo + "   " + nazevKnihy;
        } else {
            return poradoveCislo + " - " + nazevKnihy + " - " + autorKnihy + " - " + rokKnihy + " - " + vydavatelstviKnihy + " - " + zanrKnihy + " - " + jazykKnihy + " - " + umisteniKnihy;
        }*/
        return poradoveCislo + " - " + nazevKnihy + " - " + autorKnihy + " - " + rokKnihy + " - " + vydavatelstviKnihy + " - " + zanrKnihy + " - " + jazykKnihy + " - " + umisteniKnihy;
    }    
}
