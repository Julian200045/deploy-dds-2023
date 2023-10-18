package server;

import controllers.*;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  public static void init() {
    Server.app().get("/", ctx -> {
      ctx.redirect("inicio");
    });

    Server.app().routes(() -> {
      get("inicio", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
      get("alta-incidente",((IncidentesController) FactoryController.controller("Incidentes"))::create);

      post("incidentes",((IncidentesController) FactoryController.controller("Incidentes"))::save);
      patch("inicio/incidentes/{id}/dar-de-baja",((IncidentesController) FactoryController.controller("Incidentes"))::update);

    });
  }
}