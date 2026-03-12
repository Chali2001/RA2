import java.util.Random;

public class Fumador extends Thread{
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades = 0;
    private Random random = new Random();

    public Fumador(int id, Estanc estanc){
        super("Fumador " + id);
        this.id = id;
        this.estanc = estanc;
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
    }

    public void fuma(){
        if( tabac != null && paper !=null && llumi != null) {
            tabac = null;
            paper = null;
            llumi = null;
            try{
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fumades++;
        }
    }

    public void compraTabac() {
        synchronized (estanc) {
            while (estanc.esObert() && tabac == null) {
                Tabac nouTabac = estanc.venTabac();

                if (nouTabac != null) {
                    this.tabac = nouTabac;
                    System.out.println("Fumador " + id + " comprant Tabac");
                } else {
                    try {
                        estanc.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void compraPaper() {
        synchronized (estanc) {
            while (estanc.esObert() && paper == null) {
                Paper nouPaper = estanc.venPaper();

                if (nouPaper != null) {
                    this.paper = nouPaper;
                    System.out.println("Fumador " + id + " comprant Paper");
                } else {
                    try {
                        estanc.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Llumi compraLlumi(){
        Llumi nouLlumi = estanc.venLlumi();
        if(nouLlumi != null && estanc.esObert()){
            this.llumi = nouLlumi;
        }
        return nouLlumi;
    }

    public void run() {
        while (fumades < 3)
        compraTabac();
        compraPaper();
        compraLlumi();
        fuma();

    }
}
