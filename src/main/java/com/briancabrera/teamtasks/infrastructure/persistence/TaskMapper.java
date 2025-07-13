package com.briancabrera.teamtasks.infrastructure.persistence;

import com.briancabrera.teamtasks.domain.model.Task;

/**
 * Utility class for converting between domain {@link Task} objects and
 * {@link TaskEntity} JPA entities.
 */
public final class TaskMapper {

    private TaskMapper() {
        // Utility class
    }

    /**
     * Converts a domain {@link Task} to its JPA representation.
     *
     * @param task the task to convert
     * @return a {@link TaskEntity} with the same data
     */
    public static TaskEntity toEntity(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskEntity(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getAssignedUserId(),
                task.getTeamId(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link TaskEntity} to the domain {@link Task} representation.
     *
     * @param entity the entity to convert
     * @return the domain task
     */
    public static Task toDomain(TaskEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Task(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDueDate(),
                entity.getPriority(),
                entity.getStatus(),
                entity.getAssignedUserId(),
                entity.getTeamId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}

