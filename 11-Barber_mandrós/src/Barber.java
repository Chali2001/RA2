import java.util.Random;

public class Barber extends Thread {
    private Random random = new Random();

    public Barber(String nom) {
        super("Barber " + nom);
    }

    @Override
    public void run() {
        Barberia barberia = Barberia.getBarberia();

        while (true) {
            Client client;

            synchronized (barberia.getCondBarber()) {
                client = barberia.seguentClient();

                while (client == null) {
                    System.out.println("Ningú en espera\n" + getName() + " dormint");
                    try {
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    client = barberia.seguentClient();
                }
            }

            System.out.println("Li toca al Client " + client.getNom());
            client.tallarseElCabell();

            try {
                Thread.sleep(900 + random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
