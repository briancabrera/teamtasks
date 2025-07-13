package com.briancabrera.teamtasks.application.dto;

import com.briancabrera.teamtasks.domain.model.Task;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Command object used to request the creation of a new {@link Task}.
 * Bean Validation annotations enforce basic constraints on the input data.
 */
public record CreateTaskCommand(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull @Future LocalDate dueDate,
        @NotNull Task.Priority priority,
        @NotNull UUID assignedUserId,
        @NotNull UUID teamId
) {
}
