package rodinna_databaze;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Lukáš Dastych
 */
public class ObsluhaUdalosti {

    static PrvekDatabaze kniha;
    static JTable table;
    static JTextField field;

    static void udalostMetOblastNabidkaButtonNovy() {
        int pocetZaznamu = Main.vratPocetZaznamu();
        OknoOblastNabidkaNovy okno2 = new OknoOblastNabidkaNovy(pocetZaznamu);
        okno2.setVisible(true);
    }

    static void udalostMetOblastNabidkaButtonUprav(int cislo) {
        int cisloKnihy = cislo;
        kniha = Main.vratZaznamPodleCisla(cisloKnihy);
        if (kniha.getParam1() == 0) {
            JOptionPane.showMessageDialog(null, "Záznam s tímto pořadovým číslem neexistuje!", "Mazání záznamu", JOptionPane.ERROR_MESSAGE);
            Main.nastavOblastHlaseni("Připraven");
        } else {
            if (kniha.getParam2().equals("ZÁZNAM SMAZÁN!")) {
                JOptionPane.showMessageDialog(null, "Pokus o úpravu smazaného záznamu!", "Úprava záznamu", JOptionPane.ERROR_MESSAGE);
                Main.nastavOblastHlaseni("Připraven");
            } else {
                OknoOblastNabidkaUprav okno2 = new OknoOblastNabidkaUprav(kniha);
                okno2.setVisible(true);
            }            
        }

    }

