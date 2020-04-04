package org.assignment.constants;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
//Article table with fields id, user_id, article_title, article_text, article_image, created_at, updated_at
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("article_title")
	private String article_title;
	@JsonProperty("article_text")
	private String article_text;
	@JsonProperty("article_image")
	private String article_image;
	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("updated_at")
	private String updated_at;

	@JsonProperty("user_id")
	private Integer userId;

}
