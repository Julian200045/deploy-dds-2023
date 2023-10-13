package models.services.exportadoresDeInformes.exportadorDeInformesAPdf;

import models.services.exportadoresDeInformes.InformeExportable;

import java.io.IOException;

public interface AdapterExportadorDeInformesAPdf {
  void exportarAPdf(InformeExportable exportable) throws IOException;
}
