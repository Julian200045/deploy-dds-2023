package server.dtos;

import lombok.Getter;
import models.entities.roles.Rol;
@Getter
public class UsuarioDto {
    public Long id;
    public String nombre;
    public String mail;
    public String telefono;
    public Rol rol;
    public UsuarioDto(Long id, String nombre, String mail, String telefono, Rol rol){
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.rol = rol;
    }
}
