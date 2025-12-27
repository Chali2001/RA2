public class MainFutbol {

    public static void main(String[] args) {

        String[] noms = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo",
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "Mbapé"
        };

        Futbolista[] jugadors = new Futbolista[Futbolista.NUM_JUGADORS];

        System.out.println("Inici dels xuts ----------------------");

        for (int i = 0; i < Futbolista.NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
        }

        for (Futbolista f : jugadors) {
            f.start();
        }

        for (Futbolista f : jugadors) {
            try {
                f.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts -------------------------");
        System.out.println("--- Estadistiques -------");

        for (Futbolista f : jugadors) {
            System.out.println(f.getName() + " -> " + f.getNgolsIntirades() + " gols");
        }
    }
}