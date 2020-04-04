package org.assignment.constants;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Posts {
//Posts table with fields id, user_id, text, image, created_at, updated_at
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("user_id")
	private Integer userId;
	@JsonProperty("text")
	private String text;
	@JsonProperty("image")
	private String image;
	@JsonProperty("created_at")
	private String created_at;
	@JsonProperty("updated_at")
	private String updated_at;

}
