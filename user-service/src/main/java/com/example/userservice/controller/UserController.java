package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.pojo.RequestUser;
import com.example.userservice.pojo.ResponseUser;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("/user-service")
@Controller
public class UserController {

    private final UserService userService;
    private final String greetingMessage;

    public UserController(UserService userService, @Value("${greeting.welcome}") String greetingMessage) {
        this.userService = userService;
        this.greetingMessage = greetingMessage;
    }

    @GetMapping("/health")
    @ResponseBody
    public String index(){

        return "OK";
    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {


        User user = userService.createUser(requestUser);

        ResponseUser responseUser = new ResponseUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);

    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(){
        return greetingMessage;
    }

    @GetMapping("/users/{email}")
    @ResponseBody
    public ResponseEntity<ResponseUser> getUsers(@PathVariable String email) {

        User user = userService.getUserByEmail(email);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        ResponseUser responseUser = new ResponseUser(user);

        return ResponseEntity.ok(responseUser);
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<List<ResponseUser>> getUsers(){

        Iterable<User> iterable = userService.getUsers();

        List<ResponseUser> responseUsers = StreamSupport
                .stream(iterable.spliterator(), false)
                .map(ResponseUser::new)
                .toList();

        return ResponseEntity.ok(responseUsers);
    }

}
