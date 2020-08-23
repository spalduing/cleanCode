package com.spalduing.cache.springredisbetroulet.grepository;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;
import com.spalduing.cache.springredisbetroulet.gclass.Gambleri;
import com.spalduing.cache.springredisbetroulet.gclass.Roulette;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class RouletteRepositoryImpl implements RouletteRepository {

    private static final String ROULETTE = "ROULETTE";
    private static final String GAMBLER = "GAMBLER";
    private RedisTemplate<String, ?> redisTemplate;
    private HashOperations hashOperations;
    private final int upperNumberBound = 36;
    private final int upperColorBound = 1;
    private final double betAmountCap = 10000.00;
    final private GamblerRepository gamblerRepository;

    public RouletteRepositoryImpl(RedisTemplate<String, ?> redisTemplate, GamblerRepository gamblerRepository) {
        this.redisTemplate = redisTemplate;
        this.gamblerRepository = gamblerRepository;
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
    public Map<String, Gambler> getGamblers(Roulette roulette) {
        Map<String, Gambler> gamblers = gamblerRepository.findAll();
        gamblers.values().removeIf(value -> value.getRouletteId() != roulette.getId());
        gamblers.values().removeIf(value -> value.getBetAmount() > betAmountCap);
        return gamblers;
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
        Gambler tempGambler = new Gambler("","","",0,0.0,"");
        Map<String,String> gamblersAndBets = new HashMap<>();
        Roulette tempRoulette = findById(roulette);
        tempRoulette.setBetState("close");
        hashOperations.put(ROULETTE, tempRoulette.getId(), tempRoulette);
        Map<String, Gambler> rouletteGamblers = getGamblers(roulette);
        Iterator it = rouletteGamblers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            tempGambler = ((Gambler)pair.getValue());
            betResult = playBet(tempGambler.getColor(),tempGambler.getBet(),tempGambler.getBetAmount());
            gamblersAndBets.put(tempGambler.getName(),betResult);
        }
        return gamblersAndBets;
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
