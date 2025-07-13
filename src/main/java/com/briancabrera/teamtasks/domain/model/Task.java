package com.briancabrera.teamtasks.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Immutable domain entity representing a task.
 */
public final class Task {

    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDate dueDate;
    private final Priority priority;
    private final Status status;
    private final UUID assignedUserId;
    private final UUID teamId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Task(UUID id,
                String title,
                String description,
                LocalDate dueDate,
                Priority priority,
                Status status,
                UUID assignedUserId,
                UUID teamId,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be empty");
        }
        this.title = title;
        this.description = description;
        this.dueDate = Objects.requireNonNull(dueDate, "dueDate must not be null");
        if (!dueDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("dueDate must be in the future");
        }
        this.priority = Objects.requireNonNull(priority, "priority must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.assignedUserId = Objects.requireNonNull(assignedUserId, "assignedUserId must not be null");
        this.teamId = Objects.requireNonNull(teamId, "teamId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public UUID getAssignedUserId() {
        return assignedUserId;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum Status {
        PENDING, IN_PROGRESS, DONE
    }
}
