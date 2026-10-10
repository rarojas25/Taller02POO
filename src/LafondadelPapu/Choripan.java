package LafondadelPapu;

public class Choripan {
	private String cosumidor;
	private double largo;
	private double ancho;
	private String fecha;
	
	public Choripan(String cosumidor, double largo, double ancho, String fecha) {
		this.cosumidor = cosumidor;
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

	
}
