package services.notificador.formas.sinApuros;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import services.notificador.formas.sinApuros.SinApuros;

public class Notificar implements Job {

  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    SinApuros formaNotificar = (SinApuros) jobExecutionContext.get("servicioNotificador");
    formaNotificar.notificarPendientes();
  }
}
