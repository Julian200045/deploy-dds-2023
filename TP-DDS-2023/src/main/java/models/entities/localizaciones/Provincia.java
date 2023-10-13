package models.entities.localizaciones;

import models.entities.ubicaciones.Ubicacion;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provincia")
public class Provincia {
  @Id
  @GeneratedValue
  public long id;
  @Column(name = "nombre")
  public String nombre;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
  public Ubicacion ubicacion;
  @Getter
  @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
  private List<Municipio> municipios;


  public Provincia(Integer id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;

  }

  public List<Localidad> getLocalidades(){
    return municipios.stream().flatMap(municipio -> municipio.getLocalidades().stream()).toList();
  }

  public void setMunicipios(List<Municipio> municipiosList){
    if(this.municipios == null){
      municipios = municipiosList;
    }
  }

  public Provincia(){

  }
}
