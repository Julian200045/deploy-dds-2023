package servicios;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LectorPropiedades {

  String path;

  public LectorPropiedades(String path) {
    this.path = path;
  }

  /**
   * Metodo para leer un String de las propiedades.
   *
   * @param key Key a leer.
   * @return valor leido.
   * @throws IOException tipo de excepcion a arrojar en caso de fallar.
   */
  public String getPropiedad(String key) throws IOException {

    FileInputStream ip = new FileInputStream(path);
    Properties prop = new Properties();

    prop.load(ip);

    return prop.getProperty(key);
  }

  /**
   * Método para leer un Integer de las propiedades.
   *
   * @param key Key a leer.
   * @return valor leído parseado a Integer.
   * @throws IOException tipo de excepcion a arrojar en caso de fallo.
   */
  public Integer getPropiedadInt(String key) throws IOException {

    FileInputStream ip = new FileInputStream(path);
    Properties prop = new Properties();

    prop.load(ip);

    return Integer.parseInt(prop.getProperty(key));
  }

  /**
   * Método para leer un Double de las propiedades.
   *
   * @param key Key a leer.
   * @return valor leído parseado a Double.
   * @throws IOException tipo de excepcion a arrojar en caso de fallo.
   */
  public Double getPropiedadDouble(String key) throws IOException {

    FileInputStream ip = new FileInputStream(path);
    Properties prop = new Properties();

    prop.load(ip);

    return Double.parseDouble(prop.getProperty(key));
  }
}

