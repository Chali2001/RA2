import java.util.ArrayList;
import java.util.List;

public class Esdeveniment{
    private List<Assistent> assistents;
    private int placesDisponibles = 10;

    public Esdeveniment(int placesMaxim){
        this.placesDisponibles = placesMaxim;
        this.assistents = new ArrayList<>();
    }

    public synchronized void ferReserva(Assistent assistent) {

        while(placesDisponibles == 0) {
            try{
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        assistents.add(assistent);
        placesDisponibles--;
        System.out.println(assistent.getName() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if(assistents.contains(assistent)) {
            assistents.remove(assistent);
            placesDisponibles++;
            System.out.println(assistent.getName() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            notifyAll();
        } else{
            System.out.println(assistent.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
        
    }

}