package models.services.fusionadorComunidades.moldes;


public class PrestacionDeServicioMolde {
	public int id;
	public int servicio;
	public int establecimiento;

	public PrestacionDeServicioMolde(int id, int servicio, int establecimiento){
		this.id = id;
		this.servicio = servicio;
		this.establecimiento = establecimiento;
	}
}
