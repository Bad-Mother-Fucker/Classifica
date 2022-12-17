import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws IOException {


        File f = new File("Classifica.txt");
        Classifica c;

        Scanner s = new Scanner(System.in);

        System.out.println("Vuoi procere leggendo i dati? y/n ");

        String input = s.nextLine();


        if(f.exists() && !f.isDirectory() && input.equals("y")) {
            System.out.println("File trovato, procedo...");
            c = leggiClassifica("Classifica.txt");

        } else {
            System.out.println("File non trovato, procedo creando una nuova classifica, inserisci il nome del file contenente le squadre");
            String nomeFile = s.nextLine();
            c = creaNuovaClassifica(nomeFile);
        }

        boolean ripeti;

        do {
            System.out.println("Cosa vuoi fare? " +
                    "1: Stampa classifica" +
                    "2: Aggiorna classifica");


            String scelta = s.nextLine();

            if (scelta.equals("1")){

                c.stampa();

            } else if (scelta.equals("2")) {
                System.out.println("Inserisci il nome del file con i risultati");

                String nomeFile = s.nextLine();

                aggiornaClassifica(c, nomeFile);

                c.salva("Classifica.txt");

                System.out.println("Classifica aggiornata, e salvata sul file Classifica.txt");

            }

            System.out.println("Vuoi ripetere? y/n");

            String rip = s.nextLine();

            ripeti = rip.equals("y");

        } while(ripeti);





        Partita p = new Partita(c.getSquadre().get(1),c.getSquadre().get(6),0, 0);

        MonitorPartita mp = new MonitorPartita(p);

        p.addObserver(mp);

        int evento;

        do{


            System.out.println("Scegli un evento");


            System.out.println("""
                    1: Goal squadra casa
                    2: Goal squadra ospite
                    3: Fallo squadra casa
                    4: Fallo squadra ospite
                    5: Termina Partita
           """.indent(1));


            evento = s.nextInt();

            switch (evento) {
                case 1 -> p.goalCasa();
                case 2 -> p.goalOspite();
                case 3 -> p.falloCasa();
                case 4 -> p.falloOspite();
            }

        }while(evento != 5);


        c.stampa();



    }

    public static Classifica leggiClassifica(String nomefile) throws FileNotFoundException {
        Classifica c = new Classifica(nomefile);
        return c;

    }

    public static void aggiornaClassifica(Classifica c, String nomefile) throws FileNotFoundException {

        //Array di stringhe con tutte le righe del file
        ArrayList<String> righeRisultati = Helpers.leggiRighe(nomefile);

        //Per ogni stringa nell'arraylist
        righeRisultati.forEach((stringaRisultato) -> {

            //Divido la stringa utilizzando " " (spazio vuoto) come separatore - risultato 5 elementi
            String[] elementiString = stringaRisultato.split(" ");

            //In base agli indici estraggo i dati
            String nomeSquadraCasa = elementiString[0];
            String nomeSquadraOspite = elementiString[1];

            //Estraggo i dati convertendoli in int
            int goalCasa = Integer.parseInt(elementiString[2]);//Crash se la stringa non è numerica

            int goalOspite = Integer.parseInt(elementiString[4]);

            Optional<Squadra> casaOp = findSquadra(nomeSquadraCasa, c.getSquadre());

            Optional<Squadra> ospiteOp = findSquadra(nomeSquadraOspite, c.getSquadre());

            // Se l'oggetto è presente nell'arraylist lo "estraggo" dall'optional

            if(casaOp.isPresent() && ospiteOp.isPresent()){
                //Oggeto squadra da passare al costruttore di Partita
                Squadra casa = casaOp.get();
                Squadra ospite = ospiteOp.get();
                Partita p = new Partita(casa, ospite, goalCasa, goalOspite);
                p.calcolaPunteggio();
            }


        });

        c.aggiorna();
    }


    public static Classifica creaNuovaClassifica(String nomefile) throws FileNotFoundException {

        ArrayList<String> righeSquadre = Helpers.leggiRighe(nomefile);

        Classifica c = new Classifica();

        ArrayList<Squadra> squadre = new ArrayList<Squadra>();

        /*
        for(int i = 0; i < righeSquadre.size(); i++){

            Squadra s = new Squadra(righeSquadre.get(i));
            squadre.addObserver(s);

        }

        */

        /*
        for (String nomeSquadra : righeSquadre) { //forEach

            Squadra s = new Squadra(nomeSquadra);
            squadre.addObserver(s);

        }
        */

        righeSquadre.forEach(
                (nome) -> {
                    Squadra s = new Squadra(nome);
                    squadre.add(s);
                }  //Closure o Block
        );

        c.setSquadre(squadre);

        return c;
    }


    public static Optional<Squadra> findSquadra(String nomeSquadra, ArrayList<Squadra> squadre) {

        //Filtro il contenuto dell'arraylist squadre per cercare quelle con il nome della squadra ospite e della squadra cercata
        Stream<Squadra> streamSquadre = squadre.stream().filter((squadra) -> squadra.getNome().equals(nomeSquadra));

        //Recupero il primo (e unico) elemento (è opzionale perché puo essere nullo, potrebbe non essere presente)
        Optional<Squadra> opSquadra = streamSquadre.findFirst();

        return opSquadra;

    }





}