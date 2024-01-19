package io.bootify.event_planner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class InviteeRelationID implements Serializable {
	@Column(name = "invitee_id")
	private Integer inviteeId;

	@Column(name = "event_id")
	private Integer eventId;

	// default constructor

	public InviteeRelationID() {
	}

	public InviteeRelationID(Integer inviteeId, Integer eventId) {
		this.inviteeId = inviteeId;
		this.eventId = eventId;
	}


	public boolean equals(InviteeRelationID other){
		return (this.inviteeId.equals(other.inviteeId) && this.eventId.equals(other.eventId));
	}

	@Override
	public int hashCode() {
		final int multiplier = 31;
		int hash = 1;
		hash = multiplier * hash + ((inviteeId == null) ? 0 : inviteeId);
		hash = multiplier * hash + ((eventId == null) ? 0 : eventId);
		return hash;
	}

	// equals() and hashCode()
}