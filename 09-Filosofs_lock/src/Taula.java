public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;

    public Taula(int numFilosofs) {
        comensals = new Filosof[numFilosofs];
        forquilles = new Forquilla[numFilosofs];

        for (int i = 0; i < forquilles.length; i++) {
            forquilles[i] = new Forquilla(i);
        }

        for (int i = 0; i < forquilles.length; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % forquilles.length];
            comensals[i] = new Filosof(i, esquerra, dreta);
        }
    }

    public void showTaula() {
        for (int i = 0; i < forquilles.length; i++) {
            int esquerraId = forquilles[i].getId();
            int dretaId = forquilles[(i + 1) % forquilles.length].getId();
            System.out.println("Comensal:" + comensals[i].getName() + " esq:" + esquerraId + " dret:" + dretaId);
        }
        System.out.println("------------------------------");
    }

    public void cridarTaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        int numFilosofs = 5;
        
        if (args.length >= 1) {
            numFilosofs = Integer.parseInt(args[0]);
        }
        Taula taula = new Taula(numFilosofs);

        taula.showTaula();
        taula.cridarTaula();
    }
}