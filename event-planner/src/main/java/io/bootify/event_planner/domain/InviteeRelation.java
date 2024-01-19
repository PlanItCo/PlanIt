package io.bootify.event_planner.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter

public class InviteeRelation {

    @EmbeddedId
    private InviteeRelationID id;

    @Column(nullable = false)
    private Boolean isComing;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("inviteeId")
    private Invitee invitee;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    public Event event;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public void setEvent(Event event) {
        System.out.println(event instanceof Event);
        System.out.println(this.event instanceof Event);
        System.out.println(this.event.getClass().getName());
        this.event = event;
    }
}
