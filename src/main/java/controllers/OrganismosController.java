package controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import models.services.csv.LectorCSV;
import models.services.importadorDatosCSV.ImportadorDatosCSV;
import models.services.importadorDatosCSV.ImportadorEntidadesPrestadoras;
import models.services.importadorDatosCSV.ImportadorOrganismosDeControl;
import server.utils.ICrudViewsHandler;

public class OrganismosController implements ICrudViewsHandler {
  private final LectorCSV lectorCSV;
  private final ImportadorDatosCSV importadorDeOrganismosDeControl;
private final ImportadorDatosCSV importadorDeEntidadesPrestadoras;
  public OrganismosController(LectorCSV lectorCSV, ImportadorOrganismosDeControl importadorOrganismosDeControl, ImportadorEntidadesPrestadoras importadorEntidadesPrestadoras) {
    this.lectorCSV = lectorCSV;
    this.importadorDeOrganismosDeControl = importadorOrganismosDeControl;
    this.importadorDeEntidadesPrestadoras = importadorEntidadesPrestadoras;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    context.render("carga-organismos.hbs");
  }

  @Override
  public void save(Context context) {
    UploadedFile archivo = context.uploadedFile("csv-file");
    if (archivo != null) {
      String tipoOrganismo = context.formParam("tipo-organismo");
      InputStream inputStream = archivo.content();
      List<String[]> datosLeidos = lectorCSV.leerCSV(new InputStreamReader(inputStream));
      if ("control".equals(tipoOrganismo)) {
          this.importadorDeOrganismosDeControl.cargarDatos(datosLeidos);
      } else if ("prestadora".equals(tipoOrganismo)) {
          this.importadorDeEntidadesPrestadoras.cargarDatos(datosLeidos);
      }
    }
    context.redirect("/organismos/carga");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
