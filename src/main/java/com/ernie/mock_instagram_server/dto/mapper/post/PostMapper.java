package com.ernie.mock_instagram_server.dto.mapper.post;

import com.ernie.mock_instagram_server.dto.model.post.PostDto;
import com.ernie.mock_instagram_server.entity.Post;

public class PostMapper {

    public static PostDto toPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setImage_url(post.getImage_url());
        return postDto;
    }
}
