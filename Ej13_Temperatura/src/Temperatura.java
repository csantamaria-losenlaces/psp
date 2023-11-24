public class Temperatura {

	public static void main(String[] args) {
		
		int temp;
		
		if (args.length != 1) {
			System.out.println("Error, se debe introducir un solo argumento");
		} else {
			temp = ((int) (Math.random() * 50)) - 10;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(temp);
		}
		
	}
	
}