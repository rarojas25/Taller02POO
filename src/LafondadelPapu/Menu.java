package LafondadelPapu;

import java.util.Scanner;


public class Menu {
	private static final int ANCHO_MENU = 44;
	private static final int ANCHO_TABLA = 60;
	
	private Fonda fonda;
	private GestorArchivos gestorArchivos;
	private Scanner teclado;
	
	public Menu(Fonda fonda, GestorArchivos gestorArchivos) {
		this.fonda = fonda;
		this.gestorArchivos = gestorArchivos;
		this.teclado = new Scanner(System.in);
	}
	
	public void iniciar() {
		boolean salir = false;
		while(!salir) {
			System.out.println();
			imprimirTitulo("LA FONDA DEL PAPU", ANCHO_MENU);
			System.out.println(" Registros cargados: " + fonda.getCantConsumos());
			System.out.println(repetir('-', ANCHO_MENU));
			System.out.println("[1] Ver registros\r\n"
					+ "[2] Actualizar registros\r\n"
					+ "[3] Calcular metricas\r\n"
					+ "[4] Salir");
			System.out.println(repetir('=', ANCHO_MENU));
			
			int opcion = leerOpcion("Seleccione una opcion: ", 1, 4);
			
			switch(opcion){
				case 1:
					menuVerRegistros();
					break;
				case 2:
					menuActualizarRegistros();
					break;
				case 3:
					menuMetricas();
					break;
				case 4:
					System.out.println(">> Hasta luego...");
					salir = true;
			}
		}
		teclado.close();
	}
	
	public void menuVerRegistros() {
		boolean volver = false;
		while(!volver) {
			System.out.println();
			imprimirTitulo("VER REGISTROS", ANCHO_MENU);
			System.out.println("[1] Choripanes\r\n"
					+ "[2] Terremotos\r\n"
					+ "[3]Volver al menu principal");
			System.out.println(repetir('=', ANCHO_MENU));
			
			int opcion = leerOpcion("Seleccione una opcion: ", 1, 3);
			
			switch(opcion) {
				case 1:
					mostrarChoripanes();
					esperarEnter();
					break;
				case 2:
					mostrarTerremotos();
					esperarEnter();
					break;
				case 3:
					volver = true;
			}
		}
	}
	
	private void menuActualizarRegistros() {
		boolean volver = false;
		while(!volver) {
			System.out.println();
			imprimirTitulo("ACTUALIZAR REGISTROS", ANCHO_MENU);
			System.out.println("[1] Agregar\r\n"
					+ "[2] Editar\r\n"
					+ "[3] Eliminar\r\n"
					+ "[4] Volver al menu principal");
			System.out.println(repetir('=', ANCHO_MENU));
			
			int opcion = leerOpcion("Seleccione una opcion: ", 1, 4);
			
			switch(opcion) {
				case 1:
					agregarRegistro();
					break;
				case 2:
					editarRegistro();
					break;
				case 3:
					eliminarRegistro();
					break;
				case 4:
					volver = true;
			}
		}
	}
	
	private void menuMetricas() {
		boolean volver = false;
		
		while(!volver) {
			System.out.println();
			imprimirTitulo("CALCULAR METRICAS", ANCHO_MENU);
			System.out.println("[1] Choripanes comidos en total\r\n"
					+ "[2] Terremotos tomados en total\r\n"
					+ "[3] Choripanes comidos por usuario\r\n"
					+ "[4] Terremotos tomados por usuarios\r\n"
					+ "[5] KCal consumidas por usuario\r\n"
					+ "[6] Dias ebrio por usuario\r\n"
					+ "[7] Volver al menu principal");
			System.out.println(repetir('=', ANCHO_MENU));
			
			int opcion = leerOpcion("Seleccione una opcion: ", 1, 7);
			switch(opcion) {
				case 1:
					mostrarTotalChoripanes();
					break;
				case 2:
					mostrarTotalTerremotos();
					break;
				case 3:
					mostrarChoripanesPorUsuario();
					break;
				case 4:
					mostrarTerremotosPorUsuario();
					break;
				case 5:
					mostrarKcalPorUsuario();
					break;
				case 6:
					mostrarDiasEbrioPorUsuario();
					break;
				case 7:
					volver = true;
			}
		}
	}
	
