package domain.usuarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;
import com.google.common.hash.Hashing;

public class ValidadorDeContrasenia {

    private Integer min;
    private Integer max;

    public ValidadorDeContrasenia() throws IOException {

        FileInputStream ip =  new FileInputStream("src/main/resources/template/validador.properties");
        Properties prop = new Properties();

        prop.load(ip);

        this.min = Integer.parseInt(prop.getProperty("min"));
        this.max = Integer.parseInt(prop.getProperty("max"));
    }

    public Boolean EsValida(String contrasenia) throws FileNotFoundException {
        return !SeEncuentraEnTop(contrasenia) && CumpleConLongitudPermitida(contrasenia);
    }

    public Boolean CumpleConLongitudPermitida(String contrasenia){
        return min <= contrasenia.length() && contrasenia.length() <= max;
    }

    public Boolean SeEncuentraEnTop(String contrasenia) throws FileNotFoundException {
        File contraseniasInvalidas =  new File("src/main/resources/template/password-top-10000.txt");
        Scanner scaner = new Scanner(contraseniasInvalidas);

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
