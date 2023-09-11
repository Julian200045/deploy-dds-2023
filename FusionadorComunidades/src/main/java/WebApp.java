import servicios.analizadorcomunidades.AnalizadorComunidades;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioCoincidencia;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioConfianza;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioEstablecimientos;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioServicios;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioUsuarios;
import controllers.FusionComunidadesController;
import controllers.SugerenciasFusionController;
import servicios.fusionadorcomunidades.FusionadorComunidades;
import io.javalin.Javalin;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import servicios.LectorPropiedades;

public class WebApp {
  /**
   *  Este metodo es el main de nuestro servicio WebApp, utilizando la infraestructura de Javalin.
   * @param args Argumentos del main
   * @throws IOException Tipo de excepción que arrojará en caso de fallar.
   */
  @SuppressWarnings("checkstyle:LineLength")
  public static void main(String[] args) throws IOException {
    String pathPropiedades = "src/main/resources/template/project.properties";
    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);

    List<CriterioCoincidencia> criterioCoincidencias = Arrays.asList(
        new CriterioConfianza(),
        new CriterioEstablecimientos(lectorPropiedades.getPropiedadDouble("porcentaje-establecimientos")),
        new CriterioServicios(lectorPropiedades.getPropiedadDouble("porcentaje-servicios")),
        new CriterioUsuarios(lectorPropiedades.getPropiedadDouble("porcentaje-usuarios"))
    );
    AnalizadorComunidades analizadorComunidades = new AnalizadorComunidades(criterioCoincidencias);
    FusionadorComunidades fusionadorComunidades = new FusionadorComunidades();

    int port = Integer.parseInt(System.getProperty("port", "8080"));
    Javalin app = Javalin.create().start(port);

    app.get("/sugerencias_fusiones", new SugerenciasFusionController(analizadorComunidades));

    app.get("/fusion_comunidades", new FusionComunidadesController(fusionadorComunidades));
  }

}
