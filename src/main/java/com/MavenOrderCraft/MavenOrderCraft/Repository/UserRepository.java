package com.MavenOrderCraft.MavenOrderCraft.Repository;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> getByNameContaining(String name);
}

