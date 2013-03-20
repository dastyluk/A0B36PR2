A0B36PR2
========

Semestrální práce do předmětu A0B36PR2

Semestrální práce pro tento semestr bude rozšířením semestrální práce ze semestru předcházejícího a to:

          DATABÁZE KNIH
        =================

Původní zadání:
--------------------------------
   Evidence knih domácí knihovny (název knihy, autor, země a rok vydání, 
žánr, vydavatelství, pořadové číslo, umístění v knihovně). V textovém 
uživatelském prostředí bude možné zvolit požadovanou operaci (vkládání nových, 
mazání starých nebo editace stávající záznamů uložených v souboru; možnost 
vyhledávání podle zadaného výrazu, třídění knih podle různých kriterií a 
následné zobrazení v uživatelském prostředí nebo výpis do souboru, se kterým 
je možné následně pracovat nebo si ho například vytisknout.

Hlavní funkce aplikace:
 - vkládání záznamů
 - mazání záznamů
 - editace záznamů
 - výpis a seřazení záznamů podle kritérií
 - vyhledávání záznamů
 - export vyhledaných knih do textového souboru (čitelné pro tisk).

Vize do budoucna:
   Vytvořit program zahrnující všechny databáze, které doma využíváme 
(databáze knih, potravin v komoře, spotřeby a údržby auta, spotřeba energií, 
atd.). Program bude mít vstupní menu s výběrem databáze ve, které bude uživatel 
chtít pracovat. Všechny databáze budou postaveny na stejném základu a každá bude 
mít své odlišující možnosti. Uživatelské prostředí by mělo být v grafické podobě.
----------------------------------------------------------------------------------------------------------

   Hlavní funkce aplikace zůstavají přibliženě stejné změna se bude týkat především 
interní práce s databází (záznamy) a program bude mít grafické uživatelské rozhraní.
Aplikace by měla obsahovat oproti předcházející také větší množství operací pro
vyhledávání a třídění záznamů. Aplikace by měla také být připravena pro přidání další
databáze (například potravin na skladě).

Nástin řešení problému:
   Předmět databáze (kniha, potravina ...) je reprezentována jako objekt své třídy.
Předměty jsou ukládány do tabulek SQL databáze. Samotná práce například s databází knih
uvnitř programu je prováděna v databázi uložené do ArrayListu při spuštění programu.
Operace jako "nový", "uprav", "smaž" jsou prováděny ihned jak v ArrayListu, tak i v
SQL databázi.

Použité technologie:
  - GUI
  - SQL databáze
  - kolekce (ArrayList)
  - soubory
