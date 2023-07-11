package domain.informes.rankings;

import domain.informes.Informe;

import java.util.ArrayList;
import java.util.List;

public class RankingMayorCantidadReportes implements Ranking{


  public String nombre() {
    return "Cantidad de reportes";
  }

  public List<String> encabezadosTabla() {
    List<String> encabezados = new ArrayList<>();
    encabezados.add("Columna 1");
    encabezados.add("Columna 2");
    encabezados.add("Columna 3");

    return encabezados;
  }

  public List<List<String>> entradaTabla() {
    List<List<String>> filas = new ArrayList<>();

    for(int i = 0; i < 100; i++){
      List<String> fila = new ArrayList<>();
      fila.add(Integer.toString(i));
      fila.add("empresa" + i);
      fila.add(Integer.toString(i * 10));

      filas.add(fila);
    }
    return filas;
  }
}
