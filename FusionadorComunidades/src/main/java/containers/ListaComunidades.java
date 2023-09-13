package containers;

import java.util.List;

import lombok.Data;

@Data
public class ListaComunidades {
  List<Comunidad> comunidadesAFusionar;
  List<List<Comunidad>> propuestasAExcluir;

  public List<Comunidad> getComunidadesAFusionar() {
    return comunidadesAFusionar;
  }

  public List<List<Comunidad>> getPropuestasAExcluir() {
    return propuestasAExcluir;
  }
}

