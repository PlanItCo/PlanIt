package io.bootify.event_planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InviteeRelationDTO {

    private Long id;

    @NotNull
    @JsonProperty("isComing")
    private Boolean isComing;

    private Integer invitee;

    private Integer event;

}
