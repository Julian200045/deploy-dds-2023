package services.notificador.formas;
import domain.usuarios.Usuario;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import services.notificador.Notificacion;

public class NotificarSinApuros implements Job {

  FormasNotificar forma;
  Notificacion notificacion;

  public NotificarSinApuros(FormasNotificar forma, Notificacion notificacion) {
    this.forma = forma;
    this.notificacion = notificacion;
  }

  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    Usuario usuario = notificacion.getUsuario();
    usuario.getNotifiacionesPendientes().forEach(notificacionPendiente -> forma.enviarNotificacion(notificacionPendiente));
  }
}
