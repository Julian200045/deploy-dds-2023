package controllers;

import models.repositorios.incidentes.RepositorioIncidentes;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;

    switch (nombre) {
      case "Incidentes": controller = new IncidentesController(new RepositorioIncidentes()); break;
    }

    return controller;
  }
}
