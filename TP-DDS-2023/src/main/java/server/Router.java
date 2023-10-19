package server;

import controllers.*;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  public static void init() {
    Server.app().get("/", ctx -> {
      ctx.render("/bienvenida.hbs");
    });

    Server.app().routes(() -> {
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
      get("incidentes/crear",((IncidentesController) FactoryController.controller("Incidentes"))::create);
      get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
      post("incidentes",((IncidentesController) FactoryController.controller("Incidentes"))::save);
      patch("incidentes/{id}",((IncidentesController) FactoryController.controller("Incidentes"))::update);

      get("administracion/usuarios", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::index);
      get("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::show);
      get("administracion/usuario/busqueda", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::show);
      post("administracion/usuario/{id}", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::create);
      put("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::update);
      delete("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::delete);
    });
  }
}