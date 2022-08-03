package web.command;

import link.LinkService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import service_provider.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class GetCreateLinkCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        LinkService linkService = ServiceProvider.get(LinkService.class);

        Context simpleContext = new Context(
                req.getLocale(),
                Collections.emptyMap() // нічого не передаємо в шаблон
        );

        engine.process("create-link", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}