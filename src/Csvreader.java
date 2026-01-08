import java.io.*;
import java.util.Random;

public class Csvreader {
    public static void inizializzaCsv(String nomeFile) {
        String inputFile = "elhaiki.csv";
        String outputFile = "elhaiki_modificato2.csv";
        Random random = new Random();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            if ((line = br.readLine()) != null) {
                bw.write(line + ",miovalore,cancellato");
                bw.newLine();
            }

            while ((line = br.readLine()) != null) {
                int miovalore = 10 + random.nextInt(11);
                bw.write(line + "," + miovalore + ",false");
                bw.newLine();
            }

            System.out.println("Elaborazione completata. File salvato come: " + outputFile);

        } catch (IOException e) {
            System.err.println("Errore durante l'elaborazione del file: " + e.getMessage());
        }
    }
}