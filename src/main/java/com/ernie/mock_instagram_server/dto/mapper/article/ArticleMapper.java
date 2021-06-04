package com.ernie.mock_instagram_server.dto.mapper.article;

import com.ernie.mock_instagram_server.dto.model.article.ArticleDto;
import com.ernie.mock_instagram_server.entity.Article;

public class ArticleMapper {
    public static ArticleDto toArticleDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        return articleDto;
    }
}
