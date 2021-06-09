package com.ernie.mock_instagram_server.service.post;

import com.ernie.mock_instagram_server.dto.model.post.PostDto;
import com.ernie.mock_instagram_server.exception.UserIdNotFoundException;

import java.util.Date;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto) throws UserIdNotFoundException;

    List<PostDto> getAllPostsByUser(int userId) throws UserIdNotFoundException;

    List<PostDto> getPostsByTimestamp(Date date, int size);

    PostDto getPostById(int id);

    PostDto updatePost(PostDto postDto);

    void deletePost(int userId, int id);
}
