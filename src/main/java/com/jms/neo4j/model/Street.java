package com.jms.neo4j.model;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * This class represnets a road network node
 * @author Joao
 *
 */

@NodeEntity
public class Street {

	@GraphId private Long id;
	

	@Indexed private String name;
	

	@Indexed private String city;
	

	private String details;
	
	
	@RelatedTo(type="connectsTo", direction=Direction.OUTGOING)
	private Set<Street> connectsTo;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public Set<Street> getConnectsTo() {
		return connectsTo;
	}


	public void setConnectsTo(Set<Street> connectsTo) {
		this.connectsTo = connectsTo;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Street [id=").append(id).append(", name=").append(name)
				.append(", city=").append(city).append(", details=")
				.append(details).append(", connectsTo=")
				.append(connectsTo)
				.append("]");
		return builder.toString();
	}

	
}
