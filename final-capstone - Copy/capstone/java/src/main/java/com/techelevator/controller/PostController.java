package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RequestMapping(path="/posts")
@RestController
@PreAuthorize("isAuthenticated()")
public class PostController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;


    @GetMapping()
    public List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }

    @GetMapping(path="/user")
    public List<Post> getPostsForUser(Principal principal){
        return postDao.getPostsFromUsername(principal.getName());
    }

    @GetMapping(path="/{id}")
    public Post getSpecificPost(@PathVariable int id){
        return postDao.getAPost(id);
    }

    @PostMapping()
    public void createAPost(Principal principal, @RequestBody Post post){
        post.setUserId(userDao.findIdByUsername(principal.getName()));
        postDao.createPost(post);
    }

    @PutMapping()
    public void updatePost(Principal principal, @RequestBody Post post){
        post.setUserId(userDao.findIdByUsername(principal.getName()));
        boolean updated=postDao.updatePost(post);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"post not updated");
        }
    }

    @DeleteMapping(path="/{id}")
    public void deletePost(@PathVariable int id){
        postDao.deletePost(id);
    }
}
