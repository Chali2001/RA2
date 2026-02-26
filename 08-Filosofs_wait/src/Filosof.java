import java.util.Random;

public class Filosof extends Thread {
    private int id;
    private final Forquilla esquerra;
    private final Forquilla dreta;
    private int gana;
    private Random random = new Random();

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        super("Filòsof " + id);
        this.id = id;
        this.esquerra = esquerra;
        this.dreta = dreta;
        this.gana = 0;
    }

    public void menjar(){
        System.out.println(getName() + ": menja");
        try {
            Thread.sleep(1000 + (random.nextInt(1000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void agafarForquilles(){
        while (true) {
            try {
                agafarForquillaEsquerra();
                if (agafarForquillaDreta()) {
                    break;
                }
                esquerra.deixar(id);
                System.out.println(getName() + ": no pot agafar la forquilla dreta, espera i ho torna a intentar");
                Thread.sleep(500 + (random.nextInt(500)));
            } catch (InterruptedException e) {
                deixarForquilles();
                return;
            }
        }
    }

    public void agafarForquillaEsquerra() throws InterruptedException{
        esquerra.intentarAgafar(id);
        System.out.println(getName() + ": agafa forquilla esquerra: " + esquerra.getId());
    }

    public boolean agafarForquillaDreta(){
        boolean agafada = dreta.intentarAgafarSenseEsperar(id);
        if (agafada) {
            System.out.println(getName() + ": agafa forquilla dreta: " + dreta.getId());
        }
        return agafada;
    }

    public void deixarForquilles(){
        esquerra.deixar(id);
        dreta.deixar(id);
        System.out.println(getName() + ": deixa les forquilles");
    }

    public void pensar(){
        System.out.println(getName() + ": pensa");
        gana++;
        try {
            Thread.sleep(1000 + (random.nextInt(1000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (gana < 3) {
                agafarForquilles();
                menjar();
                deixarForquilles();
                pensar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
  
    }


}