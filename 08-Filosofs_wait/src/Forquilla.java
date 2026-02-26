public class Forquilla {
    private int id;
    private int propietari;
    private final int LLIURE = -1;

    public Forquilla(int id) {
        this.id = id;
        this.propietari = LLIURE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    public int getLLIURE() {
        return LLIURE;
    }

    public synchronized void intentarAgafar(int idFilosof) throws InterruptedException{
        while (propietari != LLIURE) {
            wait();
        }
        propietari = idFilosof;
    }
    public synchronized boolean intentarAgafarSenseEsperar(int idFilosof) {
        if (propietari == LLIURE) {
            propietari = idFilosof;
            return true;
        }
        return false;
    }

    public synchronized void deixar(int idFilosof) {
        if (propietari == idFilosof) {
            propietari = LLIURE;
            notifyAll();
        }
    }
    
}