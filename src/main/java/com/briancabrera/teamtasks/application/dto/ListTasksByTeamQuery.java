package com.briancabrera.teamtasks.application.dto;

import com.briancabrera.teamtasks.domain.model.Task;

import java.util.Optional;
import java.util.UUID;

/**
 * Query object used to request tasks belonging to a team
 * optionally filtered by status and priority.
 */
public record ListTasksByTeamQuery(
        UUID teamId,
        Optional<Task.Status> status,
        Optional<Task.Priority> priority
) {
}
