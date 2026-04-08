import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    private final int BANY_BUIT = 0;
    private final int BANY_AMB_HOMES = 1;
    private final int BANY_AMB_DONES = 2;
    private int estatActual;
    private int ocupants = 0;
    private final int CAPACITAT_MAX = 3;
    private final Semaphore semafor = new Semaphore(CAPACITAT_MAX, true);
    private final ReentrantLock lockEstat = new ReentrantLock(true);

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
    }

    public void entraHome(String nom) {
        while (true) {
            lockEstat.lock();
            try {
                if ((estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) && ocupants < CAPACITAT_MAX) {
                    semafor.acquire();
                    if (estatActual == BANY_BUIT) {
                        estatActual = BANY_AMB_HOMES;
                    }
                    ocupants++;
                    System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
                    return;
                } 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                lockEstat.unlock();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
    }

    public void entraDona(String nom) {
        while (true) {
            lockEstat.lock();
            try {
                if ((estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) && ocupants < CAPACITAT_MAX) {
                    semafor.acquire();
                    if (estatActual == BANY_BUIT) {
                        estatActual = BANY_AMB_DONES;
                    }
                    ocupants++;
                    System.out.println(nom + " entra al bany. Ocupants: " + ocupants);
                    return;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                lockEstat.unlock();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            semafor.release();
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            semafor.release();
            System.out.println(nom + " surt del bany. Ocupants: " + ocupants);

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public static void main (String[] args) {
        BanyUnisex banyUnisex = new BanyUnisex();

        for (int i = 0; i < 5; i++){
            Home home = new Home("Home-" + i, banyUnisex);
            home.start();
        }

        for (int i = 0; i < 5; i++){
            Dona dona = new Dona("Dona-" + i, banyUnisex);
            dona.start();
        }
    }
    
}
