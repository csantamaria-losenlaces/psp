package Ej3PrioridadHilos;

public class Hilos extends Thread {

	private Integer veces = 0;

	public Hilos(String s) {
		super(s);

	}

	public void run() {
		while (true) {
			veces++;
			System.out.println("hilo " + getName() + " frecuencia= " + veces + " prioridad " + this.getPriority());
			for (int i = 0; i <= 100000; i++) {
				//for (int j = 0; j <= 100000; j++)
					;
			}
		}
	}

}
