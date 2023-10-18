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

      get("administracion/usuarios", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::index);
      get("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::show);
      get("administracion/usuario/busqueda", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::show);
      post("administracion/usuario/{id}", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::create);
      put("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::update);
      delete("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::delete);
    });
  }
}