package com.example.TodoApp.repository;

import com.example.TodoApp.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepo {
    private JdbcTemplate jdbcTemplate;

    public ArticleRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveArticle(Article article){
        String sql = "INSERT INTO articles (TITLE, CONTENT, USERID) VALUES (?,?,?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getUserId());
    }
}
