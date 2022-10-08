package com.DemoSocialMediaApplication.service;

import com.DemoSocialMediaApplication.model.Post;
import com.DemoSocialMediaApplication.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostDaoService {

    @Autowired
    private PostRepository postRepository;

    public UUID savePost(Post post) {
        Post savePost =  postRepository.save(post);
        return savePost.getId();
    }
}
