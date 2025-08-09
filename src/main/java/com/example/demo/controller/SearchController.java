package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService service;//サービスクラスをもってきてる
	

}
