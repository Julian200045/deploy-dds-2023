package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "localidad")
public class Localidad {
  @Id
  @GeneratedValue
  public Long id;
  @Column
  public String nombre;
  @ManyToOne
  @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
  public Ubicacion ubicacion;
  @Getter
  @ManyToOne
  @JoinColumn(name = "municipio_id", referencedColumnName = "id")
  private Municipio municipio;

  public Localidad(Long id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
  }

  public void setMunicipio(Municipio municipioASetear){
    if(municipio == null){
      municipio = municipioASetear;
    }
  }
}
