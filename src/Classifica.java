import Interfaces.Observer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class Classifica implements Observer {
    private ArrayList<Squadra> squadre;

    public Classifica() {
        squadre = new ArrayList<Squadra>();
    }

    public Classifica(String nomeFile) throws FileNotFoundException {
        squadre = new ArrayList<Squadra>();
        this.leggi(nomeFile);
    }

    public void salva(String nomeFile) throws IOException {

        FileWriter fw = new FileWriter(nomeFile);

        for(Squadra s: squadre){

            fw.write(s.toString());

        }

        fw.close();

    }


    public ArrayList<Squadra> getSquadre(){
        return squadre;
    }

    private void leggi(String nomeFile) throws FileNotFoundException {

        ArrayList<String> righe = Helpers.leggiRighe(nomeFile);

        for(String riga: righe) {

            String[] elementi = riga.split("\t");

            Squadra s = new Squadra(elementi[0], Integer.parseInt( elementi[1] ), Integer.parseInt(elementi[3]), Integer.parseInt(elementi[4]));

            squadre.add(s);
        }


    }

    public void setSquadre(ArrayList<Squadra> squadre) {
        this.squadre = squadre;
    }


    public void aggiorna() {


        squadre.sort( (s1,s2) -> {

            int p1 = s1.getPunteggio();
            int p2 = s2.getPunteggio();

            int d1 = s1.getDifferenzaReti();
            int d2 = s2.getDifferenzaReti();

            if (p1 == p2) {
                return d2-d1;
            }

            return p2 - p1; // ordinamento decrescente

        });


    }

    public void stampa(){

        for (Squadra s : squadre) {

            System.out.println(s.toString());
        }
    }


    @Override
    public void update() {
        this.aggiorna();
    }
}
