public class Squadra {

    private String nome;
    private int goalFatti;
    private int goalSubiti;
    private int punteggio;


    public Squadra (String nome){
        this.nome = nome;
        goalFatti = 0;
        goalSubiti = 0;
        punteggio = 0;
    }


    public Squadra(String nome, int punteggio, int gf, int gs){
        this.nome =nome;
        goalFatti = gf;
        goalSubiti = gs;
        this.punteggio = punteggio;
    }


    public String getNome() {
        return nome;
    }

    public void aumentaPunteggio(int punti) {

        punteggio += punti;

    }


    public void aumentaGoal(int gf, int gs) {
        goalFatti += gf;
        goalSubiti += gs;
    }

    public int getPunteggio(){
        return punteggio;
    }

    public int getDifferenzaReti() {
        return goalFatti - goalSubiti;
    }


    public String toString(){

        return nome + "\t" + punteggio + "\t" + getDifferenzaReti()+ "\t" + goalFatti+ "\t" + goalSubiti +"\n";

    }



}