	private void mostrarChoripanes() {
		System.out.println();
		imprimirTitulo("CHORIPANES", ANCHO_TABLA);
		
		int cant = mostrarListadoChoripanes(true);
		if(cant == 0) {
			System.out.println(" (no hay choripanes registrados)");
		}
		System.out.println(repetir('-', ANCHO_TABLA));
		System.out.println(" Total: " + cant + " choripanes registrados");
		System.out.println(repetir('=', ANCHO_TABLA));
	}
	
	private void mostrarTerremotos() {
		System.out.println();
		imprimirTitulo("TERREMOTOS", ANCHO_TABLA);
		int cant = mostrarListadoTerremotos(true);
		if(cant == 0) {
			System.out.println(" (no hay terremotos registrados)");
		}
		System.out.println(repetir('-', ANCHO_TABLA));
		System.out.println(" Total: " + cant + "terremotos registrados ("
		+ Utilidades.dosDecimales(fonda.litrosTotales()) + "L)");
		System.out.println(repetir('=', ANCHO_TABLA));
	}
	
	private int mostrarListadoChoripanes(boolean incluirKcal) {
		String encabezado = " " + Utilidades.rellenarDerecha("#", 3)
				+ " " + Utilidades.rellenarDerecha("Consumidor", 12)
				+ " " + Utilidades.rellenarIzquierda("Largo", 9)
				+ " " + Utilidades.rellenarIzquierda("Ancho", 9)
				+ " " + Utilidades.rellenarDerecha("Fecha", 11);
		if(incluirKcal) {
			encabezado += Utilidades.rellenarIzquierda("KCal", 8);
		}
		System.out.println(encabezado);
		
		for(int i = 0; i < fonda.getCantChoripanes(); i++) {
			System.out.println(fonda.getChoripan(i).armarFila(i + 1, incluirKcal));
		}
		return fonda.getCantChoripanes();
	}
	
	private int mostrarListadoTerremotos(boolean incluirKcal) {
		String encabezado = " " + Utilidades.rellenarDerecha("#", 3)
		+ " " + Utilidades.rellenarDerecha("Consumidor", 12)
		+ " " + Utilidades.rellenarIzquierda("Litros", 9)
		+ " " + Utilidades.rellenarDerecha("Fecha", 11);
		if(incluirKcal) {
			encabezado += Utilidades.rellenarIzquierda("KCal", 8);
		}
		System.out.println(encabezado);
		
		for(int i = 0; i < fonda.getCantTerremotos(); i++) {
			System.out.println(fonda.getTerremoto(i).amarFila(i + 1, incluirKcal));
		}
		return fonda.getCantTerremotos();
	}
	
	private void esperarEnter() {
		System.out.println();
		leerLinea("Presione ENTER para continuar...");
	}
	
