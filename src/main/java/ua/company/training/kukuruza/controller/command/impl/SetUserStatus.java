package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;
import ua.company.training.kukuruza.controller.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetUserStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        Integer userStatus = Integer.valueOf(req.getParameter("userStatus"));

        ServiceFactory.getInstance().getAdminService().setUserStatus(userId, userStatus);
        req.setAttribute("success", "success");

        return Path.SEND_NOTIFICATION_PAGE;
    }
}
