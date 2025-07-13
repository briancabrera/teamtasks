package com.briancabrera.teamtasks.domain.service;

import java.util.UUID;

/**
 * Abstraction used to verify if a user belongs to a team.
 * This interface will be implemented by infrastructure layers
 * so use cases can validate team membership without depending
 * on specific implementations.
 */
public interface TeamMembershipChecker {

    /**
     * Checks whether a user is a member of a given team.
     *
     * @param userId identifier of the user to check
     * @param teamId identifier of the team
     * @return {@code true} if the user belongs to the team, otherwise {@code false}
     */
    boolean isUserMemberOfTeam(UUID userId, UUID teamId);
}
