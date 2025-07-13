package com.briancabrera.teamtasks.entrypoints.rest;

import com.briancabrera.teamtasks.application.dto.CreateTaskCommand;
import com.briancabrera.teamtasks.application.dto.TaskResponseDTO;
import com.briancabrera.teamtasks.application.usecases.CreateTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes task related endpoints.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
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
}
