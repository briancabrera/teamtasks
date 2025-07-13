package com.briancabrera.teamtasks.application.dto;

import com.briancabrera.teamtasks.domain.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data transfer object representing a task returned by the application layer.
 */
public record TaskResponseDTO(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        Task.Priority priority,
        Task.Status status,
        UUID assignedUserId,
        UUID teamId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
