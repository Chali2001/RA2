public class Forquilla {
    private final int id;
    private boolean enUs;

    public Forquilla(int id) {
        this.id = id;
        this.enUs = false;
    }

    public int getId() {
        return id;
    }

    public synchronized boolean isEnUs() {
        return enUs;
    }

    public synchronized void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }

    public synchronized boolean agafar() {
        if (!enUs) {
            enUs = true;
            return true;
        }
        return false;
    }

    public synchronized void deixar() {
        enUs = false;
    }
}
