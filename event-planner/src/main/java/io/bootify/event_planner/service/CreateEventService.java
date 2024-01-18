package io.bootify.event_planner.service;

import io.bootify.event_planner.domain.Event;
import io.bootify.event_planner.domain.Invitee;
import io.bootify.event_planner.domain.InviteeRelation;
import io.bootify.event_planner.domain.User;
import io.bootify.event_planner.model.CreateEventDTO;
import io.bootify.event_planner.model.EventDTO;
import io.bootify.event_planner.model.InviteeDTO;
import io.bootify.event_planner.model.InviteeRelationDTO;
import io.bootify.event_planner.repos.EventRepository;
import io.bootify.event_planner.repos.InviteeRepository;
import io.bootify.event_planner.repos.UserRepository;
import io.bootify.event_planner.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CreateEventService {
    private final EventService eventService;
    private final InviteeService inviteeService;
    private final InviteeRelationService inviteeRelationService;

    public CreateEventService(final EventService eventService, final InviteeService inviteeService, final InviteeRelationService inviteeRelationService){
        this.eventService = eventService;
        this.inviteeRelationService = inviteeRelationService;
        this.inviteeService = inviteeService;
    }

    public Integer createEvent(CreateEventDTO createEvent){
        EventDTO event = new EventDTO(createEvent.getTitle(), createEvent.getDescription(), createEvent.getStatus(), createEvent.getCreatedAt(), createEvent.getEventTime(), createEvent.getLocation(), createEvent.getCreator(), createEvent.getInviteeList().size());
        Integer eventID = eventService.create(event);
        for(InviteeDTO invitee: createEvent.getInviteeList()){
            Integer inviteeID = inviteeService.create(invitee);
            InviteeRelationDTO inviteeRelation = new InviteeRelationDTO(eventID, inviteeID);
            System.out.println("hello");
            inviteeRelationService.create(inviteeRelation);
        }

        return eventID;
    }


}
