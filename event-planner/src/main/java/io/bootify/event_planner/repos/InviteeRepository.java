package io.bootify.event_planner.repos;

import io.bootify.event_planner.domain.Invitee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InviteeRepository extends JpaRepository<Invitee, Integer> {

    boolean existsByEmailIgnoreCase(String email);

}
