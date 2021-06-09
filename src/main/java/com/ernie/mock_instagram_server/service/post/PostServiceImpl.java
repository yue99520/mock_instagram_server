package com.ernie.mock_instagram_server.service.post;

import com.ernie.mock_instagram_server.dto.mapper.post.PostMapper;
import com.ernie.mock_instagram_server.dto.model.post.PostDto;
import com.ernie.mock_instagram_server.entity.Post;
import com.ernie.mock_instagram_server.entity.User;
import com.ernie.mock_instagram_server.exception.UserIdNotFoundException;
import com.ernie.mock_instagram_server.repository.PostRepository;
import com.ernie.mock_instagram_server.repository.UserRepository;
import com.ernie.mock_instagram_server.service.auth.SecurityContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> getAllPostsByUser(int userId) throws UserIdNotFoundException {
        User user = attemptGetUser(userId);
        List<Post> posts = postRepository.findAllByUser(user);
        return mapToPostDtos(posts);
    }

    @Override
    public List<PostDto> getPostsByTimestamp(Date date, int size) {
        List<Post> posts = postRepository.findAllByCreatedAtBeforeOrderByCreatedAtDesc(date);
        if (posts.size() <= size) {
            return mapToPostDtos(posts);
        } else {
            return mapToPostDtos(posts.subList(0, size));
        }
    }

    @Override
    public PostDto getPostById(int id) {
        Post post = attemptGetPostById(id);
        return PostMapper.toPostDto(post);
    }

    @Override
    public PostDto createPost(PostDto postDto) throws UserIdNotFoundException {
        Date now = new Date();
        Post post = new Post();
        User user = attemptGetUser(postDto.getUserId());

        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        post.setUser(user);
        post.setUpdatedAt(now);
        post.setCreatedAt(now);

        return PostMapper.toPostDto(postRepository.save(post));
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        Post post = attemptGetPostByIdAndUserId(postDto.getId(), postDto.getUserId());

        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        post.setUpdatedAt(new Date());
        Post savedPost = postRepository.save(post);

        return PostMapper.toPostDto(savedPost);
    }

    @Override
    public void deletePost(int userId, int id) {
        Post post = attemptGetPostByIdAndUserId(id, userId);

        postRepository.delete(post);
    }

    private Post attemptGetPostByIdAndUserId(int id, int userId) {
        Optional<Post> optionalPost = postRepository.findByIdAndUserId(id, userId);
        if (optionalPost.isEmpty()) {
            throw new EntityNotFoundException(String.format("Post id not found: %d", id));
        }
        return optionalPost.get();
    }

    private Post attemptGetPostById(int id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new EntityNotFoundException(String.format("Post id not found: %d", id));
        }
        return optionalPost.get();
    }

    private User attemptGetUser(int id) throws UserIdNotFoundException {
        Optional<User> optionalUser = userRepository.getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new UserIdNotFoundException(id);
        }
        return optionalUser.get();
    }

    private List<PostDto> mapToPostDtos(List<Post> posts) {
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(PostMapper.toPostDto(post));
        }
        return postDtos;
    }
}
