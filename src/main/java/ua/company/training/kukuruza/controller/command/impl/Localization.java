package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.util.AttributeNames;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;
import ua.company.training.kukuruza.model.entity.User;
import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.util.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;
import java.util.Objects;

public class Localization implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter(RequestParameters.LANG);
        if (Objects.nonNull(lang))
            Config.set(req.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag(lang));

        User user = (User) req.getSession().getAttribute(AttributeNames.USER);
        if (Objects.nonNull(user) && user.getUserTypeId() == UserType.ADMIN.getId())
            return Path.ADMIN_PAGE;

        return Path.INDEX_PAGE;
    }
}
