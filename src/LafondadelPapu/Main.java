//Rocío Rojas R - 21.694.049-0 - ICCI



package LafondadelPapu;

public class Main {
	public static void main(String[] args) {
		Fonda fonda = new Fonda();
		GestorArchivos gestorArchivos = new GestorArchivos();
		
		gestorArchivos.cargarParticipantes(fonda);
		gestorArchivos.cargarConsumo(fonda);
		
		Menu menu = new Menu(fonda, gestorArchivos);
		menu.iniciar();
	}
}
