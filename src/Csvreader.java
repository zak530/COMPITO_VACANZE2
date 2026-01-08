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

            if ((line = br.readLine()) != null) {
                bw.write(line + ",miovalore,cancellato");
                bw.newLine();
            } else {
                System.out.println("File vuoto: " + inputFile);
                return;
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

    public static int contaCampi(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String header = br.readLine();
            if (header == null) return 0;
            String[] campi = header.split(",", -1);
            return campi.length;
        } catch (IOException e) {
            System.err.println("Errore lettura file: " + e.getMessage());
            return 0;
        }
    }

    public static int lunghezzaMassimaRecord(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String line = br.readLine();
            if (line == null) return 0;

            int maxLen = 0;
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

    public static void rendiRecordFissi(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String headerLine = br.readLine();
            if (headerLine == null) {
                System.out.println("File vuoto: " + inputFile);
                return;
            }

            String[] header = headerLine.split(",", -1);
            int cols = header.length;

            int[] maxLen = new int[cols];
            for (int c = 0; c < cols; c++) maxLen[c] = header[c].length();

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                for (int c = 0; c < cols; c++) {
                    String v = (c < fields.length) ? fields[c] : "";
                    if (v.length() > maxLen[c]) maxLen[c] = v.length();
                }
            }

            try (BufferedReader br2 = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

                String header2 = br2.readLine();
                String[] h2 = header2.split(",", -1);

                bw.write(padRow(h2, maxLen));
                bw.newLine();

                while ((line = br2.readLine()) != null) {
                    String[] fields = line.split(",", -1);
                    bw.write(padRow(fields, maxLen));
                    bw.newLine();
                }
            }

            System.out.println("Record resi a dimensione fissa. File salvato come: " + outputFile);

        } catch (IOException e) {
            System.err.println("Errore durante l'elaborazione del file: " + e.getMessage());
        }
    }

    private static String padRow(String[] fields, int[] maxLen) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < maxLen.length; c++) {
            String v = (c < fields.length && fields[c] != null) ? fields[c] : "";
            sb.append(padRight(v, maxLen[c]));
            if (c < maxLen.length - 1) sb.append(",");
        }
        return sb.toString();
    }

    private static String padRight(String s, int width) {
        if (s == null) s = "";
        if (s.length() >= width) return s;
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < width) sb.append(' ');
        return sb.toString();
    }
}
