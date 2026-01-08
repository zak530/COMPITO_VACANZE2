import java.io.*;
import java.util.Random;
import java.util.Scanner;

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
                return;
            }

            while ((line = br.readLine()) != null) {
                int miovalore = 10 + random.nextInt(11);
                bw.write(line + "," + miovalore + ",false");
                bw.newLine();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int contaCampi(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String header = br.readLine();
            if (header == null) return 0;
            return header.split(",", -1).length;
        } catch (IOException e) {
            return 0;
        }
    }

    public static int lunghezzaMassimaRecord(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String line = br.readLine();
            if (line == null) return 0;

            int max = 0;
            while ((line = br.readLine()) != null) {
                if (line.length() > max) max = line.length();
            }
            return max;
        } catch (IOException e) {
            return 0;
        }
    }

    public static void rendiRecordFissi(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String headerLine = br.readLine();
            if (headerLine == null) return;

            String[] header = headerLine.split(",", -1);
            int cols = header.length;
            int[] maxLen = new int[cols];

            for (int i = 0; i < cols; i++) maxLen[i] = header[i].length();

            String line;
            while ((line = br.readLine()) != null) {
                String[] f = line.split(",", -1);
                for (int i = 0; i < cols; i++) {
                    String v = i < f.length ? f[i] : "";
                    if (v.length() > maxLen[i]) maxLen[i] = v.length();
                }
            }

            try (BufferedReader br2 = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

                String[] h = br2.readLine().split(",", -1);
                bw.write(padRow(h, maxLen));
                bw.newLine();

                while ((line = br2.readLine()) != null) {
                    bw.write(padRow(line.split(",", -1), maxLen));
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String padRow(String[] fields, int[] maxLen) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLen.length; i++) {
            String v = i < fields.length ? fields[i] : "";
            sb.append(padRight(v, maxLen[i]));
            if (i < maxLen.length - 1) sb.append(",");
        }
        return sb.toString();
    }

    private static String padRight(String s, int w) {
        if (s.length() >= w) return s;
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < w) sb.append(" ");
        return sb.toString();
    }

    public static void aggiungiRecord(String nomeFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {

            String header = br.readLine();
            if (header == null) return;

            int campi = header.split(",", -1).length;
            Scanner in = new Scanner(System.in);
            String[] nuovo = new String[campi];

            for (int i = 0; i < campi; i++) {
                System.out.print("Campo " + (i + 1) + ": ");
                nuovo[i] = in.nextLine();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile, true))) {
                bw.newLine();
                bw.write(String.join(",", nuovo));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
