/**
 * PrvekDatabaze.java
 * Semestrální práce na A0B36PR2  =  RODINNÁ DATABÁZE
 * @author Lukáš Dastych
 * Začátek tvorby: 22.2.2013
 * Databáze knih určená pro domácí použití.
 */
package rodinna_databaze;

import java.util.Comparator;

/**
 * 8 tříd jako zdroj pro Comparator (Sorter) - pro řazení záznamů v ArrayListu
 */
class razeni1 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param1 - o2.param1;
    }
}

class razeni2 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
       return o1.param2.compareTo(o2.param2);
       // return o1.compareTo(o2);
   //           return o1.nazev - o2.nazev;
    }
}

class razeni3 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param3.compareTo(o2.param3);
    }
}

class razeni4 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param4 - o2.param4;
    }
}

class razeni5 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param5.compareTo(o2.param5);
    }
}

class razeni6 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param6.compareTo(o2.param6);
    }
}

class razeni7 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param7.compareTo(o2.param7);
    }
}

class razeni8 implements Comparator<PrvekDatabaze>{
    @Override
    public int compare(PrvekDatabaze o1, PrvekDatabaze o2) {
      return o1.param8.compareTo(o2.param8);
    }
}

/**
 * Abstraktní třída definující rozhraní pro tvorbu objektů ukládaných do
 * databáze (Kniha, Potravina, ...)
 * Příprava na vytvoření dalších databází spravovaných tímto programem
 */
public abstract class PrvekDatabaze {
    int param1;
    String param2;
    String param3;
    int param4;
    String param5;
    String param6;
    String param7;
    String param8;

    abstract int getParam1();
    abstract String getParam2();
    abstract String getParam3();
    abstract int getParam4();
    abstract String getParam5();
    abstract String getParam6();
    abstract String getParam7();
    abstract String getParam8();
    abstract void setParam1(int param1);
    abstract void setParam2(String param2);
    abstract void setParam3(String param3);
    abstract void setParam4(int param4);
    abstract void setParam5(String param5);
    abstract void setParam6(String param6);
    abstract void setParam7(String param7);
    abstract void setParam8(String param8);    
    abstract int compareTo(PrvekDatabaze o);
}