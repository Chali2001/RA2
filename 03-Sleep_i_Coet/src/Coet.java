import java.util.Scanner;


public class Coet {
    private Motor[] motors = new Motor[4];

    public Coet() {
        motors[0] = new Motor("Motor 0");
        motors[1] = new Motor("Motor 1");
        motors[2] = new Motor("Motor 2");
        motors[3] = new Motor("Motor 3");
    }

    public void arranca() {
        for (Motor m : motors){
            m.start();
        }
    }

    public void passaAPotencia(int p){
        if (p < 0 || p > 10) {
            System.out.println("Error: Potència fora del rang (0-10)");
            return;
        }
        System.out.println("\nPassant a potència " + p);
        for (Motor m : motors){
            m.setPotencia(p);
        }
    }

    public static void main(String[] args){
        Coet coet = new Coet();
        coet.arranca();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            int p = scanner.nextInt();
            coet.passaAPotencia(p);
            if (p == 0){
                break;
            }
        }
        scanner.close();

        for (Motor m : coet.motors) {
            try {
                m.join();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("\nCoet apagat. Tots els motors aturats.");
    }
}