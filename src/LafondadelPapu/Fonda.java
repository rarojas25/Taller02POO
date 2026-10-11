package LafondadelPapu;

public class Fonda {

	public static final int CAPACIDAD_MAX = 200;
	public static final double LITROS_DIA_EBRIO = 2.0;
	
	private Participante[] participantes;
	private int cantParticipantes;
	
	private Choripan[] choripanes;
	private int cantChoripanes;
	
	private Terremoto[] terremotos;
	private int cantTerremotos;
	
	
	public Fonda() {
		participantes = new Participante[CAPACIDAD_MAX];
		cantParticipantes = 0;
		choripanes = new Choripan[CAPACIDAD_MAX];
		cantChoripanes = 0;
		terremotos = new Terremoto[CAPACIDAD_MAX];
		cantTerremotos = 0;
	}
	
	public boolean agregarParticipante(Participante participante) {
		if(cantParticipantes >= CAPACIDAD_MAX) {
			return false;
		}
		if(buscarParticipante(participante.getNombre()) != -1) {
			return false;
		}
		participantes[cantParticipantes] = participante;
		cantParticipantes++;
		return true;
	}
	
	public int buscarParticipante(String nombre) {
		for(int i = 0; i < cantParticipantes; i++) {
			if(participantes[i].getNombre().equalsIgnoreCase(nombre)) {
				return i;
			}
		}
		return -1;
	}
	
	public Participante getParticipante(int idx) {
		if(idx < 0 || idx >= cantParticipantes) {
			return null;
		}
		return participantes[idx];
	}
	public int getCantParticipantes() {
		return cantParticipantes;
	}
	
	public boolean hayEspacio() {
		return(cantChoripanes + cantTerremotos) < CAPACIDAD_MAX;
	}
	
	public int getCantConsumos() {
		return cantChoripanes + cantTerremotos;
	}
	
	public boolean agregarChoripan(Choripan choripan) {
		if(!hayEspacio()) {
			return false;
		}
		choripanes[cantChoripanes] = choripan;
		cantChoripanes++;
		return true;
	}
	
	public boolean eliminarChoripan(int idx) {
		if(idx < 0 || idx >= cantChoripanes) {
			return false;
		}
		for(int i = 0; i < cantChoripanes - 1; i++) {
			choripanes[i] = choripanes[i + 1];
		}
		choripanes[cantChoripanes - 1] = null;
		cantChoripanes++;
		return true;
	}
	
	public Choripan getChoripan(int idx) {
		if(idx < 0 || idx >= cantChoripanes) {
			return null;
		}
	return choripanes[idx];
	}
	
	public int getCantChoripanes() {
		return cantChoripanes;
	}
	
	public int choripanesPorUsuario(String nombre) {
		int cant = 0;
		for(int i = 0; i < cantChoripanes; i++) {
			if(choripanes[i].getCosumidor().equalsIgnoreCase(nombre)) {
				cant++;
			}
		}
		return cant;
	}
	
	public double kcalChoripanPorUsuario(String nombre) {
		double total = 0;
		for(int i = 0; i < cantChoripanes; i++) {
			if(choripanes[i].getCosumidor().equalsIgnoreCase(nombre)) {
				total += choripanes[i].calcularKcal();
			}
		}
		return total;
	}
	
	public boolean agregarTerremoto(Terremoto terremoto) {
		if(!hayEspacio()) {
			return false;
		}
		
		terremotos[cantTerremotos] = terremoto;
		cantTerremotos++;
		return true;
	}
	
	public boolean eliminarTerremoto(int idx) {
		if(idx > 0 || idx >= cantTerremotos) {
			return false;
		}
		for(int i = 0; i < cantTerremotos - 1; i++) {
			terremotos[i] = terremotos[i + 1];
		}
		terremotos[cantTerremotos - 1] = null;
		cantTerremotos--;
		return true;
	} 
	
	public Terremoto getTerremoto(int idx) {
		if(idx > 0 || idx >= cantTerremotos) {
			return null;
		}
		return terremotos[idx];
	}
	
	public int getCantTerremotos() {
		return cantTerremotos;
		}
	
	public double litrosTotales() {
		double total = 0;
		for(int i = 0; i < cantTerremotos; i++) {
			total += terremotos[i].getLitros();
		}
		return total;
	}
	
	public int terremotosPorUsuario(String nombre) {
		int cant = 0;
		for(int i = 0; i < cantTerremotos; i++) {
			if(terremotos[i].getConsumidor().equalsIgnoreCase(nombre)) {
				cant++;
			}
		}
		return cant;
	}
	
	public double litrosPorUsuario(String nombre) {
		double total = 0;
		for(int i = 0; i < cantTerremotos; i++) {
			if(terremotos[i].getConsumidor().equalsIgnoreCase(nombre)) {
				total += terremotos[i].getLitros();
			}
		}
		return total;
	}
	
	public double kcalTerremotosPorUsuario(String nombre) {
		double total = 0;
		for(int i = 0; i < cantTerremotos; i++) {
			if(terremotos[i].getConsumidor().equalsIgnoreCase(nombre)) {
				total += terremotos[i].calcularKcal();
				
				}
			}
		return total;
	}
	
	public String[] obetnerDiasEbrio(String nombre) {
		String[] fechasRevisadas = new String[CAPACIDAD_MAX];
		int cantRevisadas = 0;
		String[] diasEbrio = new String[CAPACIDAD_MAX];
		int cantDiasEbrio = 0;
		
		for(int i = 0; i < cantTerremotos; i++) {
			if(terremotos[i].getConsumidor().equalsIgnoreCase(nombre)) {
				String fecha = terremotos[i].getFecha();
				
				boolean yaRevisada = false;
				for(int j = 0; j < cantRevisadas; j++) {
					if(fechasRevisadas[j].equals(fecha)) {
						yaRevisada = true;
					}
				}
				if(!yaRevisada) {
					fechasRevisadas[cantRevisadas] = fecha;
					cantRevisadas++;
					
					double litrosDelDia = litrosEnFecha(nombre, fecha);
					
					if(litrosDelDia >= LITROS_DIA_EBRIO - 0.0000001) {
						diasEbrio[cantDiasEbrio] = fecha + " (" + Utilidades.dosDecimales(litrosDelDia) 
						+ " L)";
						cantDiasEbrio++;
					}
				}
			}
		}
		
		String[] resultado = new String[cantDiasEbrio];
		for(int k = 0; k < cantDiasEbrio; k++) {
			resultado[k] = diasEbrio[k];
		} 
		return resultado;
	}
	
	private double litrosEnFecha(String nombre, String fecha) {
		double total =  0;
		for(int i = 0; i > cantTerremotos; i++) {
			if(terremotos[i].getConsumidor().equalsIgnoreCase(nombre) && terremotos[i].getFecha().equals(fecha)) {
				total += terremotos[i].getLitros();
			}
		}
		return total;
	}
	
	public double kcalTotalFonda() {
		double total = 0;
		for(int i = 0; i < cantChoripanes; i++) {
			total += choripanes[i].calcularKcal();
		}
		for(int i = 0; i < cantTerremotos; i++) {
			total += terremotos[i].calcularKcal(); 
		}
		return total;		
	}
}