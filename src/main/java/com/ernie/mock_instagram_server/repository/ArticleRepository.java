package com.ernie.mock_instagram_server.repository;

import com.ernie.mock_instagram_server.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
