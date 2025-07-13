package com.briancabrera.teamtasks.entrypoints.rest;

import com.briancabrera.teamtasks.application.dto.CreateTaskCommand;
import com.briancabrera.teamtasks.application.dto.TaskResponseDTO;
import com.briancabrera.teamtasks.application.usecases.ListTasksByTeamUseCase;
import com.briancabrera.teamtasks.application.usecases.CreateTaskUseCase;
import com.briancabrera.teamtasks.domain.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTaskUseCase createTaskUseCase;

    @MockBean
    private ListTasksByTeamUseCase listTasksByTeamUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTask_shouldReturn201_whenValidRequest() throws Exception {
        UUID taskId = UUID.randomUUID();
        TaskResponseDTO responseDTO = new TaskResponseDTO(
                taskId,
                "Title",
                "Desc",
                LocalDate.now().plusDays(1),
                Task.Priority.HIGH,
                Task.Status.PENDING,
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(createTaskUseCase.execute(any(CreateTaskCommand.class))).thenReturn(responseDTO);

        CreateTaskCommand command = new CreateTaskCommand(
                "Title",
                "Desc",
                LocalDate.now().plusDays(1),
                Task.Priority.HIGH,
                UUID.randomUUID(),
                UUID.randomUUID()
        );

        String json = objectMapper.writeValueAsString(command);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":\"" + taskId + "\"}", false));
    }

    @Test
    void listByTeam_shouldReturn200_withTasks() throws Exception {
        UUID taskId = UUID.randomUUID();
        UUID teamId = UUID.randomUUID();
        TaskResponseDTO dto = new TaskResponseDTO(
                taskId,
                "Title",
                "Desc",
                LocalDate.now().plusDays(1),
                Task.Priority.HIGH,
                Task.Status.PENDING,
                UUID.randomUUID(),
                teamId,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(listTasksByTeamUseCase.execute(any())).thenReturn(List.of(dto));

        mockMvc.perform(get("/tasks/team/" + teamId)
                        .param("status", Task.Status.PENDING.name())
                        .param("priority", Task.Priority.HIGH.name()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id':'" + taskId + "'}]", false));
    }
}
