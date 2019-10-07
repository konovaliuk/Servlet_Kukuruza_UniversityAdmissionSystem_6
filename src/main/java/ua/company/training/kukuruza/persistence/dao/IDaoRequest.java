package ua.company.training.kukuruza.persistence.dao;

import ua.company.training.kukuruza.persistence.entity.Request;

import java.util.List;
import java.util.Optional;

public interface IDaoRequest extends IDaoGeneric<Request, Long> {
    List<Request> findByEducationOptionId(Long educationOptionId);

    Optional<Request> findByUserId(Long userId);

    boolean deleteByUserId(Long userId);
}
