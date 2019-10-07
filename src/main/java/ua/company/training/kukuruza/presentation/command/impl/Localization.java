package ua.company.training.kukuruza.presentation.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.company.training.kukuruza.util.AttributeNames;
import ua.company.training.kukuruza.util.Path;
import ua.company.training.kukuruza.util.RequestParameters;
import ua.company.training.kukuruza.persistence.entity.User;
import ua.company.training.kukuruza.presentation.command.ICommand;
import ua.company.training.kukuruza.util.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;
import java.util.Objects;

public class Localization implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(Localization.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Try to change a language");
        String lang = req.getParameter(RequestParameters.LANG);
        if (Objects.nonNull(lang))
            Config.set(req.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag(lang));
        LOGGER.info("A language was successfully changed");
        User user = (User) req.getSession().getAttribute(AttributeNames.USER);
        if (Objects.nonNull(user) && user.getUserTypeId() == UserType.ADMIN.getId())
            return Path.ADMIN_PAGE;

        return Path.INDEX_PAGE;
    }
}
