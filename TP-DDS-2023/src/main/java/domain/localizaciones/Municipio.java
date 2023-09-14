package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "municipio")
public class Municipio{
  @Id
  @GeneratedValue
  public long id;
  @Column
  public String nombre;
  @ManyToOne
  @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
  public Ubicacion ubicacion;
  @Getter
  @ManyToOne
  @JoinColumn(name = "provincia_id", referencedColumnName = "id")
  private Provincia provincia;
  @Getter
  @OneToMany(mappedBy = "municipio")
  private List<Localidad> localidades;

  public Municipio(int id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;

  }

  public void setProvincia(Provincia provinciaASetear){
    if(provincia == null){
      provincia = provinciaASetear;
    }
  }

  public void setLocalidades(List<Localidad> localidadesList){
    if(localidades == null){
      localidades = localidadesList;
    }
  }

  public Municipio(){

  }
}
