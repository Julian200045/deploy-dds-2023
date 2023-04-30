package services.ValidadorDeContrasenia.Validacion;
import java.util.regex.*;

public class ValidacionRepeticionLetras implements Validacion{

  @Override
  public Boolean valida(String contrasenia) {
    return Pattern.matches("^(?!.*(\\w)\\1{3,}).+$",contrasenia);
  }
}
