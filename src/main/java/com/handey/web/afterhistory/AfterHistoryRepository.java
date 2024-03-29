package com.handey.web.afterhistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AfterHistoryRepository {
    AfterHistory save(AfterHistory afterHistory);
    Optional<AfterHistory> findById(Long id);
    List<AfterHistory> findByDate(LocalDate date);
    List<AfterHistory> findAll();
    void deleteById(Long id);
    List<AfterHistory> findByUserId(Long userId);
}
