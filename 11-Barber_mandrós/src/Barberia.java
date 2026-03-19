import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
	private final Queue<Client> salaEspera;
	private final int cadiresMaximes;
	private final Object condBarber;

	private static Barberia barberia;

	private int seguentIdClient;

	public Barberia(int cadiresMaximes) {
		this.salaEspera = new LinkedList<>();
		this.cadiresMaximes = cadiresMaximes;
		this.condBarber = new Object();
		this.seguentIdClient = 1;
	}

	public static Barberia getBarberia() {
		return barberia;
	}

	public Object getCondBarber() {
		return condBarber;
	}

	public Client seguentClient() {
		synchronized (condBarber) {
			if (salaEspera.isEmpty()) {
				return null;
			}
			return salaEspera.poll();
		}
	}

	public void entrarClient(Client client) {
		synchronized (condBarber) {
			if (salaEspera.size() < cadiresMaximes) {
				salaEspera.add(client);
				System.out.println("Client " + client.getNom() + " en espera");
				condBarber.notify();
			} else {
				System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
			}
		}
	}

	private void afegirClients(int quantitat) {
		for (int i = 0; i < quantitat; i++) {
			Client client = new Client(seguentIdClient);
			seguentIdClient++;
			entrarClient(client);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	@Override
	public void run() {
		afegirClients(10);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		afegirClients(10);
	}

	public static void main(String[] args) {
		barberia = new Barberia(3);
		Barber barber = new Barber("Pepe");

		barber.start();
		barberia.start();
	}
}
