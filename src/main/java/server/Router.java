package server;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import controllers.ComunidadesController;
import controllers.FactoryController;
import controllers.IncidentesController;
import controllers.OrganismosController;
import controllers.UsuariosController;
import models.entities.roles.TipoRol;

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

      get("comunidades", ((ComunidadesController) FactoryController.controller("Comunidades"))::index);
      get("usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::index);
      get("incidentes/{id}", ((IncidentesController) FactoryController.controller("Incidentes"))::show);
      get("usuarios/crear", ((UsuariosController) FactoryController.controller("Usuarios"))::create);
      get("organismos/carga", ((OrganismosController) FactoryController.controller("Organismos"))::create, TipoRol.ADMIN);
      get("usuarios/logout",((UsuariosController) FactoryController.controller("Usuarios"))::logout);
      post("usuarios/login", ((UsuariosController) FactoryController.controller("Usuarios"))::login);
      post("incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::save);
      post("usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::save);
      post("organismos", ((OrganismosController) FactoryController.controller("Organismos"))::save, TipoRol.ADMIN); //TODO: checkear permisos usuario
      patch("incidentes/{id}/baja", ((IncidentesController) FactoryController.controller("Incidentes"))::update);
      patch("comunidades/{id}", ((ComunidadesController) FactoryController.controller("Comunidades"))::update);

      get("administracion/usuarios", ((UsuariosController) FactoryController.controller("Usuarios"))::index, TipoRol.ADMIN);
      get("administracion/usuario", ((UsuariosController) FactoryController.controller("Usuarios"))::show, TipoRol.ADMIN);
      post("administracion/usuario", ((UsuariosController) FactoryController.controller("Usuarios"))::saveAdmin, TipoRol.ADMIN);
      put("administracion/usuario", ((UsuariosController) FactoryController.controller("Usuarios"))::update, TipoRol.ADMIN);
      delete("administracion/usuario", ((UsuariosController) FactoryController.controller("Usuarios"))::delete, TipoRol.ADMIN);
    });
  }
}