package com.ernie.mock_instagram_server.controller.api.response.post;

import com.ernie.mock_instagram_server.dto.model.post.PostDto;

public class PostResponseBody {

    private PostDto post;

    public PostResponseBody(PostDto post) {
        this.post = post;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }
}
