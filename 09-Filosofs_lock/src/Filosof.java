import java.util.Random;

public class Filosof extends Thread {
    private long iniciGana;
    private long fiGana;
    private int gana;
    private Forquilla esquerra;
    private Forquilla dreta;
    private Random random = new Random();

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        super("Filosof " + id);
        this.esquerra = esquerra;
        this.dreta = dreta;
    }

    public void menjar() {
        agafarForquilles();
        calcularGana();
        System.out.println(getName() + " menja amb gana " + gana);
        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resetGana();
        deixarForquilles();
    }

    public void agafarForquilles() {
        while (true) {
            if (agafarForquillaEsquerra()) {
                if (agafarForquillaDreta()) {
                    System.out.println(getName() + " té forquilles esq(" + esquerra.getId() + ") dreta(" + dreta.getId() + ")");
                    return;
                }
                esquerra.deixar();
            }
            try {
                Thread.sleep(1 + random.nextInt(10));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public boolean agafarForquillaEsquerra() {
        return esquerra.agafar();
    }

    public boolean agafarForquillaDreta(){
        return dreta.agafar();
    }

    public void deixarForquilles() {
        dreta.deixar();
        esquerra.deixar();
    }

    public void pensar() {
        System.out.println(getName() + " pensant");
        iniciGana = System.currentTimeMillis();
        try {
            Thread.sleep(1000 + (random.nextInt(1000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    public void calcularGana() {
        fiGana = System.currentTimeMillis();
        gana = (int) ((fiGana - iniciGana) / 1000);
    }

    public void resetGana(){
        gana = 0;
    }

    @Override
    public void run(){
        while (true) {
            pensar();
            menjar();
        }
    }
}