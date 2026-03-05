import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int id;

    public Forquilla(int id) {
        this.id = id;
    }

    private ReentrantLock bloqueig = new ReentrantLock();

    public int getId() {
        return id;
    }

    public boolean agafar() {
        return bloqueig.tryLock();
    }

    public void deixar() {
        bloqueig.unlock();
    }
    

}