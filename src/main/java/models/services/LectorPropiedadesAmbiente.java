package models.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LectorPropiedadesAmbiente {

    public String getPropiedad(String key) {
        return System.getenv(key);
    }

    public Integer getPropiedadInt(String key) {
        return Integer.parseInt(getPropiedad(key));
    }
}
