package com.ernie.mock_instagram_server.controller.api.request.post;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

public class UpdatePostRequestBody {

    @NotEmpty
    private String content;

    @URL
    @NotEmpty
    private String image_url;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
