package com.MavenOrderCraft.MavenOrderCraft.Controller;

import com.MavenOrderCraft.MavenOrderCraft.Request.UserRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.UserResponse;
import com.MavenOrderCraft.MavenOrderCraft.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Users")
public class UserController implements BaseController<UserRequest>{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<UserResponse> getById(@RequestParam("id") UUID id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<UserResponse>> getByName(@RequestParam("name") String name) {
        List<UserResponse> userResponseList = userService.getUserByName(name);
        return ResponseEntity.ok(userResponseList);
    }
}
