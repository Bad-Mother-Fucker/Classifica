import Interfaces.Observable;
import Interfaces.Observer;

public class Partita implements Observable {

    private Squadra casa;
    private Squadra ospite;
    private int goalCasa;
    private int goalOspite;

    private int falliCasa = 0;

    private int falliOspite = 0 ;

    public Partita(Squadra casa, Squadra ospite, int goalCasa, int goalOspite){
        this.casa = casa;
        this.ospite = ospite;
        this.goalCasa = goalCasa;
        this.goalOspite = goalOspite;
    }

   public void calcolaPunteggio() {

        casa.aumentaGoal(goalCasa, goalOspite);
        ospite.aumentaGoal(goalOspite, goalCasa);

        if(goalCasa > goalOspite) {
            casa.aumentaPunteggio(3);

        }else if (goalCasa < goalOspite){
            ospite.aumentaPunteggio(3);
        } else {
            casa.aumentaPunteggio(1);
            ospite.aumentaPunteggio(1);
        }

   }

    public Squadra getCasa() {
        return casa;
    }

    public Squadra getOspite() {
        return ospite;
    }

    public int getGoalCasa() {
        return goalCasa;
    }

    public int getGoalOspite() {
        return goalOspite;
    }

    public int getFalliCasa() {
        return falliCasa;
    }

    public int getFalliOspite() {
        return falliOspite;
    }

    void goalCasa(){
        goalCasa ++;
        notifyObservers();
   }


   void goalOspite(){
        goalOspite++;
        notifyObservers();
   }


   void falloCasa() {
        falliCasa ++;
        notifyObservers();
   }

   void falloOspite() {
        falliOspite ++;
        notifyObservers();
   }


    @Override
    public void addObserver(Observer o) {
        observers.add(o);
        notifyObservers();
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers ) {
            o.update();
        }
    }
}
