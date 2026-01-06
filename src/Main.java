public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    int opzione = 0;

    do {
        // stampa menu
        System.out.println("1 Inizializza CSV (aggiunge miovalore e deleted)");
        System.out.println("0 Uscita");

        // scelta opzione
        System.out.print("Scegli opzione: ");
        opzione = Integer.parseInt(in.next());

        switch (opzione) {

            case 1:
                Csvreader.inizializzaCsv("tuocognome.csv");
                break;

            case 0:
                System.out.println("Uscita dal programma");
                break;

            default:
                System.out.println("Opzione non valida");
        }

    } while (opzione != 0);

    System.out.println("Fine programma");
}
