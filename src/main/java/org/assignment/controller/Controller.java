package org.assignment.controller;

import org.assignment.constants.Constants;
import org.assignment.service.ForumFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller extends Constants{

	@Autowired
	private ForumFetcher forumFetcherService;

	@RequestMapping(value = "/articles/{articleId}", method = RequestMethod.GET)
	public ResponseEntity<?> fetchArticleData(@PathVariable String articleId)
			throws Exception {
		ResponseEntity<?> entity = new ResponseEntity<>(forumFetcherService.fetchData(Integer.parseInt(articleId), ENTITY_TYPES.ARTICLE),
				HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> fetchUserData(@PathVariable String userId)
			throws Exception {
		ResponseEntity<?> entity = new ResponseEntity<>(forumFetcherService.fetchData(Integer.parseInt(userId), ENTITY_TYPES.USER),
				HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "/posts/{postsId}", method = RequestMethod.GET)
	public ResponseEntity<?> fetchPostData( @PathVariable String postsId)
			throws Exception {
		ResponseEntity<?> entity = new ResponseEntity<>(forumFetcherService.fetchData(Integer.parseInt(postsId), ENTITY_TYPES.POST),
				HttpStatus.OK);
		return entity;
	}

}
