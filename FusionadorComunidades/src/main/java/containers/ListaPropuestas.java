package containers;

import java.util.List;

import lombok.Data;
import lombok.Getter;

public class ListaPropuestas {
  List<List<Comunidad>> propuestas;

  public List<List<Comunidad>> getPropuestas(){
    return propuestas;
  }
}
