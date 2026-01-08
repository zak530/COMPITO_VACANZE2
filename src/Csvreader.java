// Csvreader.java
import java.io.*;
import java.util.Random;

public class Csvreader {

    public static void inizializzaCsv(String nomeFile) {
        String inputFile = nomeFile;
        String outputFile = "elhaiki_modificato2.csv";
        Random random = new Random();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;

            // Header
            if ((line = br.readLine()) != null) {
                bw.write(line + ",miovalore,cancellato");
                bw.newLine();
            } else {
                System.out.println("File vuoto: " + inputFile);
                return;
            }

            // Righe dati
            while ((line = br.readLine()) != null) {
                int miovalore = 10 + random.nextInt(11); // 10..20
                bw.write(line + "," + miovalore + ",false");
                bw.newLine();
            }

            System.out.println("Elaborazione completata. File salvato come: " + outputFile);

        } catch (IOException e) {
            System.err.println("Errore durante l'elaborazione del file: " + e.getMessage());
        }
    }

    public static int contaCampi(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String header = br.readLine();
            if (header == null) return 0;

            String[] campi = header.split(",", -1); // mantiene anche campi vuoti
            return campi.length;

        } catch (IOException e) {
            System.err.println("Errore lettura file: " + e.getMessage());
            return 0;
        }
    }

    // NUOVA FUNZIONE: lunghezza massima dei record (escluso header)
    public static int lunghezzaMassimaRecord(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {

            // salto l'header
            String line = br.readLine();
            if (line == null) return 0;

            int maxLen = 0;

            // scorro i record
            while ((line = br.readLine()) != null) {
                int len = line.length();
                if (len > maxLen) maxLen = len;
            }

            return maxLen;

        } catch (IOException e) {
            System.err.println("Errore lettura file: " + e.getMessage());
            return 0;
        }
    }
}
