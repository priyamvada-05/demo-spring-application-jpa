package com.DemoSocialMediaApplication.controller;

import com.DemoSocialMediaApplication.model.Post;
import com.DemoSocialMediaApplication.model.UserDetail;
import com.DemoSocialMediaApplication.service.PostDaoService;
import com.DemoSocialMediaApplication.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/users/{id}/post")
public class PostController {

    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private PostDaoService postDaoService;

    @GetMapping()
    public List<Post> getPostOfUser(@PathVariable UUID id) {
        UserDetail user =  userDaoService.findByUserId(id);
        return user.getPost();
    }

    @PostMapping
    public ResponseEntity<Post> savePostOfUser(
            @PathVariable UUID id,
            @Valid @RequestBody Post post
    ) {
        UserDetail user =  userDaoService.findByUserId(id);
        post.setUserDetail(user);
        UUID postID = postDaoService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("location",
                        "/api/api/users/"+id+"/post/"+postID).build();
    }
}
