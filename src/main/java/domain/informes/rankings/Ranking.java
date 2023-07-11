package domain.informes.rankings;

import java.util.List;

public interface Ranking {
  String nombre();
  List<String> encabezadosTabla();
  List<List<String>> entradaTabla();
}
