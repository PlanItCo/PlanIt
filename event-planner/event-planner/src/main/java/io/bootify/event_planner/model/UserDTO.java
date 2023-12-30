package io.bootify.event_planner.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    @NotNull
    @Size(max = 24)
    private String username;

    @NotNull
    private OffsetDateTime createdAt;

    @NotNull
    @Size(max = 24)
    private String password;

}
