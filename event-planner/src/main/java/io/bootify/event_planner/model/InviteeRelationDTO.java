package io.bootify.event_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.bootify.event_planner.domain.InviteeRelationID;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InviteeRelationDTO {
    public InviteeRelationDTO(){}
    public InviteeRelationDTO(Integer event, Integer invitee){
        this.isComing = false;
        this.invitee = invitee;
        this.event = event;

    }

    private InviteeRelationID id;

    @NotNull
    @JsonProperty("isComing")
    private Boolean isComing;

    private Integer invitee;

    private Integer event;

}
