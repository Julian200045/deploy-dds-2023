package models.services.fusionadorComunidades.request;

import java.util.List;

import models.services.fusionadorComunidades.moldes.Propuesta;

public class RequestPropuestasComunidad {
	public List<Propuesta> propuestas;

	public RequestPropuestasComunidad(List<Propuesta> propuestas){
		this.propuestas = propuestas;
	}
}
