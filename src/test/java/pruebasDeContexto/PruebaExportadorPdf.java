package pruebasDeContexto;

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

public class PruebaExportadorPdf {
  public static void main(String[] args) throws IOException {


    AdapterExportadorAPdf adapterExportadorAPdf = new BoxablePdf();
    ExportadorAPdf exportadorAPdf = new ExportadorAPdf(adapterExportadorAPdf);
    Exportador exportador = new Exportador(exportadorAPdf);

    RankingMayorCantidadReportes rankingMayorCantidadReportes = new RankingMayorCantidadReportes();
    List<Ranking> rankings = new ArrayList<Ranking>();
    rankings.add(rankingMayorCantidadReportes);
    rankings.add(rankingMayorCantidadReportes);

    Informe informe = new Informe(null,null,null,rankings);

    exportador.exportar(informe);

  }
}
