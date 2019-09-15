package ua.epam.training.kukuruza.model.dao;

import ua.epam.training.kukuruza.model.entity.Request;

import java.util.Optional;

public interface IDaoRequest extends IDaoGeneric<Request, Long> {
    Optional<Request> getByUserId(Long userId);
    boolean deleteByUserId(Long userId);
}
