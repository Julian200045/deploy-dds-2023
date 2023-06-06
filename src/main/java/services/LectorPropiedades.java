package services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LectorPropiedades {

  String path;

  public LectorPropiedades(String path){
    this.path = path;
  }

  public String getPropiedad(String key) throws IOException {

    FileInputStream ip = new FileInputStream(path);
    Properties prop = new Properties();

    prop.load(ip);

    return prop.getProperty(key);
  }

  public Integer getPropiedadInt(String key) throws IOException {

    FileInputStream ip = new FileInputStream(path);
    Properties prop = new Properties();

    prop.load(ip);

    return Integer.parseInt(prop.getProperty(key));
  }
}
