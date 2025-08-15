package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Staff;
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
		
		// サーバー側のバリデーション
	    StringBuilder errorMessages = new StringBuilder();

	    // 年齢チェック
	    if (minAge != null && maxAge != null && minAge > maxAge) {
	        errorMessages.append("左側の年齢は右側の年齢を上回らないでください。\n");
	    }

	    // 開始日チェック
	    if (startL != null && startR != null && startL.isAfter(startR)) {
	        errorMessages.append("開始日の左側は右側以前の日付にしてください。\n");
	    }

	    // 終了日チェック
	    if (endL != null && endR != null && endL.isAfter(endR)) {
	        errorMessages.append("終了日の左側は右側以前の日付にしてください。\n");
	    }

	    if (errorMessages.length() > 0) {
	        // エラー時の処理
	        model.addAttribute("error", errorMessages.toString());
	        model.addAttribute("search", List.of()); // 検索結果は空
	        model.addAttribute("count", 0);

	        // 入力値を戻す（画面再表示時に保持）
	        model.addAttribute("id", id);
	        model.addAttribute("name", name);
	        model.addAttribute("minAge", minAge);
	        model.addAttribute("maxAge", maxAge);
	        model.addAttribute("startL", startL);
	        model.addAttribute("startR", startR);
	        model.addAttribute("endL", endL);
	        model.addAttribute("endR", endR);

	        return "search"; // エラーありでそのまま画面再表示
	    }

        List<Staff> results = service.search(id, name, minAge, maxAge, startL, startR, endL, endR);

        model.addAttribute("search", results);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("minAge", minAge);
        model.addAttribute("maxAge", maxAge);
        model.addAttribute("startL", startL);
        model.addAttribute("startR", startR);
        model.addAttribute("endL", endL);
        model.addAttribute("endR", endR);
        model.addAttribute("count", results.size());//件数追加

        return "search";
    }
	
}
