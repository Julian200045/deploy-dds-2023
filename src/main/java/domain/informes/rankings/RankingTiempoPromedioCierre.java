package domain.informes.rankings;

import domain.comunidades.Comunidad;
import domain.incidentes.EstadoIncidente;
import domain.incidentes.Incidente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class RankingTiempoPromedioCierre implements Ranking{
  @Getter
  Comunidad comunidad;
  List<List<String>> listaRanking;

  public RankingTiempoPromedioCierre(Comunidad comunidad, List<Incidente> incidentesSemanales){
    this.comunidad = comunidad;
    listaRanking = new ArrayList<>();
    crearLista(incidentesSemanales);
    sacarPromedios(incidentesSemanales);
    ordenarLista();
    asignarPuestos();
  }

  public void crearLista(List<Incidente> incidentesSemanales){
    for(int i = 0; i< incidentesSemanales.size(); i++){
      Incidente incidente = incidentesSemanales.get(i);
      if(incidente.getComunidad() == comunidad) {
        String entidadNombre = incidente.entidadNombre();
        if (listaRanking.stream().anyMatch(lista -> lista.contains(entidadNombre))) {
          List<String> fila = listaRanking.stream().filter(lista -> lista.contains(entidadNombre)).collect(Collectors.toList()).get(0);
          fila.set(2, Integer.toString(Integer.parseInt(fila.get(2)) + Math.toIntExact(incidente.tiempoDeCierre())));
        } else {
          List<String> fila = new ArrayList<>();
          fila.add("0");
          fila.add(entidadNombre);
          fila.add(Integer.toString(Math.toIntExact(incidente.tiempoDeCierre())));
          listaRanking.add(fila);
        }
      }
    }
  }

  public void sacarPromedios(List<Incidente> incidentesSemanales){
    for(int i= 0; i<listaRanking.size(); i++){
      List<String> fila = listaRanking.get(i);
      int cantidad = Math.toIntExact(incidentesSemanales.stream().filter(incidente -> incidente.entidadNombre().equals(fila.get(1))).count());

      fila.set(2, Integer.toString(Integer.parseInt(fila.get(2)) / cantidad));
    }
  }
  public void ordenarLista(){
    listaRanking.sort((fila1, fila2) -> fila1.get(2).compareTo(fila2.get(2))*-1);
  }

  public void asignarPuestos(){
    for(int i = 0 ; i<listaRanking.size(); i++){
      listaRanking.get(i).set(0, Integer.toString(i+1));
    }
  }
  public String nombre() {
    return "tiempo promedio de cierre - " + comunidad.getNombre();
  }

  public List<String> encabezadosTabla() {
    List<String> encabezado = new ArrayList<>();
    encabezado.add("puesto");
    encabezado.add("entidad");
    encabezado.add("promedio de tiempo");
    return encabezado;
  }

  public List<List<String>> entradaTabla() {
    return listaRanking;
  }
}
