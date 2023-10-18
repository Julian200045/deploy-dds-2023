package containers;

import lombok.Getter;

public class PrestacionDeServicio {
  Long id;
  Long servicio;
  Long establecimiento;

  public Long getId() {
    return id;
  }

  public Long getServicio() {
    return servicio;
  }

  public Long getEstablecimiento() {
    return establecimiento;
  }
}
