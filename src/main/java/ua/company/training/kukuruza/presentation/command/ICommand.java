package ua.company.training.kukuruza.presentation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Common interface for all commands.
 *
 * @author Andrii Kukuruza
 */
public interface ICommand {
    /**
     * This method allows a command to implement unique logic
     * and return a path to a view. In redirect case,
     * you should add "redirect:" before a path.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @return path to a view
     */
    String execute(HttpServletRequest req, HttpServletResponse resp);
}
