package domain.entidades;

import domain.establecimientos.Establecimiento;
import java.util.List;
import java.util.stream.Collectors;

import domain.localizaciones.Localidad;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
@Entity
@Table(name = "entidad")
public class Entidad {
  @Getter
  @Id
  @GeneratedValue
  public long id;
  @Getter
  @Column(name = "nombre")
  public String nombre;
  @OneToMany(mappedBy = "entidad")
  private List<Establecimiento> establecimientos;
  @ManyToOne
  @JoinColumn(name = "tipo_id", referencedColumnName = "id")
  public TipoEntidad tipo;

  public Entidad(int id, String nombre,TipoEntidad tipo) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
  }

  public List<Localidad> localidades(){
    return establecimientos.stream().map(establecimiento -> establecimiento.getLocalidad()).distinct().toList();
  }

  public void agregarEstablecimiento(Establecimiento establecimiento){
    if(tipo.getTiposDeEstablecimientosPermitidos().contains(establecimiento.tipo)){
      establecimientos.add(establecimiento);
    }
  }

  public Entidad(){

  }
}
