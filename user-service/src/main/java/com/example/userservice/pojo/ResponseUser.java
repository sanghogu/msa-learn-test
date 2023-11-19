package com.example.userservice.pojo;

import com.example.userservice.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUser {

    private String email;
    private String name;

    public ResponseUser(RequestUser user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public ResponseUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
