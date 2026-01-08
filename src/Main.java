import java.util.Scanner;

public class Main {
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
                    Csvreader.inizializzaCsv("elhaiki.csv");
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