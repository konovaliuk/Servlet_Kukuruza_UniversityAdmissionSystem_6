package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;
import java.util.Objects;

public class Localization implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter("lang");
        if (Objects.nonNull(lang))
            Config.set(req.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag(lang));
        return "/index.jsp";
    }
}
