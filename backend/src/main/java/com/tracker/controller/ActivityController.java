package com.tracker.controller;

import com.tracker.dto.ActivityLogDto;
import com.tracker.dto.SuggestionDto;
import com.tracker.model.ActivityLog;
import com.tracker.security.UserDetailsImpl;
import com.tracker.service.ActivityService;
import com.tracker.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SuggestionService suggestionService;

    private Long getCurrentUserId() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId();
    }

    @PostMapping
    public ResponseEntity<?> addActivity(@RequestBody ActivityLogDto activityLogDto) {
        Long userId = getCurrentUserId();
        ActivityLog log = activityService.addActivityLog(userId, activityLogDto);
        return ResponseEntity.ok(log);
    }

    @GetMapping
    public ResponseEntity<?> getUserActivities() {
        Long userId = getCurrentUserId();
        List<ActivityLog> logs = activityService.getUserActivityLogs(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<?> getSuggestions() {
        Long userId = getCurrentUserId();
        List<ActivityLog> logs = activityService.getUserActivityLogs(userId);
        List<SuggestionDto> suggestions = suggestionService.generateSuggestions(logs);
        return ResponseEntity.ok(suggestions);
    }
}
