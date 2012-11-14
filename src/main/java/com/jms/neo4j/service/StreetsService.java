package com.jms.neo4j.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.jms.neo4j.model.Street;
import com.jms.neo4j.model.repository.StreetRepository;

@Service
@Transactional
public class StreetsService {

	@Autowired
	StreetRepository repository;
	
	public void setDefaultData(){
		
		//clear existing data...
		repository.deleteAll();
		
		ArrayList<Street> streets = new ArrayList<Street>();
		Street s1 = new Street();
		s1.setCity("A");
		s1.setName("S1");
		streets.add(s1);
		
		Street s2 = new Street();
		s2.setCity("A");
		s2.setName("S2");
		streets.add(s2);
		
		
		Street s3 = new Street();
		s3.setCity("A");
		s3.setName("S3");
		streets.add(s3);
		
		Street s4 = new Street();
		s4.setCity("A");
		s4.setName("S4");
		streets.add(s4);
		
		
		s1.setConnectsTo(ImmutableSet.<Street>builder().add(s2)
		           .build());
		s2.setConnectsTo(ImmutableSet.<Street>builder().add(s3)
		           .build());
		repository.save(streets);
		
	}
	
	public void testFind(){
		
		
		System.out.println( repository.findByNameAndCity("S1", "A"));
		
		
		System.out.println(repository.findShortestPath("S1", "S4"));
	}
	
	public List<Street> findShortestPath(String start, String end){
		
		return Lists.transform(
				ImmutableList.<Street>builder().addAll(repository.findShortestPath(start, end)).build(), 
					new Function<Street, Street>() {
						public Street apply(Street s){
							Street toReturn = new Street();
							toReturn.setCity(s.getCity());
							toReturn.setName(s.getName());
							toReturn.setDetails(s.getDetails());
							return toReturn;
						}
					});
		
	//	return ImmutableList.<Street>builder().add(repository.findShortestPath(start, end)).build();
	}
}
