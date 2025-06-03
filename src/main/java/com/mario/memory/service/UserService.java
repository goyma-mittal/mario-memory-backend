package com.mario.memory.service;

import com.mario.memory.model.User;
import com.mario.memory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        if (user.getCompletedLevels() == null) user.setCompletedLevels(new ArrayList<>());
        if (user.getStarsEarned() == null) user.setStarsEarned(new HashMap<>());
        if (user.getTimeTakenPerLevel() == null) user.setTimeTakenPerLevel(new HashMap<>());

        return userRepository.save(user);
    }

    public User loginUser(User user) {
    	 
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail()); // ✅ now uses email
         
        if (existingUser.isPresent()) {
        	 User foundUser = existingUser.get();
            if (foundUser.getPassword().equals(user.getPassword())) {
            	 System.out.println(foundUser.getEmail());
             	   System.out.println(foundUser.getUsername());
             	   System.out.println(foundUser.getPassword());
          
            	
                return foundUser;
            }
        } 
        return null;
    }

    public Optional<User> getUserProfile(String userId) {
        return userRepository.findById(userId);
    }

    public User updateUserProfile(String userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(user.getUsername());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(user.getPassword());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public User completeLevel(String userId, int levelNumber, int timeTaken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getCompletedLevels().contains(levelNumber)) {
            user.getCompletedLevels().add(levelNumber);
        }

        int stars = calculateStars(timeTaken);
        user.getStarsEarned().put(levelNumber, stars);
        user.getTimeTakenPerLevel().put(levelNumber, timeTaken);

        return userRepository.save(user);
    }

    private int calculateStars(int timeTaken) {
        if (timeTaken <= 30) {
            return 3;
        } else if (timeTaken <= 45) {
            return 2;
        } else {
            return 1;
        }
    }

    public List<User> getLeaderboard() {
        return userRepository.findAll();
    }

    public List<User> findAllUsers() {
    return userRepository.findAll();
}
}
