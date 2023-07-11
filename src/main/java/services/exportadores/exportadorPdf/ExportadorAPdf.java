package services.exportadores.exportadorPdf;

import services.exportadores.EstrategiaDeExportacion;
import services.exportadores.Exportable;

import java.io.IOException;

public class ExportadorAPdf implements EstrategiaDeExportacion {

  AdapterExportadorAPdf exportadorAPdf;

  public ExportadorAPdf(AdapterExportadorAPdf exportadorAPdf) {
    this.exportadorAPdf = exportadorAPdf;
  }

  public void exportar(Exportable exportable) throws IOException {
    exportadorAPdf.exportarAPdf(exportable);
  }
}
