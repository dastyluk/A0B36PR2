package rodinna_databaze;

import java.util.Comparator;

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
 *
 * @author Lukáš Dastych
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

    public PrvekDatabaze(int param1, String param2, String param3, int param4, String param5, String param6, String param7, String param8) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = param5;
        this.param6 = param6;
        this.param7 = param7;
        this.param8 = param8;
    }

    public int getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String getParam3() {
        return param3;
    }

    public int getParam4() {
        return param4;
    }

    public String getParam5() {
        return param5;
    }

    public String getParam6() {
        return param6;
    }

    public String getParam7() {
        return param7;
    }

    public String getParam8() {
        return param8;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public void setParam4(int param4) {
        this.param4 = param4;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    public void setParam7(String param7) {
        this.param7 = param7;
    }

    public void setParam8(String param8) {
        this.param8 = param8;
    }
    
    public int compareTo(PrvekDatabaze o) {
        return param1 - o.param1;
    }

    @Override
    public String toString() {
        return param1 + " - " + param2 + " - " + param3 + " - " + param4 + " - " + param5 + " - " + param6 + " - " + param7 + " - " + param8;
    }
    
    
    
}
