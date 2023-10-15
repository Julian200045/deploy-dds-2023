package models.services.calculadorConfianza.moldes;

import lombok.Getter;

@Getter
public class UsuarioOutput {
  Long id;
  Long puntaje_inicial;
  Long puntaje_final;
  String nivel_de_confianza;
  String recomendacion;
}
