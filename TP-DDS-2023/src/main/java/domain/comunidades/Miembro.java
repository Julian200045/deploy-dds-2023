package domain.comunidades;

import domain.localizaciones.Localidad;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.roles.Rol;
import domain.roles.RolDelMiembro;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "miembro")
public class Miembro {
  @Id
  @GeneratedValue
  long id;
  @Column(name= "nombre")
  String nombre;
  @Column(name = "apellido")
  String apellido;
  @Getter
  @OneToOne
  Usuario usuario;
  @Transient
  RolDelMiembro rolDelMiembro;
  @ManyToMany
  private List<Comunidad> comunidades; //ver como inicializar

  @Getter
  @ManyToOne()
  @JoinColumn(name = "localidad_id" , referencedColumnName = "id")
  Localidad localidadDeInteres;


  public Miembro(String nombre, String apellido, Usuario usuario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.usuario = usuario;
  }

  public void unirseAComunidad(Comunidad comunidad){
    if(!comunidad.esMiembro(this)){
      comunidades.add(comunidad);
      comunidad.agregarMiembro(this);
    }
  }

  public List<Comunidad> comunidades(){
    return new ArrayList<>(comunidades);
  }

  //Municipio getMunicipioDeInteres(){
   // return localidadDeInteres.getMunicipio();
  //}
  //Provincia getProvinciaDeInteres(){
    //return localidadDeInteres.getProvincia();
  //}
}
