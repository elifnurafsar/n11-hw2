package com.MavenOrderCraft.MavenOrderCraft.Entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_info")
public class CardInfo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @NotBlank(message = "Card number cannot be blank")
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @NotBlank(message = "Card holder name cannot be blank")
    @Column(name = "card_holder_name", nullable = false)
    private String cardHolderName;

    @NotNull(message = "CVV2 cannot be null")
    @Column(name = "cvv2", nullable = false)
    private int cvv2;
}

