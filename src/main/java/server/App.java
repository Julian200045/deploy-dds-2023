package server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class App {

  public static EntityManagerFactory entityManagerFactory;

  public static void main(String[] args) throws Exception {

    Map<String, String> env = System.getenv();
    for (String string : env.keySet()) {
      System.out.println(string + ": " + env.get(string));
    }

    entityManagerFactory =  createEntityManagerFactory();

    Server.init();
  }

  public static EntityManagerFactory createEntityManagerFactory() throws Exception {

    Map<String, String> env = System.getenv();
    Map<String, Object> configOverrides = new HashMap<String, Object>();

    String[] keys = new String[] {
        "DATABASE_URL",
        "javax__persistence__jdbc__driver",
        "javax__persistence__jdbc__password",
        "javax__persistence__jdbc__url",
        "javax__persistence__jdbc__user",
        "hibernate__hbm2ddl__auto",
        "hibernate__connection__pool_size",
        "hibernate__show_sql" };

    for (String key : keys) {

      try{
        if (key.equals("DATABASE_URL")) {


          String value = env.get(key);
          URI dbUri = new URI(value);
          String username = dbUri.getUserInfo().split(":")[0];
          String password = dbUri.getUserInfo().split(":")[1];

          value = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();// + "?sslmode=require";
          configOverrides.put("javax.persistence.jdbc.url", value);
          configOverrides.put("javax.persistence.jdbc.user", username);
          configOverrides.put("javax.persistence.jdbc.password", password);
          configOverrides.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");

        }

        String key2 = key.replace("__",".");
        if (env.containsKey(key2)) {
          String value = env.get(key2);
          configOverrides.put(key2, value);
        }
      } catch(Exception ex){
        System.out.println("Error configurando " + key);
      }
    }
    System.out.println("Config overrides ----------------------");
    for (String key : configOverrides.keySet()) {
      System.out.println(key + ": " + configOverrides.get(key));
    }
    return Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);
  }
}