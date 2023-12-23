package com.example.TodoApp.service;

import com.example.TodoApp.model.Article;
import com.example.TodoApp.repository.ArticleRepo;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepo articleRepo;

    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public void add(Article article){
        articleRepo.saveArticle(article);
    }
}
