package services.exportadores.exportadorPdf.adaptersExportadorAPdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;
import be.quodlibet.boxable.utils.PDStreamUtils;
import be.quodlibet.boxable.utils.PageContentStreamOptimized;
import domain.informes.DatosInforme;
import domain.informes.rankings.Ranking;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.exportadores.Exportable;
import services.exportadores.exportadorPdf.AdapterExportadorAPdf;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoxablePdf implements AdapterExportadorAPdf {

  float margen = 50;

  boolean drawContent = true;
  float yStart;
  float bottomMargin = 50;
  float yPosition = 820;

  PDFont font = PDType1Font.HELVETICA_BOLD;
  PDPageContentStream cos;
  PDPage page;
  float leftMargin = 50;
  float marginBetweenYElements = 20;
  float titleFontSize = 18;

  public void exportarAPdf(Exportable exportable) throws IOException {
    DatosInforme datosInforme = exportable.getDatos();


    PDDocument documento = new PDDocument();




    List<Ranking> rankings = datosInforme.getRankings();

    rankings.forEach(ranking -> {

      List<List> data = new ArrayList<>();

      data.add(ranking.encabezadosTabla());
      data.addAll(ranking.entradaTabla());

      PDPage pagina = new PDPage(PDRectangle.A4);
      float yStartNewPage = pagina.getMediaBox().getHeight() - (2 * margen);
      float tableWidth = pagina.getMediaBox().getWidth() - (2 * margen);
      yStart = yStartNewPage;

      try {

        PDPageContentStream tituloInforme = new PDPageContentStream(documento, pagina);
        PageContentStreamOptimized tituloInformeOptimized = new PageContentStreamOptimized(tituloInforme);

        PDStreamUtils.write(tituloInformeOptimized,ranking.nombre(), font, titleFontSize, leftMargin, yPosition, Color.BLACK);
        yPosition -= marginBetweenYElements;

        tituloInformeOptimized.close();

        BaseTable baseTable = new BaseTable(yPosition,yStart,bottomMargin,tableWidth,margen,documento,pagina,true,drawContent);
        DataTable dataTable = new DataTable(baseTable,pagina);
        dataTable.addListToTable(data,DataTable.HASHEADER);

        documento.addPage(pagina);
        yStart = baseTable.draw() -500;

      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      });

      documento.save("PruebaPdf.pdf");
      documento.close();

    ;


  }
}
