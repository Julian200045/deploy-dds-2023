package servicios.fusionadorcomunidades;

import containers.Comunidad;
import containers.PrestacionDeServicio;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FusionadorComunidades {

  /**
   * Este método fusiona una lista de comunidades.
   *
   * @param comunidades Las comunidades que serán fusionadas.
   * @return La comunidad resultado de fusionar todas las comunidades del parametro.
   */
  public Comunidad fusionarComunidades(List<Comunidad> comunidades) {
    Comunidad comunidadFusionada = new Comunidad();
    comunidadFusionada.setGradoConfianza(comunidades.get(0).getGradoConfianza());
    List<List<Long>> miembrosDeLasComunidades = comunidades.stream().map(Comunidad::getMiembros).toList();
    Set<Long> miembrosFusionadosSinDuplicados = new HashSet<>(miembrosDeLasComunidades.stream().flatMap(List::stream).toList());
    comunidadFusionada.setMiembros(miembrosFusionadosSinDuplicados.stream().toList());

    List<List<PrestacionDeServicio>> prestacionesDeLasComunidades = comunidades.stream().map(Comunidad::getPrestacionesDeServicio).toList();
    Set<PrestacionDeServicio> prestacionesFusionadasSinDuplicados = new HashSet<>(prestacionesDeLasComunidades.stream().flatMap(List::stream).toList());
    comunidadFusionada.setPrestacionesDeServicio(prestacionesFusionadasSinDuplicados.stream().toList());
    return comunidadFusionada;
  }
}
