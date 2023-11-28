package server.dtos;

import lombok.Getter;

@Getter
public class RolDto {
  public Long id;
  public String nombre;
  public RolDto(Long id,String nombre){
    this.id = id;
    this.nombre = nombre;
  }
}
