package services.calculadorConfianza.requests;

public class IncidenteMolde {
  Long id;
  String id_establecimiento;
  String id_servicio_afectado;
  String fecha_de_apertura;
  String fecha_de_cierre;
  Long id_usuario_de_apertura;
  Long id_usuario_de_cierre;

  public IncidenteMolde(Long id, String id_establecimiento, String id_servicio_afectado, String fecha_de_apertura, String fecha_de_cierre, Long id_usuario_de_apertura, Long id_usuario_de_cierre) {
    this.id = id;
    this.id_establecimiento = id_establecimiento;
    this.id_servicio_afectado = id_servicio_afectado;
    this.fecha_de_apertura = fecha_de_apertura;
    this.fecha_de_cierre = fecha_de_cierre;
    this.id_usuario_de_apertura = id_usuario_de_apertura;
    this.id_usuario_de_cierre = id_usuario_de_cierre;
  }
}
