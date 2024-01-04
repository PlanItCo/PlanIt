package io.bootify.event_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.bootify.event_planner.domain.InviteeRelationID;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InviteeRelationDTO {

    private InviteeRelationID id;

    @NotNull
    @JsonProperty("isComing")
    private Boolean isComing;

    private Integer invitee;

    private Integer event;

}
