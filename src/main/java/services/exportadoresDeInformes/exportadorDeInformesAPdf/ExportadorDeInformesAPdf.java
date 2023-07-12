package services.exportadoresDeInformes.exportadorDeInformesAPdf;

import services.exportadoresDeInformes.EstrategiaDeExportacion;
import services.exportadoresDeInformes.InformeExportable;

import java.io.IOException;

public class ExportadorDeInformesAPdf implements EstrategiaDeExportacion {

  AdapterExportadorDeInformesAPdf exportadorAPdf;

  public ExportadorDeInformesAPdf(AdapterExportadorDeInformesAPdf exportadorAPdf) {
    this.exportadorAPdf = exportadorAPdf;
  }

  public void exportar(InformeExportable exportable) throws IOException {
    exportadorAPdf.exportarAPdf(exportable);
  }
}
