package com.tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Platform platform;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer timeSpentMinutes;

    @Column(nullable = false)
    private Integer problemsSolved;

    public ActivityLog() {}

    public ActivityLog(User user, Platform platform, LocalDate date, Integer timeSpentMinutes, Integer problemsSolved) {
        this.user = user;
        this.platform = platform;
        this.date = date;
        this.timeSpentMinutes = timeSpentMinutes;
        this.problemsSolved = problemsSolved;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Platform getPlatform() { return platform; }
    public void setPlatform(Platform platform) { this.platform = platform; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Integer getTimeSpentMinutes() { return timeSpentMinutes; }
    public void setTimeSpentMinutes(Integer timeSpentMinutes) { this.timeSpentMinutes = timeSpentMinutes; }

    public Integer getProblemsSolved() { return problemsSolved; }
    public void setProblemsSolved(Integer problemsSolved) { this.problemsSolved = problemsSolved; }
}
