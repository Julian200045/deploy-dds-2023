package services.georef.entities;

import java.util.List;
import java.util.Optional;

public class ListaProvinciasEntity {
  public int cantidad;
  public int inicio;
  public List<ProvinciaEntity> provincias;
  public int total;

  public Optional<ProvinciaEntity> provinciaDeId(int id){
    return this.provincias.stream()
        .filter(p -> p.id == id)
        .findFirst();
  }

}
