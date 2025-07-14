package com.briancabrera.teamtasks.entrypoints.rest;

import com.briancabrera.teamtasks.application.dto.CreateTaskCommand;
import com.briancabrera.teamtasks.application.dto.ListTasksByTeamQuery;
import com.briancabrera.teamtasks.application.dto.TaskResponseDTO;
import com.briancabrera.teamtasks.application.usecases.CreateTaskUseCase;
import com.briancabrera.teamtasks.application.usecases.ListTasksByTeamUseCase;
import com.briancabrera.teamtasks.domain.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller that exposes task related endpoints.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final ListTasksByTeamUseCase listTasksByTeamUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase,
                          ListTasksByTeamUseCase listTasksByTeamUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.listTasksByTeamUseCase = listTasksByTeamUseCase;
    }

    /**
     * Creates a new task.
     *
     * @param command information required to create the task
     * @return the created task wrapped in a {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid CreateTaskCommand command) {
        try {
            TaskResponseDTO response = createTaskUseCase.execute(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Lists tasks belonging to a team applying optional filters.
     *
     * @param teamId   the team identifier
     * @param status   optional status filter
     * @param priority optional priority filter
     * @return list of tasks
     */
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<TaskResponseDTO>> listByTeam(
            @PathVariable("teamId") UUID teamId,
            @RequestParam("status") Optional<Task.Status> status,
            @RequestParam("priority") Optional<Task.Priority> priority) {
        ListTasksByTeamQuery query = new ListTasksByTeamQuery(teamId, status, priority);
        List<TaskResponseDTO> response = listTasksByTeamUseCase.execute(query);
        return ResponseEntity.ok(response);
    }
}
