`Note: The task was solved in Visual Studio Code Environment`

#### 1. Only approved and archived suppliers

Since there is already a way of checking if the Suppliers are approved in the function `generateTable()`, I would add to that:

```java
if (!lieferant.getStatus().equalsIgnoreCase("zugelassen") && !lieferant.getStatus().equalsIgnoreCase("archiviert")) {
    continue;
}
```

---

#### 2. Take into account the evaluation categories 4 + 5 as well

There already is a function for the overall calculation, `calcGesamtBewertung()` so I would add the categories 4 and 5 to that:

```java
Integer gesamtPunkte = lieferant.getBewKat1() + lieferant.getBewKat2() + lieferant.getBewKat3()
        + lieferant.getBewKat4() + lieferant.getBewKat5();
```

---

#### 3. Add commodity groups of the suppliers in plain names

First thing that came to mind was creating a Warengruppen object to keep the Group variables (WGID and Title).

- Created the object on `Warengruppen.java`

```java
public class Warengruppen {
  public Warengruppen(String WGID, String warenGruppenTitel) {
    WarenGruppenTitel = warenGruppenTitel;
    this.WGID = WGID;
  }

  private String WarenGruppenTitel;
  private String WGID;

  public String getWarenGruppenTitel() {
    return WarenGruppenTitel;
  }

  public void setWarenGruppenTitel(String warenGruppenTitel) {
    WarenGruppenTitel = warenGruppenTitel;
  }

  public String getWGID() {
    return WGID;
  }

  public void setWGID(String WGID) {
    this.WGID = WGID;
  }
}
```

After creating the Warengruppen object, I had to get the data from the file provided `Warengruppen.csv`, so in the file `CSVImporter.java` I created a function `getWarenGruppenFromCSV()`, similar to `getLieferantenFromCSV()`.

- Created `getWarenGruppenFromCSV()` in `CSVImporter.java`

```java
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
```

The last thing that needed to be done was to actually check if the WGID of the file `LieferantenDaten.csv` was matching the one in `Warengruppen.csv` with the respective Warengruppen Title. I did this in the existing `generateTable()` function on file `TabellenGenerator.java`.

- Declared `warenGruppenCSV`:

```java
String warenGruppenCSV = "./resources/Warengruppen.csv";
```

- Declared `csvFileWarengruppen`:

```java
File csvFileWarengruppen = new File(warenGruppenCSV);
```

- Updated `generateTable()` function and calling:

```java
generateTable(alleLieferanten, alleWarengruppen, "./resources/Output.csv");
```

```java
private static void generateTable(ArrayList<Lieferant> alleLieferanten, ArrayList<Warengruppen> alleWarengruppen,
      String outputPfad) throws FileNotFoundException, UnsupportedEncodingException {
    //Code Here
}
```

- Added the condition to check if the WGID of `lieferant` and `werengruppe` was matching:

```java
for (Warengruppen warengruppe : alleWarengruppen) {
    if (lieferant.getWGID().equals(warengruppe.getWGID())) {
        outputValues.put("Warengruppen", warengruppe.getWarenGruppenTitel());
    }
}
```

- Changed the output PrintWriter to print the updated `Warengruppen` category:

```java
csvFileOutputTabelle.printf(
    "%s; %s; %s; %s; %s; %s; %s; %s; %s\n", outputValues.get("LieferantenBezeichnung"),
    outputValues.get("Warengruppen"), outputValues.get("LfNR"),
    outputValues.get("Bew Kat 1"), outputValues.get("Bew Kat 2"), outputValues.get("Bew Kat 3"),
    outputValues.get("Bew Kat 4"), outputValues.get("Bew Kat 5"), outputValues.get("Gesamtbewertung")
);
```

### Output File after Changes

```csv
Buerobedarf Test AG;Musterholz GmbH;Metalltester GmbH;Kunst & Stoff AG;Klein Holz AG;Holzvertrieb AG;
LieferantenBezeichnung; Warengruppen; LfNR; Bew Kat 1; Bew Kat 2; Bew Kat 3; Bew Kat 4; Bew Kat 5; Gesamtbewertung
Buerobedarf Test AG; Bürobedarf; 01; 10; 20; 30; 5; 30; sehr gut
Musterholz GmbH; Holz; 02; 20; 20; 30; 20; 30; sehr gut
Metalltester GmbH; Metall; 03; 0; 20; 30; 15; 20; sehr gut
Kunst & Stoff AG; Kunststoff; 04; 0; 5; 30; 11; 20; sehr gut
Klein Holz AG; Holz; 05; 20; 20; 30; 15; 12; sehr gut
Holzvertrieb AG; Holz; 09; 0; 20; 30; 8; 7; sehr gut

```
