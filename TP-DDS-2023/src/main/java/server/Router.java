package server;

import controllers.*;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  public static void init() {
    Server.app().get("/", ctx -> {
      ctx.redirect("inicio");
    });

    //Server.app().get("/incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);

    Server.app().routes(() -> {
      get("inicio", ((InicioController) FactoryController.controller("Inicio"))::index);

      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
      get("incidentes/crear", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
      get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
      get("incidentes/{id}/editar", ((IncidentesController) FactoryController.controller("Incidentes"))::edit);
      post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
      post("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
      delete("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::delete); //TODO implementar mediante JS

      get("administracion/usuarios", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::index);
    });
  }
}