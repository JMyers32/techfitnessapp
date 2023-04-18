package com.techelevator.dao;

import com.techelevator.model.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcCommentDao implements CommentDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public List<Comment> getAllComments() {
        String sql="SELECT * FROM post_comments";
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql);
        List<Comment> allComments=new ArrayList<>();
        while(results.next()){
            allComments.add(mapRowToComment(results));
        }
        return allComments;
    }

    @Override
    public Comment getAComment(int commentId) {
        String sql="SELECT * FROM post_comments WHERE comment_id=?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,commentId);
        Comment comment=new Comment();
        if(results.next()){
            comment=mapRowToComment(results);
        }
        return comment;
    }

    @Override
    public List<Comment> getCommentsFromUsername(String username) {
        List<Comment> allSpecificUserComments=new ArrayList<>();
        String sql="SELECT * FROM post_comments pc " +
                "JOIN users u ON u.user_id=pc.user_id "+
                "WHERE u.username=?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,username);
        while(results.next()){
            allSpecificUserComments.add(mapRowToComment(results));
        }
        return allSpecificUserComments;
    }

    @Override
    public void createComment(Comment comment) {
     String sql="INSERT INTO post_comments (post_id, user_id, response) VALUES (?,?,?)";
     jdbcTemplate.update(sql,comment.getPostId(),comment.getUserId(),comment.getResponse());
    }

    @Override
    public boolean updateComment(Comment comment) {
        boolean success=false;
        String sql="UPDATE post_comments SET response=? WHERE comment_id=?";
        int linesUpdated= jdbcTemplate.update(sql,comment.getResponse(),comment.getCommentId());
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public boolean deleteComment(int commentId) {
        boolean success=false;
        String sql="DELETE FROM post_comments WHERE comment_id=?";
        int linesUpdated=jdbcTemplate.update(sql,commentId);
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public List<Comment> getCommentsForAPost(int postId) {
        String sql="SELECT * FROM post_comments WHERE post_id=?";
        List<Comment> postComments=new ArrayList<>();
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,postId);
        while(results.next()){
            postComments.add(mapRowToComment(results));
        }
        return postComments;
    }

    private Comment mapRowToComment(SqlRowSet results){
        Comment comment=new Comment();
        comment.setCommentId(results.getInt("comment_id"));
        comment.setPostId(results.getInt("post_id"));
        comment.setUserId(results.getInt("user_id"));
        comment.setResponse(results.getString("response"));
        return comment;
    }
}
