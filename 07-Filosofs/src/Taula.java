public class Taula {

    private final Filosof[] comensals;
    private final Forquilla[] forquilles;

    public Taula(int numFilosofs, int vegadesMenjar) {
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < numFilosofs; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilosofs];
            comensals[i] = new Filosof("fil" + i, esquerra, dreta, vegadesMenjar);
        }
    }

    public void showTaula() {
        for (int i = 0; i < comensals.length; i++) {
            int esquerraId = forquilles[i].getId();
            int dretaId = forquilles[(i + 1) % forquilles.length].getId();
            System.out.println("Comensal:" + comensals[i].getName() + " esq:" + esquerraId + " dret:" + dretaId);
        }
        System.out.println("------------------------------");
    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }

        for (Filosof filosof : comensals) {
            try {
                filosof.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        int numFilosofs = 4;
        int vegadesMenjar = 3;

        if (args.length >= 1) {
            numFilosofs = Integer.parseInt(args[0]);
        }

        if (args.length >= 2) {
            vegadesMenjar = Integer.parseInt(args[1]);
        }

        Taula taula = new Taula(numFilosofs, vegadesMenjar);
        taula.showTaula();
        taula.cridarATaula();
    }
}
