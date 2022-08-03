package web.command;

import link.Link;
import link.LinkService;
import link.ShortLinkGenerator;
import org.thymeleaf.TemplateEngine;
import service_provider.ServiceProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostCreateLinkCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        LinkService linkService = ServiceProvider.get(LinkService.class);
        String fullUrl = req.getParameter("fullUrl");

        String shortUrl;

        do {
            shortUrl = new ShortLinkGenerator().generate();
        } while (linkService.getByShortLink(shortUrl) != null);

        Link link = new Link();
        link.setShortLink(shortUrl);
        link.setLink(fullUrl);
        linkService.save(link);

        resp.sendRedirect("/list");
    }
}