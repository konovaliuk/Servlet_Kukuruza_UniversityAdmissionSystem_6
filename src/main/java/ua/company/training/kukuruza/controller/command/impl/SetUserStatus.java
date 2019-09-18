package ua.company.training.kukuruza.controller.command.impl;

import ua.company.training.kukuruza.controller.command.ICommand;
import ua.company.training.kukuruza.controller.service.ServiceFactory;
import ua.company.training.kukuruza.controller.util.AttributeNames;
import ua.company.training.kukuruza.controller.util.Path;
import ua.company.training.kukuruza.controller.util.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetUserStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Long userId = Long.valueOf(req.getParameter(RequestParameters.USER_ID));
        Integer userStatus = Integer.valueOf(req.getParameter(RequestParameters.USER_STATUS));

        ServiceFactory.getInstance().getAdminService().setUserStatus(userId, userStatus);
        req.setAttribute(AttributeNames.SUCCESS, "success");

        return Path.SEND_NOTIFICATION_PAGE;
    }
}
