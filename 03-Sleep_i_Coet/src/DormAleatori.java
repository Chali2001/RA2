import java.util.Random;

public class DormAleatori extends Thread{
    private long instantCreacio;

    public DormAleatori(String nom){
        super(nom);
        this.instantCreacio = System.currentTimeMillis();
    }

    Random random = new Random();   
    @Override
    public void run(){
        for (int i = 0; i < 10; i++){
            int intervalAleatori = random.nextInt(1000);
            long totalDesdeConstruccio = System.currentTimeMillis() - instantCreacio;

            System.out.println( getName() + "(" + i + ") a dormir " + intervalAleatori + "ms total " + totalDesdeConstruccio + "ms");
            try {
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        joan.start();
        pep.start();
        System.out.println("-- Fi de main -----------");
    }
}
