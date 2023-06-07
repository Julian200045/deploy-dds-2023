package domain.localizaciones;

import domain.ubicaciones.Ubicacion;

public interface Localizacion {
  Boolean seEncuentraEn(Ubicacion ubicacion);
}
