package com.example.demo.controller;

import static com.example.demo.commons.GlobalContants.API_POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PostEntity;
import com.example.demo.service.PostService;

@RestController
@RequestMapping(API_POST)
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/all")
	public List<PostEntity> listar() {
		return postService.readAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostEntity> listar2(@PathVariable("id") long id) {
		PostEntity prod = postService.read(id);
		if (prod!=null) {
			return new ResponseEntity<>(prod, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/Post")
	  public ResponseEntity<PostEntity> createTutorial(@RequestBody PostEntity p) {
	    try {
	      PostEntity prod = postService.create(new PostEntity(p.getId(), p.getTitulo(), p.getDescripcion(), p.getEstado()));

	      return new ResponseEntity<>(prod, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	@PutMapping("/post/{id}")
	  public ResponseEntity<PostEntity> updateTutorial(@PathVariable("id") long id, @RequestBody PostEntity p) {
	    PostEntity post = postService.read(id);

	    if (post!=null) {
	    	post.setId(post.getId());
	    	post.setTitulo(post.getTitulo());
	    	post.setDescripcion(post.getDescripcion());
	    	post.setEstado(post.getEstado());
	      return new ResponseEntity<>(postService.create(post), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	@DeleteMapping("/post/{id}")
	  public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") long id) {
	    try {
	    	postService.delete(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	
	
}
