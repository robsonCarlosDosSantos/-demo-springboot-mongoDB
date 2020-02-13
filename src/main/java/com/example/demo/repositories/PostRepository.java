package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;
@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//mongoDB - Consulta (simples com @Query)
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitle(String text);
	
	//mongoDB - Consulta com vários critérios
	@Query("{ $and: [ { 'date': { $gte: ?1} } , { 'date': { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } } , { 'body': { $regex: ?0, $options: 'i' } } , { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> findFull(String text, Date minDate, Date maxDate);
	
	//spring data - Consulta (query methods)
	List<Post> findByTitleContainingIgnoreCase(String text);

}
