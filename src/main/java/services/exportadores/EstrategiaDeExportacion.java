package services.exportadores;

import java.io.IOException;

public interface EstrategiaDeExportacion {
  void exportar(Exportable exportable) throws IOException;
}
