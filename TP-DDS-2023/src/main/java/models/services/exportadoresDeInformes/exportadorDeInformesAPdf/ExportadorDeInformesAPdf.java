package models.services.exportadoresDeInformes.exportadorDeInformesAPdf;

import models.services.exportadoresDeInformes.EstrategiaDeExportacion;
import models.services.exportadoresDeInformes.InformeExportable;

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
