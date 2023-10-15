package models.services.calculadorConfianza.requests;

import java.util.List;

public class RequestCalculadorConfianza {
  public List<UsuarioMolde> usuarios;
  public List<IncidenteMolde> incidentes;

  RequestCalculadorConfianza(List<UsuarioMolde> usuarios,List<IncidenteMolde> incidentes){
    this.incidentes = incidentes;
    this.usuarios = usuarios;
  }
}
