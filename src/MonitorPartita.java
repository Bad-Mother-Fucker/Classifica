import Interfaces.Observer;

public class MonitorPartita implements Observer {


    Partita p;

    void mostraRisultato () {

        String risutlato = """
                \t      CASA \t   OSPITE\s
                \t%10s \t%10s\s
                \t%10s \t%10s\s
                \t%10s \t%10s\s 
                """;

        risutlato = String.format(risutlato,
                p.getCasa().getNome(),
                p.getOspite().getNome(),
                p.getGoalCasa(),
                p.getGoalOspite(),
                p.getFalliCasa(),
                p.getFalliOspite()
                );


        System.out.println(risutlato);


    }

    public MonitorPartita(Partita p){
        this.p = p;
    }


    @Override
    public void update() {
        mostraRisultato();
    }
}
