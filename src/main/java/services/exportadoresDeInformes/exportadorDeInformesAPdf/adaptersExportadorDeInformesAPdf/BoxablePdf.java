package services.exportadoresDeInformes.exportadorDeInformesAPdf.adaptersExportadorDeInformesAPdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;
import be.quodlibet.boxable.utils.PDStreamUtils;
import be.quodlibet.boxable.utils.PageContentStreamOptimized;
import domain.informes.DatosInforme;
import domain.informes.rankings.Rankeador;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.LectorPropiedades;
import services.exportadoresDeInformes.InformeExportable;
import services.exportadoresDeInformes.exportadorDeInformesAPdf.AdapterExportadorDeInformesAPdf;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoxablePdf implements AdapterExportadorDeInformesAPdf {

  float margen;

  boolean drawContent = true;
  float yStart;
  float bottomMargin;
  float yPosition;

  PDFont font = PDType1Font.HELVETICA_BOLD;
  PDPageContentStream cos;
  PDPage page;
  float leftMargin;
  float marginBetweenYElements;
  float titleFontSize;

  public BoxablePdf(String pathPropiedades) throws java.io.IOException{
    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);
    this.margen = lectorPropiedades.getPropiedadInt("BoxablePDF-margen");
    this.bottomMargin = lectorPropiedades.getPropiedadInt("BoxablePDF-bottomMargin");
    this.yPosition = lectorPropiedades.getPropiedadInt("BoxablePDF-yPosition");
    this.leftMargin = lectorPropiedades.getPropiedadInt("BoxablePDF-leftMargin");
    this.marginBetweenYElements = lectorPropiedades.getPropiedadInt("BoxablePDF-marginBetweenYElements");
    this.titleFontSize = lectorPropiedades.getPropiedadInt("BoxablePDF-titleFontSize");
  }

  public void exportarAPdf(InformeExportable exportable) throws IOException {
    DatosInforme datosInforme = exportable.getDatos();


    PDDocument documento = new PDDocument();




    List<Rankeador> rankeadores = datosInforme.getRankeadores();

    rankeadores.forEach(ranking -> {

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
