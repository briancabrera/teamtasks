package com.briancabrera.teamtasks.infrastructure.persistence;

import com.briancabrera.teamtasks.domain.model.Task;
import com.briancabrera.teamtasks.domain.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter that allows the domain layer to interact with the persistence layer
 * through Spring Data JPA.
 */
@Repository
public class JpaTaskRepositoryAdapter implements TaskRepository {

    private final SpringDataTaskRepository repository;

    public JpaTaskRepositoryAdapter(SpringDataTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Task task) {
        TaskEntity entity = TaskMapper.toEntity(task);
        repository.save(entity);
    }

    @Override
    public List<Task> findActiveTasksByUser(UUID userId) {
        List<TaskEntity> entities = repository.findByAssignedUserIdAndStatusNot(userId, Task.Status.DONE);
        return entities.stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findByTeamWithFilters(UUID teamId,
                                            Optional<Task.Status> status,
                                            Optional<Task.Priority> priority) {
        List<TaskEntity> entities;
        if (status.isPresent() && priority.isPresent()) {
            entities = repository.findByTeamIdAndStatusAndPriority(teamId, status.get(), priority.get());
        } else if (status.isPresent()) {
            entities = repository.findByTeamIdAndStatus(teamId, status.get());
        } else if (priority.isPresent()) {
            entities = repository.findByTeamIdAndPriority(teamId, priority.get());
        } else {
            entities = repository.findByTeamId(teamId);
        }
        return entities.stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }
}

