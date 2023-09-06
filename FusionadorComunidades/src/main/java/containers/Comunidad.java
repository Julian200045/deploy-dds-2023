package containers;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Comunidad {
  Long id;
  Double gradoConfianza;
  List<Long> miembros;
  List<PrestacionDeServicio> prestacionesDeServicio;
}
