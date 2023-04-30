package services.Hasher;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public interface Hasher {
  public String hashear(String contrasenia);
}
