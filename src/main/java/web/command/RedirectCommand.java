package web.command;

import link.Link;
import link.LinkService;
import org.thymeleaf.TemplateEngine;
import service_provider.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String shortLink = req.getRequestURI().replace("/", "");

        System.out.println("shortLink = " + shortLink);

        LinkService linkService = ServiceProvider.get(LinkService.class);
        Link link = linkService.getByShortLink(shortLink);

        link.setOpenCount(link.getOpenCount() + 1);
        linkService.save(link);

        //TODO what if link not exists?

        String fullLink = link.getLink();
        System.out.println("fullLink = " + fullLink);
        resp.sendRedirect(fullLink);
    }
}