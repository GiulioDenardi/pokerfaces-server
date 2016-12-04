package com.pokerfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokerfaces.controller.news.News;
import com.pokerfaces.repository.news.NewsRepository;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsRepository newsRepository;

	@PostMapping
	public ResponseEntity<Void> createNews (@RequestBody News news) {
		newsRepository.save(news);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Void> invalidateNews (@RequestBody News news) {
		news.setActive(Boolean.FALSE);
		newsRepository.save(news);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<News>> list (Pageable pageable) {
		return ResponseEntity.ok(newsRepository.findAll(pageable));
	}
}
