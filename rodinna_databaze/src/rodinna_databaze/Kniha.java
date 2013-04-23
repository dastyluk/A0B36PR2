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
//        super(poradoveCislo, nazevKnihy, autorKnihy, rokKnihy, vydavatelstviKnihy, zanrKnihy, jazykKnihy, umisteniKnihy);
    }

    @Override
    public void setParam1(int poradoveCislo) {
        this.param1 = poradoveCislo;
    }

    @Override
    public void setParam2(String nazevKnihy) {
        this.param2 = nazevKnihy;
    }

    @Override
    public void setParam3(String autorKnihy) {
        this.param3 = autorKnihy;
    }

    @Override
    public void setParam4(int rokKnihy) {
        this.param4 = rokKnihy;
    }

    @Override
    public void setParam5(String vydavatelstviKnihy) {
        this.param5 = vydavatelstviKnihy;
    }

    @Override
    public void setParam6(String zanrKnihy) {
        this.param6 = zanrKnihy;
    }

    @Override
    public void setParam7(String jazykKnihy) {
        this.param7 = jazykKnihy;
    }

    @Override
    public void setParam8(String umisteniKnihy) {
        this.param8 = umisteniKnihy;
    }

    @Override
    public int getParam1() {
        return param1;
    }

    @Override
    public String getParam2() {
        return param2;
    }

    @Override
    public String getParam3() {
        return param3;
    }

    @Override
    public int getParam4() {
        return param4;
    }

    @Override
    public String getParam5() {
        return param5;
    }

    @Override
    public String getParam6() {
        return param6;
    }

    @Override
    public String getParam7() {
        return param7;
    }

    @Override
    public String getParam8() {
        return param8;
    }
    
    @Override
    public int compareTo(PrvekDatabaze o) {
        return param1 - o.param1;
    }

    @Override
    public String toString() {
        /*if (nazevKnihy.equals("--zaznam smazan--")) {
            return poradoveCislo + "   " + nazevKnihy;
        } else {
            return poradoveCislo + " - " + nazevKnihy + " - " + autorKnihy + " - " + rokKnihy + " - " + vydavatelstviKnihy + " - " + zanrKnihy + " - " + jazykKnihy + " - " + umisteniKnihy;
        }*/
        return param1 + " - " + param2 + " - " + param3 + " - " + param4 + " - " + param5 + " - " + param6 + " - " + param7 + " - " + param8;
    }    
}
