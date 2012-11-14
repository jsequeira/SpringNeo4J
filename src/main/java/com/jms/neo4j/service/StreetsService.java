package com.jms.neo4j.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.kernel.impl.core.NodeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scala.collection.JavaConversions.MapWrapper;
import scala.collection.JavaConversions.SeqWrapper;

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
		
		
		System.out.println(repository.findShortestPath(start, end));
		Map<String, List<NodeProxy>> map = repository.findShortestPath(start, end);
		
		for(String key: map.keySet()){
			System.out.println(key);
			List<NodeProxy> stf =  map.get(key);
			
			for(NodeProxy obj : stf){
				System.out.println(obj.getProperty("name"));
			}
		}
		

		return Lists.transform(
				map.get("Street"), 
					new Function<NodeProxy, Street>() {
						public Street apply(NodeProxy s){
							Street toReturn = new Street();
							
							toReturn.setId(s.getId());
							
							if(s.hasProperty("city")){
								toReturn.setCity(s.getProperty("city").toString());
							}
							if(s.hasProperty("name")){
								toReturn.setName(s.getProperty("name").toString());
							}
							if(s.hasProperty("details")){
								toReturn.setDetails(s.getProperty("details").toString());
							}
							
							return toReturn;
						}
					});
		
	//	return ImmutableList.<Street>builder().add(repository.findShortestPath(start, end)).build();
	}
}
