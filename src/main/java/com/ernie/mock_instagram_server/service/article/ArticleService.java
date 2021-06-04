package com.ernie.mock_instagram_server.service.article;

import com.ernie.mock_instagram_server.dto.model.article.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAllArticles();

    ArticleDto findArticleById(int articleId);

    ArticleDto createArticle(ArticleDto articleDto);

    ArticleDto updateArticle(ArticleDto articleDto);

    Boolean deleteArticle(int articleId);
}
