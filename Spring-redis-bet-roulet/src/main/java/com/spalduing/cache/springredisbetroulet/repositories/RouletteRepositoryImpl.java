package com.spalduing.cache.springredisbetroulet.repositories;

import com.spalduing.cache.springredisbetroulet.classes.Bet;
import com.spalduing.cache.springredisbetroulet.classes.Roulette;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
@Repository
public class RouletteRepositoryImpl implements RouletteRepository {
    private static final String ROULETTE = "ROULETTE";
    private RedisTemplate<String, ?> redisTemplate;
    private HashOperations hashOperations;
    private final int upperNumberBound = 36;
    private final int upperColorBound = 1;
    private final double betAmountCap = 10000.00;
    final private BetRepository betRepository;

    public RouletteRepositoryImpl(RedisTemplate<String, ?> redisTemplate, BetRepository betRepository) {
        this.redisTemplate = redisTemplate;
        this.betRepository = betRepository;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Roulette roulette) {
        roulette.setId(UUID.randomUUID().toString());
        hashOperations.put(ROULETTE, roulette.getId() , roulette);
    }

    @Override
    public Roulette findById(Roulette roulette) {
        String id = roulette.getId();
        return (Roulette)hashOperations.get(ROULETTE,id);
    }

    @Override
    public Map<String, Bet> getBets(Roulette roulette) {
        Map<String, Bet> bets = betRepository.findAll();
        bets.values().removeIf(value -> !value.getRouletteId().equals(roulette.getId()));
        bets.values().removeIf(value -> value.getBetAmount() > betAmountCap);
        return bets;
    }

    @Override
    public Map<String, Roulette> findAll() {
        return hashOperations.entries(ROULETTE);
    }

    @Override
    public String openRoulette(Roulette roulette) {
        Roulette tempRoulette = findById(roulette);
        tempRoulette.setBetState("open");
        hashOperations.put(ROULETTE, tempRoulette.getId(), tempRoulette);
        return tempRoulette.getBetState();
    }

    @Override
    public Map<String, String> closeRoulette(Roulette roulette) {
        String betResult = "";
        Bet tempBet = new Bet("", "", "","",0,0.0);
        Map<String,String> betsAndResults = new HashMap<>();
        Roulette tempRoulette = findById(roulette);
        tempRoulette.setBetState("close");
        hashOperations.put(ROULETTE, tempRoulette.getId(), tempRoulette);
        Map<String, Bet> rouletteBets = getBets(tempRoulette);
        if(rouletteBets.isEmpty()){
            betsAndResults.put("I have no bets","buddy");
            return betsAndResults;
        }
        Iterator it = rouletteBets.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            tempBet = ((Bet)pair.getValue());
            betResult = playBet(tempBet.getBetColor(),tempBet.getBetNumber(),tempBet.getBetAmount());
            betsAndResults.put(tempBet.getId(),betResult);
        }
        return betsAndResults;
    }

    @Override
    public String playBet(String betColor, int betNumber, double betAmount) {
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
