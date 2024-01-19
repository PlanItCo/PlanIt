package io.bootify.event_planner.service;

import io.bootify.event_planner.domain.Event;
import io.bootify.event_planner.domain.Invitee;
import io.bootify.event_planner.domain.InviteeRelation;
import io.bootify.event_planner.domain.InviteeRelationID;
import io.bootify.event_planner.model.InviteeRelationDTO;
import io.bootify.event_planner.repos.EventRepository;
import io.bootify.event_planner.repos.InviteeRelationRepository;
import io.bootify.event_planner.repos.InviteeRepository;
import io.bootify.event_planner.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InviteeRelationService {

    private final InviteeRelationRepository inviteeRelationRepository;
    private final InviteeRepository inviteeRepository;
    private final EventRepository eventRepository;

    public InviteeRelationService(final InviteeRelationRepository inviteeRelationRepository,
            final InviteeRepository inviteeRepository, final EventRepository eventRepository) {
        this.inviteeRelationRepository = inviteeRelationRepository;
        this.inviteeRepository = inviteeRepository;
        this.eventRepository = eventRepository;
    }

    public List<InviteeRelationDTO> findAll() {
        final List<InviteeRelation> inviteeRelations = inviteeRelationRepository.findAll(Sort.by("id"));
        return inviteeRelations.stream()
                .map(inviteeRelation -> mapToDTO(inviteeRelation, new InviteeRelationDTO()))
                .toList();
    }

    public InviteeRelationDTO get(final InviteeRelationID id) {
        return inviteeRelationRepository.findById(id)
                .map(inviteeRelation -> mapToDTO(inviteeRelation, new InviteeRelationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public InviteeRelationID create(final InviteeRelationDTO inviteeRelationDTO) {
        final InviteeRelation inviteeRelation = new InviteeRelation();
        mapToEntity(inviteeRelationDTO, inviteeRelation);
        return inviteeRelationRepository.save(inviteeRelation).getId();
    }

    public void update(final InviteeRelationID id, final InviteeRelationDTO inviteeRelationDTO) {
        final InviteeRelation inviteeRelation = inviteeRelationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(inviteeRelationDTO, inviteeRelation);
        inviteeRelationRepository.save(inviteeRelation);
    }

    public void delete(final InviteeRelationID id) {
        inviteeRelationRepository.deleteById(id);
    }

    private InviteeRelationDTO mapToDTO(final InviteeRelation inviteeRelation,
            final InviteeRelationDTO inviteeRelationDTO) {
        inviteeRelationDTO.setId(inviteeRelation.getId());
        inviteeRelationDTO.setIsComing(inviteeRelation.getIsComing());
        inviteeRelationDTO.setInvitee(inviteeRelation.getInvitee() == null ? null : inviteeRelation.getInvitee().getId());
        inviteeRelationDTO.setEvent(inviteeRelation.getEvent() == null ? null : inviteeRelation.getEvent().getId());
        return inviteeRelationDTO;
    }

    private InviteeRelation mapToEntity(final InviteeRelationDTO inviteeRelationDTO,
            final InviteeRelation inviteeRelation) {

        inviteeRelation.setIsComing(inviteeRelationDTO.getIsComing());
        final Invitee invitee = inviteeRelationDTO.getInvitee() == null ? null : inviteeRepository.findById(inviteeRelationDTO.getInvitee())
                .orElseThrow(() -> new NotFoundException("invitee not found"));
        inviteeRelation.setInvitee(invitee);
        final Event event = inviteeRelationDTO.getEvent() == null ? null : eventRepository.findById(inviteeRelationDTO.getEvent())
                .orElseThrow(() -> new NotFoundException("event not found"));
        inviteeRelation.setEvent(event);
        return inviteeRelation;
    }

}
