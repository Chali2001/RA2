public class PrincipalDiferents {
    public static void main(String[] args) {

        Fil f1 = new Fil("Pepe", 9, Thread.MIN_PRIORITY);  
        Fil f2 = new Fil("Juan", 9, Thread.MAX_PRIORITY);

        f1.start();
        f2.start();
        System.out.println("Acaba thread main");
    }
}