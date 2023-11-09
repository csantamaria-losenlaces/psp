import java.util.concurrent.Semaphore;

public class TicTacToc {

	public static void main(String[] args) {

		// Declaración de semáforos
		Semaphore s1 = new Semaphore(1, true);
		Semaphore s2 = new Semaphore(0, true);
		Semaphore s3 = new Semaphore(0, true);

		Thread hTic = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						s1.acquire();
						System.out.println("Tic");
						// Thread.sleep(500);
						s2.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread hTac = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						s2.acquire();
						System.out.println("Tac");
						// Thread.sleep(500);
						s3.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread hToc = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						s3.acquire();
						System.out.println("Toc");
						// Thread.sleep(500);
						s1.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		hTic.start();
		hTac.start();
		hToc.start();

	}

}