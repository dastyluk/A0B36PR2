package rodinna_databaze;

import java.io.Serializable;

/**
 *
 * @author Lukáš Dastych
 */
class Potravina extends PrvekDatabaze implements Serializable {
    public Potravina(int poradoveCislo, String nazevKnihy, String autorKnihy, int rokKnihy, 
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
