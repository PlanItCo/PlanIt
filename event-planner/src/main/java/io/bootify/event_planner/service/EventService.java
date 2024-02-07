package io.bootify.event_planner.service;

import io.bootify.event_planner.domain.Event;
import io.bootify.event_planner.domain.Invitee;
import io.bootify.event_planner.domain.User;
import io.bootify.event_planner.model.EventDTO;
import io.bootify.event_planner.repos.EventRepository;
import io.bootify.event_planner.repos.InviteeRepository;
import io.bootify.event_planner.repos.UserRepository;
import io.bootify.event_planner.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final InviteeRepository inviteeRepository;

    public EventService(final EventRepository eventRepository, final UserRepository userRepository,
            final InviteeRepository inviteeRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.inviteeRepository = inviteeRepository;
    }

    public List<EventDTO> findAll() {
        final List<Event> events = eventRepository.findAll(Sort.by("id"));
        return events.stream()
                .map(event -> mapToDTO(event, new EventDTO()))
                .toList();
    }

    public EventDTO get(final Integer id) {
        return eventRepository.findById(id)
                .map(event -> mapToDTO(event, new EventDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EventDTO eventDTO) {
        final Event event = new Event();
        mapToEntity(eventDTO, event);
        return eventRepository.save(event).getId();
    }

    public void update(final Integer id, final EventDTO eventDTO) {
        final Event event = eventRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(eventDTO, event);
        eventRepository.save(event);
    }

    public void delete(final Integer id) {
        eventRepository.deleteById(id);
    }

    private EventDTO mapToDTO(final Event event, final EventDTO eventDTO) {
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setStatus(event.getStatus());
        eventDTO.setCreatedAt(event.getCreatedAt());
        eventDTO.setEventTime(event.getEventTime());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setCreatorID(event.getCreator() == null ? null : event.getCreator().getId());
        eventDTO.setNumInvited(event.getNumInvited());
        return eventDTO;
    }

    private Event mapToEntity(final EventDTO eventDTO, final Event event) {
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStatus(eventDTO.getStatus());
        event.setCreatedAt(eventDTO.getCreatedAt());
        event.setEventTime(eventDTO.getEventTime());
        event.setLocation(eventDTO.getLocation());
        final User creator = eventDTO.getCreatorID() == null ? null : userRepository.findById(eventDTO.getCreatorID())
                .orElseThrow(() -> new NotFoundException("creator not found"));
        event.setCreator(creator);
        final Integer invited = eventDTO.getNumInvited();
//                == null ? null : inviteeRepository.findById(eventDTO.getInvited())
//                .orElseThrow(() -> new NotFoundException("invited not found"));
        event.setNumInvited(invited);

        return event;
    }

}
