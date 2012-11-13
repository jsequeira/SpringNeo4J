package com.jms.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jms.neo4j.model.Street;
import com.jms.neo4j.model.StreetRepository;

@Service
@Transactional
public class StreetsService {

	@Autowired
	StreetRepository repository;
	
	public void setDefaultData(){
		
		repository.findAll();
		
	}
}
