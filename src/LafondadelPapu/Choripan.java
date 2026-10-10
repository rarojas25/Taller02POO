package LafondadelPapu;

public class Choripan {
	
	public static final String TIPO = "Choripan";
	
	private String consumidor;
	private double largo;
	private double ancho;
	private String fecha;
	
	public Choripan(String cosumidor, double largo, double ancho, String fecha) {
		this.consumidor = cosumidor;
		this.largo = largo;
		this.ancho = ancho;
		this.fecha = fecha;
	}

	public String getCosumidor() {
		return cosumidor;
	}

	public void setCosumidor(String cosumidor) {
		this.cosumidor = cosumidor;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double caclularKcal() {
		return (largo * ancho) / 3;
	}
	
	public String alinearArchivo() {
		return TIPO + ";" + consumidor + ";" + Utilidades.numeroSimple(largo)
		+ ";" + Utilidades.numeroSimple(ancho) + ";" + fecha;
	}
	
	public String describir() {
		return TIPO + " | " + consumidor + " | " + Utilidades.numeroSimple(largo)
		+ " cm x " + Utilidades.numeroSimple(ancho) + " cm | " + fecha
		+ " | " + Utilidades.dosDecimales(calcularKcal()) + " kcal";
	}
	
	public String armarFila(int numero, boolean incluirKcal) {
		String fila = " " + Utilidades.rellenarDerecha(String .valueOf(numero), 3)
		+ " " + Utilidades.rellenarDerecha(consumidor, 12)
		+ " " + Utilidades.rellenarIzquierda(Utilidades.numeroSimple(largo) ,6) + " cm "
		+ Utilidades.rellenarIzquierda(Utilidades.numeroSimple(ancho), 6) + " cm "
		+ Utilidades.rellenarDerecha(fecha, 11);
		
		if(incluirKcal) {
			fila = fila + Utilidades.rellenarIzquierda(Utilidades.dosDecimales(calcularKcal()), 8);
		}
		return fila;
	}
}
