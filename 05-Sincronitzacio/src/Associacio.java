public class Associacio {
    
    private int numSocis = 1000;
    private Soci[] socis;

    public Associacio() {
        socis = new Soci[numSocis];
        for(int i = 0; i < numSocis; i++) {
            socis[i] = new Soci("Soci " + i);
        }
    }
    public void iniciaCompteTempsSocis() {
        for (Soci s : socis) {
            s.start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Soci s : socis) {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostraBalancComptes() {
        System.out.println("Saldo final del compte: " + Compte.getInstancia().getSaldo());
    }
        public static void main(String[] args) {
        Associacio associacio = new Associacio();
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}
