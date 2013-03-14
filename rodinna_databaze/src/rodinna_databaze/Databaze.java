package rodinna_databaze;

import java.util.Scanner;

/**
 *
 * @author Lukáš Dastych
 */
public class Databaze {
    
    Scanner vstup = new Scanner(System.in);

    public PrvekDatabaze nactiNovouKnihu(int pocetKnih) {
        PrvekDatabaze pomocnaKniha;

        System.out.printf("%n%n DATABAZE KNIH -> NOVY ZAZNAM%n");
        System.out.printf("==============================%n%n");

        System.out.printf("Zadej nazev knihy: ");
        String nazevKnihy = vstup.nextLine();
        System.out.printf("Zadej autora knihy: ");
        String autorKnihy = vstup.nextLine();
        System.out.printf("Zadej rok vydani knihy: ");
        int rokKnihy = vstup.nextInt();
        String ztratZnak = vstup.nextLine();
        System.out.printf("Zadej vydavatelstvi knihy: ");
        String vydavatelstviKnihy = vstup.nextLine();
        System.out.printf("Zadej zanr knihy: ");
        String zanrKnihy = vstup.nextLine();
        System.out.printf("Zadej jazyk textu knihy: ");
        String jazykKnihy = vstup.nextLine();
        System.out.printf("Zadej umisteni knihy v knihovne: ");
        String umisteniKnihy = vstup.nextLine();

        pomocnaKniha = new Kniha(pocetKnih + 1, nazevKnihy, autorKnihy, rokKnihy, vydavatelstviKnihy, zanrKnihy, jazykKnihy, umisteniKnihy);

        System.out.printf("%nNove vytvoreny zaznam: %n");
        //System.out.println("%4d  %-20s  %-15s  %4d  %-17s  %-10s  %-10s  %-8s%n", pomocnaKniha.getParam1(), pomocnaKniha.getParam2(), pomocnaKniha.getParam3(), pomocnaKniha.getParam4(), pomocnaKniha.getParam5(), pomocnaKniha.getParam6(), pomocnaKniha.getParam7(), pomocnaKniha.getParam8());  //zobrazeniKnihy
        System.out.print(pomocnaKniha.getParam1()); System.out.print(" - ");
        System.out.print(pomocnaKniha.getParam2()); System.out.print(" - ");
        System.out.print(pomocnaKniha.getParam3()); System.out.print(" - ");
        System.out.print(pomocnaKniha.getParam4()); System.out.print(" - ");
        System.out.print(pomocnaKniha.getParam5()); System.out.print(" - ");
        System.out.print(pomocnaKniha.getParam6()); System.out.print(" - ");
        System.out.print(pomocnaKniha.getParam7()); System.out.print(" - ");
        System.out.println(pomocnaKniha.getParam8());

        System.out.printf("%nPro navrat do hlavniho MENU stiskni ENTER.%n");
        //cekejEnter();
        ztratZnak = vstup.nextLine();

        return (pomocnaKniha);
    }
}
