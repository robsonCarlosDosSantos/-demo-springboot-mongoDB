package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id){
		Optional<User> obj = repository.findById(id);
		if(!obj.isPresent()) {
			throw new ObjectNotFoundException("Object not found!");
		}
		return obj.get();
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDTO(UserDTO obj) {
		return new User(obj.getId(),obj.getName(),obj.getEmail());
	}
	
	public void update(User obj) {
		User objRet = findById(obj.getId());
		dataUpdate(obj,objRet);
		repository.save(objRet);
	}

	private void dataUpdate(User obj, User objRet) {
		objRet.setName(obj.getName());
		objRet.setEmail(obj.getEmail());
	}
	
}
