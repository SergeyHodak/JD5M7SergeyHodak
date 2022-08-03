package web.command;

import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private final Map<String, Command> commands;

    private Command defaultCommand; //для обробки постійно змінюючоїсь лінки

    public CommandService() {
        commands = new HashMap<>();

        commands.put("GET /", new IndexCommand());
        commands.put("GET /list", new ListCommand());
        commands.put("GET /link/create", new GetCreateLinkCommand());
        commands.put("POST /link/create", new PostCreateLinkCommand());
        commands.put("GET /link/delete", new DeleteLinkCommand());

        defaultCommand = new RedirectCommand();
    }

    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;

        Command command = commands.get(commandKey);
        if (command != null) {
            command.process(req, resp, engine);
        } else {
            defaultCommand.process(req, resp, engine);
        }
    }
}