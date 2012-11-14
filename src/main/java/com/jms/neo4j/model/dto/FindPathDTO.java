package com.jms.neo4j.model.dto;

public class FindPathDTO {

	private String startStreetName;
	private String startStreetCity;
	
	private String endStreetName;
	private String endStreetCity;
	public String getStartStreetName() {
		return startStreetName;
	}
	public void setStartStreetName(String startStreetName) {
		this.startStreetName = startStreetName;
	}
	public String getStartStreetCity() {
		return startStreetCity;
	}
	public void setStartStreetCity(String startStreetCity) {
		this.startStreetCity = startStreetCity;
	}
	public String getEndStreetName() {
		return endStreetName;
	}
	public void setEndStreetName(String endStreetName) {
		this.endStreetName = endStreetName;
	}
	public String getEndStreetCity() {
		return endStreetCity;
	}
	public void setEndStreetCity(String endStreetCity) {
		this.endStreetCity = endStreetCity;
	}
	
	
}
