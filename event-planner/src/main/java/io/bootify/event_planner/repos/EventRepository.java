package io.bootify.event_planner.repos;

import io.bootify.event_planner.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Integer> {
}
