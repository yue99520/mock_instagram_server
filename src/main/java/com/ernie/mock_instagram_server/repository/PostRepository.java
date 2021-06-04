package com.ernie.mock_instagram_server.repository;

import com.ernie.mock_instagram_server.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    //
}
