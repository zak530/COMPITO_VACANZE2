import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opzione;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Inizializza CSV");
            System.out.println("2 - Conta campi");
            System.out.println("3 - Lunghezza massima record");
            System.out.println("4 - Record a dimensione fissa");
            System.out.println("5 - Aggiungi record in coda");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");

            opzione = Integer.parseInt(in.next());

            switch (opzione) {
                case 1:
                    Csvreader.inizializzaCsv("elhaiki.csv");
                    break;
                case 2:
                    System.out.println(Csvreader.contaCampi("elhaiki.csv"));
                    break;
                case 3:
                    System.out.println(Csvreader.lunghezzaMassimaRecord("elhaiki.csv"));
                    break;
                case 4:
                    Csvreader.rendiRecordFissi("elhaiki.csv", "elhaiki_fisso.csv");
                    break;
                case 5:
                    in.nextLine();
                    Csvreader.aggiungiRecord("elhaiki.csv");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opzione non valida");
            }
        } while (opzione != 0);

        in.close();
    }
}
