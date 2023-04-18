package com.techelevator.dao;

import com.techelevator.model.Comment;
import com.techelevator.model.Post;

import java.util.List;

public interface CommentDao {

    List<Comment> getAllComments();

    Comment getAComment(int commentId);

    List<Comment> getCommentsFromUsername(String username);

    void createComment(Comment comment);

    boolean updateComment(Comment comment);

    boolean deleteComment(int commentId);

    List<Comment> getCommentsForAPost(int postId);

}
