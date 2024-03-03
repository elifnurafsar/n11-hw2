package com.MavenOrderCraft.MavenOrderCraft.Service;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.UserMapper;
import com.MavenOrderCraft.MavenOrderCraft.Repository.UserRepository;
import com.MavenOrderCraft.MavenOrderCraft.Request.UserRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserMapper.INSTANCE.requestToEntity(userRequest);
        user.setCreatedAt(LocalDateTime.of(2023, 6, 6, 12, 58, 45));
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.entityToResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.entityListToResponseList(users);
    }

    public List<UserResponse> getUserByName(String name) {
        List<User> users = userRepository.getByNameContaining(name);
        return UserMapper.INSTANCE.entityListToResponseList(users);
    }
}

