package com.ernie.mock_instagram_server.service.post;

import com.ernie.mock_instagram_server.dto.mapper.post.PostMapper;
import com.ernie.mock_instagram_server.dto.model.post.PostDto;
import com.ernie.mock_instagram_server.entity.Post;
import com.ernie.mock_instagram_server.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setImage_url(postDto.getImage_url());
        Post savedPost = postRepository.save(post);
        return PostMapper.toPostDto(savedPost);
    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public PostDto getPostById(int id) {
        return null;
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public PostDto deletePost(int id) {
        return null;
    }
}
