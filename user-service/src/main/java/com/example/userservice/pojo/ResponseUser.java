package com.example.userservice.pojo;

import com.example.userservice.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUser {

    private Long id;
    private String email;
    private String name;

    public ResponseUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
