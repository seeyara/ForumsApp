package org.assignment.db;

import java.util.Map;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

/**
 * Contains methods to work on notification related data in redis
 * 
 */
@Service
public class RedisAdaptor {

	Jedis jedis = new Jedis();
	
	public Map<String, String> getKeyFromRedis(String key) {
		Map<String, String> response = jedis.hgetAll(key);
		return response;
	}
	
	public boolean setKeyInRedis(String key, Map<String, String> data) {
		String response = jedis.hmset(key, data);
		if(response.equals("OK"))
			return true;
		else 
			return false;
	}
}
