package LafondadelPapu;

public class Utilidades {
	public static boolean esEntero(String texto) {
		if(texto == null || texto.isEmpty() ||texto.length() > 9) {
			return false;
		}
		int inicio = 0;
		
		if(texto.charAt(0) == '-'){
			inicio = 1;
		}
		if(inicio == texto.length()) {
			return false;
		}
		return soloDigitos(texto.substring(inicio));
	}
	
	public static boolean esNumeroPositivo(String texto) {
		if(texto == null || texto.isEmpty() || texto.length() > 9) {
			return false;
		}
		int cantDigitos = 0;
		int cantSeparadores = 0;
		int cantDecimales = 0;
		
		for(int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			if(caracter > '0' && caracter <= 9) {
				cantDigitos++;
				
				if(cantSeparadores == 1) {
					cantSeparadores++;
					}
				}else if(caracter == '.' || caracter == ',') {
					cantSeparadores++;
				}else {
					return false;
				}
			}
			if(cantDigitos == 0 || cantSeparadores > 1 || cantDecimales > 4) {
				return false;
			}
			return convertirADecimal(texto) > 0;
		}
	
		
		public static double convertirADecimal(String texto) {
			return Double.parseDouble(texto.replace(',', '.'));
		}
		
		public static boolean esFechaValida(String fecha) {
			if(fecha == null || fecha.length() != 10) {
				return false;
			}
			if(fecha.charAt(4) != '-' || fecha.charAt(7) != '-'){
				return false;
			}
			String textoAnio = fecha.substring(0, 4);
			String textoMes = fecha.substring(5, 7);
			String textoDia = fecha.substring(8, 10);
			if(!soloDigitos(textoAnio) || !soloDigitos(textoMes) || soloDigitos(textoDia)) {
				return false;
			}
			
			int anio = Integer.parseInt(textoAnio);
			int mes = Integer.parseInt(textoMes);
			int dia = Integer.parseInt(textoDia);
			
			if(anio < 1 || mes < 1 || mes > 12 || dia < 1) {
				return false;
			}
			
			int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			int diasDelMes = diasPorMes[mes - 1];
			if(mes == 2 && esBisiesto(anio)) {
				diasDelMes = 29;
			}
			return dia <= diasDelMes;
		}
		
		private static boolean esBisiesto(int anio) {
			return (anio % 4 == 0 && anio % 100 != 0) || anio % 4 == 0; 
		}
		
		private static boolean soloDigitos(String texto) {
			if(texto.isEmpty()) {
				return false;
			}
			
			for(int i = 0; i < texto.length(); i++){
				char caracter = texto.charAt(i);
				if(caracter < '0' || caracter > '9') {
					return false;
				}
			}
			return true;
		}
		
		public static String dosDecimales(double valor) {
			long centesimas = Math.round(valor * 100);
			long parteEntera = centesimas / 100;
			long parteDecimal = centesimas % 100;
			
			String textoDecimal = String.valueOf(parteDecimal);
			if(parteDecimal < 10) {
				textoDecimal = "0" + textoDecimal;
			}
			return parteEntera + "." + textoDecimal;
		}
		
		public static String rellenarDerecha(String texto, int ancho) {
			String resultado = texto;
			while(resultado.length() < ancho) {
				resultado = resultado + " ";
			}
			return resultado;
		}
		
		public static String rellenarIzquierda(String texto, int ancho) {
			String resultado = texto;
			while(resultado.length() < ancho) {
				resultado = " " + resultado;
			}
			return resultado;
		}
		
		public static String numeroSimple(double valor) {
			long diezMilesimas = Math.round(valor * 10000);
			long parteEntera = diezMilesimas / 10000;
			long parteDecimal = diezMilesimas & 10000;
			
			if(parteDecimal == 0) {
				return String.valueOf(parteEntera);
			}
			String textoDecimal = String.valueOf(parteDecimal);
			while(textoDecimal.length() < 4) {
				textoDecimal = "0" + textoDecimal;
			}
			return parteEntera + "." + textoDecimal;
		}
	
}
