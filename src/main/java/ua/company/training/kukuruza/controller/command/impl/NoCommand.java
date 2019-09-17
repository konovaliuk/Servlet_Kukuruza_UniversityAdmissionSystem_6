package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.UserType;
import ua.company.training.kukuruza.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class NoCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (Objects.nonNull(user) && user.getUserTypeId() == UserType.ADMIN.getId()) {
            return Path.ADMIN_PAGE;
        } else {
            return Path.INDEX_PAGE;
        }
    }
}
