package com.spalduing.cache.springredisbetroulet.repositories;

import com.spalduing.cache.springredisbetroulet.classes.Bet;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
@Repository
public class BetRepositoryImpl implements BetRepository {
    private static final String BET = "BET";
    private RedisTemplate<String, ?> redisTemplate;
    private HashOperations hashOperations;

    public BetRepositoryImpl(RedisTemplate<String, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Bet bet) {
        bet.setId(UUID.randomUUID().toString());
        hashOperations.put(BET,bet.getId(), bet);
    }

    @Override
    public Map<String, Bet> findAll() {
        return hashOperations.entries(BET);
    }

    @Override
    public Bet findById(Bet bet) {
        String id = bet.getId();
        return (Bet) hashOperations.get(BET, id);
    }
}
