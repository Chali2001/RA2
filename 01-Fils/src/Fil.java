public class Fil extends Thread {

    private String nom;
    private int vegades;

    public Fil(String nom, int vegades, int prioritat) {
        this.nom = nom;
        this.vegades = vegades;
        this.setName(nom);
        this.setPriority(prioritat);
    }

    @Override
    public void run() {
        for (int i = 1; i <= vegades; i++) {
            int comptador = 0;
            while (comptador < 1000) {
                comptador++;
            }
            System.out.println(nom + " " + i);
        }
        System.out.println("Acaba el fil " + nom);
    }
}