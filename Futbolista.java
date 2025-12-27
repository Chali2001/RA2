public class Futbolista extends Thread {

    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    private int ngolsIntirades;

    public Futbolista(String nom) {
        super(nom);
        ngolsIntirades = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            if (Math.random() < PROBABILITAT) {
                ngolsIntirades++;
            }
        }
    }

    public int getNgolsIntirades() {
        return ngolsIntirades;
    }
}
