package com.pokerfaces.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokerfaces.controller.news.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}