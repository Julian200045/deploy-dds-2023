package services.notificador.enviadores;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import services.notificador.Notificacion;

public class EnviadorWPP {

        // Find your Account SID and Auth Token at twilio.com/console
        // and set the environment variables. See http://twil.io/secure
        LectorPropiedades lectorPropiedades = new LectorPropiedades("src/main/resources/template/project.properties");
        public static final String ACCOUNT_SID = lectorPropiedades.getPropiedad("Wpp-Account-Sid");
        public static final String AUTH_TOKEN = lectorPropiedades.getPropiedad("Wpp-Auth-Token");

        public static void enviar(Notificacion notificacion) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(lectorPropiedades.getPropiedad("Wpp-Nro-Saliente");), //nro del que envia
                            new com.twilio.type.PhoneNumber(lectorPropiedades.getPropiedad("Wpp-Nro-Receptor");), //nro del receptor
                            notificacion.getMensaje())
                    .create();

            System.out.println(message.getSid());
        }
    }