	private void agregarRegistro() {
		System.out.println();
		
		if(!fonda.hayEspacio()) {
			System.out.println(" >> Error: se alcanzo la capacidad maxima de registros ("
					+ Fonda.CAPACIDAD_MAX + ").");
			System.out.println(" No es posible agregar mas consumos.");
			return;
		}
		if(fonda.getCantParticipantes() == 0) {
			System.out.println(">> Error: no hay consumidores disponibles (revise Participantes.txt).");
			return;
		}
		
		int opcionTipo = pedirTipoRegistro("Tipo de registro: ");
		if(opcionTipo == 3) {
			System.out.println(">> Operacion cancelada.");
			return;
		}
		
		System.out.println();
		System.out.println("Consumidores disponibles: ");
		mostrarParticipantes();
		
		int numCosumidor = leerOpcion("Seleccione el consumidor: ", 1, fonda.getCantParticipantes());
		String consumidor = fonda.getParticipante(numCosumidor - 1).getNombre();
		System.out.println();
		
		if(opcionTipo == 1) {
			double largo = leerDecimalPositivo("Largo del choripan (cm): ", "el largo");
			double ancho =  leerDecimalPositivo("Ancho del choripan (cm) ", "el ancho");
			String fecha = leerFecha("Fecha (YYYY-MM-DD): ");
			Choripan nuevo = new Choripan(consumidor, largo, ancho, fecha);
			
			if(!fonda.agregarChoripan(nuevo)) {
				System.out.println(">> Error: se alcanzo la capacidad maxima de registros ("
						+ Fonda.CAPACIDAD_MAX + ").");
				return;
			}
			System.out.println();
			
			if(gestorArchivos.guardarConsumos(fonda)) {
				System.out.println(" >> Registro agregado correctamente y guardado en Consumido.txt");
			}else {
				System.out.println(" >> ADVERTENCIA: el registro se agrego, pero no se pudo guardar en Consumidor.txt");
			}
			System.out.println(" " + nuevo.describir());
		}else {
			double litros = leerDecimalPositivo("Litros del terremoto: ", "los litros");
			String fecha = leerFecha("Fecha (YYYY-MM-DD): ");
			Terremoto nuevo = new Terremoto(consumidor, litros, fecha);
			
			if(!fonda.agregarTerremoto(nuevo)) {
				System.out.println(" >> Error: se alcanzo la capacidad maxima de registros("
						+ Fonda.CAPACIDAD_MAX + ").");
				return;
			}
			System.out.println();
			if(gestorArchivos.guardarConsumos(fonda)) {
				System.out.println(" >> Registro agregado correctamente y guardado en Consumos.txt");
			}else {
				System.out.println(" >> ADVERTENCIA: el registro se agrego, pero no se pudo guardar en Consumido.txt");
			}
			System.out.println(" " + nuevo.describir());
		}
	}
	
	private void editarRegistro() {
		System.out.println();
		int opcionTipo = pedirTipoRegistro("Tipo de registro a editar: ");
		if(opcionTipo == 3) {
			System.out.println(">> Operacion cancelada.");
			return;
		}
		
		if(opcionTipo == 1) {
			System.out.println();
			int cant = mostrarListadoChoripanes(false);
			if(cant == 0) {
				System.out.println(">> No hay registros de ese tipo para editar.");
				return;
			}
			System.out.println();
			int num = leerNumeroRegistro("Seleccione el registro a editar (0 para cancelar):" , cant);
			if(num == 0) {
				System.out.println(">> Operacion cancelada.");
				return;
			}
			Choripan choripan = fonda.getChoripan(num - 1);
			System.out.println();
			System.out.println("Registro actual -> " + choripan.describir());
			System.out.println("Deje el campo vacio para mantener el valor actual.");
			System.out.println("Consumidores disponibles: ");
			mostrarParticipantes();
			System.out.println();
			
			editarChoripan(choripan);
			
			System.out.println();
			
			if(gestorArchivos.guardarConsumos(fonda)) {
				System.out.println(">> Registro actualizado correctamente y guardado en Consumido.txt");
			}else {
				System.out.println(">> ADVERTENCIA: el registro se actualizo, pero no se pudo guardar en Consumido.txt");
			}
			System.out.println(" " + choripan.describir());
		}else {
			System.out.println();
			
			int cant = mostrarListadoTerremotos(false);
			if(cant == 0) {
				System.out.println(">> No hay registros de ese tipo para editar.");
				return;
			}
			System.out.println();
			int num = leerNumeroRegistro("Seleccione el registro a editar (0 para cancelar): ", cant);
			if(num == 0) {
				System.out.println(">> Operacion cancelada.");
				return;
			}
			
			Terremoto terremoto = fonda.getTerremoto(num - 1);
			System.out.println();
			System.out.println("Registro actual -> " + terremoto.describir());
			System.out.println("Deje el campo vacio para mantener el valor actual.");
			System.out.println("Consumidores disponibles: ");
			mostrarParticipantes();
			System.out.println();
			
			editarTerremoto(terremoto);
			
			System.out.println();
			if(gestorArchivos.guardarConsumos(fonda)) {
				System.out.println(">> Registro actualizado correctamente y guardado en Consumido.txt");
			}else {
				System.out.println(">> ADVERTENCIA: el registro se actualizo, pero no se pudo guardar en Consumido.txt");
			}
			System.out.println(" " + terremoto.describir());
		}
	}
	
