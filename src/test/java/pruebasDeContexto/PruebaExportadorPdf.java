package pruebasDeContexto;

import domain.informes.Informe;
import domain.informes.rankings.Rankeador;
import domain.informes.rankings.RankeadorMayorCantidadReportes;
import services.exportadoresDeInformes.ExportadorDeInformes;
import services.exportadoresDeInformes.exportadorDeInformesAPdf.AdapterExportadorDeInformesAPdf;
import services.exportadoresDeInformes.exportadorDeInformesAPdf.ExportadorDeInformesAPdf;
import services.exportadoresDeInformes.exportadorDeInformesAPdf.adaptersExportadorDeInformesAPdf.BoxablePdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class PruebaExportadorPdf {
  public static void main(String[] args) throws IOException {

    AdapterExportadorDeInformesAPdf adapterExportadorAPdf = new BoxablePdf("src/main/resources/template/project.properties");
    ExportadorDeInformesAPdf exportadorAPdf = new ExportadorDeInformesAPdf(adapterExportadorAPdf);
    ExportadorDeInformes exportador = new ExportadorDeInformes(exportadorAPdf);

    RankeadorMayorCantidadReportes rankingMayorCantidadReportes = mock(RankeadorMayorCantidadReportes.class);
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


    List<Rankeador> rankings = new ArrayList<Rankeador>();
    rankings.add(rankingMayorCantidadReportes);
    rankings.add(rankingMayorCantidadReportes);



    Informe informe = new Informe(null,null,null,rankings);

    exportador.exportar(informe);

  }
}
