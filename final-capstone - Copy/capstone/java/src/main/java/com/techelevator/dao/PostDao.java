package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    List<Post> getAllPosts();

    Post getAPost(int postId);

    List<Post> getPostsFromUsername(String username);

    void createPost(Post post);

    boolean updatePost(Post post);

    boolean deletePost(int postId);


}
