package services.calculadorConfianza.requests;

public class UsuarioMolde {
  Long id;
  Double puntaje_inicial;

  public UsuarioMolde(Long id, Double puntaje_inicial) {
    this.id = id;
    this.puntaje_inicial = puntaje_inicial;
  }
}
