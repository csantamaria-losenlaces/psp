public class TicTacSinSincronizar {

	public static void main(String[] args) {

		// Creo dos hilos uno que escribe tic y otro tac
		Thread hTic = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true)
					System.out.println("tic");
			}
		});

		Thread hTac = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true)
					System.out.println("tac");

			}
		});

		hTic.start();
		hTac.start();

	}

}