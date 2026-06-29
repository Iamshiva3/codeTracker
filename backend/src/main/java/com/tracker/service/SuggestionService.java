package com.tracker.service;

import com.tracker.dto.SuggestionDto;
import com.tracker.model.ActivityLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionService {

    public List<SuggestionDto> generateSuggestions(List<ActivityLog> logs) {
        List<SuggestionDto> suggestions = new ArrayList<>();
        
        if (logs == null || logs.isEmpty()) {
            suggestions.add(new SuggestionDto("You haven't logged any activity yet. Start coding today!"));
            return suggestions;
        }

        int totalProblems = logs.stream().mapToInt(ActivityLog::getProblemsSolved).sum();
        int totalMinutes = logs.stream().mapToInt(ActivityLog::getTimeSpentMinutes).sum();
        
        if (totalProblems == 0 && totalMinutes > 0) {
            suggestions.add(new SuggestionDto("You are spending time but not solving problems. Try focusing on easier problems to build momentum."));
        } else if (totalProblems > 0) {
            double avgTimePerProblem = (double) totalMinutes / totalProblems;
            if (avgTimePerProblem > 60) {
                suggestions.add(new SuggestionDto("You are taking over an hour per problem. Don't hesitate to look at hints or editorials if you're stuck for more than 45 minutes."));
            } else if (avgTimePerProblem < 10) {
                suggestions.add(new SuggestionDto("You are solving problems very quickly! Consider increasing the difficulty level."));
            } else {
                suggestions.add(new SuggestionDto("Great pace! Keep practicing consistently."));
            }
        }

        // Check consistency
        long distinctDays = logs.stream().map(ActivityLog::getDate).distinct().count();
        if (distinctDays < 3 && logs.size() > 5) {
            suggestions.add(new SuggestionDto("Try to spread your coding sessions across more days to build a consistent habit."));
        }

        return suggestions;
    }
}
