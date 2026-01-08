import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opzione;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Inizializza CSV (miovalore + cancellato)");
            System.out.println("2 - Conta numero campi (colonne) del CSV");
            System.out.println("3 - Lunghezza massima dei record (in caratteri)");
            System.out.println("4 - Rendi record a dimensione fissa (padding spazi)");
            System.out.println("0 - Esci");
            System.out.print("Scegli opzione: ");

            opzione = Integer.parseInt(in.next());

            switch (opzione) {
                case 1:
                    Csvreader.inizializzaCsv("elhaiki.csv");
                    break;
                case 2:
                    int n = Csvreader.contaCampi("elhaiki.csv");
                    System.out.println("Numero di campi (colonne): " + n);
                    break;
                case 3:
                    int max = Csvreader.lunghezzaMassimaRecord("elhaiki.csv");
                    System.out.println("Lunghezza massima dei record: " + max + " caratteri");
                    break;
                case 4:
                    Csvreader.rendiRecordFissi("elhaiki.csv", "elhaiki_fisso.csv");
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
