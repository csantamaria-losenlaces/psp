/* Nombre: Carlos Santamaría Gracia
 * Curso: 2º D.A.M. Vespertino
 * Examen 1ª evaluación */

public class Pares {

	// Método principal de la clase
	public static void main(String[] args) {
		if (Integer.valueOf(args[0]) <= Integer.valueOf(args[1])) {
			for (int i = Integer.valueOf(args[0]); i <= Integer.valueOf(args[1]); i++) {
				if (i % 2 == 0) {
					System.out.println(i);
				}
			}
		} else {
			System.out.println("Error. El primer valor debe ser menor o igual que el segundo.");
		}
	}

}