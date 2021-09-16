package com.handey.web.service;

import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.history.WeeklyElm;

import java.util.List;
import java.util.Optional;

public interface WeeklyElmRepository {
    WeeklyElm save(WeeklyElm weeklyElm);
    Optional<WeeklyElm> findById(Long id);
    Optional<WeeklyElm> findByTitle(String title);
    List<WeeklyElm> findAll();
    void deleteById(Long id);
}