package ua.epam.training.kukuruza.controller.service;

import ua.epam.training.kukuruza.model.dao.AbstractDaoFactory;
import ua.epam.training.kukuruza.model.entity.User;

public class CheckStatusService {
    private AbstractDaoFactory factory;

    public CheckStatusService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public User getUpdatedUser(User user) {
        return factory.getDaoUser().get(user.getId()).orElseThrow(RuntimeException::new);
    }
}
