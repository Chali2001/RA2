import java.util.ArrayList;
import java.util.List;

public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri(){
        this.estanc = new Estanc(new Tabac(), new Paper(), new Llumi());
        this.fumadors = new Fumador[3];
        for(int i = 0; i < 3; i++){
            this.fumadors[i] = new Fumador(i, estanc);
        }
    }
    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            fumadors[i].start();
        }

        for(int i = 0; i < fumadors.length; i++){
             try {
                fumadors[i].join();
            } catch (InterruptedException e) { 
                e.printStackTrace(); // 
            }
        }

        estanc.tancaEstanc();
    }
        

}
