package server;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.plugin.bundled.CorsPluginConfig;
import io.javalin.rendering.JavalinRenderer;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import models.entities.incidentes.Incidente;

public class Server {
  private static Javalin app = null;

  public static Javalin app() {
    if(app == null)
      throw new RuntimeException("App no inicializada");
    return app;
  }

  public static void init() {
    if(app == null) {
      int port = Integer.parseInt(System.getProperty("port", "8080"));
      app = Javalin.create(config()).start(port);
      initTemplateEngine();
      Router.init();
    }
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/public";
        config.plugins.enableCors(cors -> {
          cors.add(CorsPluginConfig::anyHost);
        });
      });
    };
  }

  private static void initTemplateEngine() {
    JavalinRenderer.register(
        (path, model, context) -> {
          Handlebars handlebars = new Handlebars();

          handlebars.registerHelper("estaVacia", (unaLista, ctx) -> {
            List<Object> lista = (List<Object>) unaLista;
            return lista.isEmpty();
          });

          Template template = null;
          try {
            template = handlebars.compile(
                "templates/" + path.replace(".hbs", ""));
            return template.apply(model);
          } catch (IOException e) {
            e.printStackTrace();
            context.status(HttpStatus.NOT_FOUND);
            return "No se encuentra la página indicada";
          }
        }, ".hbs"
    );
  }
}