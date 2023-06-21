package services.localizacionesService.georef.moldes;

import java.util.List;
import java.util.Optional;

public class ListaProvinciasMolde {
  public int cantidad;
  public int inicio;
  public List<ProvinciaMolde> provincias;
  public int total;

  public Optional<ProvinciaMolde> provinciaDeId(int id){
    return this.provincias.stream()
        .filter(p -> p.id == id)
        .findFirst();
  }

}
