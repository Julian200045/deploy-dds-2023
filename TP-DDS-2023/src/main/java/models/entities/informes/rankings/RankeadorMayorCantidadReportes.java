package models.entities.informes.rankings;

import models.entities.incidentes.Incidente;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class RankeadorMayorCantidadReportes implements Rankeador {
  @Getter
  private List<List<String>> listaRanking;

  public RankeadorMayorCantidadReportes(List<Incidente> incidentesSemanales){
    listaRanking = new ArrayList<>();
    List<Incidente> incidentesFiltrados = filtraIncidentes(incidentesSemanales);
    crearLista(incidentesFiltrados);
    ordenarLista();
    asignarPuestos();
  }

  public List<Incidente> filtraIncidentes(List<Incidente> incidentes){
    for(int i = 0; i< incidentes.size(); i++){
      Incidente  incidente = incidentes.get(i);
      List<Incidente> incidentesABorrar = incidentes.stream().filter(incidente1 -> cumpleCriterio(incidente, incidente1)).collect(Collectors.toList());
      for (int k = 0; k<incidentesABorrar.size(); k++){
        incidentes.remove(incidentesABorrar.get(k));
      }
      incidentes.add(incidente);
    }
    return incidentes;
  }

  public boolean cumpleCriterio(Incidente incidente1, Incidente incidente2){
    long horas = 24;
    return incidente1.getPrestacionDeServicio() == incidente2.getPrestacionDeServicio() && horas > Math.abs(ChronoUnit.HOURS.between(incidente2.getFechaYHoraDeApertura(),incidente1.getFechaYHoraDeApertura()));
  }

  public void ordenarLista(){
    listaRanking.sort((fila1, fila2) -> fila1.get(2).compareTo(fila2.get(2))*-1);
  }

  public void asignarPuestos(){
    for(int i = 0 ; i<listaRanking.size(); i++){
      listaRanking.get(i).set(0, Integer.toString(i+1));
    }
  }
  public void crearLista(List<Incidente> incidentesSemanales){
    for(int i = 0; i< incidentesSemanales.size(); i++){
      String entidadNombre = incidentesSemanales.get(i).entidadNombre();
      if (listaRanking.stream().anyMatch(lista -> lista.contains(entidadNombre))) {
        List<String> fila = listaRanking.stream().filter(lista -> lista.contains(entidadNombre)).collect(Collectors.toList()).get(0);
        fila.set(2, Integer.toString(Integer.parseInt(fila.get(2)) + 1));
      } else {
        List<String> fila = new ArrayList<>();
        fila.add("0");
        fila.add(entidadNombre);
        fila.add("1");
        listaRanking.add(fila);
      }
    }
  }

  public String nombre() {
    return "Ranking de mayor cantidad de reportes de incidentes";
  }

  public List<String> encabezadosTabla() {
    List<String> encabezados = new ArrayList<>();
    encabezados.add("Puesto");
    encabezados.add("entidad");
    encabezados.add("valor");

    return encabezados;
  }

  public List<List<String>> entradaTabla() {
    return listaRanking;
  }
}
