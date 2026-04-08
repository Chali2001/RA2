public class Dona extends Thread{
    private String nom;
    private BanyUnisex banyUnisex;

    public Dona(String nom, BanyUnisex banyUnisex) {
        this.nom = nom;
        this.banyUnisex = banyUnisex;
    }

    public void entraDona() {
        System.out.println(nom + " vol entrar al bany");
        banyUnisex.entraDona(nom);
    }

    public void utilitzaLavabo() {
        try {
            Thread.sleep((long) (Math.random() * 1000) + 2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void surtDona(){
        banyUnisex.surtDona(nom);
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    @Override
    public void run() {
        entraDona();
        utilitzaLavabo();
        surtDona();
    }
}
