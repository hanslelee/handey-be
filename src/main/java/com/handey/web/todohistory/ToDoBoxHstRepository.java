package com.handey.web.todohistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ToDoBoxHstRepository {
    ToDoBoxHst save(ToDoBoxHst toDoBoxHst);
    Optional<ToDoBoxHst> findById(Long id);
    List<ToDoBoxHst> findByDate(LocalDate saveDt);
    List<ToDoBoxHst> findAll();
    List<ToDoBoxHst> findByUserId(Long userId);
    List<ToDoBoxHst> findByUserIdAndDate(Long userId, LocalDate saveDt);
    void deleteById(Long id);
}