	private void editarChoripan(Choripan choripan) {
		choripan.setCosumidor(leerConsumidorOpcional(choripan.getCosumidor()));
		choripan.setLargo(leerDecimalOpcional("Largo (cm)", choripan.getLargo()));
		choripan.setAncho(leerDecimalOpcional("Ancho(cm)", choripan.getAncho()));
		choripan.setFecha(leerFechaOpcional(choripan.getFecha()));
	}
	
	private void editarTerremoto(Terremoto terremoto) {
		terremoto.setConsumidor(leerConsumidorOpcional(terremoto.getConsumidor()));
		terremoto.setLitros(leerDecimalOpcional("Litros", terremoto.getLitros()));
		terremoto.setFecha(leerFechaOpcional(terremoto.getFecha()));
	}
	
	private void eliminarRegistro() {
		System.out.println();
		int opcionTipo = pedirTipoRegistro("Tipo de registro a eliminar: ");
		if(opcionTipo == 3) {
			System.out.println(">> Operacion cancelada.");
			return;
		}
		
		if(opcionTipo == 1) {
			System.out.println();
			int cant = mostrarListadoChoripanes(false);
			if(cant == 0) {
				System.out.println(">> No hay registros de ese tipo para eliminar.");
				return;
			}
			System.out.println();
			int num = leerNumeroRegistro("Seleccione el registro a eliminar (0 para calcelar): ", cant);
			if(num == 0) {
				System.out.println(">> Operacion cancelada.");
				return;
			}
			Choripan choripan = fonda.getChoripan(num - 1);
			boolean confirmado = leerConfirmacion("Confirma la eliminacion de \"" + choripan.describir() + "\"? (s/n): ");
			if(!confirmado) {
				System.out.println(">> Eliminacion cancelada. No se hicieron cambios.");
				return;
			}
			
			fonda.eliminarChoripan(num - 1);
			System.out.println();
			if(gestorArchivos.guardarConsumos(fonda)) {
				System.out.println(">> Registro eliminado correctamente. Cambios guardados en Consumido.txt");
			}else {
				System.out.println(">> ADVERTENCIA: el registro se elimino, pero no se pudo guardar en Consumido.txt");
				}
			}else {
				System.out.println();
				int cant = mostrarListadoTerremotos(false);
				if(cant == 0){
					System.out.println(">> No hay registros de ese tipo para eliminar.");
					return;
				}
				System.out.println();
				int num = leerNumeroRegistro("Seleccione el registro a eliminar (0 para cancelar): ", cant);
				if(num == 0) {
					System.out.println(">> Operacion cancelada.");
					return;
				}
				Terremoto terremoto = fonda.getTerremoto(num - 1);
				boolean confirmado = leerConfirmacion("Confirma la eliminacion de \"" + terremoto.describir() + "\"? (s/n): ");
				if(!confirmado) {
					System.out.println(">> Eliminacion cancelada. No se hicieron cambios.");
					return;
				}
				fonda.eliminarTerremoto(num - 1);
				System.out.println();
				if(gestorArchivos.guardarConsumos(fonda)) {
					System.out.println(">> Registro eliminado correctamente. Cambios guardados en Consumido.txt");
				}else {
					System.out.println(">> ADVERTENCIA: el registro se elimino, pero no se pudo guardar en Consumido.txt");
				}
			}
		}
	
