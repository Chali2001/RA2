import java.util.Random;

public class Filosof extends Thread {

    private final Forquilla forquillaDreta;
    private final Forquilla forquillaEsquerra;
    private int gana;
    private final int vegadesMenjar;
    private final Random random;
    

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta, int vegadesMenjar) {
        super(nom);
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
        this.vegadesMenjar = vegadesMenjar;
        this.random = new Random();
        
    }

    

    private void menjar() {
        System.out.println("Filòsof: " + getName() + " menja");
        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filòsof: " + getName() + " ha acabat de menjar");
    }
    private void pensar() {
        System.out.println("Filòsof: " + getName() + " pensant");
        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void esperar() {
        try {
            Thread.sleep(500 + random.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void intentarMenjar() {
        boolean menjat = false;

        while (!menjat) {
            if (forquillaEsquerra.agafar()) {
                System.out.println("Filòsof: " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getId());
                if (forquillaDreta.agafar()) {
                    System.out.println("Filòsof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getId());
                    menjar();
                    forquillaDreta.deixar();
                    forquillaEsquerra.deixar();
                    menjat = true;
                } else {
                    forquillaEsquerra.deixar();
                    System.out.println("Filòsof: " + getName() + " deixa l'esquerra(" + forquillaEsquerra.getId() + ") i espera (dreta ocupada)");
                    gana++;
                    System.out.println("Filòsof: " + getName() + " gana=" + gana);
                    esperar();
                }
            } else {
                esperar();
            }
        }
    }
    public int getGana() {
        return gana;
    }

    @Override
    public void run() {
        for (int i = 0; i < vegadesMenjar; i++) {
            intentarMenjar();
            if (i < vegadesMenjar - 1) {
                pensar();
            }
        }
    }
    public static void main(String[] args) {
        int numFilosofs = 4;
        int vegadesMenjar = 3;

        if (args.length >= 1) {
            numFilosofs = Integer.parseInt(args[0]);
        }

        if (args.length >= 2) {
            vegadesMenjar = Integer.parseInt(args[1]);
        }

        Taula taula = new Taula(numFilosofs, vegadesMenjar);
        taula.showTaula();
        taula.cridarATaula();
    }
}
