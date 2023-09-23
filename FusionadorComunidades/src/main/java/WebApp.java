import controllers.FusionComunidadesController;
import controllers.SugerenciasFusionController;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import servicios.LectorPropiedades;
import servicios.analizadorcomunidades.AnalizadorComunidades;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioCoincidencia;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioConfianza;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioEstablecimientos;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioServicios;
import servicios.analizadorcomunidades.criterioscoincidencias.CriterioUsuarios;
import servicios.fusionadorcomunidades.FusionadorComunidades;

public class WebApp {
  /**
   * Este metodo es el main de nuestro servicio WebApp, utilizando la infraestructura de Javalin.
   *
   * @param args Argumentos del main
   * @throws IOException Tipo de excepción que arrojará en caso de fallar.
   */
  @SuppressWarnings("checkstyle:LineLength")
  public static void main(String[] args) throws IOException {
    String pathPropiedades = "src/main/resources/template/project.properties";
    LectorPropiedades lectorPropiedades = new LectorPropiedades(pathPropiedades);

    Map<String, String> mensajesDeError = Map.of(
        "mensaje-error-mappeo", lectorPropiedades.getPropiedad("mensaje-error-mappeo"),
        "mensaje-error-body", lectorPropiedades.getPropiedad("mensaje-error-body")
    );

    List<CriterioCoincidencia> criterioCoincidencias = Arrays.asList(
        new CriterioConfianza(),
        new CriterioEstablecimientos(lectorPropiedades.getPropiedadDouble("porcentaje-establecimientos")),
        new CriterioServicios(lectorPropiedades.getPropiedadDouble("porcentaje-servicios")),
        new CriterioUsuarios(lectorPropiedades.getPropiedadDouble("porcentaje-usuarios"))
    );
    AnalizadorComunidades analizadorComunidades = new AnalizadorComunidades(criterioCoincidencias);
    FusionadorComunidades fusionadorComunidades = new FusionadorComunidades();

    int port = Integer.parseInt(System.getProperty("port", "8080"));

    Javalin app = Javalin.create(config -> configOpenAPI(config)).start(port);

    app.get("/sugerencias_fusiones", new SugerenciasFusionController(analizadorComunidades, mensajesDeError));

    app.get("/fusion_comunidades", new FusionComunidadesController(fusionadorComunidades, mensajesDeError));
  }


  private static void configOpenAPI(JavalinConfig config){
      config.plugins.register(new OpenApiPlugin(
              new OpenApiPluginConfiguration()
                  .withDocumentationPath("/openapi")
                  .withDefinitionConfiguration((version, definition) -> definition
                      .withOpenApiInfo((openApiInfo) -> {
                        openApiInfo.setTitle("Servicio Fusionador de Comunidades");
                        openApiInfo.setVersion("1.0.0");
                      })
                      .withServer((openApiServer) -> {
                        openApiServer.setUrl(("http://localhost:8080/" + version + "/"));
                      }))
          )
      );

      SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
      swaggerConfiguration.setDocumentationPath("/openapi");
      config.plugins.register(new SwaggerPlugin(swaggerConfiguration));
  }
}
