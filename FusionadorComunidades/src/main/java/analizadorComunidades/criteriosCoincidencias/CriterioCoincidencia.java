package analizadorComunidades.criteriosCoincidencias;

import containers.Comunidad;

public interface CriterioCoincidencia {
  public Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2);
}
