package server;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import controllers.FactoryController;
import controllers.IncidentesController;
import controllers.UsuariosController;

public class Router {

  public static void init() {
    Server.app().get("/", ctx -> {
      ctx.redirect("/login");
    });

    Server.app().get("/login", ctx -> {
      ctx.render("login.hbs");
    });

    Server.app().routes(() -> {
      get("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
      get("incidentes/alta", ((IncidentesController) FactoryController.controller("Incidentes"))::create);
      get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
      post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
      patch("incidentes/{id}/baja", ((IncidentesController) FactoryController.controller("Incidentes"))::update);

      post("usuarios/login", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::login);

      get("administracion/usuarios", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::index);
      get("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::show);
      get("administracion/usuario/busqueda", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::show);
      post("administracion/usuario/{id}", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::create);
      put("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::update);
      delete("administracion/usuario", ((UsuariosController) FactoryController.controller("AdminUsuarios"))::delete);
    });
  }
}