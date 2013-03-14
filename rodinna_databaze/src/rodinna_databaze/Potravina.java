package rodinna_databaze;

/**
 *
 * @author Lukáš Dastych
 */
public class Potravina extends PrvekDatabaze {
    int poradoveCislo;
    String typ;
    String nazev;
    int mnozstvi;
    String datum1;
    String datum2;
    String datum3;
    String datum4;

    public Potravina(int poradoveCislo, String typ, String nazev, int mnozstvi, String datum1, String datum2, String datum3, String datum4) {
        super(poradoveCislo, typ, nazev, mnozstvi, datum1, datum2, datum3, datum4);
        /*this.poradoveCislo = poradoveCislo;
        this.typ = typ;
        this.nazev = nazev;
        this.mnozstvi = mnozstvi;
        this.datum1 = datum1;
        this.datum2 = datum2;
        this.datum3 = datum3;
        this.datum4 = datum4;*/
    }

//    @Override
//    public int getParam1() {
//        return poradoveCislo;
//    }
//
//    @Override
//    public String getParam2() {
//        return typ;
//    }
//
//    @Override
//    public String getParam3() {
//        return nazev;
//    }
//
//    @Override
//    public int getParam4() {
//        return mnozstvi;
//    }
//
//    @Override
//    public String getParam5() {
//        return datum1;
//    }
//
//    @Override
//    public String getParam6() {
//        return datum2;
//    }
//
//    @Override
//    public String getParam7() {
//        return datum3;
//    }
//
//    @Override
//    public String getParam8() {
//        return datum4;
//    }
//
//    @Override
//    public void setParam1(int poradoveCislo) {
//        this.poradoveCislo = poradoveCislo;
//    }
//
//    @Override
//    public void setParam2(String typ) {
//        this.typ = typ;
//    }
//
//    @Override
//    public void setParam3(String nazev) {
//        this.nazev = nazev;
//    }
//
//    @Override
//    public void setParam4(int mnozstvi) {
//        this.mnozstvi = mnozstvi;
//    }
//
//    @Override
//    public void setParam5(String datum1) {
//        this.datum1 = datum1;
//    }
//
//    @Override
//    public void setParam6(String datum2) {
//        this.datum2 = datum2;
//    }
//
//    @Override
//    public void setParam7(String datum3) {
//        this.datum3 = datum3;
//    }
//
//    @Override
//    public void setParam8(String datum4) {
//        this.datum4 = datum4;
//    }
//    
//    public int compareTo(Kniha o) {
//        return poradoveCislo - o.poradoveCislo;
//    }

    @Override
    public String toString() {
        return poradoveCislo + " - " + typ + " - " + nazev + " - " + mnozstvi + " - " + datum1 + " - " + datum2 + " - " + datum3 + " - " + datum4;
    }    
    
}
