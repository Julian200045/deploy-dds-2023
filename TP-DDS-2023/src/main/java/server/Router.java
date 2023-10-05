package server;

import controllers.*;

public class Router {

  public static void init() {
    Server.app().get("/", ctx -> {
      ctx.result("Hola mundo");
    });

    Server.app().get("/incidentes", ((IncidentesController) FactoryController.controller("Incidentes"))::index);
  }
}
