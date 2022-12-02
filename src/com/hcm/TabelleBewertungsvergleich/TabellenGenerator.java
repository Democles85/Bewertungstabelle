package com.hcm.TabelleBewertungsvergleich;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TabellenGenerator {

  public static void main(String[] args) {

    // Import files
    String pfadInputCSV = "./resources/LieferantenDaten.csv";
    String warenGruppenCSV = "./resources/Warengruppen.csv";

    int rowsNumber = 0;
    int columnsNumber = 0;

    File csvFileLieferanten = new File(pfadInputCSV);
    File csvFileWarengruppen = new File(warenGruppenCSV);

    ArrayList<Lieferant> alleLieferanten = CSVImporter.getLieferantenFromCSV(csvFileLieferanten);
    ArrayList<Warengruppen> alleWarengruppen = CSVImporter.getWarenGruppenFromCSV(csvFileWarengruppen);

    // Testing Warengruppen for the correct Lieferanten
    // for(Lieferant lieferant : alleLieferanten) {
    // for(Warengruppen warengruppe : alleWarengruppen) {
    // if(lieferant.getWGID().equals(warengruppe.getWGID())) {
    // System.out.println("Lieferant: " + lieferant.getLieferantenBezeichnung() + "
    // hat die Warengruppe: " + warengruppe.getWarenGruppenTitel());
    // }
    // }
    // }

    try {
      // Output file
      rowsNumber = getFileRowsNumber("./resources/Output.csv");
      columnsNumber = getFileColumnsNumber("./resources/Output.csv");
      System.out.println("Rows: " + rowsNumber + " Columns: " + columnsNumber);
      generateTable(alleLieferanten, alleWarengruppen, "./resources/Output.csv");
      // transposeTable(alleLieferanten, alleWarengruppen,
      // "./resources/OutputTransposed.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

  }

  // --------------------------------------------------------------------------------------------
  private static void generateTable(ArrayList<Lieferant> alleLieferanten, ArrayList<Warengruppen> alleWarengruppen,
      String outputPfad)
      throws FileNotFoundException, UnsupportedEncodingException {

    PrintWriter csvFileOutputTabelle = new PrintWriter(outputPfad, "UTF-8");

    for (Lieferant lieferant : alleLieferanten) {
      if (lieferant.getStatus().equals("zugelassen")
          || lieferant.getStatus().equals("archiviert")) {
        csvFileOutputTabelle.print(lieferant.getLieferantenBezeichnung() + ";");
      }
    }
    csvFileOutputTabelle.println();
    csvFileOutputTabelle
        .println(
            "LieferantenBezeichnung; Warengruppen; LfNR; Bew Kat 1; Bew Kat 2; Bew Kat 3; Bew Kat 4; Bew Kat 5; Gesamtbewertung");
    
    // Put the values of the first column of Output file
    

    HashMap<String, String> outputValues = new HashMap<>();

    for (Lieferant lieferant : alleLieferanten) {
      if (!lieferant.getStatus().equalsIgnoreCase("zugelassen")
          && !lieferant.getStatus().equalsIgnoreCase("archiviert")) {
        continue;
      }

      outputValues.put("LieferantenBezeichnung", lieferant.getLieferantenBezeichnung());
      for (Warengruppen warengruppe : alleWarengruppen) {
        if (lieferant.getWGID().equals(warengruppe.getWGID())) {
          outputValues.put("Warengruppen", warengruppe.getWarenGruppenTitel());
        }
      }
      outputValues.put("LfNR", lieferant.getLfNR());
      outputValues.put("Bew Kat 1", lieferant.getBewKat1().toString());
      outputValues.put("Bew Kat 2", lieferant.getBewKat2().toString());
      outputValues.put("Bew Kat 3", lieferant.getBewKat3().toString());
      // Include the remaining categories here
      outputValues.put("Bew Kat 4", lieferant.getBewKat4().toString());
      outputValues.put("Bew Kat 5", lieferant.getBewKat5().toString());
      // --------------------------------------------------------------------------------------------
      // Calculate the total rating
      outputValues.put("Gesamtbewertung", calcGesamtBewertung(lieferant));

      // Write Bew Kat 4 and 5 field to the output file
      csvFileOutputTabelle.printf(
          "%s; %s; %s; %s; %s; %s; %s; %s; %s\n", outputValues.get("LieferantenBezeichnung"),
          outputValues.get("Warengruppen"), outputValues.get("LfNR"),
          outputValues.get("Bew Kat 1"), outputValues.get("Bew Kat 2"), outputValues.get("Bew Kat 3"),
          outputValues.get("Bew Kat 4"), outputValues.get("Bew Kat 5"), outputValues.get("Gesamtbewertung"));
    }
    csvFileOutputTabelle.close();
  }

  // --------------------------------------------------------------------------------------------
  // Trying to transpose the HashMap by knowing the number of rows and columns (Didn't work) :P
  private static int getFileColumnsNumber(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
    File file = new File(fileName);
    Scanner scanner;
    scanner = new Scanner(file);

    int columnsNumber = 0;
    if (scanner.hasNextLine()) {
      columnsNumber = scanner.nextLine().split(";").length;
    }
    scanner.close();
    return columnsNumber;
  }

  private static int getFileRowsNumber(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
    File file = new File(fileName);
    Scanner scanner;
    scanner = new Scanner(file);

    int rowsNumber = 0;
    while (scanner.hasNextLine()) {
      scanner.nextLine();
      rowsNumber++;
    }
    scanner.close();
    return rowsNumber;
  }

  private static String calcGesamtBewertung(Lieferant lieferant) {

    // Total points calculation formula
    Integer gesamtPunkte = lieferant.getBewKat1() + lieferant.getBewKat2() + lieferant.getBewKat3()
        + lieferant.getBewKat4() + lieferant.getBewKat5();

    if (gesamtPunkte >= 61) {
      return "sehr gut";
    } else if (gesamtPunkte >= 51) {
      return "gut";
    } else if (gesamtPunkte >= 41) {
      return "OK";
    } else {
      return "schlecht";
    }
  }
}
