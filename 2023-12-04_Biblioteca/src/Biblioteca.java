public class Biblioteca {

    private int aforo;

    public Biblioteca() {
        this.aforo = 50;
    }

    // Método para acceder a la biblioteca (sincronizado)
    synchronized public void accederBiblioteca() {
        if (aforo > 0) {
            aforo--;
        } else {
            System.out.println("Aforo completo. Personas dentro: " + aforo);
        }
    }

    // Método para salir de la biblioteca (sincronizado)
    synchronized public void salirBiblioteca() {      
            aforo++;
    }

    synchronized public Integer getAforo() {
        return aforo;
    }
}
