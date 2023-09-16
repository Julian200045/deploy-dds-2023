package domain.comunidades;

import domain.localizaciones.Localidad;
import domain.roles.RolDelMiembro;
import domain.usuarios.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "persona")
public class Persona {
  @Id
  @GeneratedValue
  private long id;
  @Column(name= "nombre")
  private String nombre;
  @Column(name = "apellido")
  private String apellido;
  @Getter
  @OneToOne
  private Usuario usuario;

  @OneToMany(mappedBy = "persona")
  private List<Miembro> membresias;
  //@Transient
  //RolDelMiembro rolDelMiembro;

  @Getter
  @ManyToOne
  @JoinColumn(name = "localidad_id" , referencedColumnName = "id")
  private Localidad localidadDeInteres;


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

  //Municipio getMunicipioDeInteres(){
   // return localidadDeInteres.getMunicipio();
  //}
  //Provincia getProvinciaDeInteres(){
    //return localidadDeInteres.getProvincia();
  //}
}
