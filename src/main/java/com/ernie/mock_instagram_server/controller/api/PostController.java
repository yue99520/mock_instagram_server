package com.ernie.mock_instagram_server.controller.api;


import com.ernie.mock_instagram_server.dto.model.post.PostDto;
import com.ernie.mock_instagram_server.service.post.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    @ResponseBody
    public Map<String, List<PostDto>> getAll() {
        List<PostDto> postDtos = postService.getAllPost();
        Map<String, List<PostDto>> response = new HashMap<>();
        response.put("posts", postDtos);
        return response;
    }
}
