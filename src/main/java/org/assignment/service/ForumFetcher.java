package org.assignment.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

import org.assignment.constants.Constants;
import org.assignment.db.DBadaptor;
import org.assignment.db.RedisAdaptor;
import org.assignment.util.JsonReader;

@Component
public class ForumFetcher extends Constants {

	@Autowired
	private RedisAdaptor redisAdaptor;

	@Autowired
	private DBadaptor dbAdaptor;

	public Object fetchData(int id, ENTITY_TYPES entityType) {
		String key = null;
		switch (entityType) {
		case POST:
			key = REDIS_POST_KEY + id;
			break;
		case USER:
			key = REDIS_USER_KEY + id;
			break;
		case ARTICLE:
			key = REDIS_ARTICLE_KEY + id;
			break;
		}

		Map<String, String> response = redisAdaptor.getKeyFromRedis(key);

		if (response.size() == 0) {
			try {
				if (createRedisData(entityType, key, id)) {
					response = redisAdaptor.getKeyFromRedis(key);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;

	}

	private boolean createRedisData(ENTITY_TYPES entityType, String key, int id) throws SQLException {
		Map<String, String> data = new HashMap<>();
		Object dataObj = null;
		switch (entityType) {
		case POST:
			dataObj = dbAdaptor.getPostsData(id);
			break;
		case USER:
			dataObj = dbAdaptor.getUsersData(id);
			break;
		case ARTICLE:
			dataObj = dbAdaptor.getArticlesData(id);
			break;
		}
		data = JsonReader.mapper.convertValue(dataObj, new TypeReference<Map<String, String>>() {
		});

		if (redisAdaptor.setKeyInRedis(key, data)) {
			return true;
		} else
			return false;
	}

}
