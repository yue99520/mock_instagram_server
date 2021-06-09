package com.ernie.mock_instagram_server.controller.api;


import com.ernie.mock_instagram_server.controller.api.request.post.CreatePostRequestBody;
import com.ernie.mock_instagram_server.controller.api.request.post.UpdatePostRequestBody;
import com.ernie.mock_instagram_server.controller.api.response.post.PostResponseBody;
import com.ernie.mock_instagram_server.controller.api.response.post.PostsResponseBody;
import com.ernie.mock_instagram_server.dto.model.post.PostDto;
import com.ernie.mock_instagram_server.exception.UserIdNotFoundException;
import com.ernie.mock_instagram_server.service.auth.SecurityContext;
import com.ernie.mock_instagram_server.service.post.PostService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping(path = "/posts")
public class PostController {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private final PostService postService;

    private final SecurityContext securityContext;

    public PostController(PostService postService, SecurityContext securityContext) {
        this.postService = postService;
        this.securityContext = securityContext;
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<PostsResponseBody> getPosts(
            @RequestParam int size,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATETIME_FORMAT) Date date
    ) {
        if (date == null) {
            date = new Date();
        }
        List<PostDto> postDtos = postService.getPostsByTimestamp(date, size);
        return new ResponseEntity<>(new PostsResponseBody(postDtos), HttpStatus.OK);
    }

    @GetMapping("/{id}/")
    @ResponseBody
    public ResponseEntity<PostResponseBody> getPost(
            @PathVariable int id
    ) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(new PostResponseBody(postDto), HttpStatus.OK);
    }

    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostResponseBody> create(
            @RequestBody CreatePostRequestBody body) throws UserIdNotFoundException {

        int userId = securityContext.getCurrentUser().getId();
        PostDto postDto = new PostDto();
        postDto.setUserId(userId);
        postDto.setContent(body.getContent());
        postDto.setImageUrl(body.getImageUrl());
        postDto = postService.createPost(postDto);
        return new ResponseEntity<>(new PostResponseBody(postDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostResponseBody> update(
            @PathVariable int id,
            @RequestBody UpdatePostRequestBody body) {

        int userId = securityContext.getCurrentUser().getId();
        PostDto postDto = new PostDto();
        postDto.setId(id);
        postDto.setUserId(userId);
        postDto.setContent(body.getContent());
        postDto.setImageUrl(body.getImage_url());
        postDto = postService.updatePost(postDto);
        return new ResponseEntity<>(new PostResponseBody(postDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int userId = securityContext.getCurrentUser().getId();
        postService.deletePost(userId, id);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
