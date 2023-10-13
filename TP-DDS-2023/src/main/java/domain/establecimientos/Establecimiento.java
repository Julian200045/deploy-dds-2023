package domain.establecimientos;

import domain.entidades.Entidad;
import domain.localizaciones.Localidad;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.servicios.PrestacionDeServicio;
import domain.ubicaciones.Ubicacion;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

import java.util.List;
import lombok.Setter;

@Entity
@Table(name = "establecimiento")
public class Establecimiento {
  @Getter
  @Id
  @GeneratedValue
  long id;
  @Column(name = "nombre")
  private String nombre;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
  private Ubicacion ubicacion;
  @OneToMany(mappedBy = "establecimiento", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
  private List<PrestacionDeServicio> prestaciones;

  @Setter
  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "entidad_id", referencedColumnName = "id")
  public Entidad entidad;
  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "localidad_id", referencedColumnName = "id")
  public Localidad localidad;
  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_establecimiento_id", referencedColumnName = "id")
  public TipoEstablecimiento tipo;

  public Establecimiento(String nombre, Ubicacion ubicacion,TipoEstablecimiento tipo) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.tipo = tipo;
  }

  public void agregarPrestacionDeServicio(PrestacionDeServicio prestacion) {
    prestaciones.add(prestacion);
  }

  //Municipio getMunicipioDeInteres(){
   // return localidad.getMunicipio();
  //}
  //Provincia getProvinciaDeInteres(){
   // return localidad.getProvincia();
  //}

  public Establecimiento(){

  }
}
