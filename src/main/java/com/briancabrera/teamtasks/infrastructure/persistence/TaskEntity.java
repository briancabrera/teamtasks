package com.briancabrera.teamtasks.infrastructure.persistence;

import com.briancabrera.teamtasks.domain.model.Task;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA entity that maps the {@link Task} domain object to the database.
 */
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    private UUID id;

    private String title;

    private String description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Task.Priority priority;

    @Enumerated(EnumType.STRING)
    private Task.Status status;

    @Column(name = "assigned_user_id")
    private UUID assignedUserId;

    @Column(name = "team_id")
    private UUID teamId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Default constructor required by JPA.
     */
    protected TaskEntity() {
        // JPA requires a no-args constructor
    }

    public TaskEntity(UUID id,
                      String title,
                      String description,
                      LocalDate dueDate,
                      Task.Priority priority,
                      Task.Status status,
                      UUID assignedUserId,
                      UUID teamId,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.assignedUserId = assignedUserId;
        this.teamId = teamId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Task.Priority getPriority() {
        return priority;
    }

    public Task.Status getStatus() {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Task.Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }

    public void setAssignedUserId(UUID assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

