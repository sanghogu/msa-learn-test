package com.example.userservice.pojo;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class RequestUser {


    @NotNull(message = "이메일 필수")
    @Email(message = "이메일 형싟이 아님")
    private String email;

    @NotNull(message = "이름 필수로 적어야함")
    @Size(min = 2, message = "이름 최소 2글자이")
    private String name;

    @NotNull(message = "비밀번호 입력 필수임")
    @Size(min = 2, message = "비밀번호 최소 2글자")
    private String password;


}
