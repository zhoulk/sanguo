package com.mud.context;

import com.mud.mapper.UserAuth;

/**
 * Created by leeesven on 17/8/22.
 */
public class UserContext implements AutoCloseable {

    static final ThreadLocal<UserAuth> current = new ThreadLocal<>();

    public UserContext(UserAuth userAuth){
        current.set(userAuth);
    }

    public static UserAuth getCurrentUserAuth() {
        return current.get();
    }

    @Override
    public void close() throws Exception {
        current.remove();
    }
}
