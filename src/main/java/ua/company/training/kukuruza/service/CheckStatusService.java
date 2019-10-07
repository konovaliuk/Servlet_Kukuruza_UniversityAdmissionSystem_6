package ua.company.training.kukuruza.service;

import ua.company.training.kukuruza.persistence.dao.AbstractDaoFactory;
import ua.company.training.kukuruza.persistence.entity.User;

public class CheckStatusService {
    private AbstractDaoFactory factory;

    CheckStatusService(AbstractDaoFactory factory) {
        this.factory = factory;
    }

    public User getUpdatedUser(Long userId) {
        return factory.getDaoUser().find(userId).orElseThrow(RuntimeException::new);
    }
}
