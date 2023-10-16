package models.services.calculadorConfianza.moldes;

import lombok.Getter;

import java.util.List;

@Getter
public class InformeConfianza {
  List<UsuarioOutput> usuarios_output;
  Double nivel_de_confianza;
}
