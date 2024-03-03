package com.MavenOrderCraft.MavenOrderCraft.Repository;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo, UUID> {
    CardInfo findByCardNumberAndCardHolderNameAndCvv2(String cardNumber, String cardHolderName, int cvv2);
}
