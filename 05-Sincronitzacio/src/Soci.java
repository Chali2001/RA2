import java.util.Random;

public class Soci extends Thread{
    private Compte compte;
    private float aportacio = 10f;
    private int esperaMax = 100;
    private Random random;
    private int maxAnys = 10;

    public Soci(String nom){
        super(nom);
        compte = Compte.getInstancia();
        random = new Random();
    };
    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run(){
        int mesos = maxAnys * 12;

        for(int i = 0; i < mesos; i++) {
            synchronized(compte){
                float saldoActual = compte.getSaldo();
                if(i % 2 == 0){
                    compte.setSaldo(saldoActual + aportacio);
                    System.out.println(getName() + " en el mes " + i + " ingresa " + aportacio + ". I el saldo es de: " + compte.getSaldo());
                } else {
                    compte.setSaldo(saldoActual - aportacio);
                    System.out.println(getName() + " en el mes " + i + " retira " + aportacio + ". I el saldo es de: " + compte.getSaldo());

                }
            }

            try{
                Thread.sleep(random.nextInt(esperaMax));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
