package com.briancabrera.teamtasks.application.usecases;

import com.briancabrera.teamtasks.application.dto.CreateTaskCommand;
import com.briancabrera.teamtasks.application.dto.TaskResponseDTO;
import com.briancabrera.teamtasks.domain.model.Task;
import com.briancabrera.teamtasks.domain.repository.TaskRepository;
import com.briancabrera.teamtasks.domain.service.TeamMembershipChecker;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Application service that handles the creation of tasks.
 */
public class CreateTaskUseCase {

    private final TaskRepository taskRepository;
    private final TeamMembershipChecker teamMembershipChecker;

    public CreateTaskUseCase(TaskRepository taskRepository,
                             TeamMembershipChecker teamMembershipChecker) {
        this.taskRepository = taskRepository;
        this.teamMembershipChecker = teamMembershipChecker;
    }

    /**
     * Creates a new task based on the provided command.
     *
     * @param command data required to create the task
     * @return representation of the created task
     */
    public TaskResponseDTO execute(@Valid CreateTaskCommand command) {
        UUID userId = command.assignedUserId();
        UUID teamId = command.teamId();

        if (!teamMembershipChecker.isUserMemberOfTeam(userId, teamId)) {
            throw new IllegalArgumentException("User is not a member of the team");
        }

        List<Task> activeTasks = taskRepository.findActiveTasksByUser(userId);
        if (activeTasks.size() >= 5) {
            throw new IllegalStateException("User already has 5 active tasks");
        }

        LocalDateTime now = LocalDateTime.now();
        Task task = new Task(
                UUID.randomUUID(),
                command.title(),
                command.description(),
                command.dueDate(),
                command.priority(),
                Task.Status.PENDING,
                userId,
                teamId,
                now,
                now
        );

        taskRepository.save(task);

        return new TaskResponseDTO(
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
}
