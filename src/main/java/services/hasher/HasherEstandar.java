package services.hasher;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HasherEstandar implements Hasher{

  public String hashear(String contrasenia){
    return Hashing.sha256().hashString(contrasenia, StandardCharsets.UTF_8).toString();
  }
}
