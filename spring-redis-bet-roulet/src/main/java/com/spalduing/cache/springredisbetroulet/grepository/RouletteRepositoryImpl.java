package com.spalduing.cache.springredisbetroulet.grepository;

import com.spalduing.cache.springredisbetroulet.gclass.Roulette;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
@Repository
public class RouletteRepositoryImpl implements RouletteRepository {

    private static final String ROULETTE = "ROULETTE";
    private RedisTemplate<String, ?> redisTemplate;
    private HashOperations hashOperations;
    private final int upperNumberBound = 36;
    private final int upperColorBound = 1;

    public RouletteRepositoryImpl(RedisTemplate<String, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Roulette roulette) {
        hashOperations.put(ROULETTE, UUID.randomUUID().toString(), roulette);
    }

    @Override
    public Roulette findById(Roulette roulette) {
        String id = roulette.getId();
        return (Roulette)hashOperations.get(ROULETTE,id);
    }

    @Override
    public Map<String, Roulette> findAll() {
        return hashOperations.entries(ROULETTE);
    }

    @Override
    public String openRoulette(Roulette roulette) {
        roulette.setBetState("open");
        return roulette.getBetState();
    }

    @Override
    public String closeRoulette(Roulette roulette) {
        roulette.setBetState("close");
        return roulette.getBetState();
    }

    @Override
    public String playBet(String betColor, int betNumber, int betAmount) {
        Random rand = new Random();
        String[] rouletteColors = {"red","black"};
        int colorPosition = rand.nextInt(upperColorBound);
        int winnerNumber = rand.nextInt(upperNumberBound);
        String winnerColor = rouletteColors[colorPosition];
        if(winnerNumber == betNumber && winnerColor == betColor){
            return "YOU WIN";
        }

        return "YOU LOSE";
    }
}
