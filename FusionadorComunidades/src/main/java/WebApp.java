import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.SecurityConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
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
    Javalin app = Javalin.create(config -> {
      OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
      openApiConfiguration.getInfo().setTitle("Javalin OpenAPI example");
      config.plugins.register(new OpenApiPlugin(openApiConfiguration));
      config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
    }).start(port);

    app.get("/sugerencias_fusiones", new SugerenciasFusionController(analizadorComunidades,lectorPropiedades.getPropiedad("mensaje-error-mapeo")));

    app.get("/fusion_comunidades", new FusionComunidadesController(fusionadorComunidades));
  }

}
