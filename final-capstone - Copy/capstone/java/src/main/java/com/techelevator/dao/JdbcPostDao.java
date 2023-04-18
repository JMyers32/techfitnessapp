package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPostDao implements PostDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    @Override
    public List<Post> getAllPosts() {
        List<Post> allPosts=new ArrayList<>();
        String sql="SELECT * FROM posts";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            allPosts.add(mapRowToPost(results));
        }
        return allPosts;
    }

    @Override
    public Post getAPost(int postId) {
        String sql="SELECT * FROM posts WHERE post_id=?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,postId);
        Post post=new Post();
        if(results.next()){
            post=mapRowToPost(results);
        }
        return post;
    }

    @Override
    public List<Post> getPostsFromUsername(String username) {
        String sql="SELECT * FROM posts p " +
                "JOIN users u ON u.user_id=p.user_id "+
                "WHERE u.username=?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,username);
        List<Post> allUsersPosts=new ArrayList<>();
        while(results.next()){
            allUsersPosts.add(mapRowToPost(results));
        }
        return allUsersPosts;

    }

    @Override
    public void createPost(Post post) {
     String sql="INSERT INTO posts (user_id, post) VALUES (?,?)";
     jdbcTemplate.update(sql,post.getUserId(),post.getPost());
    }

    @Override
    public boolean updatePost(Post post) {
        boolean success=false;
        String sql="UPDATE posts SET post=? WHERE post_id=?";
        int linesUpdated= jdbcTemplate.update(sql,post.getPost(),post.getPostId());
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public boolean deletePost(int postId) {
        boolean success=false;
        String sql="DELETE FROM posts WHERE post_id=?";
        int linesUpdated= jdbcTemplate.update(sql,postId);
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    private Post mapRowToPost(SqlRowSet results){
        Post post=new Post();
        post.setPostId(results.getInt("post_id"));
        post.setUserId(results.getInt("user_id"));
        post.setPost(results.getString("post"));
        return post;
    }
}
