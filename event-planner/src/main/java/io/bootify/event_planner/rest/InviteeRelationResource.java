package io.bootify.event_planner.rest;

import io.bootify.event_planner.domain.InviteeRelationID;
import io.bootify.event_planner.model.InviteeRelationDTO;
import io.bootify.event_planner.service.InviteeRelationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/inviteeRelations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InviteeRelationResource {

    private final InviteeRelationService inviteeRelationService;

    public InviteeRelationResource(final InviteeRelationService inviteeRelationService) {
        this.inviteeRelationService = inviteeRelationService;
    }

    @GetMapping
    public ResponseEntity<List<InviteeRelationDTO>> getAllInviteeRelations() {
        return ResponseEntity.ok(inviteeRelationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InviteeRelationDTO> getInviteeRelation(
            @PathVariable(name = "id") final InviteeRelationID id) {
        return ResponseEntity.ok(inviteeRelationService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<InviteeRelationID> createInviteeRelation(
            @RequestBody @Valid final InviteeRelationDTO inviteeRelationDTO) {
        final InviteeRelationID createdId = inviteeRelationService.create(inviteeRelationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InviteeRelationID> updateInviteeRelation(@PathVariable(name = "id") final InviteeRelationID id,
            @RequestBody @Valid final InviteeRelationDTO inviteeRelationDTO) {
        inviteeRelationService.update(id, inviteeRelationDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInviteeRelation(@PathVariable(name = "id") final InviteeRelationID id) {
        inviteeRelationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
