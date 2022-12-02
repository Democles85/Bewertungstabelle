package com.hcm.TabelleBewertungsvergleich;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVImporter {

  public static ArrayList<String> getLinesFromCSV(String pfad) {
    ArrayList<String> lines = new ArrayList<String>();
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pfad)));
      String line = "";
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return lines;
  }

  public static ArrayList<Warengruppen> getWarenGruppenFromCSV(File csvImportFileWarengruppen) {
    System.out.println("Importing Warengruppen from CSV file: " + csvImportFileWarengruppen.getAbsolutePath());

    String line = "";

    ArrayList<Warengruppen> warenGruppen = new ArrayList<>();

    BufferedReader reader = null;

    boolean firstLine = true;

    try {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvImportFileWarengruppen), "UTF-8"));

      while ((line = reader.readLine()) != null) {
        if (firstLine) {
          firstLine = false;
          continue;
        }

        String[] warenGruppeSplittedLine = line.split(";", -1);
        warenGruppen.add(new Warengruppen(warenGruppeSplittedLine[0].trim(), warenGruppeSplittedLine[1].trim()));

      }
    } catch (Exception e) {
      System.err.println("Fehler beim CSV-Import!");
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (Exception e) {
          System.err.println("Fehler beim CSV-Import!");
        }
      }
    }

    return warenGruppen;

  }

  // --------------------------------------------------------------------------------------------

  public static ArrayList<Lieferant> getLieferantenFromCSV(File csvImportFileLieferanten) {

    System.out.println(
        "Import Datei: " + csvImportFileLieferanten.getName() + " exists: " + csvImportFileLieferanten.exists());

    String line = "";

    BufferedReader reader = null;

    ArrayList<Lieferant> lieferanten = new ArrayList<>();

    boolean firstLine = true;

    try {

      reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvImportFileLieferanten), "UTF-8"));

      while ((line = reader.readLine()) != null) {

        if (firstLine) {
          firstLine = false;
          continue;
        }

        String[] lieferantenInformationenSplittedLine = line.split(";", -1);
        lieferanten.add(new Lieferant(lieferantenInformationenSplittedLine[0].trim(),
            lieferantenInformationenSplittedLine[1].trim(), lieferantenInformationenSplittedLine[2].trim(),
            Integer.parseInt(lieferantenInformationenSplittedLine[3].trim()),
            Integer.parseInt(lieferantenInformationenSplittedLine[4].trim()),
            Integer.parseInt(lieferantenInformationenSplittedLine[5].trim()),
            Integer.parseInt(lieferantenInformationenSplittedLine[6].trim()),
            Integer.parseInt(lieferantenInformationenSplittedLine[7].trim()),
            lieferantenInformationenSplittedLine[8].trim(), lieferantenInformationenSplittedLine[9].trim(),
            lieferantenInformationenSplittedLine[10].trim()));

      }

    } catch (Exception e) {
      System.err.println("Fehler beim CSV-Import!");
    } finally {
      try {
        reader.close();
      } catch (Exception e) {
        System.err.println("Fehler beim CSV-Import!");
      }
    }
    return lieferanten;

  }
}
