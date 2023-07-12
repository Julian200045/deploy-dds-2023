package services.exportadoresDeInformes.exportadorDeInformesAPdf;

import services.exportadoresDeInformes.InformeExportable;

import java.io.IOException;

public interface AdapterExportadorDeInformesAPdf {
  void exportarAPdf(InformeExportable exportable) throws IOException;
}
