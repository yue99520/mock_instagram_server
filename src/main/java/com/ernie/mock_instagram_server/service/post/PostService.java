package com.ernie.mock_instagram_server.service.post;

import com.ernie.mock_instagram_server.dto.model.post.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(int id);

    PostDto updatePost(PostDto postDto);

    PostDto deletePost(int id);
}
