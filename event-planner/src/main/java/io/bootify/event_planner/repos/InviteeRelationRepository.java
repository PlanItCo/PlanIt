package io.bootify.event_planner.repos;

import io.bootify.event_planner.domain.InviteeRelation;
import io.bootify.event_planner.domain.InviteeRelationID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InviteeRelationRepository extends JpaRepository<InviteeRelation, InviteeRelationID> {
}
