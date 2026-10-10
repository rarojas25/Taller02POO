package LafondadelPapu;

public class Terremoto {
	public static final String TIPO = "Terremoto";
	
	private String consumidor;
	private double litros;
	private String fecha;
	
	public Terremoto(String consumidor, double litros, String fecha) {
		this.consumidor = consumidor;
		this.litros = litros;
		this.fecha = fecha;
	}

	public String getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(String consumidor) {
		this.consumidor = consumidor;
	}

	public double getLitros() {
		return litros;
	}

	public void setLitros(double litros) {
		this.litros = litros;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public double calcularKcal() {
		return(litros * 10) / 3;
	}
	
	public String alineaArchivo() {
		return TIPO + ";" + consumidor + " | " + Utilidades.numeroSimple(litros) + ";" + fecha; 
	}
	
	public String describir() {
		return TIPO + " | " + consumidor + " | " + Utilidades.numeroSimple(litros) 
		+ " L | " + fecha + " | " + Utilidades.dosDecimales(calcularKcal()) + " kcal";
				
	}
	
	public String amarFila(int numero, boolean incluirKcal) {
		String fila = " " + Utilidades.rellenarDerecha(String.valueOf(numero), 3) 
			+ " " + Utilidades.rellenarDerecha(cosumidor, 12)
			+ " " + Utilidades.rellenarIzquierda(Utilidades.numeroSimple(litros), 7)
			+ " L " + Utilidades.rellenarDerecha(fecha, 11);
		if(incluirKcal) {
			fila = fila + Utilidades.rellenarIzquierda(Utilidades.dosDecimales(calcularKcal()), 8); 
		}
		return fila;
	}
}
