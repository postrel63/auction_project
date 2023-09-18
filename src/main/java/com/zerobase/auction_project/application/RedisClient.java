package com.zerobase.auction_project.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.auction_project.domain.Bid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisClient {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    public Bid getBid(Long auctionId, Long userId){
        return get(getKey(auctionId, userId), Bid.class);
    }

    private <T> T get(String key, Class<T> classType){
        String redisValue = (String) redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(redisValue)){
            return null;
        }else{
            try{
                return mapper.readValue(redisValue,classType);
            }catch (JsonProcessingException e){
                log.error("Parsing error", e);
                throw new RuntimeException(e);
            }
        }
    }

    public void putBid(Long auctionId, Long userId, Bid bid){
        put(getKey(auctionId, userId), bid);
    }

    private void put(String key, Bid bid){
        try {
            redisTemplate.opsForValue().set(key,mapper.writeValueAsString(bid));
        }catch (JsonProcessingException e){
            log.error("JSON conversion error", e);
            throw new RuntimeException(e);
        }
    }

    private String getKey(Long auctionId, Long userId) {
        return "auction:" + auctionId + ":user:" + userId;
    }
}