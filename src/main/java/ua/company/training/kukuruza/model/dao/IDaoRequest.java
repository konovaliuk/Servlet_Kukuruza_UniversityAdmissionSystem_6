package ua.company.training.kukuruza.model.dao;

import ua.company.training.kukuruza.model.entity.Request;

import java.util.List;
import java.util.Optional;

public interface IDaoRequest extends IDaoGeneric<Request, Long> {
    List<Request> getByEducationOptionId(Long educationOptionId);

    Optional<Request> getByUserId(Long userId);

    boolean deleteByUserId(Long userId);
}
