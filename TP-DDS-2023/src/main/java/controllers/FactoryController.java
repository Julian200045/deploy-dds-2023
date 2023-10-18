package controllers;

import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioNotificaciones;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioPrestacionesDeServicio;
import models.repositorios.RepositorioUsuarios;
import models.services.IncidentesService;
import models.services.notificador.GeneradorNotificaciones;
import models.repositorios.RepositorioUsuarios;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;

    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new RepositorioIncidentes(),
                                                       new RepositorioPersonas(),
                                                       new RepositorioUsuarios(),
                                                       new RepositorioPrestacionesDeServicio(),
                                                       new IncidentesService(new RepositorioIncidentes(),new GeneradorNotificaciones(new RepositorioNotificaciones()))
                                                       ); break;
      case "AdminUsuarios": controller = new UsuariosController(new RepositorioUsuarios()); break;
    }
    return controller;
  }
}
