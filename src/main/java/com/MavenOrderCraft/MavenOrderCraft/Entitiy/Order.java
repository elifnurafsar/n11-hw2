package com.MavenOrderCraft.MavenOrderCraft.Entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @NotNull(message = "Item cannot be null")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "item_id", nullable = false)
    private UUID item_id;

    @NotNull(message = "Quantity cannot be null")
    @Column(name = "quantity", nullable = false)
    private int quantity;

}