		private void mostrarTotalChoripanes() {
			System.out.println();
			System.out.println(">> Total de choripanes comidos: " + fonda.getCantChoripanes());
		}
		
		private void mostrarTotalTerremotos() {
			System.out.println();
			System.out.println(" >> Total de terremotos tomados: " + fonda.getCantTerremotos()
			+ " (" + Utilidades.dosDecimales(fonda.litrosTotales()) + " litros en total)");
		}
		
		public void mostrarChoripanesPorUsuario() {
			System.out.println();
			imprimirTitulo("CHORIPANES POR USUARIO", ANCHO_TABLA);
			System.out.println(" " + Utilidades.rellenarDerecha("Usuario", 12)
			+ " " + Utilidades.rellenarIzquierda("Cantidad", 8));
			
			if(fonda.getCantParticipantes() == 0) {
				System.out.println(" (no hay participantes registrados)");
			}
			for(int i = 0; i < fonda.getCantParticipantes(); i++) {
				String nombre = fonda.getParticipante(i).getNombre();
				int cant = fonda.choripanesPorUsuario(nombre);
				System.out.println(" " + Utilidades.rellenarDerecha(nombre, 12)
				+ " " + Utilidades.rellenarIzquierda(String.valueOf(cant), 8));
			}
			System.out.println(repetir('=', ANCHO_TABLA));
		}
		
		private void mostrarTerremotosPorUsuario() {
			System.out.println();
			imprimirTitulo("TERREMOTOS POR USUARIO", ANCHO_TABLA);
			System.err.println(" " + Utilidades.rellenarDerecha("Usuario", 12)
			+ " " + Utilidades.rellenarIzquierda("Cantidad", 8) 
			+ " " + Utilidades.rellenarIzquierda("Litros", 10));
			
			if(fonda.getCantParticipantes() == 0) {
				System.out.println(" (no hay participantes registrados)");
			}
			for(int i = 0; i < fonda.getCantParticipantes(); i++) {
				String nombre = fonda.getParticipante(i).getNombre();
				int cant = fonda.terremotosPorUsuario(nombre);
				String litros = Utilidades.dosDecimales(fonda.litrosPorUsuario(nombre)) + " L";
				System.out.println(" " + Utilidades.rellenarDerecha(nombre, 12)
				+ " " + Utilidades.rellenarIzquierda(String.valueOf(cant), 8)
				+ " " + Utilidades.rellenarIzquierda(litros, 10));
			}
			System.out.println(repetir('=', ANCHO_TABLA));
		}
		
		private void mostrarKcalPorUsuario() {
			System.out.println();
			imprimirTitulo("KCAL POR USUARIO", ANCHO_TABLA);
			System.out.println(" " + Utilidades.rellenarDerecha("Usuario", 12)
			+ " " + Utilidades.rellenarIzquierda("Choripan", 10) 
			+ " " + Utilidades.rellenarIzquierda("Terremoto", 11)
			+ " " + Utilidades.rellenarIzquierda("Total", 10));
			
			if(fonda.getCantParticipantes() == 0) {
				System.out.println(" (no hay participantes registrados)");
			}
			for(int i = 0; i < fonda.getCantParticipantes(); i++) {
				String nombre = fonda.getParticipante(i).getNombre();
				double kcalChoripan = fonda.kcalChoripanPorUsuario(nombre);
				double kcalTerremoto = fonda.kcalTerremotosPorUsuario(nombre);
				System.out.println(" " + Utilidades.rellenarDerecha(nombre, 12)
					+ " " + Utilidades.rellenarIzquierda(Utilidades.dosDecimales(kcalChoripan), 10)
					+ " " + Utilidades.rellenarIzquierda(Utilidades.dosDecimales(kcalTerremoto), 11)
					+ " " + Utilidades.rellenarIzquierda(Utilidades.dosDecimales(kcalChoripan + kcalTerremoto), 10));
			}
			System.out.println(repetir('-', ANCHO_TABLA));
			System.out.println(" Consumo total de la fonda: " + Utilidades.dosDecimales(fonda.kcalTotalFonda()) + " kcal");
			System.out.println(repetir('=', ANCHO_TABLA));
		}
		
