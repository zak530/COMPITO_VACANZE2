
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Csvreader {

        static void inizializzaCsv(String nomeFile) {

            try {
                BufferedReader br = new BufferedReader(new FileReader(nomeFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter("temp.csv"));

                Random rnd = new Random();
                String riga;
                boolean primaRiga = true;

                while ((riga = br.readLine()) != null) {

                    // HEADER
                    if (primaRiga) {
                        bw.write(riga + "\tmiovalore\tdeleted");
                        bw.newLine();
                        primaRiga = false;
                    }
                    // RECORD
                    else {
                        int miovalore = 10 + rnd.nextInt(11); // 10..20
                        bw.write(riga + "\t" + miovalore + "\t0");
                        bw.newLine();
                    }
                }

                br.close();
                bw.close();

                // sostituisce il file originale
                File originale = new File(nomeFile);
                File temp = new File("temp.csv");

                originale.delete();
                temp.renameTo(originale);

                System.out.println("CSV inizializzato correttamente.");

            } catch (IOException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        // ===== MAIN (MENU COME IL PROF) =====
        public static void main(String[] args) {

            Scanner in = new Scanner(System.in);
            int opzione;

            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1 - Inizializza CSV (miovalore + deleted)");
                System.out.println("0 - Esci");
                System.out.print("Scegli opzione: ");

                opzione = Integer.parseInt(in.next());

                switch (opzione) {

                    case 1:
                        inizializzaCsv("tuocognome.csv");
                        break;

                    case 0:
                        System.out.println("Uscita dal programma");
                        break;

                    default:
                        System.out.println("Opzione non valida");
                }

            } while (opzione != 0);

            in.close();
            System.out.println("Fine programma");
        }
    }

