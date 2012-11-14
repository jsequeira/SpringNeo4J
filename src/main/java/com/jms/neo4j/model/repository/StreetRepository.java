package com.jms.neo4j.model.repository;

import java.util.List;
import java.util.Map;

import org.neo4j.kernel.impl.core.NodeProxy;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.TraversalRepository;

import com.jms.neo4j.model.Street;


public interface StreetRepository extends GraphRepository<Street>, 
NamedIndexRepository<Street>, 
TraversalRepository<Street>,
CypherDslRepository<Street>
{

	Street findByNameAndCity(String name, String city);
	
	@Query("START source=node:Street(name={0}), destination=node:Street(name={1})\n" +
			"MATCH p = shortestPath(source-[*]->destination)\n" +
			"RETURN nodes(p) as Street")
	Map<String, List<NodeProxy>> findShortestPath(String origin, String destination);
	
	@Query("START source=node:Street(name={0}, city={1}), destination=node:Street(name={2}, city={3})\n" +
			"MATCH p = shortestPath(source-[*]->destination)\n" +
			"RETURN nodes(p) as Street")
	Map<String, List<NodeProxy>> findShortestPath(String originName, String originCity, String destinationName, String destinationCity);
}
