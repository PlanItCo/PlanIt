package io.bootify.event_planner.service;

import io.bootify.event_planner.domain.Invitee;
import io.bootify.event_planner.model.InviteeDTO;
import io.bootify.event_planner.repos.InviteeRepository;
import io.bootify.event_planner.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InviteeService {

    private final InviteeRepository inviteeRepository;

    public InviteeService(final InviteeRepository inviteeRepository) {
        this.inviteeRepository = inviteeRepository;
    }

    public List<InviteeDTO> findAll() {
        final List<Invitee> invitees = inviteeRepository.findAll(Sort.by("id"));
        return invitees.stream()
                .map(invitee -> mapToDTO(invitee, new InviteeDTO()))
                .toList();
    }

    public InviteeDTO get(final Integer id) {
        return inviteeRepository.findById(id)
                .map(invitee -> mapToDTO(invitee, new InviteeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final InviteeDTO inviteeDTO) {
        final Invitee invitee = new Invitee();
        mapToEntity(inviteeDTO, invitee);
        return inviteeRepository.save(invitee).getId();
    }

    public void update(final Integer id, final InviteeDTO inviteeDTO) {
        final Invitee invitee = inviteeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(inviteeDTO, invitee);
        inviteeRepository.save(invitee);
    }

    public void delete(final Integer id) {
        inviteeRepository.deleteById(id);
    }

    private InviteeDTO mapToDTO(final Invitee invitee, final InviteeDTO inviteeDTO) {
        inviteeDTO.setId(invitee.getId());
        inviteeDTO.setName(invitee.getName());
        inviteeDTO.setEmail(invitee.getEmail());
        return inviteeDTO;
    }

    private Invitee mapToEntity(final InviteeDTO inviteeDTO, final Invitee invitee) {
        invitee.setName(inviteeDTO.getName());
        invitee.setEmail(inviteeDTO.getEmail());
        return invitee;
    }

    public boolean emailExists(final String email) {
        return inviteeRepository.existsByEmailIgnoreCase(email);
    }

}
