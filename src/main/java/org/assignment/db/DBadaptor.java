package org.assignment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.assignment.config.PFSqlConfig;
import org.assignment.constants.Article;
import org.assignment.constants.Posts;
import org.assignment.constants.Users;

/**
 * Class containing data related operations for notification messages.
 * 
 */
@Service
public class DBadaptor {

	@Autowired
	private PFSqlConfig config;

	private static final String GET_ARTICLE = "select * from articles where id=?";
	private static final String GET_POST = "select * from posts where id=?";
	private static final String GET_USER = "select * from users where id=?";

	public void saveArticles(List<Article> articlesList) throws SQLException {
		String SAVE_ARTICLE = "insert into articles (id, user_id, article_title, article_text, article_image, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = config.dataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SAVE_ARTICLE);) {

			for (Article article : articlesList) {
				pstmt.setInt(1, article.getId());
				pstmt.setInt(2, article.getUserId());
				pstmt.setString(3, article.getArticle_title());
				pstmt.setString(4, article.getArticle_text());
				pstmt.setString(5, article.getArticle_image());
				pstmt.setString(6, article.getCreated_at());
				pstmt.setString(7, article.getUpdated_at());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
		System.out.println("Completed creating article data");
	}

	public void savePosts(List<Posts> postsList) throws SQLException {
		String SAVE_POST = "insert into posts (id, user_id, text, image, created_at, updated_at) values (?, ?, ?, ?, ?, ?)";
		;
		try (Connection conn = config.dataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SAVE_POST);) {

			for (Posts posts : postsList) {
				pstmt.setInt(1, posts.getId());
				pstmt.setInt(2, posts.getUserId());
				pstmt.setString(3, posts.getText());
				pstmt.setString(4, posts.getImage());
				pstmt.setString(5, posts.getCreated_at());
				pstmt.setString(6, posts.getUpdated_at());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
		System.out.println("Completed creating posts data");
	}

	public void saveUsers(List<Users> usersList) throws SQLException {
		final String SAVE_USER = "insert into users (id, user_name, email, password, created_at, updated_at) values (?, ?, ?, ?, ?, ?)";
		try (Connection conn = config.dataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SAVE_USER);) {

			for (Users users : usersList) {
				pstmt.setInt(1, users.getId());
				pstmt.setString(2, users.getUserName());
				pstmt.setString(3, users.getEmail());
				pstmt.setString(4, users.getPassword());
				pstmt.setString(5, users.getCreated_at());
				pstmt.setString(6, users.getUpdated_at());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
		System.out.println("Completed creating user data");
	}
	
	public Posts getPostsData(int postId) throws SQLException {
		//id, user_id, text, image, created_at, updated_at
		Posts post = new Posts();
		try (Connection conn = config.dataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_POST);) {
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				post.setId(postId);
				post.setUserId(rs.getInt("user_id"));
				post.setText(rs.getString("text"));
				post.setImage(rs.getString("image"));
				post.setCreated_at(rs.getString("created_at"));
				post.setUpdated_at(rs.getString("updated_at"));
			}
		}
		return post;
		
	}
	
	public Users getUsersData(int userId) throws SQLException {
		//id, user_name, email, password, created_at, updated_at
		Users users = new Users();
		try (Connection conn = config.dataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_USER);) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				users.setId(userId);
				users.setUserName(rs.getString("user_name"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setCreated_at(rs.getString("created_at"));
				users.setUpdated_at(rs.getString("updated_at"));
			}
		}
		return users;
		
	}

	public Article getArticlesData(int articleId) throws SQLException {
		//id, user_id, article_title, article_text, article_image, created_at, updated_at
		Article article = new Article();
		try (Connection conn = config.dataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(GET_ARTICLE);) {
			pstmt.setInt(1, articleId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				article.setId(articleId);
				article.setUserId(rs.getInt("user_id"));
				article.setArticle_title(rs.getString("article_title"));
				article.setArticle_image(rs.getString("article_image"));
				article.setArticle_text(rs.getString("article_text"));
				article.setCreated_at(rs.getString("created_at"));
				article.setUpdated_at(rs.getString("updated_at"));
			}
		}
		return article;

	}

}
