package web.command;

import link.Link;
import link.LinkService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import service_provider.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        LinkService linkService = ServiceProvider.get(LinkService.class);

        List<Link> links;

        if (req.getParameterMap().containsKey("query")) {
            links = linkService.search(req.getParameter("query"));
        } else {
            links = linkService.listAll();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("links", links);
        params.put("query", req.getParameter("query"));

        Context simpleContext = new Context(
                req.getLocale(),
                params
        );

        engine.process("list", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}