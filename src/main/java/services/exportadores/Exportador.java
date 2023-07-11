package services.exportadores;

import java.io.IOException;

public class Exportador {
  EstrategiaDeExportacion estrategia;

  public Exportador(EstrategiaDeExportacion estrategia) {
    this.estrategia = estrategia;
  }

  public void exportar(Exportable exportable) throws IOException {
    estrategia.exportar(exportable);
  }
}
