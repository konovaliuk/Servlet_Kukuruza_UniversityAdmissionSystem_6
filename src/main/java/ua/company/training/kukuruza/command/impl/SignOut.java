package ua.company.training.kukuruza.command.impl;

import ua.company.training.kukuruza.command.ICommand;
import ua.company.training.kukuruza.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return Path.INDEX_PAGE;
    }
}
