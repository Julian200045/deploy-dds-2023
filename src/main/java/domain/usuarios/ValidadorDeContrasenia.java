package domain.usuarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.common.hash.Hashing;

public class ValidadorDeContrasenia {

    private Integer min;
    private Integer max;

    public File contraseniasInvalidas =  new File("src/main/resources/template/password-top-10000.txt");
    private Scanner scaner = new Scanner(contraseniasInvalidas);

    public ValidadorDeContrasenia(Integer min, Integer max) throws FileNotFoundException {
        this.min = min;
        this.max = max;
    }

    public Boolean EsValida(String contrasenia){
        return !SeEncuentraEnTop(contrasenia) && CumpleConLongitudPermitida(contrasenia);
    }

    public Boolean CumpleConLongitudPermitida(String contrasenia){
        return min <= contrasenia.length() && contrasenia.length() <= max;
    }

    public Boolean SeEncuentraEnTop(String contrasenia){
        while (scaner.hasNextLine()) {
            String data = scaner.nextLine();
            if (data.equals(contrasenia)) {
                return true;
            }
        }
        return false;
    }

    public String Hashear(String contrasenia){
        return Hashing.sha256().hashString(contrasenia, StandardCharsets.UTF_8).toString();
    }
}
