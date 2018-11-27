package org.wingsico.bookstore.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.wingsico.bookstore.domain.User;

/**
 * 进行返回的user数据
 *
 */
public class UserData {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
