package services.fusionadorComunidades.moldes;

import java.util.List;

public class ComunidadMolde {
	public long gradoConfianza;
	public List<Integer> miembros;
	public List<PrestacionDeServicioMolde> prestacionesDeServicio;
	public int id;

	public ComunidadMolde(long gradoConfianza, List<Integer> miembros, List<PrestacionDeServicioMolde> prestacionesDeServicio, int id){
		this.id = id;
		this.miembros = miembros;
		this.gradoConfianza = gradoConfianza;
		this.prestacionesDeServicio = prestacionesDeServicio;
	}
}
