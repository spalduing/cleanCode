package com.spalduing.cache.redisbettingroulette;

import com.spalduing.cache.redisbettingroulette.gambler.Gambler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisBettingRouletteApplication {

	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, Gambler> redisTemplate(){
		RedisTemplate<String, Gambler> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisBettingRouletteApplication.class, args);
	}

}
