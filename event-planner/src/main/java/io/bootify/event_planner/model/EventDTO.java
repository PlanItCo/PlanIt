package io.bootify.event_planner.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventDTO {

    public EventDTO(){}
    public EventDTO(String title, String description, String status, OffsetDateTime createdAt, OffsetDateTime eventTime, String location, Integer creatorID, Integer numInvited){
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.eventTime = eventTime;
        this.location = location;
        this.creatorID = creatorID;
        this.numInvited = numInvited;
    }

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String title;

    @NotNull
    private String description;

    private String status;

    @NotNull
    private OffsetDateTime createdAt;

    private OffsetDateTime eventTime;

    @Size(max = 255)
    private String location;

    @NotNull
    private Integer creatorID;

    private Integer numInvited;

}
