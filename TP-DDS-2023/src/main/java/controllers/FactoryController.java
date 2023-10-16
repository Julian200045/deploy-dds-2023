package controllers;

import models.repositorios.RepositorioIncidentes;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;

    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new RepositorioIncidentes()); break;
      case "Inicio": controller = new InicioController(new RepositorioIncidentes()); break;
    }

    return controller;
  }
}
