import java.util.Random;

public class Assistent extends Thread{

    private Esdeveniment esdeveniment;
    private Random random;

    public Assistent(String nom, Esdeveniment esdeveniment){
        super(nom);
        this.esdeveniment = esdeveniment;
        this.random = new Random();
    }

    @Override
    public void run(){
        while(true){
            boolean reservar = random.nextBoolean();
            /*int proba = random.nextInt(100);
            if (proba >= 70) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            } */

            if(reservar){
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            } 

            try{
                Thread.sleep(random.nextInt(1000));
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}