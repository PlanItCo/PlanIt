package io.bootify.event_planner.rest;

import io.bootify.event_planner.model.CreateEventDTO;
import io.bootify.event_planner.model.EventDTO;
import io.bootify.event_planner.service.CreateEventService;
import io.bootify.event_planner.service.EventService;
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
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventResource {

    private final EventService eventService;
    private final CreateEventService createEventService;

    public EventResource(final EventService eventService, final CreateEventService createEventService) {
        this.eventService = eventService;
        this.createEventService = createEventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(eventService.get(id));
    }

    @PostMapping("/create")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createEvent(@RequestBody @Valid final CreateEventDTO createEventDTO){
        final Integer eventID = createEventService.createEvent(createEventDTO);
        return new ResponseEntity<>(eventID, HttpStatus.CREATED);
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createEvent(@RequestBody @Valid final EventDTO eventDTO) {
        final Integer createdId = eventService.create(eventDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PostMapping("/init")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> initEvents(@RequestBody @Valid final EventDTO eventDTO) {
        final Integer createdId = eventService.create(eventDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateEvent(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final EventDTO eventDTO) {
        eventService.update(id, eventDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEvent(@PathVariable(name = "id") final Integer id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
