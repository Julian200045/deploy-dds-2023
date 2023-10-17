package controllers;

import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioUsuarios;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;

    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new RepositorioIncidentes()); break;
<<<<<<< HEAD
      case "AdminUsuarios": controller = new UsuariosController(new RepositorioUsuarios()); break;
=======
      case "Inicio": controller = new InicioController(new RepositorioIncidentes()); break;
>>>>>>> e5ab56c4275182e59aabe7f15e07cddd6c3ca7df
    }

    return controller;
  }
}
