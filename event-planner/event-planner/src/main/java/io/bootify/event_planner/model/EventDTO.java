package io.bootify.event_planner.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventDTO {

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
    private Integer creator;

    private Integer invited;

}
