package io.bootify.event_planner.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InviteeDTO {

    private Integer id;

    @NotNull
    @Size(max = 24)
    private String name;

    @NotNull
    @Size(max = 50)
    private String email;

}
