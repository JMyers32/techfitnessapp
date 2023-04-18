package com.techelevator.controller;

import com.techelevator.dao.CommentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RequestMapping(path="/comments")
@RestController
@PreAuthorize("isAuthenticated()")
public class CommentController {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/{id}")
    public List<Comment> getCommentsForPost(@PathVariable int id){
        return commentDao.getCommentsForAPost(id);
    }

    @GetMapping(path="/single/{id}")
    public Comment getSpecificComment(@PathVariable int id){
        return commentDao.getAComment(id);
    }
    @GetMapping(path = "/user")
    public List<Comment> allUserComments(Principal principal){
        return commentDao.getCommentsFromUsername(principal.getName());
    }

    @PostMapping()
    public void createAComment(Principal principal, @RequestBody Comment comment){
        comment.setUserId(userDao.findIdByUsername(principal.getName()));
        commentDao.createComment(comment);
    }

    @PutMapping(path="/{id}")
    public void updateAComment(Principal principal, @PathVariable int id,@RequestBody Comment comment){
        comment.setUserId(userDao.findIdByUsername(principal.getName()));
        comment.setCommentId(id);
        boolean updated=commentDao.updateComment(comment);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"comment not updated");
        }
    }
    @DeleteMapping(path = "/{id}")
    public void deleteComment(@PathVariable int id){
        boolean updated=commentDao.deleteComment(id);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"comment not deleted");
        }
    }


}
