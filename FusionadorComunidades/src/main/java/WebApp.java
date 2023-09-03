import io.javalin.Javalin;

public class WebApp {
  public static void main(String[] args){
    Integer port = Integer.parseInt(
        System.getProperty("port","8080"));
    Javalin app = Javalin.create().start(port);
    app.get("/",ctx -> ctx.result("Que onda gente"));
  }
}
