package negocio;

import java.util.Vector;

import persistencia.ReclamoMapper;
import vista.ReporteView;

public class Reporte {
	private String nombre;
	private int cantidad;
	private int mes;
	private int anio;
	
	public Reporte(String nombre, int cantidad, int mes, int anio) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.mes = mes;
		this.anio = anio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public ReporteView getView() {
		return new ReporteView(this.nombre, this.cantidad, this.mes, this.anio);
	}
}
