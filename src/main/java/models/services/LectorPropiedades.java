package models.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LectorPropiedades {

  String path;

  public LectorPropiedades(String path) {
    this.path = path;
  }

  public String getPropiedad(String key) {
    try {
      FileInputStream ip = new FileInputStream(path);
      Properties prop = new Properties();
      prop.load(ip);
      return prop.getProperty(key);
    } catch(FileNotFoundException e) {
      return null;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Integer getPropiedadInt(String key)  {
    try {
      FileInputStream ip = new FileInputStream(path);
      Properties prop = new Properties();
      prop.load(ip);
      return Integer.parseInt(prop.getProperty(key));
    } catch(IOException i) {
      throw new RuntimeException(i);
    }
  }
}
