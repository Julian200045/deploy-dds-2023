import analizadorComunidades.AnalizadorComunidades;
import analizadorComunidades.criteriosCoincidencias.CriterioCoincidencia;
import analizadorComunidades.criteriosCoincidencias.CriterioConfianza;
import analizadorComunidades.criteriosCoincidencias.CriterioEstablecimientos;
import analizadorComunidades.criteriosCoincidencias.CriterioServicios;
import analizadorComunidades.criteriosCoincidencias.CriterioUsuarios;
import io.javalin.Javalin;
import controllers.SugerenciasFusionController;
import servicios.LectorPropiedades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebApp {
  public static void main(String[] args) throws IOException {

    LectorPropiedades lectorPropiedades = new LectorPropiedades("src/main/resources/template/project.properties");

    List<CriterioCoincidencia> criterioCoincidencias = Arrays.asList(
        new CriterioConfianza(),
        new CriterioEstablecimientos(lectorPropiedades.getPropiedadDouble("porcentaje-establecimientos")),
        new CriterioServicios(lectorPropiedades.getPropiedadDouble("porcentaje-servicios")),
        new CriterioUsuarios(lectorPropiedades.getPropiedadDouble("porcentaje-usuarios"))
    );
    AnalizadorComunidades analizadorComunidades = new AnalizadorComunidades(criterioCoincidencias);

    Integer port = Integer.parseInt(System.getProperty("port", "8080"));
    Javalin app = Javalin.create().start(port);

    app.get("/sugerencias_fusiones", new SugerenciasFusionController(analizadorComunidades));
  }

}
