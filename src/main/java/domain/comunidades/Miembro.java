package domain.comunidades;

import domain.localizaciones.Localidad;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

public class Miembro {
  String nombre;
  String apellido;
  String email;
  Usuario usuario;

  @Getter
  Localidad localidadDeInteres;


  public Miembro(String nombre, String apellido, String email, Usuario usuario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.usuario = usuario;
  }

  Municipio getMunicipioDeInteres(){
    return localidadDeInteres.getMunicipio();
  }
  Provincia getProvinciaDeInteres(){
    return localidadDeInteres.getProvincia();
  }
}
