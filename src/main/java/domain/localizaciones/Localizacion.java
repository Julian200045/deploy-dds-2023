package domain.localizaciones;

import domain.ubicaciones.Ubicacion;

public interface Localizacion {
  public Boolean seEncuentraEn(Ubicacion ubicacion);
}
