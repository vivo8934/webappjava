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

        get("/", (req,res) -> {

            Map<String, String> dataMap = new HashMap<>();
            return new ModelAndView(dataMap, "greets.hbs");
        },  new HandlebarsTemplateEngine());

        post("/greet", (req, res) -> {

            String name = req.queryParams("firstname");
            String language = req.queryParams("optradio");


            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("name", greet(name, language));
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
    public static String greet(String name, String language) {
        String msg = "Invalid Selection";
         if(language.equals("English") & name.length() > 0){
            msg = "Hello " + name;
             System.out.println(msg);
        }else if(language.equals("Xhosa") & name.length() > 0){
            msg = "Molo " + name;
        }else if(language.equals("Mandarin") & name.length() > 0) {
            msg = "Ni hao " + name;

        }
        return msg;
    }
}
