package ua.epam.training.kukuruza.controller.command.impl;

import ua.epam.training.kukuruza.controller.command.ICommand;
import ua.epam.training.kukuruza.controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetUserStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        Integer userStatus = Integer.valueOf(req.getParameter("userStatus"));

        ServiceFactory.getInstance().getAdminService().setUserStatus(userId, userStatus);
        req.setAttribute("success", "success");

        return "/admin/sendNotification.jsp";
    }
}
