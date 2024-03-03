package com.MavenOrderCraft.MavenOrderCraft.Service;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.CardInfo;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.CardInfoMapper;
import com.MavenOrderCraft.MavenOrderCraft.Repository.CardInfoRepository;
import com.MavenOrderCraft.MavenOrderCraft.Request.CardInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CardInfoService {

    private final CardInfoRepository cardInfoRepository;

    @Autowired
    public CardInfoService(CardInfoRepository cardInfoRepository) {
        this.cardInfoRepository = cardInfoRepository;
    }

    @Transactional
    public CardInfo createCardInfo(CardInfoRequest cardInfoRequest) {
        CardInfo existingCard = cardInfoRepository.findByCardNumberAndCardHolderNameAndCvv2(
                cardInfoRequest.getCardNumber(),
                cardInfoRequest.getCardHolderName(),
                cardInfoRequest.getCvv2()
        );

        if (existingCard != null) {
            return existingCard;
        } else {
            CardInfo cardInfo = CardInfoMapper.INSTANCE.requestToEntity(cardInfoRequest);
            return cardInfoRepository.save(cardInfo);
        }
    }

}
