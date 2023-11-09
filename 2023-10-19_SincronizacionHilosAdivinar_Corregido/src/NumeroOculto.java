
/*
 * Clase que genera el numero aleatiorio y da respuesta a los intentos de adivinar
 * 
 */
public class NumeroOculto {
	
	private Integer n;
	private Boolean adivinado=false;
	
	public NumeroOculto(){
		this.n=(int)(Math.random()*100+1);//numero a adivinar
	}
	
	//metodo que gestiona el intento
	public Integer intento(Integer x) {
		
		if (adivinado) return -1;
		if (x==n) {adivinado=true; return 1;}else {return 0;}
		
	}

	public  Integer getN() {
		return n;
	}

	public  Boolean getAdivinado() {
		return adivinado;
	}

	
	

}
