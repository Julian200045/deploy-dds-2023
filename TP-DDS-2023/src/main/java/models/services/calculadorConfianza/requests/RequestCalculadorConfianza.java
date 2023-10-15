package models.services.calculadorConfianza.requests;

import java.util.List;

public class RequestCalculadorConfianza {
  List<UsuarioMolde> usuarios;
  List<IncidenteMolde> incidentes;

  RequestCalculadorConfianza(List<UsuarioMolde> usuarios,List<IncidenteMolde> incidentes){
    this.incidentes = incidentes;
    this.usuarios = usuarios;
  }
}
