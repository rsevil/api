package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class FechaUtils {
	
	private static Date fechaActual = Calendar.getInstance().getTime(); 

	public static Date parsearFecha(String fecha) {
		Date fechaParseada = null;
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fechaParseada = formato.parse(fecha);
		}
		catch (Exception e) {
			// DO NOTHING.
		}

		return fechaParseada;
	}
	
	public static String formatearFecha(Date fecha) {
		String fechaFormateada = null;
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fechaFormateada = formato.format(fecha);
		}
		catch (Exception e) {
			// DO NOTHING.
		}
		
		return fechaFormateada;
	}

	public static Date getFechaActual() {
		return fechaActual;
	}
}