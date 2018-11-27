package org.wingsico.bookstore.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.wingsico.bookstore.data.UserData;

/**
 * 进行返回信息，包括状态码，错误或正确信息，以及user数据
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStatus {
    private int status;
    private String message;
    private UserData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
