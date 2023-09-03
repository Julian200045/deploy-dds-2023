package services.exportadoresDeInformes;

import java.io.IOException;

public class ExportadorDeInformes {
  EstrategiaDeExportacion estrategia;

  public ExportadorDeInformes(EstrategiaDeExportacion estrategia) {
    this.estrategia = estrategia;
  }

  public void exportar(InformeExportable exportable) throws IOException {
    estrategia.exportar(exportable);
  }
}