		private void mostrarDiasEbrioPorUsuario() {
			System.out.println();
			imprimirTitulo("DIAS EBIO POR USUARIO", ANCHO_TABLA);
			System.out.println(" (Se cuenta un dia ebrio con " + Utilidades.numeroSimple(Fonda.LITROS_DIA_EBRIO)
			+ " L o mas en la misma fecha)");
			System.out.println();
			System.out.println(" " + Utilidades.rellenarDerecha("Usuario", 12)
			+ " " + Utilidades.rellenarIzquierda("Dias ebrio", 10) + "Detalle");
		
			if(fonda.getCantParticipantes() == 0) {
				System.out.println(" (no hay participantes registrados)");
			}
			for(int i = 0; i < fonda.getCantParticipantes(); i++) {
				String nombre = fonda.getParticipante(i).getNombre();
				String[] diasEbrio = fonda.obetnerDiasEbrio(nombre);
				
				String detalle = "-";
				if(diasEbrio.length > 0) {
					detalle = diasEbrio[0];
					for(int j = 1; j < diasEbrio.length; j++) {
						detalle += ", " + diasEbrio[j];
					}
				}
				System.out.println(" " + Utilidades.rellenarDerecha(nombre, 12)
				+ " " + Utilidades.rellenarIzquierda(String.valueOf(diasEbrio.length), 10)
				+ " " + detalle);
			}
			System.out.println(repetir('=', ANCHO_TABLA));
		}
		
		private String leerLinea(String mensaje) {
			System.out.println(mensaje);
			if(!teclado.hasNextLine()) {
				System.out.println();
				System.out.println(" >> Fin de la entrada de datos. Cerrando el programa...");
				System.exit(0);
			}
			return teclado.nextLine().trim();
		}
		
		private int leerOpcion(String mensaje, int min, int max) {
			int opcion = min;
			boolean valida = false;
			
			while(!valida) {
				String texto = leerLinea(mensaje);
				
				if(texto.isEmpty()) {
					System.out.println(">> Error: no ingreso ninguna opcion. Intente nuevamente.");
				}else if(!Utilidades.esEntero(texto)) {
					System.out.println(" >> Error: la opcion debe ser un numero entre " + min + " y " 
					+ max + ". Intente nuevamente.");
				}else {
					opcion = Integer.parseInt(texto);
					if(opcion < min || opcion > max) {
						System.out.println(" >> Error: opcion fuera de rango. Intente nuevamente.");
					}else {
						valida = true;
					}
				}
			}
			return opcion;
		}
		
		private int leerNumeroRegistro(String mensaje, int cant) {
			int num = 0;
			boolean valido = false;
			
			while(!valido) {
				String texto = leerLinea(mensaje);
				
				if(texto.isEmpty()) {
					System.out.println(" >> Error: no ingreso ningun numero. Intente nuevamente.");
				}else if(!Utilidades.esEntero(texto)) {
					System.out.println(" >> Error: debe ingresar un numero entero. Intente nuevamente ");
				}else {
					num = Integer.parseInt(texto);
					if(num < 0 || num > cant) {
						System.out.println(" >> Error: no existe un registro con ese numero. Intente nuevamente.");
					}else {
						valido = true;
					}
				}
			}
			return num;
		}
		
		private double leerDecimalPositivo(String mensaje, String nombreCampo) {
			double valor = 0;
			boolean valido = false;
			
			while(!valido) {
				String texto = leerLinea(mensaje);
				if(Utilidades.esNumeroPositivo(texto)) {
					valor = Utilidades.convertirADecimal(texto);
					valido = true;
				}else {
					System.out.println(" >> Error: valor invalido para " + nombreCampo
							+ ". Debe ser un numero mayor a 0 (maximo 4 decimales). Intente nuevamente.");
				}
			}
			return valor;
		}
		
