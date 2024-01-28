package io.bootify.event_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotNull
    private Integer id;

    @NotNull
    @JsonProperty("isComing")
    private Boolean isComing;

    @NotNull
    private Integer invitee;

    @NotNull
    private Integer event;

}
