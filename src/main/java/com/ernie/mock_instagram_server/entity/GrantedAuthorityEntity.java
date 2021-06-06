package com.ernie.mock_instagram_server.entity;


import com.ernie.mock_instagram_server.service.auth.Authority;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;


@Entity
public class GrantedAuthorityEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String authority;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User user;

    @Override
    public String getAuthority() {
        return authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority.name();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
