public class PrincipalEstricte {
    public static void main(String[] args) {

        FilEstricte f1 = new FilEstricte("Juan");
        FilEstricte f2 = new FilEstricte("Pepe");

        f1.start();
        f2.start();
        System.out.println("Termina thread main");
    }
}