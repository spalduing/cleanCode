package com.spalduing.cache.springredisbetroulet.grepository;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class GamblerRepositoryImpl implements GamblerRepository {

    private RedisTemplate<String, Gambler> redisTemplate;
    private HashOperations hashOperations;

    public GamblerRepositoryImpl(RedisTemplate<String, Gambler> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Gambler gambler) {
        hashOperations.put("GAMBLER", gambler.getId(), gambler);
    }

    @Override
    public Map<String, Gambler> findAll() {
        return hashOperations.entries("GAMBLER");
    }

    @Override
    public Gambler findById(Gambler gambler) {
        String id = gambler.getId();
        return (Gambler)hashOperations.get("GAMBLER", id);
    }

    @Override
    public void update(Gambler gambler) {
        save(gambler);
    }

    @Override
    public void delete(Gambler gambler) {
        String id = gambler.getId();
        hashOperations.delete("GAMBLER", id);
    }
}
