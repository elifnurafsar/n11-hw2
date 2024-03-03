package com.MavenOrderCraft.MavenOrderCraft.Repository;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
