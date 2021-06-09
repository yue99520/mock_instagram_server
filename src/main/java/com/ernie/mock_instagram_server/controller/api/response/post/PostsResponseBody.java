package com.ernie.mock_instagram_server.controller.api.response.post;

import com.ernie.mock_instagram_server.dto.model.post.PostDto;

import java.util.Date;
import java.util.List;

public class PostsResponseBody {

    List<PostDto> posts;

    public PostsResponseBody(List<PostDto> posts) {
        this.posts = posts;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
}
