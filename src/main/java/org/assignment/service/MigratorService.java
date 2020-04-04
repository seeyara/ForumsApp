package org.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.assignment.constants.Article;
import org.assignment.constants.Posts;
import org.assignment.constants.Users;
import org.assignment.db.DBadaptor;
import org.assignment.util.JsonReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MigratorService {

	@Autowired
	private JsonReader jsonReader;

	@Autowired
	private DBadaptor dbAdaptor;

	public void runMigration(String[] args) {
		createData("users.json");
		createData("articles.json");
		createData("posts.json");
	}

	private void createData(String fileName) {
		try {
			if ("articles.json".equals(fileName)) {
				List<Article> articlesList = jsonReader.getArticleObject(fileName);
				dbAdaptor.saveArticles(articlesList);
			} else if ("posts.json".equals(fileName)) {
				List<Posts> postsList = jsonReader.getPostsObject(fileName);
				dbAdaptor.savePosts(postsList);
			} else if ("users.json".equals(fileName)) {
				List<Users> usersList = jsonReader.getUsersObject(fileName);
				dbAdaptor.saveUsers(usersList);
			}
		} catch (Exception e) {
			log.error("Exception in processing {}", fileName, e);
		}
	}

}