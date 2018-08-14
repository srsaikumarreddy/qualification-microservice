package com.stackroute.qualificationmicroservice.indexermodel;

import com.stackroute.qualificationmicroservice.relationshipmodel.StudiedAtRelationshipProperties;

public class Relationship {
	
	private String studiedAt;

	public String getStudiedAt() {
		return studiedAt;
	}

	public void setStudiedAt(String studiedAt) {
		this.studiedAt = studiedAt;
	}

	@Override
	public String toString() {
		return "Relationship [studiedAt=" + studiedAt + "]";
	}

	public Relationship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Relationship(StudiedAtRelationshipProperties studiedAtRelationshipProperties) {
		// TODO Auto-generated constructor stub
	}
	
	

}
