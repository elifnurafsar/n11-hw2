package com.MavenOrderCraft.MavenOrderCraft.Repository;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}
