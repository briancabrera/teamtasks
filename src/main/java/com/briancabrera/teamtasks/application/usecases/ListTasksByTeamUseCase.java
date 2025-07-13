package com.briancabrera.teamtasks.application.usecases;

import com.briancabrera.teamtasks.application.dto.ListTasksByTeamQuery;
import com.briancabrera.teamtasks.application.dto.TaskResponseDTO;
import com.briancabrera.teamtasks.domain.model.Task;
import com.briancabrera.teamtasks.domain.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service that retrieves tasks for a team applying optional filters.
 */
@Service
public class ListTasksByTeamUseCase {

    private final TaskRepository taskRepository;

    public ListTasksByTeamUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Returns tasks for a team applying optional status and priority filters.
     *
     * @param query the query containing team id and filters
     * @return list of tasks mapped to DTOs
     */
    public List<TaskResponseDTO> execute(ListTasksByTeamQuery query) {
        List<Task> tasks = taskRepository.findByTeamWithFilters(
                query.teamId(),
                query.status(),
                query.priority()
        );

        return tasks.stream()
                .map(task -> new TaskResponseDTO(
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
                ))
                .collect(Collectors.toList());
    }
}
