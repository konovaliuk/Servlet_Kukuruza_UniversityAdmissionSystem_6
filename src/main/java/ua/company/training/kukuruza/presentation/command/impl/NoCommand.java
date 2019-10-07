package ua.company.training.kukuruza.presentation.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.UserType;
import ua.company.training.kukuruza.persistence.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class NoCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.warn("Wrong command name");
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);
        if (Objects.nonNull(user) && user.getUserTypeId() == UserType.ADMIN.getId()) {
            return Path.ADMIN_PAGE;
        } else {
            return Path.INDEX_PAGE;
        }
    }
}
