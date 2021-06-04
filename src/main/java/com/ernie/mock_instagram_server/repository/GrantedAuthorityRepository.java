package com.ernie.mock_instagram_server.repository;

import com.ernie.mock_instagram_server.entity.GrantedAuthorityEntity;
import com.ernie.mock_instagram_server.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrantedAuthorityRepository extends CrudRepository<GrantedAuthorityEntity, Integer> {

    List<GrantedAuthority> getGrantedAuthorityEntitiesByUser(User user);

    boolean existsByUserAndAuthority(User user, String authority);

    void deleteByUserAndAuthority(User user, String authority);
}
