package com.briancabrera.teamtasks.domain.repository;

import com.briancabrera.teamtasks.domain.model.Task;

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
}
