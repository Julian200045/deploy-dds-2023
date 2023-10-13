package domain.comunidades;

import domain.localizaciones.Localidad;
import domain.roles.RolDelMiembro;
import domain.usuarios.Usuario;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

@Entity
@Table(name = "persona")
public class Persona {
  @Getter
  @Id
  @GeneratedValue
  private long id;
  @Column(name= "nombre")
  private String nombre;
  @Column(name = "apellido")
  private String apellido;

  @Setter
  @Getter
  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
  private Usuario usuario;

  @OneToMany(mappedBy = "persona", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
  private List<Miembro> membresias;

  @Getter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "localidad_id" , referencedColumnName = "id")
  private Localidad localidadDeInteres;

  @Getter
  @Setter
  private Double gradoConfianza;

  public Persona(String nombre, String apellido, Usuario usuario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.usuario = usuario;
  }

  public void unirseAComunidad(Comunidad comunidad){
    if(!comunidad.esMiembro(this)){
      Miembro miembro = new Miembro();
      miembro.setComunidad(comunidad);
      comunidad.agregarMiembro(miembro);
    }
  }

  public List<Comunidad> comunidades(){
    List<Comunidad> comunidads  = new ArrayList<>();
    membresias.forEach(membresia -> comunidads.add(membresia.getComunidad()));
    return comunidads;
  }

  public Persona(){

  }

  // Para testear
  public Persona(long id, String nombre, String apellido) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.gradoConfianza = 5D;
  }

  //Municipio getMunicipioDeInteres(){
   // return localidadDeInteres.getMunicipio();
  //}
  //Provincia getProvinciaDeInteres(){
    //return localidadDeInteres.getProvincia();
  //}
}
