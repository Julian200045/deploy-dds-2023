package servicios.analizadorcomunidades.criterioscoincidencias;

import containers.Comunidad;

public interface CriterioCoincidencia {
  Boolean coinciden(Comunidad comunidad1, Comunidad comunidad2);
}
