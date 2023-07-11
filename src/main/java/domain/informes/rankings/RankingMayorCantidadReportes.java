package domain.informes.rankings;

import domain.entidades.Entidad;
import domain.incidentes.Incidente;
import domain.informes.Informe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class RankingMayorCantidadReportes implements Ranking{
  @Getter
  List<List<String>> listaRanking;

  public RankingMayorCantidadReportes(List<Incidente> incidentesSemanales){
    listaRanking = new ArrayList<>();
    crearLista(incidentesSemanales);
    ordenarLista();
    asignarPuestos();
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
      if(listaRanking.stream().anyMatch(lista -> lista.contains(entidadNombre))){
        List<String> fila = listaRanking.stream().filter(lista -> lista.contains(entidadNombre)).collect(Collectors.toList()).get(0);
        fila.set(2, Integer.toString(Integer.parseInt(fila.get(2))+1));
      }
      else{
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
    encabezados.add("puesto");
    encabezados.add("entidad");
    encabezados.add("valor");

    return encabezados;
  }

  public List<List<String>> entradaTabla() {
    return listaRanking;
  }
}
