package com.tracker.service;

import com.tracker.dto.ActivityLogDto;
import com.tracker.model.ActivityLog;
import com.tracker.model.User;
import com.tracker.repository.ActivityLogRepository;
import com.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private UserRepository userRepository;

    public ActivityLog addActivityLog(Long userId, ActivityLogDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ActivityLog log = new ActivityLog(user, dto.getPlatform(), dto.getDate(), dto.getTimeSpentMinutes(), dto.getProblemsSolved());
        return activityLogRepository.save(log);
    }

    public List<ActivityLog> getUserActivityLogs(Long userId) {
        return activityLogRepository.findByUserIdOrderByDateDesc(userId);
    }
}
