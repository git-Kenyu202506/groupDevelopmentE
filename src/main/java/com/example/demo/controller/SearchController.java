package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService service;//サービスクラスをもってきてる
	@GetMapping("/search")
    public String search(
        @RequestParam(required=false) Integer id,
        @RequestParam(required=false) String name,
        @RequestParam(required=false) Integer minAge,
        @RequestParam(required=false) Integer maxAge,
        @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startL,
        @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startR,
        @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endL,
        @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endR,
        Model model) {

        List<Search> results = service.search(id, name, minAge, maxAge, startL, startR, endL, endR);

        model.addAttribute("search", results);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("minAge", minAge);
        model.addAttribute("maxAge", maxAge);
        model.addAttribute("startL", startL);
        model.addAttribute("startR", startR);
        model.addAttribute("endL", endL);
        model.addAttribute("endR", endR);
        return "search";
	}
	
	@PostMapping("/delete")
    public String delete(@RequestParam(name="ids", required=false) List<Long> ids) {
        service.deleteByIds(ids);
        return "redirect:/search";
    }
	
}
