package domain.comunidades;

import domain.localizaciones.Localidad;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.roles.Rol;
import domain.roles.RolDelMiembro;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Miembro {
  String nombre;
  String apellido;
  @Getter
  Usuario usuario;
  RolDelMiembro rolDelMiembro;
  private List<Comunidad> comunidades; //ver como inicializar

  @Getter
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
