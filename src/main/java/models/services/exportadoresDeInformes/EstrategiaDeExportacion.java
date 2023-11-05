package models.services.exportadoresDeInformes;

import java.io.IOException;

public interface EstrategiaDeExportacion {
  void exportar(InformeExportable exportable) throws IOException;
}
