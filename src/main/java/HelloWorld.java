import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class HelloWorld {

    public static void main(String[] args) {

        staticFileLocation("/public");
        port(getHerokuAssignedPort());
        get("/hello", (req, res) -> {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("name", "Mfundo");
            return new ModelAndView(dataMap, "hello.hbs");

        }, new HandlebarsTemplateEngine());

        get("/hello/:name", (req, res) -> {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("name", ":name");
            return new ModelAndView(dataMap, "hello.hbs");
        }, new HandlebarsTemplateEngine());

//      initExceptionHandler((e) -> System.out.println("Uh-oh"));

    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
