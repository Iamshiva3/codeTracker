package com.tracker.dto;

import com.tracker.model.Platform;
import java.time.LocalDate;

public class ActivityLogDto {
    private Platform platform;
    private LocalDate date;
    private Integer timeSpentMinutes;
    private Integer problemsSolved;

    public Platform getPlatform() { return platform; }
    public void setPlatform(Platform platform) { this.platform = platform; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Integer getTimeSpentMinutes() { return timeSpentMinutes; }
    public void setTimeSpentMinutes(Integer timeSpentMinutes) { this.timeSpentMinutes = timeSpentMinutes; }
    public Integer getProblemsSolved() { return problemsSolved; }
    public void setProblemsSolved(Integer problemsSolved) { this.problemsSolved = problemsSolved; }
}
