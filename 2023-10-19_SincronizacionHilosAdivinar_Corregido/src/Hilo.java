

public class Hilo implements Runnable {
	
	private NumeroOculto n;
	public Hilo(NumeroOculto n) {
		this.n=n;
	}

	@Override
	public void run() {
		
		synchronized (n) {
			while(!n.getAdivinado()) {
				int x=(int)(Math.random()*100+1);
				System.out.println("hilo "+ Thread.currentThread().getId()+" probando "+x+ " numero correcto " + n.getN());
				if (n.intento(x)==1) {
					System.out.println("hilo "+ Thread.currentThread().getId()+" Gano");
					n.notifyAll();
					
				}else {
					System.out.println("hilo "+ Thread.currentThread().getId()+" fallo");
					n.notifyAll();
					try {
						n.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		}
		System.out.println("hilo "+ Thread.currentThread().getId()+" acabo.");
		

	}

}
