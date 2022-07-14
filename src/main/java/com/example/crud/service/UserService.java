package com.example.crud.service;


import com.example.crud.domain.User;
import com.example.crud.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepo;
    
    public User create(User user){
        return userRepo.save(user);
    }
    
    public Optional<User> findOne(Long id){
        return userRepo.findById(id);
    }
    
    public Optional<User> findOneByUsername(String username){
        return userRepo.findByUsername(username);
    }
    
    public List<User> findAll(){
        return userRepo.findAll();
    }
    
    public User update(User user){
        return userRepo.save(user);
    }
    
    public void delete(User user){
        userRepo.delete(user);
    }
    
    
}
