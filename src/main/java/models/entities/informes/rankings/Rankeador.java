package models.entities.informes.rankings;

import java.util.List;

public interface Rankeador {
  String nombre();
  List<String> encabezadosTabla();
  List<List<String>> entradaTabla();
}