		private String leerFecha(String mensaje) {
			String fecha = "";
			boolean valida = false;
			
			while(!valida) {
				fecha = leerLinea(mensaje);
				if(Utilidades.esFechaValida(fecha)) {
					valida = true;
				}else {
					System.out.println(" >> Error: la fecha debe ser real y tener el formato YYYY-MM-DD, Intente nuevamente.");
				}
			}
			return fecha;
		}
		
		private double leerDecimalOpcional(String nombreCampo, double valorActual) {
			double valor = valorActual;
			boolean valido = false;
			
			while(!valido) {
				String texto = leerLinea(nombreCampo + " [" + Utilidades.numeroSimple(valorActual) + "]: ");
				if(texto.isEmpty()){
					valido = true;
				}else if(Utilidades.esNumeroPositivo(texto)) {
					valor = Utilidades.convertirADecimal(texto);
					valido = true;
				}else {
					System.out.println(" >> Error: valor invalido para " + nombreCampo 
							+ ". Debe ser un numero mayor a 0 (maximo 4 decimales). Intente nuevamente.");
				}
			}
			return valor;
		}
		
	private String leerFechaOpcional(String fechaActual) {
		String fecha = fechaActual;
		boolean valida = false;
		
		while(!valida) {
			String texto = leerLinea("Fecha [" + fechaActual +"]: ");
			if(texto.isEmpty()) {
				valida = true;
			}else if(Utilidades.esFechaValida(texto)) {
				fecha = texto;
				valida = true;
			}else {
				System.out.println(" >> Error: la fecha debe ser real y tener el formato YYYY-MM-DD . Intente nuevamente. ");
			}
		}
		return fecha;
	}
	
	private String leerConsumidorOpcional(String consumidorActual) {
		String consumidor = consumidorActual;
		boolean valido = false;
		
		while(!valido) {
			String texto = leerLinea("Consumidor [" + consumidorActual + "]: ");
			if(texto.isEmpty()) {
				valido = true;
			}else {
				int posicion = fonda.buscarParticipante(texto);
				if(posicion == -1){
					System.out.println(" >> Error: ese consumidor no esta en la lista de participantes. Intente nuevamente.");
				}else {
					consumidor = fonda.getParticipante(posicion).getNombre();
					valido = true;
				}
			}
		}
		return consumidor;
	}
	
	private boolean leerConfirmacion(String mensaje) {
		boolean respuesta = false;
		boolean valida = false;
		
		while(!valida) {
			String texto = leerLinea(mensaje);
			if(texto.equalsIgnoreCase("s")) {
				respuesta = true;
				valida = true;
			}else if(texto.equalsIgnoreCase("n")) {
				respuesta = false;
				valida = true;
			}else {
				System.out.println(" >> Error: responda \"s\" para si o \"n\" para no. Intente nuevamente.");
			}
		}
		return respuesta;
	}
	
	private int pedirTipoRegistro(String titulo) {
		System.out.println(titulo);
		System.out.println("[1] Choripan\r\n"
				+ "[2] Terremoto\r\n"
				+ "[3] Cancelar");
		return leerOpcion("Seleccione una opcion: ", 1, 3);
	}
	
	private void mostrarParticipantes() {
		for(int i = 0; i < fonda.getCantParticipantes(); i++) {
			System.out.println(" [" + (i+1) + "] " + fonda.getParticipante(i).getNombre());
		}
	}
	
	private void imprimirTitulo(String texto, int ancho) {
		String tituloConEspacios = " " + texto + " ";
		int sobrante = ancho - tituloConEspacios.length();
		if(sobrante < 0) {
			sobrante = 0;
		}
		int izq = sobrante / 2;
		int der = sobrante - izq;
		System.out.println(repetir('=', izq) + tituloConEspacios + repetir('=', der));
	}
	
	private String repetir(char caracter, int veces) {
		String texto = "";
		for(int i = 0; i < veces; i++) {
			texto += caracter;
		}
		return texto;
	}
}
