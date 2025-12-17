public class FilEstricte extends Thread {

    private static Object lock = new Object();
    private static boolean turnoJuan = true;

    private String nom;

    public FilEstricte(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            synchronized (lock) {
                while (!meToca()) {
                    try { lock.wait(); } catch (InterruptedException e) {}
                }
                System.out.println(nom + " " + i);
                cambiarTurno();
                lock.notifyAll();
            }
            try { Thread.sleep(1); } catch (InterruptedException e) {}
        }
        System.out.println("Termina el fil " + nom);
    }
    private boolean meToca() {
        return (nom.equals("Juan") && turnoJuan) ||
               (nom.equals("Pepe") && !turnoJuan);
    }
    private void cambiarTurno() {
        turnoJuan = !turnoJuan;
    }
}