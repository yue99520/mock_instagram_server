package com.ernie.mock_instagram_server.service.auth;

import com.ernie.mock_instagram_server.entity.User;
import com.ernie.mock_instagram_server.exception.NotAuthenticationException;
import org.springframework.lang.Nullable;

public interface SecurityContext {

    /**
     * 請先確定 Context 為已登入狀態，不然會返回 null
     * */
    User getCurrentUser();

    boolean isAuthenticated();

    boolean isAnonymous();
}
