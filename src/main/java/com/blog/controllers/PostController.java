package com.blog.controllers;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDto createPost= this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts= this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts= this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy ){

        PostResponse postResponse= this.postService.getAllPost(pageNumber, pageSize, sortBy);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto= this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/post/{postId}")
    public ApiResponse  deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted", true);
    }

    //update post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto>  updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost= this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //search post
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keywords") String keywords){
        List<PostDto> result= this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }
}
