package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository repository;
	
	public Post findById(String id){
		Optional<Post> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new ObjectNotFoundException("Object not found!");
		}
		return obj.get();
	}
	
	public List<Post> findByTitle(String text){
		return repository.findByTitle(text);
	}
	
}
