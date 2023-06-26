package repositorios.entidades;

import domain.entidades.Entidad;
import domain.entidades.TipoEntidad;

import java.util.List;

public interface RepoEntidades {

  public void agregarEntidad(int id, String nombre, TipoEntidad tipo);

  public Entidad devolverPorId(int id);

  public List<Entidad> devolverPorIds(List<Integer> ids);


}