    static void udalostMetOblastNabidkaButtonSmaz(int cislo) {
        try {
            int cisloKnihy = cislo;
            kniha = Main.vratZaznamPodleCisla(cisloKnihy);
            if (kniha.getParam1() == 0) {
                JOptionPane.showMessageDialog(null, "Záznam s tímto pořadovým číslem neexistuje!", "Mazání záznamu", JOptionPane.ERROR_MESSAGE);
            } else {
                String hlaska = String.format("Opravdu chcete smazat tento záznam? \n %d - %s - %s - %d - %s - %s - %s - %s",
                        kniha.getParam1(), kniha.getParam2(), kniha.getParam3(),
                        kniha.getParam4(), kniha.getParam5(), kniha.getParam6(),
                        kniha.getParam7(), kniha.getParam8());
                GUI_hl okno2 = new GUI_hl();
                Object[] options = {"Ano", "Ne"};
                switch (JOptionPane.showOptionDialog(okno2,
                        hlaska,
                        "Mazání záznamu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, //nepouzivat zakladni ikony
                        options, //napisy ikon
                        options[0])) {  //defaultni ikona             
                    //showConfirmDialog     JOptionPane.ERROR_MESSAGE)){
                    case JOptionPane.OK_OPTION:  //ukonci program
                        kniha = new Kniha(cisloKnihy, "ZÁZNAM SMAZÁN!", "-", 0, "-", "-", "-", "-");
                        Main.upravZaznamVSQLDatabaziAArrayListu(cisloKnihy, kniha);
                        Main.mainVypisTabulkuDoOblastiHlavni();
                    case JOptionPane.CANCEL_OPTION:  //rozmyslel si to, nedelej nic.
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GUI_hl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void udalostMetOblastNabidkaButtonTisk(JTable tab) {
        table = tab;
        MessageFormat header = new MessageFormat("Strana {0,number,integer}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
        } catch (java.awt.print.PrinterException ee) {
            JOptionPane.showMessageDialog(null, "Chyba tisku!", "Tisk", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void udalostMetOblastNabidkaButtonExport(JTable tab) {
        table = tab;
        List<String> arrayList;
        arrayList = new ArrayList<>();
        String zaznam;
        String nazevSouboru = "";
        int[] noveOcislovani = new int[1000];

        do {
            String dialog = JOptionPane.showInputDialog(null, "Zadejte název souboru pro exportování (bez přípony): ", "Exportování záznamů z Databáze knih", JOptionPane.QUESTION_MESSAGE);
            if (dialog.equals("")) {
                JOptionPane.showMessageDialog(null, "Nezadal jste žádný název.\nExport bude proveden do souboru se \nstandatním názvem \"export_tisk.txt\".", "Exportování záznamů z Databáze knih", JOptionPane.PLAIN_MESSAGE);
                nazevSouboru = "export_tisk.txt";
            } else {
                //vyhledá existenci hledaneho retezce v retezci
                //hodnoty: -1 neobsahuje, >=0 obsahuje    \/:*?"<>|
                //if ((poradoveCislo.indexOf(hledanyVyraz)) != -1) {
                if ((dialog.indexOf("\\") >= 0) || (dialog.indexOf("/") >= 0) || (dialog.indexOf(":") >= 0)
                        || (dialog.indexOf("*") >= 0) || (dialog.indexOf("?") >= 0) || (dialog.indexOf("\"") >= 0)
                        || (dialog.indexOf("<") >= 0) || (dialog.indexOf(">") >= 0) || (dialog.indexOf("|") >= 0)) {
                    JOptionPane.showMessageDialog(null, "Název souboru nesmí obsahovat \nžádný z následujících znaků\n      \\ / : * ? \" < > |", "Exportování záznamů z Databáze knih", JOptionPane.PLAIN_MESSAGE);
                } else {
                    nazevSouboru = dialog + ".txt";
                }
            }
        } while (nazevSouboru.equals(""));


        arrayList.add(String.format("Exportovany vypis z programu Databaze knih: %n%n"));
        arrayList.add(String.format("                                                         D A T A B A Z E   K N I H                                                         %n"));
        arrayList.add(String.format("--------------------------------------------------------------------------------------------------------------------------------------------%n"));
        arrayList.add(String.format("| %4s  %-44s  %-20s  %-4s  %-20s  %-10s  %-10s  %-10s |%n", "P.C.", "NAZEV", "AUTOR", "ROK", "VYDAVATELSTVI", "ZANR", "JAZYK", "UMISTENI"));
        arrayList.add(String.format("--------------------------------------------------------------------------------------------------------------------------------------------%n"));

        for (int k = 0; k < table.getRowCount(); k++) {
            noveOcislovani[k] = table.convertRowIndexToModel(k);
        }

        for (int i = 0; i < table.getRowCount(); i++) {  //radky
            zaznam = "| ";
            for (int j = 0; j < 8; j++) {  //sloupce
                if (j == 0) {
                    zaznam = zaznam + String.format("%4s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                } else {
                    if (j == 1) {
                        zaznam = zaznam + String.format("%-44s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                    } else {
                        if (j == 2) {
                            zaznam = zaznam + String.format("%-20s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                        } else {
                            if (j == 3) {
                                zaznam = zaznam + String.format("%-4s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                            } else {
                                if (j == 4) {
                                    zaznam = zaznam + String.format("%-20s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                                } else {
                                    if (j == 5) {
                                        zaznam = zaznam + String.format("%-10s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                                    } else {
                                        if (j == 6) {
                                            zaznam = zaznam + String.format("%-10s  ", table.getModel().getValueAt(noveOcislovani[i], j));
                                        } else {
                                            zaznam = zaznam + String.format("%-10s ", table.getModel().getValueAt(noveOcislovani[i], j));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            zaznam = zaznam + (String.format("|%n"));
            arrayList.add(zaznam);
            zaznam = "";
        }
        arrayList.add(String.format("--------------------------------------------------------------------------------------------------------------------------------------------%n"));

        try {
            //inicializace textoveho souboru exportu pro cteni a zapis
            //pro postupne ukladani upravene kopie souboru dat_knih.bin     
            File exportTisk = new File(nazevSouboru);
            try (RandomAccessFile exportTiskRW = new RandomAccessFile(exportTisk, "rw")) {
                exportTiskRW.setLength(0);  //smazani obsahu souboru exportTisk.txt

                for (Iterator<String> it = arrayList.iterator(); it.hasNext();) {
                    zaznam = it.next();
                    exportTiskRW.writeBytes(zaznam);
                }
                exportTiskRW.close();
                String hlaskaExportOK = String.format("Záznam byl exportován do souboru %s.", exportTisk);
                JOptionPane.showMessageDialog(null, hlaskaExportOK, "Exportování záznamů z Databáze knih", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ee) {
            JOptionPane.showMessageDialog(null, "Chyba pri exportovani do souboru!", "Exportování záznamů z Databáze knih", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void udalostMetPolozkaMenuPolozka1() {
        int realnyPocetZaznamu = Main.vratRealnyPocetZaznamu();
        String hlaska = String.format("Počet záznamů v Databázi knih je %d. \n        (Bez smazaných záznamů.)", realnyPocetZaznamu);
        JOptionPane.showMessageDialog(null, hlaska, "Počet záznamů v Databázi knih", JOptionPane.INFORMATION_MESSAGE);
    }

    static void udalostMetPolozkaMenuKonec() {
        GUI_hl okno2 = new GUI_hl();
        Object[] options = {"Ano", "Ne"};
        switch (JOptionPane.showOptionDialog(okno2,
                "Opravdu chcete tento program ukončit?",
                "Ukončovací dialog",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //nepouzivat zakladni ikony
                options, //napisy ikon
                options[0])) {  //defaultni ikona             
            //showConfirmDialog     JOptionPane.ERROR_MESSAGE)){
            case JOptionPane.OK_OPTION:  //ukonci program
                System.exit(0);
                break;
            case JOptionPane.CANCEL_OPTION:  //rozmyslel si to, nedelej nic.
            }
    }

    static void udalostMetPolozkaMenuNapoveda() {
        JOptionPane.showMessageDialog(null, "Značení umístění v knihovně\n"
                + "---------------------------\n"
                + " » police jsou číslovány od 1 do 8 (od shora)\n"
                + " » přední část police se značí A, zadní část police se značí B\n"
                + " » každá police je z předního pohledu rozdělena na 4 části,zprava 1 až 4\n\n"
                + "Ovládání programu\n"
                + "---------------------------\n"
                + " » program se ovládá pomocí myši a klávesnice\n"
                + " » v menu si můžete vybrat zda chcete pracovat s daty uloženými v systémové databázi\n"
                + "    nebo data načíst ze souboru\n"
                + " » pro vytvoření nového záznamu zvolte v pravém panelu možnost Nový záznam\n"
                + " » pro úpravu záznamu můžete použít možnost dialogového okna vyvoláním v pravém panelu\n"
                + "    zadáním čísla řádku pro požadovanou úpravu a kliknutím na Upravit záznam nebo\n"
                + "    stačí 2x kliknout do buňky v tabulce kterou chcete upravit a přepsat novým údajem\n"
                + " » pro smazání záznamu zvolte v pravém panelu možnost Smazat záznam s uvedením čísla\n"
                + "    řádku, který chcete smazat; pořadové číslo zůstane, pouze budou smazány údaje o záznamu\n"
                + " » pro hledání zadejte hledaný výraz do pole na konci pravého panelu\n"
                + " » pro třídění podle určitého sloupce klikněte na záhlaví sloupce podle, kterého chcete\n"
                + "    záznamy setřídit, pro třídění v opačném smyslu klikněte znovu\n"
                + " » v celém programu můžete používat všechny znaky podle národních zvyklostí\n"
                + " » pro vytištění právě zobrazených záznamů klikněte v pravém panelu na Tisk\n"
                + " » pro export právě zobrazených záznamů klikněte v pravém panelu na Export a\n"
                + "    zadejte požadovaný název souboru (bez připony, soubor bude typu .txt)\n"
                + " » pro ukončení programu klikněte na ukončovací křížek v pravém horním rohu okna nebo\n"
                + "    v Menu -> Konec\n",
                "Nápověda k programu Databáze knih", JOptionPane.PLAIN_MESSAGE);

    }
}