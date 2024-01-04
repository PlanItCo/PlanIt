package io.bootify.event_planner.rest;

import io.bootify.event_planner.model.InviteeDTO;
import io.bootify.event_planner.service.InviteeService;
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
@RequestMapping(value = "/api/invitees", produces = MediaType.APPLICATION_JSON_VALUE)
public class InviteeResource {

    private final InviteeService inviteeService;

    public InviteeResource(final InviteeService inviteeService) {
        this.inviteeService = inviteeService;
    }

    @GetMapping
    public ResponseEntity<List<InviteeDTO>> getAllInvitees() {
        return ResponseEntity.ok(inviteeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InviteeDTO> getInvitee(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(inviteeService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createInvitee(@RequestBody @Valid final InviteeDTO inviteeDTO) {
        final Integer createdId = inviteeService.create(inviteeDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateInvitee(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final InviteeDTO inviteeDTO) {
        inviteeService.update(id, inviteeDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInvitee(@PathVariable(name = "id") final Integer id) {
        inviteeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
