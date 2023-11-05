package server.dtos;

import lombok.Getter;

@Getter
public class DireccionDto {
  public String calle;
  public String altura;

  public DireccionDto(String calle, String altura) {
    this.calle = calle;
    this.altura = altura;
  }
}
