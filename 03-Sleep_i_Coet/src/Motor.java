import java.util.Random;

public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;

    private boolean started = false;
    private boolean imprèsFerRes = false;

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
        this.started = true;
        this.imprèsFerRes = false;
    }

    public Motor(String nom) {
        super(nom);
    }

    Random random = new Random();

    @Override
    public void run() {
        while (true) {
            if (potenciaActual < potenciaObjectiu) {
                potenciaActual++;
                System.out.println(getName() + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
            } else if (potenciaActual > potenciaObjectiu) {
                potenciaActual--;
                System.out.println(getName() + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
            } else {
                if (!imprèsFerRes) {
                    System.out.println(
                            getName() + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    imprèsFerRes = true;
                }
                if (potenciaObjectiu == 0 && started) {
                    return;
                }
            }
            try {
                Thread.sleep(random.nextInt(1001) + 1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}