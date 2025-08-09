package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.SearchMapper;

@Service
public class SearchService {

	@Autowired
	private SearchMapper mapper;
	
	
}
