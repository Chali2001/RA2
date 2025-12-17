public class PrincipalIguals{
    public static void main(String[] args) {
        Fil f1 = new Fil("Juan", 9, Thread.NORM_PRIORITY);
        Fil f2 = new Fil("Pepe", 9, Thread.NORM_PRIORITY);

        f1.start();
        f2.start();
        
        System.out.println("Acaba thread main");
    }
}