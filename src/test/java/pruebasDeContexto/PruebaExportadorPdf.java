package pruebasDeContexto;

import domain.incidentes.Incidente;
import domain.informes.Informe;
import domain.informes.rankings.Ranking;
import domain.informes.rankings.RankingMayorCantidadReportes;
import org.checkerframework.checker.units.qual.A;
import services.exportadores.Exportador;
import services.exportadores.exportadorPdf.AdapterExportadorAPdf;
import services.exportadores.exportadorPdf.ExportadorAPdf;
import services.exportadores.exportadorPdf.adaptersExportadorAPdf.BoxablePdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class PruebaExportadorPdf {
  public static void main(String[] args) throws IOException {

    AdapterExportadorAPdf adapterExportadorAPdf = new BoxablePdf("src/main/resources/template/project.properties");
    ExportadorAPdf exportadorAPdf = new ExportadorAPdf(adapterExportadorAPdf);
    Exportador exportador = new Exportador(exportadorAPdf);

    RankingMayorCantidadReportes rankingMayorCantidadReportes = mock(RankingMayorCantidadReportes.class);
    List<List<String>> filas = new ArrayList<>();
    for(int i = 0; i < 100; i++){
      List<String> fila = new ArrayList<>();
      fila.add(Integer.toString(i));
      fila.add("empresa" + i);
      fila.add(Integer.toString(i * 10));

      filas.add(fila);
    }
    when(rankingMayorCantidadReportes.entradaTabla()).thenReturn(filas);
    when(rankingMayorCantidadReportes.nombre()).thenReturn("ranking por cantidad de reportes");
    List<String> encabezados = new ArrayList<>();
    encabezados.add("puesto");
    encabezados.add("entidad");
    encabezados.add("cantidad");
    when(rankingMayorCantidadReportes.encabezadosTabla()).thenReturn(encabezados);


    List<Ranking> rankings = new ArrayList<Ranking>();
    rankings.add(rankingMayorCantidadReportes);
    rankings.add(rankingMayorCantidadReportes);



    Informe informe = new Informe(null,null,null,rankings);

    exportador.exportar(informe);

  }
}
