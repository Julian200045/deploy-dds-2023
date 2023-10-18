package containers;

import java.util.List;

import lombok.Data;

@Data
public class RequestSugerenciasFusion {
  List<Comunidad> comunidadesAFusionar;
  List<List<Long>> propuestasAExcluir;

  public List<Comunidad> getComunidadesAFusionar() {
    return comunidadesAFusionar;
  }

  public List<List<Long>> getPropuestasAExcluir() {
    return propuestasAExcluir;
  }
}

