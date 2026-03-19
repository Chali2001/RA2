import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades = 0;
    private Random random = new Random();

    public Fumador(int id, Estanc estanc) {
        super("Fumador " + id);
        this.id = id;
        this.estanc = estanc;
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
    }

    public void fuma() {
        if (tabac != null && paper != null && llumi != null) {
            System.out.println("Fumador " + id + " fumant");
            tabac = null;
            paper = null;
            llumi = null;
            try {
                Thread.sleep(500 + random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fumades++;
            System.out.println("Fumador " + id + " ha fumat " + fumades + " vegades");
        }
    }

    public Tabac compraTabac() {
        synchronized (estanc) {
            while (tabac == null && estanc.esObert()) {
                Tabac nouTabac = estanc.venTabac();
                if (nouTabac != null) {
                    this.tabac = nouTabac;
                    System.out.println("Fumador " + id + " comprant Tabac");
                    return nouTabac;
                }
                try {
                    estanc.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return tabac;
    }

    public Paper compraPaper() {
        synchronized (estanc) {
            while (paper == null && estanc.esObert()) {
                Paper nouPaper = estanc.venPaper();
                if (nouPaper != null) {
                    this.paper = nouPaper;
                    System.out.println("Fumador " + id + " comprant Paper");
                    return nouPaper;
                }
                try {
                    estanc.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return paper;
    }

    public Llumi compraLlumi() {
        synchronized (estanc) {
            while (llumi == null && estanc.esObert()) {
                Llumi nouLlumi = estanc.venLlumi();
                if (nouLlumi != null) {
                    this.llumi = nouLlumi;
                    System.out.println("Fumador " + id + " comprant Llumí");
                    return nouLlumi;
                }
                try {
                    estanc.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return llumi;
    }

    @Override
    public void run() {
        while (fumades < 3) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
    }
}
