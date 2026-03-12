import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread{
    private List<Tabac> tabac = new ArrayList<>();
    private List<Paper> paper = new ArrayList<>();
    private List<Llumi> llumi = new ArrayList<>();
    private Random random = new Random();
    private boolean estancObert;
    
    public Estanc(List<Tabac> tabac, List<Paper> paper, List<Llumi> llumi) {
        this.tabac = tabac;
        this.paper = paper;
        this.llumi = llumi;
    }

    public synchronized void nouSubministrament() {
        int produeix = random.nextInt(3);
        if (produeix == 0) {
            addTabac();
        } else if (produeix == 1) {
            addLlumi();
        }else {
            addPaper();
        }
        notifyAll();
    }

    public synchronized void addTabac(){
       tabac.add(new Tabac());
    }

    public synchronized void addLlumi(){
        llumi.add(new Llumi());
    }

    public synchronized void addPaper(){
        paper.add(new Paper());
    }

    public synchronized Tabac venTabac(){
        if(!tabac.isEmpty()){
            return tabac.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi(){
        if(!llumi.isEmpty()){
            return llumi.remove(0);
        }
        return null;
    }  

    public synchronized Paper venPaper(){
        if(!paper.isEmpty()){
            return paper.remove(0);
        }
        return null;
    }

    public synchronized void tancaEstanc(){
        estancObert = false;
        notifyAll();
    }

    public synchronized boolean esObert(){
        return estancObert = true;
    }

    @Override
    public void run(){
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
