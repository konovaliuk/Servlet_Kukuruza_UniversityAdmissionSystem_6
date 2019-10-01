package ua.company.training.kukuruza;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.command.CommandFactory;
import ua.company.training.kukuruza.util.RequestParameters;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doGet");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("doPost");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("URI is: " + req.getRequestURI());
        ICommand command = CommandFactory.getCommand(req.getParameter(RequestParameters.COMMAND));
        LOGGER.info("Command is: " + command.getClass().getSimpleName());
        String view = command.execute(req, resp);
        LOGGER.info("View is: " + view);
        if (view.startsWith("redirect:")) {
            resp.sendRedirect(req.getContextPath() + view.substring(9));
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(req, resp);
        }
    }
}
