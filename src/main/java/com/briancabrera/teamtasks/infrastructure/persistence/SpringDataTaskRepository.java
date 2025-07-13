
package com.briancabrera.teamtasks.infrastructure.persistence;

import com.briancabrera.teamtasks.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data repository used internally by the persistence adapter.
 */
public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, UUID> {

    /**
     * Finds tasks assigned to a user whose status is not the given one.
     *
     * @param userId the id of the user
     * @param status status to exclude
     * @return matching task entities
     */
    List<TaskEntity> findByAssignedUserIdAndStatusNot(UUID userId, Task.Status status);
}
