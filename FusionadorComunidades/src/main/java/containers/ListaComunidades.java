package containers;

import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class ListaComunidades {
  @Getter
  List<Comunidad> comunidadesAFusionar;
  @Getter
  List<List<Comunidad>> propuestasAnteriores;
}
