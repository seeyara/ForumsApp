package org.assignment.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.assignment.constants.Article;
import org.assignment.constants.Posts;
import org.assignment.constants.Users;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonReader {
	public static final ObjectMapper mapper = new ObjectMapper();
	
	public List<Article> getArticleObject(String filename) {
		// JSON parser object to parse read file
		List<Article> articleList = new ArrayList<>();
		try (FileReader reader = new FileReader(filename)) {
			// Read JSON file
			JsonNode obj = mapper.readTree(reader);
			
			for (JsonNode attrCode : obj) {
				articleList.add(mapper.convertValue(attrCode, Article.class));
			}
			
//			System.out.println(articleList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleList;
	}
	
	public List<Posts> getPostsObject(String filename) {
		// JSON parser object to parse read file
		List<Posts> postsList = new ArrayList<>();
		try (FileReader reader = new FileReader(filename)) {
			// Read JSON file
			JsonNode obj = mapper.readTree(reader);
			
			for (JsonNode attrCode : obj) {
				postsList.add(mapper.convertValue(attrCode, Posts.class));
			}
			
//			System.out.println(articleList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return postsList;
	}

	public List<Users> getUsersObject(String filename) {
		// JSON parser object to parse read file
		List<Users> usersList = new ArrayList<>();
		try (FileReader reader = new FileReader(filename)) {
			// Read JSON file
			JsonNode obj = mapper.readTree(reader);

			for (JsonNode attrCode : obj) {
				usersList.add(mapper.convertValue(attrCode, Users.class));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return usersList;
	}
}
