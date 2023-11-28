package models.services.calculadorConfianza.requests;

import lombok.Getter;

@Getter
public class UsuarioMolde {
  Long id;
  Double puntaje_inicial;

  public UsuarioMolde(Long id, Double puntaje_inicial) {
    this.id = id;
    this.puntaje_inicial = puntaje_inicial;
  }
}
