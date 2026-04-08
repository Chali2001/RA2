public class Home extends Thread{
    private String nom;
    private BanyUnisex banyUnisex;

    public Home(String nom, BanyUnisex banyUnisex) {
        this.nom = nom;
        this.banyUnisex = banyUnisex;
    }

    public void entraHome() {
        System.out.println(nom + " vol entrar al bany");
        banyUnisex.entraHome(nom);        

    }

    public void utilitzaLavabo() {
        try {
            Thread.sleep((long) (Math.random() * 1000) + 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void surtHome() {
        banyUnisex.surtHome(nom);
        System.out.println(nom + " ha acabat d'usar el bany");

    }
    @Override
    public void run() {
        entraHome();
        utilitzaLavabo();
        surtHome();
    }
}
