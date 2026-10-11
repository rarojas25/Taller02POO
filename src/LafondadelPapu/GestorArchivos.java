package LafondadelPapu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestorArchivos {
	private static final String ARCHIVO_PARTICIPANTES = "Participantes.txt";
	private static final String ARCHIVO_CONSUMIDO = "Consumido.txt"; 

	private static final int RESULTADO_AGREGADA = 0;
	private static final int RESULTADO_DESCARTADA = 1;
	private static final int RESULTADO_SIN_ESPACIO = 2;
	
	public void cargarParticipantes(Fonda fonda) {
		File archivo = new File(ARCHIVO_PARTICIPANTES);
		
		if(!archivo.exists() ) {
			System.out.println(">> ADVERTENCIA: no se encontro el archivo\""
					+  ARCHIVO_PARTICIPANTES + "\".");
			System.out.println(" Se iniciara el programa sin participantes.");
			return;
		}
		
		int lineasDescartadas = 0;
		try {
			Scanner lector = new Scanner(archivo);
			while(lector.hasNextLine()) {
				String nombre = lector.nextLine().trim();
				
				if(!nombre.isEmpty()) {
					if(nombre.indexOf(';') != -1) {
						lineasDescartadas++;
					}else {
						fonda.agregarParticipante(new Participante(nombre));
					}
				}
			}
			lector.close();
		}catch(IOException e) {
			System.out.println(">> ADVERTENCIA: hubo un problema al leer\"" 
			+ ARCHIVO_PARTICIPANTES + "\"." );
		}
		if(lineasDescartadas > 0) {
			System.out.println(">> ADVERTENCIA: se descartaron " + lineasDescartadas
					+ " linea(s) mal formada(s) de \"" + ARCHIVO_PARTICIPANTES + "\"." );
		}
	}
	
	public void cargarConsumo(Fonda fonda) {
		File archivo = new File(ARCHIVO_CONSUMIDO);
		
		if(!archivo.exists()) {
			System.out.println(" >> ADVERTENCIA: no se encontro el archivo\""
					+ ARCHIVO_CONSUMIDO + "\".");
			System.out.println(" Se iniciara el programa sin registros previos.");
			return;
		}
		int lineasDescartadas = 0;
		int lineasSinEspacio = 0;
		
		try {
			Scanner lector =  new Scanner(archivo);
			while(lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				
				if(!linea.isEmpty()) {
					String[] partes = linea.split(";", -1);
					String tipo = partes[0].trim();
					
					int resultado;
					if(tipo.equalsIgnoreCase(Choripan.TIPO)) {
						resultado = procesarChoripan(fonda, partes);
					}else if(tipo.equalsIgnoreCase(Terremoto.TIPO)){
						resultado = procesarTerremoto(fonda, partes);
					}else {
						resultado = RESULTADO_DESCARTADA;
					}
					
					if(resultado == RESULTADO_DESCARTADA) {
						lineasDescartadas++;
					}else if(resultado == RESULTADO_SIN_ESPACIO) {
						lineasSinEspacio++;
					}
				}
			}
			lector.close();
		}catch(IOException e) {
			System.out.println(" >> ADVERTENCIA: hubo un problema al leer\"" + ARCHIVO_CONSUMIDO + "\".");
		}
		if(lineasDescartadas > 0) {
			System.out.println(" >> ADVERTENCIA: se descartaron " + lineasDescartadas
					+ " linea(s) mal formada(s) de \"" + ARCHIVO_CONSUMIDO + "\".");
		}
		if(lineasSinEspacio > 0) {
			System.out.println(" >> ADVERTENCIA: se alcanzo la capacidad maxima de registros ("
					+ Fonda.CAPACIDAD_MAX + "). Se ignoraron " + lineasSinEspacio + "linea(s)."   );
		}
	}
	
	private int procesarChoripan(Fonda fonda, String[] partes) {
		if(partes.length != 5) {
			return RESULTADO_DESCARTADA;
		}
		String consumidor = partes[1].trim();
		String largo = partes[2].trim();
		String ancho = partes[3].trim();
		String fecha = partes[4].trim();
		
		if(consumidor.isEmpty() || !Utilidades.esNumeroPositivo(largo) || !Utilidades.esNumeroPositivo(ancho) || Utilidades.esFechaValida(fecha)) {
			return RESULTADO_DESCARTADA;
		}
		
		int posicion = fonda.buscarParticipante(consumidor);
		if(posicion == -1) {
			return RESULTADO_DESCARTADA;
		}
			
		String nombreOficial = fonda.getParticipante(posicion).getNombre();
		
		Choripan choripan = new Choripan(nombreOficial, Utilidades.convertirADecimal(largo),
				Utilidades.convertirADecimal(ancho), fecha);
		if(!fonda.agregarChoripan(choripan)) {
			return RESULTADO_SIN_ESPACIO;
		}
		return RESULTADO_AGREGADA;
	}
	
	private int procesarTerremoto(Fonda fonda, String[] partes) {
		if(partes.length != 4) {
			return RESULTADO_DESCARTADA;
		}
		String consumidor = partes[1].trim();
		String litros = partes[2].trim();
		String fecha = partes[3].trim();
		
		if(consumidor.isEmpty() || !Utilidades.esNumeroPositivo(litros)
				|| !Utilidades.esFechaValida(fecha)) {
			return RESULTADO_DESCARTADA;
		}
		
		int posicion = fonda.buscarParticipante(consumidor);
		if(posicion == -1) {
			return RESULTADO_DESCARTADA;
		}
		
		String nombreOficial = fonda.getParticipante(posicion).getNombre();
		
		Terremoto terremoto = new Terremoto(nombreOficial, Utilidades.convertirADecimal(litros), fecha);
		if(!fonda.agregarTerremoto(terremoto)) {
			return RESULTADO_SIN_ESPACIO;
		}
		return RESULTADO_AGREGADA;
	}
	
	public boolean guardarConsumos(Fonda fonda) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_CONSUMIDO));
			for(int i = 0; i < fonda.getCantChoripanes(); i++) {
				escritor.write(fonda.getChoripan(i).alinearArchivo());
				escritor.newLine();
			}
			for(int i = 0; i < fonda.getCantTerremotos(); i++) {
				escritor.write(fonda.getTerremoto(i).alineaArchivo());
				escritor.newLine();
			}
			escritor.close();
			return true;
		}catch(IOException e) {
			System.out.println(">> Error: no se pudo escribir en \"" + ARCHIVO_CONSUMIDO + "\".");
			return false;
		}
	}
}