import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private List<Tabac> tabac = new ArrayList<>();
    private List<Paper> paper = new ArrayList<>();
    private List<Llumi> llumi = new ArrayList<>();
    private Random random = new Random();
    private boolean estancObert;

    public Estanc(Tabac tabacInicial, Paper paperInicial, Llumi llumiInicial) {
        this.estancObert = true;
    }

    public synchronized void nouSubministrament() {
        int produeix = random.nextInt(3);
        if (produeix == 0) {
            addTabac();
            System.out.println("Afegint tabac");
        } else if (produeix == 1) {
            addLlumi();
            System.out.println("Afegint llumí");
        } else {
            addPaper();
            System.out.println("Afegint paper");
        }
        notifyAll();
    }

    public void addTabac() {
        tabac.add(new Tabac());
    }

    public void addLlumi() {
        llumi.add(new Llumi());
    }

    public void addPaper() {
        paper.add(new Paper());
    }

    public synchronized Tabac venTabac() {
        if (!tabac.isEmpty()) {
            return tabac.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() {
        if (!llumi.isEmpty()) {
            return llumi.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() {
        if (!paper.isEmpty()) {
            return paper.remove(0);
        }
        return null;
    }

    public synchronized void tancaEstanc() {
        estancObert = false;
        notifyAll();
        System.out.println("Estanc tancat");
    }

    public synchronized boolean esObert() {
        return estancObert;
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (estancObert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
