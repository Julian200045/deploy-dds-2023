package domain.repositorios.entidades;

import domain.entidades.Entidad;
import java.util.List;

public interface RepoEntidades {

  public void agregarEntidad(int id, String nombre);

  public Entidad devolverPorId(int id);

  public List<Entidad> devolverPorIds(List<Integer> ids);


}
