package com.ernie.mock_instagram_server.controller.api.request.post;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;


public class CreatePostRequestBody {

    @NotEmpty
    private String content;

    @URL
    @NotEmpty
    private String imageUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
