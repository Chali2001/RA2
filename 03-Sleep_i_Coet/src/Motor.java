import java.util.Random;

public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
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
                System.out.println(getName() + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                if (potenciaObjectiu == 0 && potenciaActual != 0) {
                    return;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
            continue;

        }
    }

}