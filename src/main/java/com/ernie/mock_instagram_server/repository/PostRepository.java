package com.ernie.mock_instagram_server.repository;

import com.ernie.mock_instagram_server.entity.Post;
import com.ernie.mock_instagram_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findByIdAndUserId(int id, int userId);

    List<Post> findAllByUser(User user);

    List<Post> findAllByCreatedAtBeforeOrderByCreatedAtDesc(Date beforeDate);
}
