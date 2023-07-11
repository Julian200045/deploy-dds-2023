package services.exportadores.exportadorPdf;

import services.exportadores.Exportable;

import java.io.IOException;

public interface AdapterExportadorAPdf {
  void exportarAPdf(Exportable exportable) throws IOException;
}
