package models.services.notificador.enviadores;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import models.services.LectorPropiedades;
import models.services.notificador.Notificacion;

import java.io.IOException;

public class EnviadorWPP {

        // Find your Account SID and Auth Token at twilio.com/console
        // and set the environment variables. See http://twil.io/secure
        static LectorPropiedades lectorPropiedades = new LectorPropiedades("src/main/resources/template/project.properties");
        public static String ACCOUNT_SID = null;

  static {
    try {
      ACCOUNT_SID = lectorPropiedades.getPropiedad("Wpp-Account-Sid");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String AUTH_TOKEN = null;

  static {
    try {
      AUTH_TOKEN = lectorPropiedades.getPropiedad("Wpp-Auth-Token");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void enviar(Notificacion notificacion) throws IOException {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(lectorPropiedades.getPropiedad("Wpp-Nro-Saliente")), //nro del que envia
                            new com.twilio.type.PhoneNumber(lectorPropiedades.getPropiedad("Wpp-Nro-Receptor")), //nro del receptor
                            notificacion.getMensaje())
                    .create();

            System.out.println(message.getSid());
        }
    }
