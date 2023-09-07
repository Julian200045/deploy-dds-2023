package containers;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Comunidad {
  Long id;
  @Setter
  Double gradoConfianza;
  @Setter
  List<Long> miembros;
  @Setter
  List<PrestacionDeServicio> prestacionesDeServicio;
}
