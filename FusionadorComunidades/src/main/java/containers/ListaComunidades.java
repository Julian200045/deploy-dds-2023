package containers;

import java.util.List;
import lombok.Getter;

public class ListaComunidades {
  @Getter
  List<Comunidad> comunidadesAFusionar;
  @Getter
  List<List<Comunidad>> propuestasAnteriores;
}
