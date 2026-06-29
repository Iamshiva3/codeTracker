package com.tracker.repository;

import com.tracker.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByUserIdOrderByDateDesc(Long userId);
    List<ActivityLog> findByUserIdAndDateBetweenOrderByDateAsc(Long userId, LocalDate startDate, LocalDate endDate);
}
