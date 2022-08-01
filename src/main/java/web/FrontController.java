package web;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import storage.DatabaseInitService;
import web.command.CommandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/*") //сервлет який реагує на все
public class FrontController extends HttpServlet {
    private TemplateEngine engine;
    //для сервісу команд
    private CommandService commandService;

    @Override
    public void init() {
        new DatabaseInitService().initDb();

        engine = new TemplateEngine();

//        FileTemplateResolver resolver = new FileTemplateResolver();
//        //String urlProject = getServletContext().getRealPath("");
//        //resolver.setPrefix(urlProject + "view/"); //пакет в src/main/webapp/ + view/
//        resolver.setPrefix("classpath:view/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML5");
//        resolver.setOrder(engine.getTemplateResolvers().size());
//        resolver.setCacheable(false);
//        engine.addTemplateResolver(resolver);

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver(); //універсальний солдат пошуку файлів
        resolver.setPrefix("view/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);

        commandService = new CommandService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("Success"); // позаже просто цей текст на сторінці
//        resp.getWriter().close();
        //resp.setContentType("text/html");

//        Map<String, String[]> parameterMap = req.getParameterMap();
//
//        Map<String, Object> params = new LinkedHashMap<>();
//        for (Map.Entry<String, String[]> keyValue : parameterMap.entrySet()) {
//            params.put(keyValue.getKey(), keyValue.getValue()[0]);
//        }

        //Context simpleContext = new Context(
        //        req.getLocale(),
        //        //Map.of("queryParams", params)
        //        Collections.emptyMap()
        //);

        //engine.process("index", simpleContext, resp.getWriter());
        //resp.getWriter().close();

        commandService.process(req, resp, engine);
    }
}