package com.briancabrera.teamtasks.domain.repository;

import com.briancabrera.teamtasks.domain.model.Task;

import java.util.Optional;

import java.util.List;
import java.util.UUID;

/**
 * Abstraction of the persistence layer for {@link Task} entities.
 */
public interface TaskRepository {

    /**
     * Persist a task instance.
     *
     * @param task the task to save
     */
    void save(Task task);

    /**
     * Retrieve all active tasks assigned to a specific user.
     *
     * @param userId the id of the user
     * @return list of active tasks
     */
    List<Task> findActiveTasksByUser(UUID userId);

    /**
     * Retrieve tasks for a team optionally filtered by status and priority.
     *
     * @param teamId   the team identifier
     * @param status   optional status filter
     * @param priority optional priority filter
     * @return list of tasks matching the filters
     */
    List<Task> findByTeamWithFilters(UUID teamId,
                                     Optional<Task.Status> status,
                                     Optional<Task.Priority> priority);
}
