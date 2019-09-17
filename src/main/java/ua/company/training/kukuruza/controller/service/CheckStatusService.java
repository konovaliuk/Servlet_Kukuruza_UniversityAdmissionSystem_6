package ua.company.training.kukuruza.controller.service;

import ua.company.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.model.entity.User;

public class CheckStatusService {
    private AbstractDaoFactory factory;

    public CheckStatusService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public User getUpdatedUser(User user) {
        return factory.getDaoUser().get(user.getId()).orElseThrow(RuntimeException::new);
    }
}
