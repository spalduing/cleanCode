package com.spalduing.cache.springredisbetroulet;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class SpringRedisBetRouletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisBetRouletApplication.class, args);
	}

}
